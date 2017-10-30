package net.anchong.app.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.EditAddressParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.ShopSoperationRequest;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.entity.response.model.SendCompanyResponse;
import net.anchong.app.entity.response.model.ShopOrderResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;


/**
 * 店铺手动发货界面
 * Created by baishixin on 16/5/25.
 */
public class ShopSendOutActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    /**
     * 界面展示
     */
    @ViewInject(R.id.gtbv_shop_send_out)
    private GeneralTitleBarView mGeneralTitleBarView;
    //物流公司
    @ViewInject(R.id.tv_shop_send_out_company)
    private TextView company;
    //快递单号
    @ViewInject(R.id.tv_shop_send_out_logistcsnum)
    private EditText logistcsnum;
    //订单编号
    @ViewInject(R.id.tv_shop_order_num)
    private TextView orderNum;
    //创建日期
    @ViewInject(R.id.tv_shop_order_create_at)
    private TextView createAt;
    //收货人姓名
    @ViewInject(R.id.tv_shop_order_name)
    private TextView orderName;
    //收货人电话
    @ViewInject(R.id.tv_shop_order_phone)
    private TextView orderPhone;
    //收货地址
    @ViewInject(R.id.tv_shop_order_address)
    private TextView orderAddress;


    /**
     * 数据存储
     */
    private ShopOrderResponse.ResultDataEntity.Order order;
    private SendCompanyResponse sendCompanyResponse;
    private String[] companys;
    private ProgressDialog pd;
    /**
     * 点击事件
     */
    //点击选择物流公司
    @ViewInject(R.id.ll_activity_shop_send_company)
    private LinearLayout ll_company;
    //确认发货按钮
    @ViewInject(R.id.tv_shop_send_out_ok)
    private TextView sendOk;

    /**
     *
     */

    public static void start(Context context, ShopOrderResponse.ResultDataEntity.Order order) {
        Intent intent = new Intent(context, ShopSendOutActivity.class);
        intent.putExtra("order", order);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_send_out);
        order = (ShopOrderResponse.ResultDataEntity.Order) getIntent().getSerializableExtra("order");
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        initData();
//        initView();
    }

    private void initData() {
        //数据检查完成，可以提交数据
        EditAddressParamModel editAddressParamModel = new EditAddressParamModel("");
        String requestJson = new Gson().toJson(editAddressParamModel);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            pd.show();
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", editAddressParamModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            String signature = ACRequestUtils.getMD5(MyApplication.SHOPSLOGISTCOMPANY + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.SHOPSLOGISTCOMPANY)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", requestJson)
                    .addParams("signature", signature)
                    .build()
                    .execute(SendCompanyResponse.class, new CommonCallback<SendCompanyResponse>() {
                        @Override
                        public void onSuccess(SendCompanyResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                pd.dismiss();
                                sendCompanyResponse = response;
                                parseCompany();
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

    private void parseCompany() {
        if (sendCompanyResponse != null) {
            companys = new String[sendCompanyResponse.getResultData().size()];
            for (int i = 0; i < sendCompanyResponse.getResultData().size(); i++) {
                companys[i] = sendCompanyResponse.getResultData().get(i);
            }
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mGeneralTitleBarView.setData("手动发货", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        ll_company.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 实例化建造者
                AlertDialog.Builder builder = new AlertDialog.Builder(ShopSendOutActivity.this);
                builder.setItems(companys,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                company.setText(companys[which].toString());
                            }
                        });
                builder.show();
            }

        });
        sendOk.setOnClickListener(this);
        if (order != null) {


            //设置订单编号
            if (!TextUtils.isEmpty(order.getOrder_num())) {
                orderNum.setText(order.getOrder_num());
            } else {
                orderNum.setText("");
            }

            //设置创建日期
            if (!TextUtils.isEmpty(order.getCreated_at())) {
                createAt.setText(order.getCreated_at());
            } else {
                createAt.setText("");
            }
            //设置收货人
            if (!TextUtils.isEmpty(order.getName())) {
                orderName.setText(order.getName());
            } else {
                orderName.setText("");
            }
            //设置收货电话
            if (!TextUtils.isEmpty(order.getPhone())) {
                orderPhone.setText(order.getPhone());
            } else {
                orderPhone.setText("");
            }
            //设置收货地址
            if (!TextUtils.isEmpty(order.getAddress())) {
                orderAddress.setText(order.getAddress());
            } else {
                orderAddress.setText("");
            }


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
            case R.id.tv_shop_send_out_ok:
                String strCompany = company.getText().toString().trim();
                if (TextUtils.isEmpty(strCompany) || "选择物流".equals(strCompany)) {
                    showMessage("请选择物流公司");
                    return;
                }

                String strCompanyNum = logistcsnum.getText().toString().trim();
                if (TextUtils.isEmpty(strCompanyNum)) {
                    showMessage("请填写运单号");
                    return;
                }

                String order_id = order.getOrder_id() + "";
                String order_num = order.getOrder_num();


                ShopSoperationRequest shopSoperationRequest = new ShopSoperationRequest(order_id, order_num, "2", strCompanyNum, strCompany);
                sendOk(shopSoperationRequest);

                Logger.i("物流公司：" + strCompanyNum);
                break;
        }
    }

    private void sendOk(ShopSoperationRequest shopSoperationRequest) {
        //判断用户是否登录
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
                            if ("0".equals(response.getServerNo())) {
                                pd.dismiss();
                                ResponseErrorModel responseErrorModel = response;
                                showMessage(responseErrorModel.getResultData().getMessage());
                                finish();
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

    }
}


