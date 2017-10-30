package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.StickyHeaderListView.adapter.TravelingAdapter;
import net.anchong.app.adapter.GoodsListAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.MyShopsRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.ShopIndexRequest;
import net.anchong.app.entity.response.model.GoodsListResponseModel;
import net.anchong.app.entity.response.model.MyShopsResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.ShopIndexHeaderView;
import net.anchong.app.view.ShopIndexTabView;
import net.anchong.app.view.WhiteGeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/5/30.
 */
public class ShopIndexActivity extends BaseActivity implements View.OnClickListener, WhiteGeneralTitleBarView.GeneralTitleBarOnclickListener, ShopIndexTabView.ShopIndexTabOnclickListener, XListView.IXListViewListener {

    /**
     *
     */
    @ViewInject(R.id.xlv_shop_index)
    private XListView xListView;
    @ViewInject(R.id.wgtbv_shop_index)
    private WhiteGeneralTitleBarView titleBarView;
    @ViewInject(R.id.sitv_shop_index)
    private ShopIndexTabView shopIndexTabView;
    @ViewInject(R.id.tv_shop_goods_type)
    private TextView goodsType;
    @ViewInject(R.id.tv_shop_goods_kefu)
    private TextView goodsKeFu;
    /**
     *
     */

    /**
     *
     */

    private TravelingAdapter mAdapter; // 主页数据

    private String sid;//店铺
    private int page = 1;//分页
    private GoodsListResponseModel goodsListResponseModel;
    private List<GoodsListResponseModel.ResultDataEntity.ListEntity> list = new ArrayList<>();
    private GoodsListAdapter adapter;
    private String selectAction = "0";
    private int showPrice;
    private String api = "";
    private ShopIndexHeaderView shopIndexHeaderView;
    //店铺信息结果
    private MyShopsResponse myShopsResponse;

    public static void start(Context context, String sid) {
        Intent intent = new Intent(context, ShopIndexActivity.class);
        intent.putExtra("sid", sid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_index2);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.tab_bg);// 通知栏所需颜色
        x.view().inject(this);
        sid = getIntent().getStringExtra("sid");
//        initData();
        initShop();
    }

    /**
     * 初始化店铺信息
     */
    private void initShop() {
        if (sid != null && !"".equals(sid)) {
            //数据检查完成，可以提交数据
            MyShopsRequest myShopsRequest = new MyShopsRequest(sid);
            String json = new Gson().toJson(myShopsRequest);

            RequestModel requestModel = null;
            String copyToken = null;
            //判断用户是否登录
            //登录状态
            if (MainActivity.isLogin == true) {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", myShopsRequest);
                copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            } else {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", myShopsRequest);
                copyToken = MyApplication.DEFAULT_TOKEN;
            }
            String signature = ACRequestUtils.getMD5(MyApplication.SHOPSMYSHOPS + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.SHOPSMYSHOPS)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(MyShopsResponse.class, new CommonCallback<MyShopsResponse>() {
                        @Override
                        public void onSuccess(MyShopsResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                myShopsResponse = response;
                                initData();
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
    }

    /**
     * 初始化数据
     */
    private void initData() {
        api = MyApplication.SHOPSSHOPSINDEX;
        if (sid != null && !"".equals(sid)) {
            //数据检查完成，可以提交数据
            page = 1;
            ShopIndexRequest shopIndexRequest = new ShopIndexRequest(sid, selectAction, "", page + "");
            String json = new Gson().toJson(shopIndexRequest);

            RequestModel requestModel = null;
            String copyToken = null;
            //判断用户是否登录
            //登录状态
            if (MainActivity.isLogin == true) {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", shopIndexRequest);
                copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            } else {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", shopIndexRequest);
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
                    .execute(GoodsListResponseModel.class, new CommonCallback<GoodsListResponseModel>() {
                        @Override
                        public void onSuccess(GoodsListResponseModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                goodsListResponseModel = response;
                                if (list.size() > 0) {
                                    list.clear();
                                }
                                list = goodsListResponseModel.getResultData().getList();
                                showPrice = Integer.parseInt(goodsListResponseModel.getResultData().getShowprice());
                                //是否有下一页
                                if (list.size() < goodsListResponseModel.getResultData().getTotal()) {
                                    page++;
                                }
                                if (list.size() == goodsListResponseModel.getResultData().getTotal()) {
                                    xListView.setPullLoadEnable(false);
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
        }

    }

    private void initView() {
        titleBarView.setData("", "");
        titleBarView.setGeneralTitleBarOnclickListener(this);

        shopIndexTabView.setShopIndexTabOnclickListener(this);
        if (shopIndexHeaderView == null) {
            shopIndexHeaderView = new ShopIndexHeaderView(this);
            shopIndexHeaderView.setData(myShopsResponse,sid);
            xListView.addHeaderView(shopIndexHeaderView);
        }
        adapter = new GoodsListAdapter(this, getLayoutInflater(), list, showPrice);
        xListView.setAdapter(adapter);
        xListView.setXListViewListener(this);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(false);
        if (list.size() == goodsListResponseModel.getResultData().getTotal()) {
            xListView.setPullLoadEnable(false);
        }

        goodsType.setOnClickListener(this);
        goodsKeFu.setOnClickListener(this);
    }

    private void loadGoods() {


        if (sid != null && !"".equals(sid)) {
            //数据检查完成，可以提交数据
            page = 1;
            ShopIndexRequest shopIndexRequest = new ShopIndexRequest(sid, selectAction, "", page + "");
            String json = new Gson().toJson(shopIndexRequest);

            RequestModel requestModel = null;
            String copyToken = null;
            //判断用户是否登录
            //登录状态
            if (MainActivity.isLogin == true) {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", shopIndexRequest);
                copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            } else {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", shopIndexRequest);
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
                    .execute(GoodsListResponseModel.class, new CommonCallback<GoodsListResponseModel>() {
                        @Override
                        public void onSuccess(GoodsListResponseModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                goodsListResponseModel = response;
                                if (list.size() > 0) {
                                    list.clear();
                                }
                                list = goodsListResponseModel.getResultData().getList();
                                //是否有下一页
                                if (list.size() < goodsListResponseModel.getResultData().getTotal()) {
                                    page++;
                                }
                                if (list.size() == goodsListResponseModel.getResultData().getTotal()) {
                                    xListView.setPullLoadEnable(false);
                                }
                                adapter.setData(list);
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


    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onRefresh() {

    }

    private void onLoad() {
        xListView.stopLoadMore();
        xListView.setRefreshTime("刚刚");
    }

    @Override
    public void onLoadMore() {
        loadMore();
    }

    /**
     * 上拉加载更多
     */
    private void loadMore() {
        if (sid != null && !"".equals(sid)) {
            //数据检查完成，可以提交数据
//            page = 1;
            ShopIndexRequest shopIndexRequest = new ShopIndexRequest(sid, selectAction, "", page + "");
            String json = new Gson().toJson(shopIndexRequest);

            RequestModel requestModel = null;
            String copyToken = null;
            //判断用户是否登录
            //登录状态
            if (MainActivity.isLogin == true) {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", shopIndexRequest);
                copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            } else {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", shopIndexRequest);
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
                    .execute(GoodsListResponseModel.class, new CommonCallback<GoodsListResponseModel>() {
                        @Override
                        public void onSuccess(GoodsListResponseModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                goodsListResponseModel = response;
                                if (goodsListResponseModel.getResultData().getList().size() > 0) {
                                    list.addAll(goodsListResponseModel.getResultData().getList());
                                    if (list.size() < goodsListResponseModel.getResultData().getTotal()) {
                                        page++;
                                    }
                                    if (list.size() == goodsListResponseModel.getResultData().getTotal()) {
                                        xListView.setPullLoadEnable(false);
                                    }
                                    adapter.setData(list);
                                }
                                onLoad();
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
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_shop_goods_type:
                Toast.makeText(this, "商品分类", Toast.LENGTH_SHORT).show();
                ShopGoodsTypeActivity.start(this);
                break;
            case R.id.tv_shop_goods_kefu:
                Toast.makeText(this, "联系客服", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void indexClick() {
        api = MyApplication.SHOPSSHOPSINDEX;
        selectAction = "";
        loadGoods();
    }

    @Override
    public void allClick(String action) {
        api = MyApplication.SHOPSSHOPSGOODS;
        selectAction = action;
        loadGoods();
    }

    @Override
    public void newClick() {
        api = MyApplication.SHOPSNEWGOODS;
        selectAction = "";
        loadGoods();
    }
}
