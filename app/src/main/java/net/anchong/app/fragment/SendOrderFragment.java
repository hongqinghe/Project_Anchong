package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import net.anchong.app.adapter.SendOrderAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.OrderInfoRequestParam;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.OrderInfoResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.ui.LoginActivity;
import net.anchong.app.ui.OrderInfoActivity;
import net.anchong.app.ui.OrderMoreInfoActivity;
import net.anchong.app.uitls.ACRequestUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 查看全部订单
 * Created by baishixin on 16/3/8.
 */
public class SendOrderFragment extends BaseFragment implements XListView.IXListViewListener ,AdapterView.OnItemClickListener{
    @ViewInject(R.id.fl_empty_view)
    private LinearLayout emptyView;
    @ViewInject(R.id.xlv_order)
    private XListView mListView;

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;
    private ProgressDialog pd;
    private OrderInfoRequestParam orderInfoRequestParam;
    private OrderInfoResponse orderInfoResponse;
    private List<OrderInfoResponse.ResultDataEntity.ListEntity> listBeanList = new ArrayList<>();
    private SendOrderAdapter mAdapter;
    /**
     * 数据请求
     */
    //state : 0为全部1为待支付2为代发货3为待收货4为退款
    private String state = "2";
    //分页页码，默认第一页
    private int page = 1;

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
        if (orderInfoResponse == null || orderInfoResponse.getResultData().getTotal() == 0) {
            mListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            pd.dismiss();
        } else {
            mListView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            mAdapter = new SendOrderAdapter(mContext, mInflater, this, orderInfoResponse, listBeanList,(OrderInfoActivity)getActivity());
            mListView.setAdapter(mAdapter);
            mListView.setPullLoadEnable(true);
            mListView.setPullRefreshEnable(false);
            mListView.setXListViewListener(this);
            mListView.setOnItemClickListener(this);
            pd.dismiss();
        }
    }

    private void requestOrder() {
        page = 1;
        //判断用户是否登录
        if (MainActivity.isLogin) {
            orderInfoRequestParam = new OrderInfoRequestParam(state, page + "");
            String json = new Gson().toJson(orderInfoRequestParam);
            RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", orderInfoRequestParam);
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
                    .execute(OrderInfoResponse.class, new CommonCallback<OrderInfoResponse>() {
                        @Override
                        public void onSuccess(OrderInfoResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                Logger.i("订单信息：" + response);
                                orderInfoResponse = response;
                                listBeanList.clear();
                                if (orderInfoResponse.getResultData().getList() != null && orderInfoResponse.getResultData().getList().size() > 0) {
                                    listBeanList.addAll(orderInfoResponse.getResultData().getList());
                                    page++;
                                }
                                initView();
                            } else {
                                showMessage(getString(R.string.request_error_msg));
                            }
                            pd.dismiss();
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
        if (listBeanList.size() == orderInfoResponse.getResultData().getTotal()) {
            mListView.setPullLoadEnable(false);
//            mListView
        } else if (listBeanList.size() < orderInfoResponse.getResultData().getTotal()) {
            pd.show();
            //判断用户是否登录
            if (MainActivity.isLogin) {
                orderInfoRequestParam = new OrderInfoRequestParam(state, page + "");
                String json = new Gson().toJson(orderInfoRequestParam);
                final RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", orderInfoRequestParam);
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
                        .execute(OrderInfoResponse.class, new CommonCallback<OrderInfoResponse>() {
                            @Override
                            public void onSuccess(OrderInfoResponse response, Object... obj) {
                                if ("0".equals(response.getServerNo())) {
                                    orderInfoResponse = response;
                                    if (orderInfoResponse.getResultData().getList() != null && orderInfoResponse.getResultData().getList().size() > 0) {
                                        listBeanList.addAll(orderInfoResponse.getResultData().getList());
                                        if (listBeanList.size() == orderInfoResponse.getResultData().getTotal()) {
                                            mListView.setPullLoadEnable(false);
                                        }
                                        page++;
                                    }
                                    pd.dismiss();
                                } else {
                                    showMessage(getString(R.string.request_error_msg));
                                }
                                pd.dismiss();
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
//        onLoad();
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onLoadMore() {
        initMore();
        mAdapter.notifyDataSetChanged();
        onLoad();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        OrderInfoResponse.ResultDataEntity.ListEntity listBean = listBeanList.get(position - 1);
        if (listBean != null) {
            Intent intent = new Intent(mContext, OrderMoreInfoActivity.class);
            intent.putExtra("model", listBean);
            mContext.startActivity(intent);
        }
    }
}
