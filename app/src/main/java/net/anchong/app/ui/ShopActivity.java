package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;

import net.anchong.app.R;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/3/18.
 */
public class ShopActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener {

    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_basicinfo_title)
    private GeneralTitleBarView mGeneralTitleBarView;

    public static void start(Context context) {
        Intent intent = new Intent(context, ShopActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// fresco
        Fresco.initialize(this);
        setContentView(R.layout.layout_activity_shop);
        x.view().inject(this);
        //初始化数据
        initData();
        //初始化视图
        initView();

    }

    /**
     * 初始化视图组件
     */
    private void initView() {
        mGeneralTitleBarView.setData("企业", "保存");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
    }

    //TODO:连接服务器，拉取会员信息表，填充界面。暂时是填充数据，
    private void initData() {

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
    }
}
