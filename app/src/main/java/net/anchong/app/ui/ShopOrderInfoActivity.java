package net.anchong.app.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import net.anchong.app.R;
import net.anchong.app.fragment.ShopAllOrderFragment;
import net.anchong.app.fragment.ShopObligationOrderFragment;
import net.anchong.app.fragment.ShopSaleAfterOrderFragment;
import net.anchong.app.fragment.ShopSendOrderFragment;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * 商家订单管理界面
 * Created by baishixin on 16/5/16.
 */
public class ShopOrderInfoActivity extends FragmentActivity implements View.OnClickListener, GeneralTitleBarView.GeneralTitleBarOnclickListener {


    /**
     * 数据请求
     */
    //state : 0为全部1为待支付2为代发货3为待收货4为退款
    private String state = "0";

    /**
     * 数据展示
     */
    @ViewInject(R.id.gtbv_shop_order)
    private GeneralTitleBarView mGeneralTitleBarView;
    private Fragment mContent;
    private ShopAllOrderFragment shopAllOrderFragment;
    private ShopObligationOrderFragment obligationsOrderFragment;
    private ShopSendOrderFragment sendOrderFragment;
    private ShopSaleAfterOrderFragment saleAfterOrderFragment;
    /**
     * 导航按钮
     */
    @ViewInject(R.id.ll_shop_all_order)
    private LinearLayout allOrder;
    @ViewInject(R.id.ll_shop_order_obligations)
    private LinearLayout obligationsOrder;
    @ViewInject(R.id.ll_shop_order_send)
    private LinearLayout sendOrder;
    @ViewInject(R.id.ll_shop_order_sale_after)
    private LinearLayout saleAfterOrder;


    @ViewInject(R.id.view_line_shop_all_order)
    private View line_allOrder;
    @ViewInject(R.id.view_line_shop_obligations_order)
    private View line_obligationsOrder;
    @ViewInject(R.id.view_line_shop_send_order)
    private View line_sendOrder;
    @ViewInject(R.id.view_line_shop_sale_after)
    private View line_saleAfterOrder;


    public static void start(Context context) {
        Intent intent = new Intent(context, ShopOrderInfoActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_shop_order_info);
        x.view().inject(this);


    }

    /**
     * 初始化，获取订单数据
     */
    private void initData() {
        if (!TextUtils.isEmpty(state)) {
            switch (state) {
                case "0":
                    line_allOrder.setVisibility(View.VISIBLE);
                    line_obligationsOrder.setVisibility(View.INVISIBLE);
                    line_sendOrder.setVisibility(View.INVISIBLE);
                    line_saleAfterOrder.setVisibility(View.INVISIBLE);
                    switchConent(shopAllOrderFragment);
                    break;
                case "1":
                    line_allOrder.setVisibility(View.INVISIBLE);
                    line_obligationsOrder.setVisibility(View.VISIBLE);
                    line_sendOrder.setVisibility(View.INVISIBLE);
                    line_saleAfterOrder.setVisibility(View.INVISIBLE);
                    switchConent(obligationsOrderFragment);
                    break;
                case "2":
                    line_allOrder.setVisibility(View.INVISIBLE);
                    line_obligationsOrder.setVisibility(View.INVISIBLE);
                    line_sendOrder.setVisibility(View.VISIBLE);
                    line_saleAfterOrder.setVisibility(View.INVISIBLE);
                    switchConent(sendOrderFragment);
                    break;
                case "3":
                    line_allOrder.setVisibility(View.INVISIBLE);
                    line_obligationsOrder.setVisibility(View.INVISIBLE);
                    line_sendOrder.setVisibility(View.INVISIBLE);
                    line_saleAfterOrder.setVisibility(View.VISIBLE);
                    switchConent(saleAfterOrderFragment);
                    break;
            }
        }
        initView();
    }

    private void initView() {
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.theme);// 通知栏所需颜色
        mGeneralTitleBarView.setData("订单管理", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);


        mContent = new ShopAllOrderFragment();
        shopAllOrderFragment = new ShopAllOrderFragment();
        obligationsOrderFragment = new ShopObligationOrderFragment();
        sendOrderFragment = new ShopSendOrderFragment();
        saleAfterOrderFragment = new ShopSaleAfterOrderFragment();



        state = "0";
        line_allOrder.setVisibility(View.VISIBLE);
        line_obligationsOrder.setVisibility(View.INVISIBLE);
        line_sendOrder.setVisibility(View.INVISIBLE);
        line_saleAfterOrder.setVisibility(View.INVISIBLE);
        switchConent(shopAllOrderFragment);
    }

    private void initEvent() {
        allOrder.setOnClickListener(this);
        obligationsOrder.setOnClickListener(this);
        sendOrder.setOnClickListener(this);
        saleAfterOrder.setOnClickListener(this);
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

    /**
     * 切换Fragment
     *
     * @param fragment
     */
    public void switchConent(Fragment fragment) {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_shop_order, fragment).commit();
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_shop_all_order:
                state = "0";
                line_allOrder.setVisibility(View.VISIBLE);
                line_obligationsOrder.setVisibility(View.INVISIBLE);
                line_sendOrder.setVisibility(View.INVISIBLE);
                line_saleAfterOrder.setVisibility(View.INVISIBLE);
                switchConent(shopAllOrderFragment);
//                initData();
                break;
            case R.id.ll_shop_order_obligations:
                state = "1";
                line_allOrder.setVisibility(View.INVISIBLE);
                line_obligationsOrder.setVisibility(View.VISIBLE);
                line_sendOrder.setVisibility(View.INVISIBLE);
                line_saleAfterOrder.setVisibility(View.INVISIBLE);
                switchConent(obligationsOrderFragment);
//                initData();
                break;
            case R.id.ll_shop_order_send:
                state = "2";
                line_allOrder.setVisibility(View.INVISIBLE);
                line_obligationsOrder.setVisibility(View.INVISIBLE);
                line_sendOrder.setVisibility(View.VISIBLE);
                line_saleAfterOrder.setVisibility(View.INVISIBLE);
                switchConent(sendOrderFragment);
//                initData();
                break;
            case R.id.ll_shop_order_sale_after:
                state = "3";
                line_allOrder.setVisibility(View.INVISIBLE);
                line_obligationsOrder.setVisibility(View.INVISIBLE);
                line_sendOrder.setVisibility(View.INVISIBLE);
                line_saleAfterOrder.setVisibility(View.VISIBLE);
                switchConent(saleAfterOrderFragment);
//                initData();
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
//        initData();
        initEvent();
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
