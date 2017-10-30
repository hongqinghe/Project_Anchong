package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.BusinessHotProjectAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.HotProjectRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.HotProjectResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.BusinessProjectTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

/**
 * 商机首页最新招标项目
 * Created by baishixin on 16/6/23.
 */
public class BusinessNewProjectActivity extends BaseActivity implements BusinessProjectTitleBarView.GeneralTitleBarOnclickListener {


    /**
     * UI 组件
     */
    @ViewInject(R.id.title_business_hot_project)
    private BusinessProjectTitleBarView titleBarView;
    @ViewInject(R.id.xlv_activity_hot_project)
    private XListView xListView;
    private BusinessHotProjectAdapter adapter;

    /**
     *
     */
    private String type;
    private String api;
    private int page;
    private HotProjectResponse hotProjectResponse;
    private List<HotProjectResponse.ResultDataEntity.ListEntity> hotprojectList;
    private int showPhone;

    /**
     *
     */

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, BusinessNewProjectActivity.class);
        intent.putExtra("type", type);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_project);
        x.view().inject(this);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.tab_bg);// 通知栏所需颜色

        type = getIntent().getStringExtra("type");
        switch (type) {
            case "1":
                api = MyApplication.BUSINESSHOTPROJECT;
                break;
            case "2":
                api = MyApplication.BUSINESSRECENT;
                break;
        }
        initData();
//        initView();
    }

    private void initView() {
        titleBarView.setData("1", "");
        titleBarView.setGeneralTitleBarOnclickListener(this);
        if (hotprojectList == null || hotprojectList.size() <= 0) {
            adapter = new BusinessHotProjectAdapter(this, getLayoutInflater(), null, showPhone);
            xListView.setPullLoadEnable(false);
            xListView.setPullRefreshEnable(false);
        } else {
            adapter = new BusinessHotProjectAdapter(this, getLayoutInflater(), hotprojectList, showPhone);
            xListView.setPullLoadEnable(true);
            xListView.setPullRefreshEnable(false);
            if (hotprojectList.size() == hotProjectResponse.getResultData().getTotal()) {
                xListView.setPullLoadEnable(false);
            }
        }
        xListView.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //数据检查完成，可以提交数据
        page = 1;
        HotProjectRequest hotProjectRequest = new HotProjectRequest(page + "");
        String json = new Gson().toJson(hotProjectRequest);

        RequestModel requestModel = null;
        String copyToken = null;

        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", hotProjectRequest);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", hotProjectRequest);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(api + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + api)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(HotProjectResponse.class, new CommonCallback<HotProjectResponse>() {
                    @Override
                    public void onSuccess(HotProjectResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            hotProjectResponse = response;
                            hotprojectList = hotProjectResponse.getResultData().getList();
                            showPhone = hotProjectResponse.getResultData().getShowphone();
                            if (hotprojectList != null) ;
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
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}









