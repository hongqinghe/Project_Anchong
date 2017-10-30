package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 我的商铺界面
 * Created by baishixin on 16/5/24.
 */
public class MyShopActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    /**
     * 界面展示
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.gtbv_my_shop)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.sdv_my_shop_headpic)
    private SimpleDraweeView headpic;
    @ViewInject(R.id.tv_my_shop_name)
    private TextView shopName;


    /**
     * 数据存储
     */
    //商铺名称
    private String name;
    //商铺头像地址
    private String url;
    //商铺
    private String shopID;

    /**
     * 点击事件
     */
    @ViewInject(R.id.ll_my_shop_commodity_management)
    private LinearLayout commodityManager;
    @ViewInject(R.id.ll_my_shop_product_details)
    private LinearLayout productDetails;
    @ViewInject(R.id.ll_my_shop_settings)
    private LinearLayout settings;


    public static void start(Context context, String name, String url, String shopid) {
        Intent intent = new Intent(context, MyShopActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("url", url);
        intent.putExtra("shopID", shopid);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_shop_my);
        x.view().inject(this);
        name = getIntent().getStringExtra("name");
        url = getIntent().getStringExtra("url");
        shopID = getIntent().getStringExtra("shopID");
        initView();
    }

    private void initView() {
        mGeneralTitleBarView.setData("我的商铺", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        commodityManager.setOnClickListener(this);
        productDetails.setOnClickListener(this);
        settings.setOnClickListener(this);

        if (name != null && !"".equals(name)) {
            shopName.setText(name);
        }
        if (url != null && !"".equals(url)) {
            headpic.setImageURI(Uri.parse(url));
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
            case R.id.ll_my_shop_commodity_management:
                ShopGoodsManagerActivity.start(this);
                break;
            case R.id.ll_my_shop_product_details:
                ShopOrderInfoActivity.start(this);
                break;
            case R.id.ll_my_shop_settings:
                showMessage("设置");
                break;
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
