package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.ShopCarAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.CreateOrderRequestModel;
import net.anchong.app.entity.request.model.DelShopCarParam;
import net.anchong.app.entity.request.model.EditAddressParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.entity.response.model.ShopCarResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 购物车
 * Created by baishixin on 16/4/26.
 */
public class ShopCarActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {


    /**
     * 数据展示部分
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_shopcar_title)
    private GeneralTitleBarView mGeneralTitleBarView;

    @ViewInject(R.id.settlement)
    private TextView settlement;

    //全选按钮
    @ViewInject(R.id.checkbox)
    private CheckBox checkAll;

    @ViewInject(R.id.listview_shop_car)
    private XListView listView;

    @ViewInject(R.id.price)
    private TextView price;

    @ViewInject(R.id.num)
    private TextView tv_num;

    @ViewInject(R.id.order_layout)
    private LinearLayout order_layout;
    //

    /**
     * 数据存储部分
     */
    private ShopCarResponseModel shopCarResponseModel = null;

    private ShopCarAdapter adapter;

//    private List<>

    //所有商品总价钱
    private float priceTotal = 0.0f;
    //所有商品总数量
    private int goodsTotal = 0;


    //当前是否处于编辑状态 true为编辑状态
    private boolean isEdit = false;

    private ProgressDialog pd;

    /**
     * 数据请求部分
     */
    private CreateOrderRequestModel createOrderRequestModel = null;


    public static void start(Context context) {
        Intent intent = new Intent(context, ShopCarActivity.class);

        context.startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        priceTotal = 0.0f;
        goodsTotal = 0;
        isEdit = false;
        price.setText(priceTotal + "");
        tv_num.setText("共 " + goodsTotal + " 件");
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        x.view().inject(this);
    }

    /**
     * 获取购物车数据
     */
    private void initData() {
        pd = new ProgressDialog(this);
        pd.show();
        shopCarResponseModel = null;
        //数据检查完成，可以提交数据
        EditAddressParamModel editAddressParamModel = new EditAddressParamModel("");
        String requestJson = new Gson().toJson(editAddressParamModel);

        RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), editAddressParamModel);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.CARTCARTINFO + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.CARTCARTINFO)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestJson)
                .addParams("signature", signature)
                .build()
                .execute(ShopCarResponseModel.class, new CommonCallback<ShopCarResponseModel>() {
                    @Override
                    public void onSuccess(ShopCarResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            shopCarResponseModel = response;
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

    private void initView() {
        pd.dismiss();
        mGeneralTitleBarView.setData("我的购物车", "编辑");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        adapter = new ShopCarAdapter(this, getLayoutInflater(), shopCarResponseModel.getResultData());
        listView.setAdapter(adapter);

        //设置结算按钮事件
        settlement.setOnClickListener(this);
        checkAll.setOnClickListener(this);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        if (isEdit) {
            mGeneralTitleBarView.setData("我的购物车", "编辑");
            settlement.setText("去结算");
            order_layout.setVisibility(View.VISIBLE);
            isEdit = false;
        } else {
            mGeneralTitleBarView.setData("我的购物车", "完成");
            settlement.setText("删除");
            order_layout.setVisibility(View.INVISIBLE);
            isEdit = true;
        }

    }

    /**
     * 选中商品增加总价钱
     *
     * @param goodPrice
     */
    public void addPrice(float goodPrice, int goodsNum) {
        priceTotal += goodPrice;
        goodsTotal += goodsNum;
        price.setText(priceTotal + "");
        tv_num.setText("共 " + goodsTotal + " 件");
    }

    /**
     * 取消选择商品减少总价钱
     */
    public void delPrice(float goodPrice, int goodsNum) {
        priceTotal -= goodPrice;
        goodsTotal -= goodsNum;
        price.setText(priceTotal + "");
        tv_num.setText("共 " + goodsTotal + " 件");
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settlement:
                String action = settlement.getText().toString();
                if ("去结算".equals(action)) {
                    createOrder();
                }
                if ("删除".equals(action)) {
                    delShopCar();
                }
                break;
            case R.id.checkbox:
                if (checkAll.isChecked()) {
                    Logger.i("全选");
                    List<ShopCarResponseModel.ResultDataBean> shopList = shopCarResponseModel.getResultData();
                    for (int i = 0; i < shopList.size(); i++) {
                        shopList.get(i).setIsSelect(true);
                    }
                    adapter.setShopList(shopList);
                } else {
                    Logger.i("不全选");
                    List<ShopCarResponseModel.ResultDataBean> shopList = shopCarResponseModel.getResultData();
                    for (int i = 0; i < shopList.size(); i++) {
                        shopList.get(i).setIsSelect(false);
                    }
                    adapter.setShopList(shopList);
                }
                break;
        }
    }

    private List<Integer> car_idList = new ArrayList<>();

    private void delShopCar() {
        boolean isOK = false;
        //根据选中的商品生成订单
        if (shopCarResponseModel == null) {

        } else {
            if (shopCarResponseModel.getResultData() == null || shopCarResponseModel.getResultData().size() <= 0) {
                showMessage("还没有商品哦，快去购买吧！");
            } else {
                //生成订单请求数据
                createOrderRequestModel = new CreateOrderRequestModel();
                List<CreateOrderRequestModel.ListEntity> listEntities = new ArrayList<>();
                float freightTotal = 0.0f;
                for (int i = 0; i < shopCarResponseModel.getResultData().size(); i++) {
                    List<CreateOrderRequestModel.ListEntity.GoodsEntity> goodsEntities = new ArrayList<>();
                    ShopCarResponseModel.ResultDataBean shop = shopCarResponseModel.getResultData().get(i);

                    List<ShopCarResponseModel.ResultDataBean.GoodsBean> goodsList = shop.getGoods();
                    ShopCarResponseModel.ResultDataBean.GoodsBean goods = null;
                    for (int j = 0; j < goodsList.size(); j++) {
                        goods = goodsList.get(j);
                        if (goods.isSelect()) {
                            isOK = true;
                            car_idList.add(goods.getCart_id());
                        }
                    }
                }

                if (isOK == false) {
                    showMessage("请选择要删除的商品");
                }
                if (car_idList.size() > 0) {
                    //数据检查完成，可以提交数据
                    DelShopCarParam delShopCarParam = new DelShopCarParam(car_idList);
                    String requestJson = new Gson().toJson(delShopCarParam);
                    RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), delShopCarParam);
                    String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                    String signature = ACRequestUtils.getMD5(MyApplication.CARTCARTDEL + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
                    requestModel.setSignature(signature);
                    HttpManager.getInstance()
                            .post(MyApplication.API + MyApplication.CARTCARTDEL)
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
                                        initData();
                                        ResponseErrorModel responseErrorModel = response;
                                        showMessage(responseErrorModel.getResultData().getMessage());
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
        }
    }

    /**
     * 生成订单
     */
    private void createOrder() {
        boolean isOK = false;
        //根据选中的商品生成订单
        if (shopCarResponseModel == null) {

        } else {
            if (shopCarResponseModel.getResultData() == null || shopCarResponseModel.getResultData().size() <= 0) {
                showMessage("还没有商品哦，快去购买吧！");
            } else {
                //生成订单请求数据
                createOrderRequestModel = new CreateOrderRequestModel();
                List<CreateOrderRequestModel.ListEntity> listEntities = new ArrayList<>();
//                CreateOrderRequestModel.ListEntity listEntity = new CreateOrderRequestModel.ListEntity();
                float freightTotal = 0.0f;
                for (int i = 0; i < shopCarResponseModel.getResultData().size(); i++) {
                    ShopCarResponseModel.ResultDataBean shop = shopCarResponseModel.getResultData().get(i);

                    List<ShopCarResponseModel.ResultDataBean.GoodsBean> goodsList = shop.getGoods();
                    ShopCarResponseModel.ResultDataBean.GoodsBean goods = null;
                    if (goodsList.size() <= 0) {
                        continue;
                    }
                    List<CreateOrderRequestModel.ListEntity.GoodsEntity> goodsEntities = new ArrayList<>();
                    //当前商铺下购买商品的数量
                    int currentNum = 0;
                    //当前商铺下购买商品的总价钱
                    float shopPriceTotal = 0.0f;
                    for (int j = 0; j < goodsList.size(); j++) {
                        goods = goodsList.get(j);
                        if (goods.isSelect()) {
                            isOK = true;
                            //货品单价
                            float price = Float.parseFloat(goods.getGoods_price());
                            //货品数量
                            int num = Integer.parseInt(goods.getGoods_num());

                            shopPriceTotal += price * num;

                            CreateOrderRequestModel.ListEntity.GoodsEntity goodsEntity = new CreateOrderRequestModel.ListEntity.GoodsEntity();
                            goodsEntity.setCart_id(goods.getCart_id() + "");
                            goodsEntity.setGid(goods.getGid());
                            goodsEntity.setGoods_name(goods.getGoods_name());
                            goodsEntity.setGoods_num(goods.getGoods_num());
                            goodsEntity.setGoods_price(goods.getGoods_price());
                            goodsEntity.setGoods_type(goods.getGoods_type());
                            goodsEntity.setImg(goods.getImg());
                            goodsEntities.add(goodsEntity);
                        }
                    }


                    //计算是否有运费
                    //所购买的货品价钱超过商铺包邮价，免邮费
                    if (shopPriceTotal > Float.parseFloat(shop.getFree_price())) {
//                        createOrderRequestModel.setFreight("0");
                    } else {
                        freightTotal = Float.parseFloat(shop.getFreight());
//                        createOrderRequestModel.setFreight(shop.getFreight());
                    }
                    CreateOrderRequestModel.ListEntity shopEntity = new CreateOrderRequestModel.ListEntity();
                    shopEntity.setSid(shop.getSid());
                    shopEntity.setSname(shop.getSname());
                    shopEntity.setTotal_price(shopPriceTotal + "");
                    shopEntity.setGoods(goodsEntities);
                    shopEntity.setFreight(freightTotal + "");
                    listEntities.add(shopEntity);
                }

                if (isOK == false) {
                    showMessage("请选择商品");
                }
                createOrderRequestModel.setList(listEntities);
//                createOrderRequestModel.setFreight(freightTotal + "");
                CreateOrderActivity.start(this, createOrderRequestModel);

            }
        }
    }
}






