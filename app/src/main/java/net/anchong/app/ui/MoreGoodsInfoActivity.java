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
import android.widget.Toast;

import net.anchong.app.R;
import net.anchong.app.fragment.DataPicFragment;
import net.anchong.app.fragment.DetailPicFragment;
import net.anchong.app.fragment.ParameterPicFragment;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 商品详情界面，展示三个界面，（商品详情界面，规格参数界面，更多资料界面）
 * Created by baishixin on 16/4/11.
 */
public class MoreGoodsInfoActivity extends FragmentActivity implements View.OnClickListener, GeneralTitleBarView.GeneralTitleBarOnclickListener {

    @ViewInject(R.id.gtvv_publish_title)
    private GeneralTitleBarView mGeneralTitleBarView;
    //四个选项卡
    @ViewInject(R.id.layout_tab_one)
    private LinearLayout one;
    @ViewInject(R.id.layout_tab_two)
    private LinearLayout two;
    @ViewInject(R.id.layout_tab_three)
    private LinearLayout three;

    private Fragment mContent;
    //展示内容的四个Fragment
    private DetailPicFragment detailPicFragment;
    private ParameterPicFragment parameterPicFragment;
    private DataPicFragment dataPicFragment;

    /**
     * 启动
     *
     * @param context
     */
    public static void start(Context context) {
        Intent intent = new Intent(context, MoreGoodsInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initView();
        initEvent();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void initEvent() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
    }

    private void initView() {
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.theme);// 通知栏所需颜色
        mGeneralTitleBarView.setData("详细参数", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        detailPicFragment = new DetailPicFragment();
        parameterPicFragment = new ParameterPicFragment();
        dataPicFragment = new DataPicFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, detailPicFragment).commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_moreinfo);
        x.view().inject(this);
    }

    @Override
    public void leftClick() {
        finish();
    }


    @Override
    public void rightClick() {
        MyPublishActivity.start(this);
    }

    public void showMessage(String msg) {
        Toast.makeText(MoreGoodsInfoActivity.this, msg, Toast.LENGTH_SHORT).show();
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_tab_one:
                detailPicFragment = new DetailPicFragment();
                switchConent(detailPicFragment);
                break;
            case R.id.layout_tab_two:
                switchConent(parameterPicFragment);
                break;
            case R.id.layout_tab_three:
                switchConent(dataPicFragment);
                break;
        }
    }

    /**
     * 切换Fragment
     *
     * @param fragment
     */
    public void switchConent(Fragment fragment) {
        mContent = fragment;
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment).commit();
    }
}










