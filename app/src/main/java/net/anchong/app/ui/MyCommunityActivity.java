package net.anchong.app.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.MyCommunityAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.MyCommunityRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.MyCommunityResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.MyCommunityHeadView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/6/16.
 */
public class MyCommunityActivity extends Activity implements MyCommunityHeadView.CommunityHeadListener {

    /**
     *
     */
    @ViewInject(R.id.title_my_community)
    private MyCommunityHeadView myCommunityHeadView;
    @ViewInject(R.id.xlv_community_my)
    private XListView xListView;
    private MyCommunityAdapter adapter;
    private ProgressDialog pd;
    private int page = 1;


    /**
     * 数据存储
     */
    private MyCommunityResponse myCommunityResponse;
    private List<MyCommunityResponse.ResultDataEntity.ListEntity> list;


    public static void start(Context context) {
        Intent intent = new Intent(context, MyCommunityActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.activity_community_my);
        x.view().inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pd = new ProgressDialog(this);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        pd.show();
        page = 1;
        if (MainActivity.isLogin) {
            final MyCommunityRequest myCommunityRequest = new MyCommunityRequest("", page + "");
            String requestParam = new Gson().toJson(myCommunityRequest);
            RequestModel requestModel = null;
            String copyToken = null;
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), myCommunityRequest);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
//        }
            String signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYMYCOMMUNITY + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.COMMUNITYMYCOMMUNITY)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", requestParam)
                    .addParams("signature", signature)
                    .build()
                    .execute(MyCommunityResponse.class, new CommonCallback<MyCommunityResponse>() {
                        @Override
                        public void onSuccess(MyCommunityResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                pd.dismiss();
                                Logger.i("个人聊聊信息：" + response);
                                myCommunityResponse = response;
                                list = myCommunityResponse.getResultData().getList();
                                if (list.size() < myCommunityResponse.getResultData().getTotal()) {
                                    page++;
                                }
                                initView();
                            } else {
                                showMessage(getString(R.string.request_error_msg));
                            }
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            showMessage(getString(R.string.request_error_msg));
                            e.printStackTrace();
                        }
                    });
        } else {

        }

    }

    private void showMessage(String msg) {
        Toast.makeText(MyCommunityActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        myCommunityHeadView.setCommunityHeadListener(this);
        if (myCommunityResponse == null || myCommunityResponse.getResultData().getList() == null || myCommunityResponse.getResultData().getList().size() <= 0) {
            adapter = new MyCommunityAdapter(this, getLayoutInflater(), null);
            xListView.setAdapter(adapter);
            xListView.setPullLoadEnable(false);
            xListView.setPullRefreshEnable(false);
        } else {
            adapter = new MyCommunityAdapter(this, getLayoutInflater(), myCommunityResponse.getResultData().getList());
            xListView.setAdapter(adapter);
            xListView.setPullLoadEnable(true);
            xListView.setPullRefreshEnable(false);
            if (list.size() == myCommunityResponse.getResultData().getTotal()) {
                xListView.setPullLoadEnable(false);
            }
        }

    }

    @Override
    public void communityHeadBack() {
        finish();
    }
}
