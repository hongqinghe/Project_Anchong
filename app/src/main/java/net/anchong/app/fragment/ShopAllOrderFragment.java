package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.ShopAllOrderAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.ShopOrderRequest;
import net.anchong.app.entity.request.model.ShopSoperationRequest;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.entity.response.model.ShopOrderResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.ui.LoginActivity;
import net.anchong.app.uitls.ACRequestUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 查看商铺全部订单
 * Created by baishixin on 16/3/8.
 */
public class ShopAllOrderFragment extends BaseFragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    @ViewInject(R.id.fl_empty_view)
    private LinearLayout emptyView;
    @ViewInject(R.id.xlv_order)
    private XListView mListView;

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;
    private ProgressDialog pd;
    private ShopOrderRequest shopOrderRequest;
    private ShopOrderResponse shopOrderResponse;
    private List<ShopOrderResponse.ResultDataEntity.Order> orderList = new ArrayList<>();
    private ShopAllOrderAdapter mAdapter;
    /**
     * 数据请求
     */
    //state : 0为全部1为待支付2为代发货3为退款
    private String state = "0";
    //商铺ID
    private String sid = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        Fresco.initialize(mContext);
        view = inflater.inflate(R.layout.fragment_all_order, container, false);
        x.view().inject(this, view);
        pd = new ProgressDialog(mContext);
        pd.setTitle("加载中..");
        pd.setCanceledOnTouchOutside(false);
        initData();
//        initView();
        return view;
    }

    /**
     * 加载数据
     */
    private void initData() {
        pd.show();
        requestOrder();
    }


    private void initView() {
//        pd.dismiss();
        if (shopOrderResponse == null || shopOrderResponse.getResultData().getTotal() == 0) {
            mListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            pd.dismiss();
        } else {
            mListView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            mAdapter = new ShopAllOrderAdapter(mContext, mInflater, this, shopOrderResponse, orderList);
            mListView.setAdapter(mAdapter);
            mListView.setPullLoadEnable(false);
            mListView.setPullRefreshEnable(true);
            mListView.setXListViewListener(this);
            mListView.setOnItemClickListener(this);
            pd.dismiss();
        }
    }

    private void requestOrder() {
        //判断用户是否登录
        if (MainActivity.isLogin) {
            shopOrderRequest = new ShopOrderRequest(state, sid);
            String json = new Gson().toJson(shopOrderRequest);
            RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", shopOrderRequest);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

            String signature = ACRequestUtils.getMD5(MyApplication.SHOPSSHOPSORDER + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.SHOPSSHOPSORDER)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(ShopOrderResponse.class, new CommonCallback<ShopOrderResponse>() {
                        @Override
                        public void onSuccess(ShopOrderResponse response, Object... obj) {
                            Log.e("MainActivity", response.toString());
                            //返回的状态码为0 代表请求正常
                            if ("0".equals(response.getServerNo())) {
                                Logger.i("商铺订单信息：" + response);
                                shopOrderResponse = response;
                                orderList = shopOrderResponse.getResultData().getList();
                                initView();
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
        } else {
            LoginActivity.start(mContext);
        }
    }

    private void initMore() {
        if (orderList.size() == shopOrderResponse.getResultData().getTotal()) {
            mListView.setPullLoadEnable(false);
//            mListView
        } else if (orderList.size() < shopOrderResponse.getResultData().getTotal()) {
            pd.show();
            //判断用户是否登录
            if (MainActivity.isLogin) {
                shopOrderRequest = new ShopOrderRequest(state, sid);
                String json = new Gson().toJson(shopOrderRequest);
                RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", shopOrderRequest);
                String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

                String signature = ACRequestUtils.getMD5(MyApplication.ORDERORDERINFO + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                requestModel.setSignature(signature);
                HttpManager.getInstance()
                        .post(MyApplication.API + MyApplication.ORDERORDERINFO)
                        .addParams("time", requestModel.getTime())
                        .addParams("version", requestModel.getVersion())
                        .addParams("guid", requestModel.getGuid())
                        .addParams("param", json)
                        .addParams("signature", signature)
                        .build()
                        .execute(ShopOrderResponse.class, new CommonCallback<ShopOrderResponse>() {
                            @Override
                            public void onSuccess(ShopOrderResponse response, Object... obj) {
                                Log.e("MainActivity", response.toString());
                                //返回的状态码为0 代表请求正常
                                if ("0".equals(response.getServerNo())) {
                                    shopOrderResponse = null;
                                    orderList = null;
                                    shopOrderResponse = response;
                                    orderList = shopOrderResponse.getResultData().getList();
                                    onRefresh();
                                    pd.dismiss();
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
            } else {
                LoginActivity.start(mContext);
            }
        }
    }


    public void orderAction(String action, ShopOrderResponse.ResultDataEntity.Order order) {
        ShopSoperationRequest shopSoperationRequest = new ShopSoperationRequest(order.getOrder_id() + "", order.getOrder_num(), action, "", "");
        if (MainActivity.isLogin) {
            pd.show();
            String json = new Gson().toJson(shopSoperationRequest);
            RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", shopSoperationRequest);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

            String signature = ACRequestUtils.getMD5(MyApplication.SHOPSSHOPSOPERATION + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);

            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.SHOPSSHOPSOPERATION)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                        @Override
                        public void onSuccess(ResponseErrorModel response, Object... obj) {
                            Log.e("MainActivity", response.toString());
                            //返回的状态码为0 代表请求正常
                            if ("0".equals(response.getServerNo())) {
                                pd.dismiss();
                                ResponseErrorModel responseErrorModel = response;
                                showMessage(responseErrorModel.getResultData().getMessage());
                                initData();
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
        } else {
            LoginActivity.start(mContext);
        }

    }


    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
//        initData();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onRefresh() {
        initMore();
        mAdapter.notifyDataSetChanged();
        onLoad();
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }
}
