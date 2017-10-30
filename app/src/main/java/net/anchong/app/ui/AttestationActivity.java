package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import net.anchong.app.R;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/3/18.
 */
public class AttestationActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_basicinfo_title)
    private GeneralTitleBarView mGeneralTitleBarView;

    //个体认证的按钮
    @ViewInject(R.id.ll_attestation_entity)
    private LinearLayout mEntity;
    //商家认证的按钮
    @ViewInject(R.id.ll_attestation_shop)
    private LinearLayout mShop;

    public static void start(Context context) {
        Intent intent = new Intent(context, AttestationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_attestation);
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
        mGeneralTitleBarView.setData("商家认证", "保存");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        mEntity.setOnClickListener(this);
        mShop.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_attestation_entity:
                EntityActivity.start(this);
                break;
            case R.id.ll_attestation_shop:
                ShopActivity.start(this);
                break;
        }
    }
}
