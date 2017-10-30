package net.anchong.app.ui;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.CommunityInfoAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.CommuinityCommentRequest;
import net.anchong.app.entity.request.model.CommunityComRequest;
import net.anchong.app.entity.request.model.CommunityInfoRequest;
import net.anchong.app.entity.request.model.CommunityReplyRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.CommunityComResponse;
import net.anchong.app.entity.response.model.CommunityInfoResponse;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.CommunityInfoHeaderView;
import net.anchong.app.view.CommunityInfoTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 聊聊详情界面
 * Created by baishixin on 16/6/13.
 */
public class CommunityInfoActivity extends BaseActivity implements XListView.IXListViewListener, CommunityInfoTitleBarView.CommunityTitleBarOnclickListener {

    /**
     *
     */
    //评论内容的EditText
    @ViewInject(R.id.et_activity_community_info_replycontent)
    private EditText replyContent;
    //发送评论的按钮
    @ViewInject(R.id.tv_activity_community_info_send)
    private TextView send;

    @ViewInject(R.id.title_community_info)
    private CommunityInfoTitleBarView titleBarView;


    //聊聊
    private String chart_id;
    private ProgressDialog pd;
    //评论id
    private int comid;
    //被评论人的名称
    private String name;
    //收藏状态  0：未收藏   1：已收藏
    private int collresult = 0;
    //分页
    private int page = 1;
    //回复的种类，1是直接发表评论，2为回复评论
    private int comState = 1;
    //聊聊详情结果
    private CommunityInfoResponse communityInfoResponse;
    //聊聊评论请求
    private CommunityComRequest communityComRequest;
    //聊聊评论结果
    private CommunityComResponse communityComResponse;
    private List<CommunityComResponse.ResultDataEntity.ListEntity> listEntities = new ArrayList<>();

    @ViewInject(R.id.xlv_activity_community_info)
    private XListView xListView;

    private CommunityInfoHeaderView communityInfoHeaderView;
    private CommunityInfoAdapter adapter;


    public static void start(Context context, String chart_id) {
        Intent intent = new Intent(context, CommunityInfoActivity.class);
        intent.putExtra("chart_id", chart_id);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_info);
        x.view().inject(this);
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.tab_bg);// 通知栏所需颜色
        chart_id = getIntent().getStringExtra("chart_id");
        initData();
        initEvent();
//        initCommunitycom();
    }

    private void initEvent() {
        replyContent.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    if (comState == 1) {
                        replyContent.setHint("我也有话说.....");
                    }
                } else {
                    if (TextUtils.isEmpty(replyContent.getText().toString())) {
                        replyContent.setHint("我也有话说.....");
                    }
                    if (comState == 1) {
                        replyContent.setHint("我也有话说.....");
                    }
                }
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.isLogin) {
                    commitReply();
                } else {
                    LoginActivity.start(CommunityInfoActivity.this);
                }
            }
        });
    }

    /**
     * 请求聊聊详情数据
     */
    private void initData() {
        pd = new ProgressDialog(this);
        pd.show();
        page = 1;
        CommunityInfoRequest communityInfoRequest = new CommunityInfoRequest(chart_id);
        String requestJson = new Gson().toJson(communityInfoRequest);
        RequestModel requestModel = null;
        String signature = "";
        if (MainActivity.isLogin) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityComRequest);
            String tokey = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYINFO + requestModel.getTime() + requestModel.getGuid() + requestJson + tokey);
        } else {

            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", communityComRequest);
            signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYINFO + requestModel.getTime() + requestModel.getGuid() + requestJson + MyApplication.DEFAULT_TOKEN);
        }
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYCOMMUNITYINFO)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestJson)
                .addParams("signature", signature)
                .build()
                .execute(CommunityInfoResponse.class, new CommonCallback<CommunityInfoResponse>() {
                    @Override
                    public void onSuccess(CommunityInfoResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            Logger.i("聊聊详情结果:" + response);
                            if (listEntities != null) {
                                listEntities.clear();
                            }
                            communityInfoResponse = response;
                            initCommunitycom();
                        } else {
                            showMessage(response.getResultData().toString());
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage("网络不稳定，请重试");
                        e.printStackTrace();
                        pd.dismiss();
                    }
                });
    }

    /**
     * 加载聊聊评论
     */
    private void initCommunitycom() {
        communityComRequest = new CommunityComRequest(chart_id, page + "");
        String requestJson = new Gson().toJson(communityComRequest);
        page = 1;

        RequestModel requestModel = null;
        String signature = "";
        if (MainActivity.isLogin) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityComRequest);
            String tokey = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYCOM + requestModel.getTime() + requestModel.getGuid() + requestJson + tokey);
        } else {

            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", communityComRequest);
            signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYCOM + requestModel.getTime() + requestModel.getGuid() + requestJson + MyApplication.DEFAULT_TOKEN);
        }
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYCOMMUNITYCOM)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestJson)
                .addParams("signature", signature)
                .build()
                .execute(CommunityComResponse.class, new CommonCallback<CommunityComResponse>() {
                    @Override
                    public void onSuccess(CommunityComResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            Logger.i("聊聊评论结果:" + response);
                            communityComResponse = response;
                            listEntities = communityComResponse.getResultData().getList();
                            if (listEntities != null && listEntities.size() < communityComResponse.getResultData().getTotal()) {
                                page++;
                            }
                            initView();
                        } else {
                            showMessage(response.getResultData().toString());
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage("网络不稳定，请重试");
                        e.printStackTrace();
                        pd.dismiss();
                    }
                });
    }

    /**
     * 点击回复评论按钮
     *
     * @param comid
     * @param name
     */
    public void clickeCom(int comid, String name) {
        comState = 2;
        this.comid = comid;
        this.name = name;
        if (!replyContent.isFocused()) {
            replyContent.requestFocus();
            InputMethodManager imm = (InputMethodManager) replyContent.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
        }
        replyContent.setHint("回复 " + name + ":");
    }

    private void initView() {
        pd.dismiss();
        collresult = communityInfoResponse.getResultData().getCollresult();
        titleBarView.isResult(collresult);
        titleBarView.setGeneralTitleBarOnclickListener(this);
        if (communityInfoHeaderView == null) {
            communityInfoHeaderView = new CommunityInfoHeaderView(this);
            communityInfoHeaderView.setData(communityInfoResponse);
            xListView.addHeaderView(communityInfoHeaderView);
        }
        xListView.setPullRefreshEnable(false);
        xListView.setPullLoadEnable(true);
        xListView.setXListViewListener(this);
        //如果没有评论加载 empty View
        if (communityComResponse != null && communityComResponse.getResultData().getList() != null && communityComResponse.getResultData().getList().size() > 0) {
            adapter = new CommunityInfoAdapter(this, getLayoutInflater(), this, communityComResponse.getResultData().getList());
            if (communityComResponse.getResultData().getList() != null && communityComResponse.getResultData().getTotal() <= 10) {
                xListView.setPullLoadEnable(false);
            }

        } else {
            adapter = new CommunityInfoAdapter(this, getLayoutInflater(), this, null);
            xListView.setPullLoadEnable(false);
        }
        xListView.setAdapter(adapter);
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {

    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onLoadMore() {
        initMore();
        onLoad();
    }

    /**
     * 发表评论
     */
    public void commitReply() {
        RequestModel requestModel = null;
        String requestJson = null;
        String api = "";
        String signature = "";
        String copyToken = "";
        switch (comState) {
            case 1:
                CommuinityCommentRequest commuinityCommentRequest = new CommuinityCommentRequest(chart_id, replyContent.getText().toString());
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), commuinityCommentRequest);
                requestJson = new Gson().toJson(commuinityCommentRequest);
                copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMENT + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
                requestModel.setSignature(signature);
                api = MyApplication.COMMUNITYCOMMENT;
                break;
            case 2:
                CommunityReplyRequest communityReplyRequest = new CommunityReplyRequest(comid + "", name, chart_id, replyContent.getText().toString());
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityReplyRequest);
                requestJson = new Gson().toJson(communityReplyRequest);
                copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYREPLY + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
                requestModel.setSignature(signature);
                api = MyApplication.COMMUNITYREPLY;
                break;
        }
        HttpManager.getInstance()
                .post(MyApplication.API + api)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestJson)
                .addParams("signature", signature)
                .build()
                .execute(CommunityInfoResponse.class, new CommonCallback<CommunityInfoResponse>() {
                    @Override
                    public void onSuccess(CommunityInfoResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            Logger.i("发表评论结果:" + response);
                            initData();
                            replyContent.clearFocus();
                            replyContent.setText("");
                            comState = 1;
                            replyContent.setHint("我也有话说.....");
                        } else {
                            showMessage(response.getResultData().toString());
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage("网络不稳定，请重试");
                        e.printStackTrace();
                        pd.dismiss();
                    }
                });
    }

    /**
     * 收藏  取消收藏聊聊请求
     */
    private void testCollect(String api) {
        //判断用户是否登录
        if (MainActivity.isLogin) {
            CommunityInfoRequest communityInfoRequest = new CommunityInfoRequest(chart_id);
            final RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityInfoRequest);
            String requestJson = new Gson().toJson(communityInfoRequest);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            String signature = ACRequestUtils.getMD5(api + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
            requestModel.setSignature(signature);

            HttpManager.getInstance()
                    .post(MyApplication.API + api)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", requestJson)
                    .addParams("signature", signature)
                    .build()
                    .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                        @Override
                        public void onSuccess(ResponseErrorModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                ResponseErrorModel responseErrorModel = response;
                                showMessage(responseErrorModel.getResultData().getMessage());
                                if (collresult == 1) {
                                    collresult = 0;
                                } else if (collresult == 0) {
                                    collresult = 1;
                                }
                                titleBarView.isResult(collresult);
                                pd.dismiss();
                            } else {
                                showMessage("网络不稳定，请重试");
                                pd.dismiss();
                            }
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            showMessage("网络不稳定，请重试");
                            e.printStackTrace();
                            pd.dismiss();
                        }
                    });
        } else {
            LoginActivity.start(this);
        }
    }

    private void initMore() {
        communityComRequest = new CommunityComRequest(chart_id, page + "");
        String requestJson = new Gson().toJson(communityComRequest);

        RequestModel requestModel = null;
        String signature = "";
        if (MainActivity.isLogin) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityComRequest);
            String tokey = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYCOM + requestModel.getTime() + requestModel.getGuid() + requestJson + tokey);
        } else {

            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", communityComRequest);
            signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYCOM + requestModel.getTime() + requestModel.getGuid() + requestJson + MyApplication.DEFAULT_TOKEN);
        }
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYCOMMUNITYCOM)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestJson)
                .addParams("signature", signature)
                .build()
                .execute(CommunityComResponse.class, new CommonCallback<CommunityComResponse>() {
                    @Override
                    public void onSuccess(CommunityComResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            Logger.i("聊聊评论结果:" + response);
                            communityComResponse = response;

                            List<CommunityComResponse.ResultDataEntity.ListEntity> list = communityComResponse.getResultData().getList();
                            if (list != null && list.size() < communityComResponse.getResultData().getTotal()) {
                                page++;
                            }

                            listEntities.addAll(list);
                            if (listEntities.size() == communityComResponse.getResultData().getTotal()) {
                                xListView.setPullLoadEnable(false);
                            }
                            Logger.i("请求之后的集合长度" + listEntities.size());
                            adapter.setList(listEntities);
                        } else {
                            showMessage(response.getResultData().toString());
                            pd.dismiss();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage("网络不稳定，请重试");
                        e.printStackTrace();
                        pd.dismiss();
                    }
                });
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        switch (collresult) {
            case 0:
                testCollect(MyApplication.COMMUNITYADDCOLLECT);
                break;
            case 1:
                testCollect(MyApplication.COMMUNITYDELCOLLECT);
                break;
        }
    }
}
