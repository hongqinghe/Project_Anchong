package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.ShopGoodsSaleingAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.GoodsActionRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.ShopGoodsShowRequest;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.entity.response.model.ShopGoodsShowResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.ui.GoodsInformationActivity;
import net.anchong.app.ui.LoginActivity;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.FileUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 店铺货品管理出售中的商品界面
 * Created by baishixin on 16/3/8.
 */
public class SaleingFragment extends BaseFragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener {
    @ViewInject(R.id.fl_empty_view)
    private LinearLayout emptyView;
    @ViewInject(R.id.xlv_fragment_saleing_list)
    private XListView mListView;

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;
    private ProgressDialog pd;
    private ShopGoodsShowRequest shopGoodsShowRequest;
    private ShopGoodsShowResponse shopGoodsShowResponse;
    private List<ShopGoodsShowResponse.GoodsList.Goods> goodsList = new ArrayList<>();
    private ShopGoodsSaleingAdapter mAdapter;
    /**
     * 数据请求
     */
    //state : 0为全部1为待支付2为代发货3为待收货4为退款
    private String added = "1";
    //分页页码，默认第一页
    private String sid = "1";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        Fresco.initialize(mContext);
        view = inflater.inflate(R.layout.fragment_shopgoods_saleing, container, false);
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
        if (shopGoodsShowResponse == null || shopGoodsShowResponse.getResultData().getTotal() == 0) {
            mListView.setVisibility(View.GONE);
            emptyView.setVisibility(View.VISIBLE);
            pd.dismiss();
        } else {
            mListView.setVisibility(View.VISIBLE);
            emptyView.setVisibility(View.GONE);
            mAdapter = new ShopGoodsSaleingAdapter(mContext, mInflater, shopGoodsShowResponse.getResultData().getList(), this);
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
            GetUserMessageResponseModel getUserMessageResponseModel = FileUtils.getUserMessage(mContext);
            if (getUserMessageResponseModel != null) {
                //TODO:商铺ID现在写死为1，后面要将注释打开，动态获取ID。
//                sid = getUserMessageResponseModel.getResultData().getShopid();

                shopGoodsShowRequest = new ShopGoodsShowRequest(added, sid);
                String json = new Gson().toJson(shopGoodsShowRequest);
                RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", shopGoodsShowRequest);
                String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

                String signature = ACRequestUtils.getMD5(MyApplication.SHOPSGOODSSHOW + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                requestModel.setSignature(signature);

                HttpManager.getInstance()
                        .post(MyApplication.API + MyApplication.SHOPSGOODSSHOW)
                        .addParams("time", requestModel.getTime())
                        .addParams("version", requestModel.getVersion())
                        .addParams("guid", requestModel.getGuid())
                        .addParams("param", json)
                        .addParams("signature", signature)
                        .build()
                        .execute(ShopGoodsShowResponse.class, new CommonCallback<ShopGoodsShowResponse>() {
                            @Override
                            public void onSuccess(ShopGoodsShowResponse response, Object... obj) {
                                if ("0".equals(response.getServerNo())) {
                                    shopGoodsShowResponse = response;
                                    goodsList = shopGoodsShowResponse.getResultData().getList();
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
                showMessage("读取商品信息失败");
            }

        } else {
            LoginActivity.start(mContext);
        }
    }

    /**
     * 下拉刷新列表
     */
    private void onRefreshGoods() {
        //判断用户是否登录
        if (MainActivity.isLogin) {
            GetUserMessageResponseModel getUserMessageResponseModel = FileUtils.getUserMessage(mContext);
            if (getUserMessageResponseModel != null) {
                //TODO:商铺ID现在写死为1，后面要将注释打开，动态获取ID。
//                sid = getUserMessageResponseModel.getResultData().getShopid();

                shopGoodsShowRequest = new ShopGoodsShowRequest(added, sid);
                String json = new Gson().toJson(shopGoodsShowRequest);
                RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", shopGoodsShowRequest);
                String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

                String signature = ACRequestUtils.getMD5(MyApplication.SHOPSGOODSSHOW + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                requestModel.setSignature(signature);
                HttpManager.getInstance()
                        .post(MyApplication.API + MyApplication.SHOPSGOODSSHOW)
                        .addParams("time", requestModel.getTime())
                        .addParams("version", requestModel.getVersion())
                        .addParams("guid", requestModel.getGuid())
                        .addParams("param", json)
                        .addParams("signature", signature)
                        .build()
                        .execute(ShopGoodsShowResponse.class, new CommonCallback<ShopGoodsShowResponse>() {
                            @Override
                            public void onSuccess(ShopGoodsShowResponse response, Object... obj) {
                                if ("0".equals(response.getServerNo())) {
                                    shopGoodsShowResponse = null;
                                    goodsList = null;
                                    shopGoodsShowResponse = response;
                                    goodsList = shopGoodsShowResponse.getResultData().getList();
                                    onLoad();
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
                showMessage("读取商品信息失败");
            }

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
//        onLoad();
        onRefreshGoods();
//        requestOrder();
//        initData();
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mAdapter.notifyDataSetChanged();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onLoadMore() {
//        initMore();
        onLoad();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsInformationActivity.start(mContext, goodsList.get(position - 1).getGid() + "", 1, goodsList.get(position - 1).getGoods_id(), goodsList.get(position - 1).getTitle());
    }

    public void goodsAction(String action, String goodsID) {
        switch (action) {
            case "1":
                goodsActionRequest("1", goodsID, "2");
                break;
            case "2":
                goodsActionRequest("2", goodsID, "");
                break;
        }
    }

    public void goodsActionRequest(String action, String goodsID, String added) {
        //判断用户是否登录
        if (MainActivity.isLogin) {
            GoodsActionRequest goodsActionRequest = new GoodsActionRequest(action, goodsID, added);
            String json = new Gson().toJson(goodsActionRequest);
            RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsActionRequest);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

            String signature = ACRequestUtils.getMD5(MyApplication.SHOPSGOODSACTION + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.SHOPSGOODSACTION)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                        @Override
                        public void onSuccess(ResponseErrorModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                ResponseErrorModel responseErrorModel = response;
                                showMessage(responseErrorModel.getResultData().getMessage());
                                initData();
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
