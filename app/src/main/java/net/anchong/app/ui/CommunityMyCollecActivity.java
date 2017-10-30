package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.CommunityShowAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.CommunityMyCollectionRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.CommunityShowResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

/**
 * 社区聊聊我的收藏界面
 * Created by baishixin on 16/6/21.
 */
public class CommunityMyCollecActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, XListView.IXListViewListener {

    /**
     * UI组件
     */
    @ViewInject(R.id.gtbv_community_my_collection)
    private GeneralTitleBarView titleBarView;
    @ViewInject(R.id.xlv_my_collection)
    private XListView listView;
    private CommunityShowAdapter adapter;
    private ProgressDialog pd;
    private int page = 1;

    /**
     * 网络请求
     */


    /**
     * 数据存储
     */
    private CommunityShowResponse communityShowResponse;
    private List<CommunityShowResponse.ResultDataEntity.ListEntity> list;

    /**
     *
     */


    public static void start(Context context) {
        Intent intent = new Intent(context, CommunityMyCollecActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_my_collection);
        x.view().inject(this);
//        initView();
//        pd = new ProgressDialog(this);
//        pd.setCanceledOnTouchOutside(false);
//        initData();
    }

    private void initData() {


        page = 1;
        CommunityMyCollectionRequest communityMyCollectionRequest = new CommunityMyCollectionRequest(page + "");
        String requestParam = new Gson().toJson(communityMyCollectionRequest);
        RequestModel requestModel = null;
        String copyToken = null;
        requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityMyCollectionRequest);
        copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYMYCOLLECT + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYMYCOLLECT)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(CommunityShowResponse.class, new CommonCallback<CommunityShowResponse>() {
                    @Override
                    public void onSuccess(CommunityShowResponse response, Object... obj) {
                        //返回的状态码为0 代表请求正常
                        if ("0".equals(response.getServerNo())) {
                            pd.dismiss();
                            Logger.i("聊聊信息.......：" + response.toString());
                            communityShowResponse = response;
                            if (list != null) {
                                list.clear();
                            }
                            list = response.getResultData().getList();
                            if (list != null && list.size() < communityShowResponse.getResultData().getTotal()) {
                                page++;
                            }
                            initView();
                        } else {
                            pd.dismiss();
                            showMessage(getString(R.string.request_error_msg));
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }


    private void initMore() {
        CommunityMyCollectionRequest communityMyCollectionRequest = new CommunityMyCollectionRequest(page + "");
        String requestParam = new Gson().toJson(communityMyCollectionRequest);
        RequestModel requestModel = null;
        String copyToken = null;
        requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityMyCollectionRequest);
        copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYMYCOLLECT + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYMYCOLLECT)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(CommunityShowResponse.class, new CommonCallback<CommunityShowResponse>() {
                    @Override
                    public void onSuccess(CommunityShowResponse response, Object... obj) {
                        //返回的状态码为0 代表请求正常
                        if ("0".equals(response.getServerNo())) {
                            communityShowResponse = response;
                            if (communityShowResponse.getResultData().getList() != null && communityShowResponse.getResultData().getList().size() > 0) {
                                list.addAll(communityShowResponse.getResultData().getList());
                            }
                            if (list != null && list.size() < communityShowResponse.getResultData().getTotal()) {
                                page++;
                            }
                            if (list.size() == communityShowResponse.getResultData().getTotal()) {
                                listView.setPullLoadEnable(false);
                            }
                            onLoad();
                            adapter.setList(list);
                        } else {
                            pd.dismiss();
                            showMessage(getString(R.string.request_error_msg));
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        pd.dismiss();
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });
    }


    private void initView() {
        titleBarView.setData("收藏", "");
        titleBarView.setGeneralTitleBarOnclickListener(this);
        adapter = new CommunityShowAdapter(this, getLayoutInflater(), communityShowResponse.getResultData().getList());
        listView.setAdapter(adapter);
        listView.setPullLoadEnable(true);
        listView.setPullRefreshEnable(false);
        listView.setXListViewListener(this);
        Logger.i("list.size() = " + list.size());
        Logger.i("communityShowResponse.getResultData().getTotal() = " +  communityShowResponse.getResultData().getTotal());
        if (list.size() == communityShowResponse.getResultData().getTotal()) {
            listView.setPullLoadEnable(false);
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        initMore();
    }

    private void onLoad() {
        listView.stopRefresh();
        listView.stopLoadMore();
        listView.setRefreshTime("刚刚");
    }
}
