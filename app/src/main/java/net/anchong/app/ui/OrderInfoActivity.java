package net.anchong.app.ui;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.OrderPayInfoRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.OrderPayInfoResponse;
import net.anchong.app.fragment.AllOrderFragment;
import net.anchong.app.fragment.ObligationsOrderFragment;
import net.anchong.app.fragment.ReceiveOrderFragment;
import net.anchong.app.fragment.SaleAfterOrderFragment;
import net.anchong.app.fragment.SendOrderFragment;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.ALiPayUtils;
import net.anchong.app.uitls.PayResult;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import okhttp3.Call;


/**
 * Created by baishixin on 16/5/16.
 */
public class OrderInfoActivity extends FragmentActivity implements View.OnClickListener, GeneralTitleBarView.GeneralTitleBarOnclickListener {


    /**
     * 数据请求
     */
    //state : 0为全部1为待支付2为代发货3为待收货4为退款
    private String state = "-1";
    //分页页码，默认第一页
    private String page = "1";

    private static final int SDK_PAY_FLAG = 1432;

    /**
     * 数据展示
     */
    @ViewInject(R.id.gtbv_order)
    private GeneralTitleBarView mGeneralTitleBarView;
    private Fragment mContent;
    private AllOrderFragment allOrderFragment;
    private ObligationsOrderFragment obligationsOrderFragment;
    private SendOrderFragment sendOrderFragment;
    private ReceiveOrderFragment receiveOrderFragment;
    private SaleAfterOrderFragment saleAfterOrderFragment;
    /**
     * 导航按钮
     */
    @ViewInject(R.id.ll_all_order)
    private LinearLayout allOrder;
    @ViewInject(R.id.ll_order_obligations)
    private LinearLayout obligationsOrder;
    @ViewInject(R.id.ll_order_send)
    private LinearLayout sendOrder;
    @ViewInject(R.id.ll_order_receive)
    private LinearLayout receiveOrder;
    @ViewInject(R.id.ll_order_sale_after)
    private LinearLayout saleAfterOrder;


    @ViewInject(R.id.view_line_all_order)
    private View line_allOrder;
    @ViewInject(R.id.view_line_obligations_order)
    private View line_obligationsOrder;
    @ViewInject(R.id.view_line_send_order)
    private View line_sendOrder;
    @ViewInject(R.id.view_line_receive_order)
    private View line_receiveOrder;
    @ViewInject(R.id.view_line_sale_after)
    private View line_saleAfterOrder;


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        state = "2";
                        initData();
                        Toast.makeText(OrderInfoActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        //如果支付成功，切换到待发货界面
                    } else {
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(OrderInfoActivity.this, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else if (TextUtils.equals(resultStatus, "6001")) {
                            Toast.makeText(OrderInfoActivity.this, "用户取消支付", Toast.LENGTH_SHORT).show();
                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(OrderInfoActivity.this, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };
    
    /**
     * @param savedInstanceState
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_order_info);
        x.view().inject(this);
        Intent intent = getIntent();
        state = intent.getStringExtra("state");
        mContent = new AllOrderFragment();
        allOrderFragment = new AllOrderFragment();
        obligationsOrderFragment = new ObligationsOrderFragment();
        sendOrderFragment = new SendOrderFragment();
        receiveOrderFragment = new ReceiveOrderFragment();
        saleAfterOrderFragment = new SaleAfterOrderFragment();
        initData();
    }

    /**
     * 初始化，获取订单数据
     */
    private void initData() {
//        pd.show();
        if (!TextUtils.isEmpty(state)) {
            switch (state) {
                case "0":
                    line_allOrder.setVisibility(View.VISIBLE);
                    line_obligationsOrder.setVisibility(View.INVISIBLE);
                    line_sendOrder.setVisibility(View.INVISIBLE);
                    line_receiveOrder.setVisibility(View.INVISIBLE);
                    line_saleAfterOrder.setVisibility(View.INVISIBLE);
                    switchConent(allOrderFragment);
                    break;
                case "1":
                    line_allOrder.setVisibility(View.INVISIBLE);
                    line_obligationsOrder.setVisibility(View.VISIBLE);
                    line_sendOrder.setVisibility(View.INVISIBLE);
                    line_receiveOrder.setVisibility(View.INVISIBLE);
                    line_saleAfterOrder.setVisibility(View.INVISIBLE);
                    switchConent(obligationsOrderFragment);
                    break;
                case "2":
                    line_allOrder.setVisibility(View.INVISIBLE);
                    line_obligationsOrder.setVisibility(View.INVISIBLE);
                    line_sendOrder.setVisibility(View.VISIBLE);
                    line_receiveOrder.setVisibility(View.INVISIBLE);
                    line_saleAfterOrder.setVisibility(View.INVISIBLE);
                    switchConent(sendOrderFragment);
                    break;
                case "3":
                    line_allOrder.setVisibility(View.INVISIBLE);
                    line_obligationsOrder.setVisibility(View.INVISIBLE);
                    line_sendOrder.setVisibility(View.INVISIBLE);
                    line_receiveOrder.setVisibility(View.VISIBLE);
                    line_saleAfterOrder.setVisibility(View.INVISIBLE);
                    switchConent(receiveOrderFragment);
                    break;
                case "4":
                    line_allOrder.setVisibility(View.INVISIBLE);
                    line_obligationsOrder.setVisibility(View.INVISIBLE);
                    line_sendOrder.setVisibility(View.INVISIBLE);
                    line_receiveOrder.setVisibility(View.INVISIBLE);
                    line_saleAfterOrder.setVisibility(View.VISIBLE);
                    switchConent(saleAfterOrderFragment);
                    break;
            }
        }
        initView();
//        requestOrder();

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

    private void initView() {
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        mGeneralTitleBarView.setData("我的订单", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.theme);// 通知栏所需颜色
        allOrder.setOnClickListener(this);
        obligationsOrder.setOnClickListener(this);
        sendOrder.setOnClickListener(this);
        receiveOrder.setOnClickListener(this);
        saleAfterOrder.setOnClickListener(this);
    }


    /**
     * 切换Fragment
     *
     * @param fragment
     */
    public void switchConent(Fragment fragment) {
//        pd.dismiss();
        mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_order, fragment).commitAllowingStateLoss();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fl_order, fragment).commit();
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_all_order:
                state = "0";
                initData();
                break;
            case R.id.ll_order_obligations:
                state = "1";
                initData();
                break;
            case R.id.ll_order_send:
                state = "2";
                initData();
                break;
            case R.id.ll_order_receive:
                state = "3";
                initData();
                break;
            case R.id.ll_order_sale_after:
                state = "4";
                initData();
                break;


        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    /**
     * 支付之前首先获取订单支付信息。
     */
    public void getOrderPayInfo(String order_id, String body, String total_price) {
        //判断用户是否登录
        if (MainActivity.isLogin) {
            OrderPayInfoRequest orderPayInfoRequest = new OrderPayInfoRequest(order_id, body, total_price);
            String json = new Gson().toJson(orderPayInfoRequest);
            RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", orderPayInfoRequest);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

            String signature = ACRequestUtils.getMD5(MyApplication.ORDERORDERPAY + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.ORDERORDERPAY)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(OrderPayInfoResponse.class, new CommonCallback<OrderPayInfoResponse>() {
                        @Override
                        public void onSuccess(OrderPayInfoResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                payOrder(response);
                            } else {
                                showMessage(getString(R.string.order_pay_info_request_error));
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


    public void payOrder(OrderPayInfoResponse response) {


        //获取支付信息


//        if (TextUtils.isEmpty(PARTNER) extUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
//            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialoginterface, int i) {
//                            //
////                            finish();
//                        }
//                    }).show();
//            return;
//        }
        String orderInfo = ALiPayUtils.getOrderInfo(response.getResultData().getSubject(),
                response.getResultData().getBody(),
                response.getResultData().getTotalFee(),
                response.getResultData().getOutTradeNo());

        /**
         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
         */
        String sign = ALiPayUtils.sign(orderInfo);
        try {
            /**
             * 仅需对sign 做URL编码
             */
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         * 完整的符合支付宝参数规范的订单信息
         */
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + ALiPayUtils.getSignType();

        Logger.i("支付信息：" + payInfo);


        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(OrderInfoActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();


    }
}
