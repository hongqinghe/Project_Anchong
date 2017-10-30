package net.anchong.app.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;

import net.anchong.app.R;
import net.anchong.app.fragment.SaleingFragment;
import net.anchong.app.fragment.WareHouseFragment;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 店家商品管理界面
 * Created by baishixin on 16/5/24.
 */
public class ShopGoodsManagerActivity extends FragmentActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {
    /**
     * 界面展示
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.gtbv_shop_goods_manager)
    private GeneralTitleBarView mGeneralTitleBarView;

    /**
     * 点击事件
     */
    //售出中选项卡按钮
    @ViewInject(R.id.ll_goods_manager_saleing)
    private LinearLayout tabSaleing;
    @ViewInject(R.id.view_line_saleing)
    private View lineSaleing;
    //仓库中选项卡按钮
    @ViewInject(R.id.ll_goods_manager_warehouse)
    private LinearLayout tabWareHouse;
    @ViewInject(R.id.view_line_warehouse)
    private View lineWareHouse;

    /**
     * 数据展示
     */
    private Fragment mContent;
    private SaleingFragment saleingFragment;
    private WareHouseFragment wareHouseFragment;

    /**
     *
     */

    public static void start(Context context) {
        Intent intent = new Intent(context, ShopGoodsManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_shop_goods_manager);
        x.view().inject(this);
        saleingFragment = new SaleingFragment();
        wareHouseFragment = new WareHouseFragment();
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
        mGeneralTitleBarView.setData("商品管理", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        tabSaleing.setOnClickListener(this);
        tabWareHouse.setOnClickListener(this);
        switchConent(saleingFragment);
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
//        pd.dismiss();
        mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_order, fragment).commit();
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
            case R.id.ll_goods_manager_saleing:
                lineSaleing.setVisibility(View.VISIBLE);
                lineWareHouse.setVisibility(View.INVISIBLE);
                switchConent(saleingFragment);
                break;
            case R.id.ll_goods_manager_warehouse:
                lineSaleing.setVisibility(View.INVISIBLE);
                lineWareHouse.setVisibility(View.VISIBLE);
                switchConent(wareHouseFragment);
                break;
        }
    }
}
