package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.CreateOrderRequestModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.CreateOrderResponse;
import net.anchong.app.entity.response.model.GetDefaultAddressResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.CreateOrderImage;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/4/29.
 */
public class CreateOrderActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {


    //标题栏
    @ViewInject(R.id.gtvv_createorder_title)
    private GeneralTitleBarView generalTitleBarView;

    private CreateOrderRequestModel createOrderRequestModel = null;

    //用户默认收货地址
    private GetDefaultAddressResponse defaultAddress = null;

    /**
     * 数据请求部分
     */
    private String freight;//运费
    private String name;//收货人姓名
    private String phone;//收货人电话
    private String address;//收货地址
    private String region;//所在地区
    private String invoice = "";//发票

    @ViewInject(R.id.commit_order)
    private TextView commit_order;

    /**
     * 页面展示部分
     */
    /**
     * 收货地址部分
     */
    //选择收货地址
    @ViewInject(R.id.ll_create_order_selectaddress)
    private LinearLayout ll_selectAddress;
    @ViewInject(R.id.tv_create_order_name)
    private TextView tv_address_name;
    @ViewInject(R.id.tv_create_order_phone)
    private TextView tv_address_phone;
    @ViewInject(R.id.tv_create_order_areaaddress)
    private TextView tv_address_areaaddress;
    @ViewInject(R.id.ll_create_order_address)
    private LinearLayout linearDefaultAddress;
    @ViewInject(R.id.ll_create_order_imgs)
    private LinearLayout ll_imgs;
    @ViewInject(R.id.et_invoice)
    private EditText et_invoice;
    /**
     * 费用部分
     */
    @ViewInject(R.id.tv_create_order_goods_price)
    private TextView tv_goodsPrice;
    @ViewInject(R.id.tv_create_order_freight)
    private TextView tv_freight;
    @ViewInject(R.id.tv_create_order_goodsTotal)
    private TextView tv_goodsTotal;

    //商品总价格
    private float goodsPriceTotal;
    //货品总数量
    private int goodsTotal;
    //邮费总价
    private float freightTotal;
    private ProgressDialog pd;

    //订单提交完成结果
    private CreateOrderResponse createOrderResponse;


    public static void start(Context context, CreateOrderRequestModel createOrderRequestModel) {
        Intent intent = new Intent(context, CreateOrderActivity.class);
        intent.putExtra("request", createOrderRequestModel);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);
        x.view().inject(this);
        Fresco.initialize(this);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        createOrderRequestModel = (CreateOrderRequestModel) getIntent().getSerializableExtra("request");
        initData();
    }

    private void initData() {
        initAddress();

    }

    /**
     * 加载用户默认收货地址
     */
    private void initAddress() {
        pd.show();
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", null);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", null);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.USERGETDEFAULTADDRESS + requestModel.getTime() + requestModel.getGuid() + copyToken);
        requestModel.setSignature(signature);

        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.USERGETDEFAULTADDRESS)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("signature", signature)
                .build()
                .execute(GetDefaultAddressResponse.class, new CommonCallback<GetDefaultAddressResponse>() {
                    @Override
                    public void onSuccess(GetDefaultAddressResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            defaultAddress = response;
                            if (defaultAddress != null) {
                                initView();
                            }
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

    View noAddress;
    boolean isHava = true;//是否有默认收货地址

    private void initView() {
        generalTitleBarView.setData("填写订单", "");
        generalTitleBarView.setGeneralTitleBarOnclickListener(this);
        ll_selectAddress.setOnClickListener(this);
        if (addressIsOk()) {
            tv_address_name.setText(defaultAddress.getResultData().getAdd_name().toString().trim());
            name = defaultAddress.getResultData().getAdd_name();
            tv_address_phone.setText(defaultAddress.getResultData().getPhone().toString().trim());
            phone = defaultAddress.getResultData().getPhone();
            tv_address_areaaddress.setText(defaultAddress.getResultData().getRegion().toString().trim() +
                    defaultAddress.getResultData().getAddress().toString().trim());
            address = defaultAddress.getResultData().getRegion() + defaultAddress.getResultData().getAddress();
        } else {
            isHava = false;
            noAddress = getLayoutInflater().inflate(R.layout.layout_no_address, null);
            tv_address_areaaddress.setVisibility(View.GONE);
            tv_address_phone.setVisibility(View.GONE);
            tv_address_name.setVisibility(View.GONE);
            linearDefaultAddress.addView(noAddress);
        }


        //设置商品图片


        //设置费用部分
        //邮费
//        if (!TextUtils.isEmpty(createOrderRequestModel.getFreight())) {
//            freight = createOrderRequestModel.getFreight();
//            tv_freight.setText(createOrderRequestModel.getFreight());
//        }
        //货品总价
        List<CreateOrderRequestModel.ListEntity> shops = createOrderRequestModel.getList();
        goodsPriceTotal = 0.0f;
        goodsTotal = 0;
        freightTotal = 0.0f;
        if (shops != null && shops.size() > 0) {

            for (int i = 0; i < shops.size(); i++) {
                //累加商品总价
                goodsPriceTotal += Float.parseFloat(shops.get(i).getTotal_price());
                freightTotal += Float.parseFloat(shops.get(i).getFreight());
                //当前店铺下所购买的商品
                List<CreateOrderRequestModel.ListEntity.GoodsEntity> goods = shops.get(i).getGoods();
                if (goods != null && goods.size() > 0) {
                    for (int j = 0; j < goods.size(); j++) {
                        CreateOrderRequestModel.ListEntity.GoodsEntity good = goods.get(j);

                        goodsTotal += Integer.parseInt(good.getGoods_num());

                        CreateOrderImage coi = new CreateOrderImage(this);
                        coi.setData(good.getImg());
                        ll_imgs.addView(coi);
                    }
                }
            }
            tv_goodsPrice.setText(goodsPriceTotal + "");
            tv_goodsTotal.setText("共" + goodsTotal + "件");
        }
        tv_freight.setText(freightTotal + "");
        freight = freightTotal + "";
        pd.dismiss();
        commit_order.setOnClickListener(this);
    }

    /**
     * 判断是否有默认收货地址
     *
     * @return
     */
    private boolean addressIsOk() {
        if (defaultAddress == null) {
            return false;
        }
        if (defaultAddress.getResultData() == null || "".equals(defaultAddress.getResultData())) {
            return false;
        }
        if (defaultAddress.getResultData().getUsers_id() == null || "".equals(defaultAddress.getResultData().getUsers_id())) {
            return false;
        }
        if (defaultAddress.getResultData().getAdd_name() == null || "".equals(defaultAddress.getResultData().getAdd_name())) {
            return false;
        }
        if (defaultAddress.getResultData().getAddress() == null || "".equals(defaultAddress.getResultData().getAddress())) {
            return false;
        }
        return true;
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_create_order_selectaddress:
                Intent intent = new Intent(this, AddressManagerActivity.class);
                intent.putExtra("tag", "select");
                startActivityForResult(intent, MyApplication.SELECT_ADDRESS);
                break;
            case R.id.commit_order:
//                createOrderRequestModel.setFreight(freight);
                invoice = et_invoice.getText().toString();
                createOrderRequestModel.setInvoice(invoice);
                createOrderRequestModel.setAddress(region + address);
                createOrderRequestModel.setName(name);
                createOrderRequestModel.setPhone(phone);


                String json = new Gson().toJson(createOrderRequestModel);
                Logger.i("请求数据：" + json);


                RequestModel requestModel = null;
                String copyToken = null;
                //判断用户是否登录
                //登录状态
                if (MainActivity.isLogin == true) {
                    //TODO:或者这里直接跳转到登录界面
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                            MainActivity.loginResponseModel.getResultData().getGuid() + "", createOrderRequestModel);
                    copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                    String signature = ACRequestUtils.getMD5(MyApplication.ORDERORDERCREATE + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                    requestModel.setSignature(signature);


                    HttpManager.getInstance()
                            .post(MyApplication.API + MyApplication.ORDERORDERCREATE)
                            .addParams("time", requestModel.getTime())
                            .addParams("version", requestModel.getVersion())
                            .addParams("guid", requestModel.getGuid())
                            .addParams("param", json)
                            .addParams("signature", signature)
                            .build()
                            .execute(CreateOrderResponse.class, new CommonCallback<CreateOrderResponse>() {
                                @Override
                                public void onSuccess(CreateOrderResponse response, Object... obj) {
                                    if ("0".equals(response.getServerNo())) {
                                        Logger.i("提交订单结果：" + response);
                                        createOrderResponse = response;
                                        //订单提交成功，跳转到待付款界面
                                        Logger.i("提交订单结果：" + createOrderResponse.toString());
                                        Intent obligation = new Intent(CreateOrderActivity.this, OrderInfoActivity.class);
                                        obligation.putExtra("state", "1");
                                        CreateOrderActivity.this.startActivity(obligation);
                                        finish();
                                    } else if ("12".equals(response.getServerNo())) {
                                        showMessage("库存不足");
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
                    LoginActivity.start(this);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && requestCode == MyApplication.SELECT_ADDRESS) {
            pd.show();
            name = data.getStringExtra("name");
            address = data.getStringExtra("address");
            region = data.getStringExtra("region");
            phone = data.getStringExtra("phone");

            if (!isHava) {
                noAddress.setVisibility(View.GONE);
            }
            tv_address_areaaddress.setVisibility(View.VISIBLE);
            tv_address_areaaddress.setText(region + address);
            tv_address_phone.setVisibility(View.VISIBLE);
            tv_address_phone.setText(phone);
            tv_address_name.setVisibility(View.VISIBLE);
            tv_address_name.setText(name);
            pd.dismiss();
        } else {
            initAddress();
        }

    }
}
