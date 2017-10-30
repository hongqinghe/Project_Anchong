package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.AddCarParamModel;
import net.anchong.app.entity.request.model.CollectRequest;
import net.anchong.app.entity.request.model.CorrelationRequest;
import net.anchong.app.entity.request.model.EditAddressParamModel;
import net.anchong.app.entity.request.model.GoodsInfoParamModel;
import net.anchong.app.entity.request.model.GoodsParamRequestModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.CorrelationResponse;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.response.model.GoodsINfoResultModel;
import net.anchong.app.entity.response.model.GoodsParamResponseModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.entity.response.model.ShopCarMountResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.FileUtils;
import net.anchong.app.view.GeneralTitleBarView;
import net.anchong.app.view.GoodInfoRotationImageView;
import net.anchong.app.view.GoodsRotationImageView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 商品详情页面
 * Created by baishixin on 16/4/25.
 */
public class GoodsInformationActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    private static final int SETGOODSPARAM = 1001;
    private static final int CHANGE_IMG = 1010;

    /**
     * 数据展示部分
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_goodsinto_title)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.riv_goodsinfo_rotation)
    private GoodInfoRotationImageView rotationImageView;
    //需要展示详情的商品ID
    private String gid = "";

    //获取已经选择的名称
    @ViewInject(R.id.tv_goods_info_title)
    private TextView tv_goodsName;
    //商品价格
    @ViewInject(R.id.tv_goods_info_market_price)
    private TextView tv_marketPrice;
    //会员价格
    @ViewInject(R.id.tv_goods_info_vip_price)
    private TextView tv_vipPrice;
    //店铺图片
    @ViewInject(R.id.iv_goods_info_shopicon)
    private SimpleDraweeView shopIcon;
    //店铺名称
    @ViewInject(R.id.tv_goods_info_name)
    private TextView tv_shopName;
    //选择颜色规格
    @ViewInject(R.id.ll_select_tag)
    private LinearLayout selectTag;
    //展示选中商品规格数量的TextView
    @ViewInject(R.id.tv_goods_info_tags)
    private TextView tv_tags;

    //是否现实批量采购价  0 未登录或者未通过审核 不显示 1 现实。
    private int showPrice = 0;

    @ViewInject(R.id.tv_buynow)
    private TextView buyNow;
    @ViewInject(R.id.tv_more_infomation)
    private TextView moreInfomation;

    @ViewInject(R.id.tv_goods_bottom_tab_goodsNum)
    private TextView tv_goodsNum;
    //点击购物车进入购物车界面的按钮
    @ViewInject(R.id.ll_goods_bottom_tab_shopcar)
    private RelativeLayout rl_shopcar;
    //点击店铺进入店家商铺
    @ViewInject(R.id.ll_goods_bottom_tab_shop)
    private LinearLayout ll_shop;
    //点击进入店铺
    @ViewInject(R.id.tv_inshop)
    private TextView inShop;
    //商品收藏按钮
    @ViewInject(R.id.ll_goods_bottom_tab_collect)
    private LinearLayout ll_collect;
    //已收藏未收藏显示图片
    @ViewInject(R.id.iv_goods_bottom_tab_collect)
    private ImageView shopCollect;

    //推荐商品和配套商品视图
    @ViewInject(R.id.griv_goods_info)
    private GoodsRotationImageView goodsRotationImageView;

    /**
     * 数据请求部分
     */
    //商品名称
    private String goodsName = "";
    //商品数量
    private int goodsNum = 1;
    //商品价格
    private String goodsPrice = "";
    //商品规格
    private String goodsType = "";
    //商品的第一张图片
    private String goodsImg = "";
    //商品ID
    private String goodsID = "";
    //店铺ID
    private String sID = "";
    //店铺名称
    private String sName = "";
    //购物车数量
    private int shopCarGoodsNum = 0;


    /**
     * 数据存储部分
     */
    //如果数据请求成功，存储商品信息的model
    public static GoodsINfoResultModel goodsINfoResultModel = null;
    //选择商品参数界面需要的Response
    private GoodsParamResponseModel goodsParamResponseModel = null;
    //推荐商品的结果
    private CorrelationResponse correlationResponse = null;
    //所有商品详细信息的集合
    private List<GoodsParamResponseModel.ResultDataEntity> resultDataBeans = new ArrayList<>();
    //所有的商品属性标签
    private List<String> tags = new ArrayList<>();
    //商品标题
    private String title = "";

    private int collection = 0;

    private ProgressDialog pd = null;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CHANGE_IMG:
                    if (collection == 0) {
                        shopCollect.setImageResource(R.drawable.clection);
                    } else if (collection == 1) {
                        shopCollect.setImageResource(R.drawable.clected_solid);
//                        collresult = "0";
                    }
                    break;
            }
        }
    };

    public static void start(Context context, String gid, int showprice, String goods_id, String title) {
        Intent intent = new Intent(context, GoodsInformationActivity.class);
        intent.putExtra("gid", gid);
        intent.putExtra("title", title);
        intent.putExtra("goods_id", goods_id);
        intent.putExtra("showPrice", showprice);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        x.view().inject(this);
        gid = getIntent().getStringExtra("gid");
        goodsID = getIntent().getStringExtra("goods_id");
        showPrice = getIntent().getIntExtra("showPrice", 0);
        title = getIntent().getStringExtra("title");

        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        initData();
    }

    /**
     * 连接服务器获取数据
     */
    private void initData() {
        if (gid != null && !"".equals(gid)) {
            pd.show();
            //数据检查完成，可以提交数据
            GoodsInfoParamModel goodsInfoParamModel = new GoodsInfoParamModel(gid, goodsID);
            String json = new Gson().toJson(goodsInfoParamModel);

            RequestModel requestModel = null;
            String copyToken = null;
            //判断用户是否登录
            //登录状态
            if (MainActivity.isLogin == true) {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                        MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsInfoParamModel);
                copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            } else {
                requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", goodsInfoParamModel);
                copyToken = MyApplication.DEFAULT_TOKEN;
            }
            Logger.i("商品请求参数：" + json);
            String signature = ACRequestUtils.getMD5(MyApplication.GOODSGOODSINFO + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);


            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.GOODSGOODSINFO)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(GoodsINfoResultModel.class, new CommonCallback<GoodsINfoResultModel>() {
                        @Override
                        public void onSuccess(GoodsINfoResultModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                Logger.i("请求数据:" + response);
                                goodsINfoResultModel = response;
                                title = goodsINfoResultModel.getResultData().getTitle().toString();
                                goodsName = goodsINfoResultModel.getResultData().getGoods_name().toString().trim();
                                goodsImg = goodsINfoResultModel.getResultData().getGoodspic().get(0).toString().trim();
                                sID = goodsINfoResultModel.getResultData().getSid().toString().trim();
                                sName = goodsINfoResultModel.getResultData().getName();
                                goodsPrice = goodsINfoResultModel.getResultData().getMarket_price().toString().trim();
                                goodsType = goodsINfoResultModel.getResultData().getGoods_name();
                                collection = goodsINfoResultModel.getResultData().getCollection();
                                if (showPrice == 1) {
                                    goodsPrice = goodsINfoResultModel.getResultData().getVip_price().toString().trim();
                                }
                                goodsID = goodsINfoResultModel.getResultData().getGoods_id().toString().trim();
                                //加载商品属性标签
                                initTag();
                            } else if ("10".equals(response.getServerNo())) {
                                pd.dismiss();
                                showMessage("商品信息获取失败，请刷新");
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
    }


    /**
     * 连接服务器加载商品属性标签 结果待传给 GoodsSetParamActivity
     */
    private void initTag() {
        //数据检查完成，可以提交数据
        GoodsParamRequestModel goodsParamRequestModel = new GoodsParamRequestModel(goodsID);
        String json = new Gson().toJson(goodsParamRequestModel);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            //TODO:或者这里直接跳转到登录界面
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsParamRequestModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", goodsParamRequestModel);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.GOODSGOODSFORMAT + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.GOODSGOODSFORMAT)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(GoodsParamResponseModel.class, new CommonCallback<GoodsParamResponseModel>() {
                    @Override
                    public void onSuccess(GoodsParamResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            Logger.i("颜色规格");
                            goodsParamResponseModel = response;
                            parseData();
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

    /**
     * 初始化UI视图
     */
    private void initView() {
        pd.dismiss();
        mGeneralTitleBarView.setData("商品详情", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        //设置商品详情轮播图
        List<String> pics = goodsINfoResultModel.getResultData().getGoodspic();
        rotationImageView.setData(pics, false);
        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        int width = wm.getDefaultDisplay().getWidth();
        int height = wm.getDefaultDisplay().getHeight();

        float scale = 1242 / width;


        rotationImageView.setMinimumHeight((int) (1320 * scale));

        tv_goodsName.setText(title.toString().trim());
//        tv_goodsName.setText(goodsINfoResultModel.getResultData().getGoods_name().toString().trim());
        tv_marketPrice.setText("价格：￥" + goodsINfoResultModel.getResultData().getMarket_price().toString().trim());
        tv_vipPrice.setText("批量采购价：￥" + goodsINfoResultModel.getResultData().getVip_price().toString().trim());
        if (!TextUtils.isEmpty(goodsINfoResultModel.getResultData().getImg())) {
            shopIcon.setImageURI(Uri.parse(goodsINfoResultModel.getResultData().getImg().toString().trim()));
        }
        if (!TextUtils.isEmpty(goodsINfoResultModel.getResultData().getName())) {
            tv_shopName.setText(goodsINfoResultModel.getResultData().getName().toString().trim());
        }
        //判断如果用户未登录或者为通过审核则不显示批量采购价
        if (showPrice == 0) {
//            tv_vipPrice.setVisibility(View.GONE);
            tv_vipPrice.setText("批量采购价：请登录后查看");
        }

        //设置推荐商品或者配套商品
        if (correlationResponse != null) {
            goodsRotationImageView.setData(correlationResponse.getResultData().getList());
        }

        //设置默认选中的商品属性和默认选择的数量
//        goodsType = tags.get(0);
//        tv_tags.setText("已选择：" + tags + "  数量：" + goodsNum);
        tv_tags.setText("已选择：" + goodsINfoResultModel.getResultData().getGoods_name() + "  数量：" + goodsNum);
        if (shopCarGoodsNum <= 0) {
            tv_goodsNum.setVisibility(View.GONE);
        } else {
            tv_goodsNum.setText(shopCarGoodsNum + "");
        }

        buyNow.setOnClickListener(this);
        selectTag.setOnClickListener(this);
        moreInfomation.setOnClickListener(this);
        rl_shopcar.setOnClickListener(this);
        ll_shop.setOnClickListener(this);
        inShop.setOnClickListener(this);
        ll_collect.setOnClickListener(this);
    }

    /**
     * 解析服务器返回的商品规格标签数据
     */
    private void parseData() {
        if (goodsParamResponseModel != null) {
            if (goodsParamResponseModel.getResultData() != null) {
                resultDataBeans = goodsParamResponseModel.getResultData();
                for (int i = 0; i < resultDataBeans.size(); i++) {
                    //组装商品属性标记集合
                    tags.add(resultDataBeans.get(i).getValue().get(0).toString().trim());
                }
            }
        }
//        initSupportingGoods();
        getShopCarNum();
    }

    /**
     * 加载配套商品和推荐商品
     */
    private void initSupportingGoods() {

        //数据检查完成，可以提交数据
        CorrelationRequest correlationRequest = new CorrelationRequest(gid, "1");
        String json = new Gson().toJson(correlationRequest);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            //TODO:或者这里直接跳转到登录界面
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", correlationRequest);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", correlationRequest);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.GOODSCORRELATION + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.GOODSCORRELATION)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(CorrelationResponse.class, new CommonCallback<CorrelationResponse>() {
                    @Override
                    public void onSuccess(CorrelationResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            correlationResponse = response;
                            Logger.i(correlationResponse.toString());
                            getShopCarNum();
                        } else {
//                            getShopCarNum();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
//                        getShopCarNum();
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

    /**
     * 处理返回结果
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //货品属性选择成功
        if (requestCode == SETGOODSPARAM && resultCode == RESULT_OK) {

            String data_gid = data.getStringExtra("gid");
//            String data_goodsid = data.getStringExtra("goods_id");
            Logger.i("data_gid:" + data_gid);
//            Logger.i("data_goodsid:" + data_goodsid);
            if (data_gid != null && !"".equals(data_gid)) {
                gid = data_gid;
//                goodsID = data_goodsid;
                goodsType = data.getStringExtra("goodsType");
                goodsINfoResultModel.getResultData().setGoods_name(goodsType);
                title = data.getStringExtra("title");
                initData();
            }
            goodsNum = data.getIntExtra("goodsNum", goodsNum);
            tv_tags.setText("已选择：" + goodsINfoResultModel.getResultData().getGoods_name() + "  数量：" + goodsNum);


        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buynow:
                //封装数据

                //判断是否已经选择商品属性
                if (goodsType == null || "".equals(goodsType)) {
                    showMessage("请选择商品属性");
                    return;
                }

                //根据用户认证状态，决定用户购买货品价格，
                GetUserMessageResponseModel getUserMessageResponseModel = FileUtils.getUserMessage(this);
                if (getUserMessageResponseModel != null) {
                    String authNUm = getUserMessageResponseModel.getResultData().getAuthNum();
                    //认证状态码为 3 时，代表用户已经通过审核
                    if ("3".equals(authNUm)) {
                        goodsPrice = goodsINfoResultModel.getResultData().getVip_price();
                    }
                }

                AddCarParamModel addCarParamModel = new AddCarParamModel(goodsName, goodsID, goodsNum + "", goodsPrice, goodsType, goodsImg, gid, sID, sName);
//                AddCarParamModel addCarParamModel = new AddCarParamModel(goodsName, goodsNum + "", goodsPrice, goodsType, goodsImg, gid, sID, sName);
                String json = new Gson().toJson(addCarParamModel);
                RequestModel requestModel = null;
                String copyToken = null;
                //判断用户是否登录
                //登录状态
                if (MainActivity.isLogin == true) {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                            MainActivity.loginResponseModel.getResultData().getGuid() + "", addCarParamModel);
                    copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                    String signature = ACRequestUtils.getMD5(MyApplication.CARTCARTADD + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                    requestModel.setSignature(signature);


                    HttpManager.getInstance()
                            .post(MyApplication.API + MyApplication.CARTCARTADD)
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
                                        //购物车添加成功，查询购物车获取数量并且更新UI
                                        getShopCarNum();

                                        ResponseErrorModel responseErrorModel = response;
                                        showMessage(responseErrorModel.getResultData().getMessage());
                                    } else {
                                        ResponseErrorModel responseErrorModel = response;
                                        showMessage(responseErrorModel.getResultData().getMessage());
                                    }
                                }

                                @Override
                                public void onError(Call call, Exception e) {
                                    showMessage(getString(R.string.request_error_msg));
                                    e.printStackTrace();
                                }
                            });
                } else {
                    showMessage("请先登录");
                    LoginActivity.start(this);
                }

                break;
            case R.id.ll_select_tag:
                Intent intent = new Intent(this, GoodsSetParamActivity.class);
                intent.putExtra("goodsNum", goodsNum);
                intent.putExtra("goods_id", goodsID);
                intent.putExtra("response", goodsParamResponseModel);
                intent.putExtra("goodsInfo", goodsINfoResultModel);
                startActivityForResult(intent, SETGOODSPARAM);
//                context.startActivity(intent);
//                GoodsSetParamActivity.start(this, gid, goodsParamResponseModel, goodsINfoResultModel);
                break;
            case R.id.tv_more_infomation:
                MoreGoodsInfoActivity.start(this);
                break;
            case R.id.ll_goods_bottom_tab_shopcar:
                ShopCarActivity.start(this);
                break;
            case R.id.ll_goods_bottom_tab_shop:
                ShopIndexActivity.start(this, goodsINfoResultModel.getResultData().getSid());
                break;
            case R.id.tv_inshop:
                ShopIndexActivity.start(this, goodsINfoResultModel.getResultData().getSid());
                break;
            case R.id.ll_goods_bottom_tab_collect:
                goodsCollection();
                break;
        }
    }

    private void goodsCollection() {
        String api = "";
        if (collection == 0) {
            api = MyApplication.COLLECTADDCOLLECT;
            collection = 1;
        } else if (collection == 1) {
            api = MyApplication.COLLECTDELCOLLECT;
            collection = 0;
        }

        CollectRequest collectRequest = new CollectRequest(goodsID, "1");
        String json = new Gson().toJson(collectRequest);

        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", collectRequest);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", collectRequest);
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
                .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                    @Override
                    public void onSuccess(ResponseErrorModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            handler.sendEmptyMessage(CHANGE_IMG);
                            showMessage(response.getResultData().getMessage());
                        } else {
                            showMessage(response.getResultData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage("网络错误");
                        e.printStackTrace();
                    }
                });
    }


    private void getShopCarNum() {

        //数据检查完成，可以提交数据
        EditAddressParamModel editAddressParamModel = new EditAddressParamModel("");
        String requestJson = new Gson().toJson(editAddressParamModel);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", editAddressParamModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            String signature = ACRequestUtils.getMD5(MyApplication.CARTCARTAMOUNT + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
            requestModel.setSignature(signature);


            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.CARTCARTAMOUNT)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", requestJson)
                    .addParams("signature", signature)
                    .build()
                    .execute(ShopCarMountResponse.class, new CommonCallback<ShopCarMountResponse>() {
                        @Override
                        public void onSuccess(ShopCarMountResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                ShopCarMountResponse shopCarMountResponse = response;
                                shopCarGoodsNum = shopCarMountResponse.getResultData().getCartamount();
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
            initView();
        }

    }
}
