package net.anchong.app.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.GoodsShowParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GoodsINfoResultModel;
import net.anchong.app.entity.response.model.GoodsParamResponseModel;
import net.anchong.app.entity.response.model.GoodsShowResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.SystemBarTintManager;
import net.anchong.app.view.GoodsParamView;
import net.anchong.app.view.OrderEditTextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 选择商品属性的界面，要求页面上部分透明
 * Created by baishixin on 16/4/25.
 */
public class GoodsSetParamActivity extends Activity {

    /**
     * 数据请求部分
     */
    private String goods_id;
    private int goodsNum = 1;
    //根据不同的商品类别请求商品详情，sb用来拼接不同的商品类别，用空格隔开
    public static StringBuffer sb = new StringBuffer();
    public static String[] selectTags;
    private GoodsShowResponseModel goodsShowResponseModel = null;
    /**
     * 数据存储部分
     */
    private GoodsINfoResultModel goodsINfoResultModel = null;
    private GoodsParamResponseModel goodsParamResponseModel = null;
    //所有商品详细信息的集合
    private List<GoodsParamResponseModel.ResultDataEntity> resultDataBeans = new ArrayList<>();
    //所有的商品属性标签
    private List<String> tags = new ArrayList<>();

    /**
     * 数据展示部分
     */
//    @ViewInject(R.id.flow_layout_tags)
//    private FlowLayout flowTags;

    @ViewInject(R.id.ll_goods_setparam_tagsview)
    private LinearLayout tagsView;

    @ViewInject(R.id.iv_goods_setparam_gpic)
    private SimpleDraweeView simpleDraweeView;
    @ViewInject(R.id.tv_goods_setparam_price)
    private TextView price;
    @ViewInject(R.id.tv_goods_setparam_vipprice)
    private TextView vipPrice;
    @ViewInject(R.id.oetv_goods_setparam_number)
    private OrderEditTextView number;


//    public static void start(Context context, String goodsID, GoodsParamResponseModel goodsParamResponseModel, GoodsINfoResultModel goodsINfoResultModel) {
//        Intent intent = new Intent(context, GoodsSetParamActivity.class);
//        intent.putExtra("gid", goodsID);
//        intent.putExtra("response", goodsParamResponseModel);
//        intent.putExtra("goodsInfo", goodsINfoResultModel);
//        context.startActivity(intent);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// fresco
        Fresco.initialize(this);
        setContentView(R.layout.activity_goods_setparam);
        x.view().inject(this);
        getWindow().setGravity(Gravity.BOTTOM);
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth(); // 设置宽度
        getWindow().setAttributes(lp);

        goodsNum = getIntent().getIntExtra("goodsNum", 1);
        goods_id = getIntent().getStringExtra("goods_id");
        goodsParamResponseModel = (GoodsParamResponseModel) getIntent().getSerializableExtra("response");
        goodsINfoResultModel = (GoodsINfoResultModel) getIntent().getSerializableExtra("goodsInfo");
        if (goods_id != null && !"".equals(goods_id) && goodsParamResponseModel != null) {
            parseData();
            initData();
//            initView();
        }
    }

    private void initView() {
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.transparent_title);// 通知栏所需颜色

        simpleDraweeView.setImageURI(Uri.parse(goodsINfoResultModel.getResultData().getImg().toString().trim()));
        price.setText("价格：￥" + goodsINfoResultModel.getResultData().getMarket_price().toString().trim());
        if (MainActivity.isLogin) {
            vipPrice.setText("批量采购价：￥" + goodsINfoResultModel.getResultData().getVip_price().toString().trim());

        } else {
            vipPrice.setText("批量采购价：请登录后查看");

        }
        number.setText(goodsNum + "");

        //将所有的商品属性标签设置到可以自动换行的FlowLayout中。
//        for (int i = 0; i < tags.size(); i++) {
//            TextView tv = new TextView(this);
//            tv.setText(tags.get(i).toString().trim());
//            tv.setTextSize(40);
//            flowTags.addView(tv);
//        }

    }

    /**
     * 请求服务器获取商品属性
     */
    private void initData() {
        //第一次根据默认选中的规格，请求货品详情。

    }

    /**
     * 解析服务器返回的商品规格标签数据
     */
    private void parseData() {
        //解析已经选中的商品类别标签
        String defaultTag = goodsINfoResultModel.getResultData().getGoods_name();

        String[] defaultTags = defaultTag.split(" ");
        selectTags = new String[defaultTags.length];
        for (int j = 0; j < defaultTags.length; j++) {
            selectTags[j] = defaultTags[j];
        }

        if (goodsParamResponseModel != null) {
            if (goodsParamResponseModel.getResultData() != null) {
                resultDataBeans = goodsParamResponseModel.getResultData();
                for (int i = 0; i < resultDataBeans.size(); i++) {
                    //组装商品属性标记集合
//                    tags.add(resultDataBeans.get(i).getGoods_name().toString().trim());
                    GoodsParamView goodsParamView = new GoodsParamView(this);
                    goodsParamView.setData(resultDataBeans.get(i), defaultTags[i], i, this);
                    tagsView.addView(goodsParamView);
                }
            }
        }
        initView();
    }

    /**
     * 通过商品类别更新商品数据
     *
     * @param tag
     * @param index
     */
    public void updateByTags(String tag, int index) {
        if (selectTags != null && selectTags.length > 0) {
            selectTags[index] = tag;
        }
        StringBuffer sb = getStringBuffer();
        GoodsShowParamModel goodsShowParamModel = new GoodsShowParamModel(goodsINfoResultModel.getResultData().getGoods_id(), sb.toString());
        String json = new Gson().toJson(goodsShowParamModel);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            //TODO:或者这里直接跳转到登录界面
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsShowParamModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            String signature = ACRequestUtils.getMD5(MyApplication.GOODSSHOW + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.GOODSSHOW)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(GoodsShowResponseModel.class, new CommonCallback<GoodsShowResponseModel>() {
                        @Override
                        public void onSuccess(GoodsShowResponseModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                goodsShowResponseModel = response;
                                updateUI();
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
            showMessage("请先登录");
            return;
        }


//        if(goodsShowResponseModel!=null){
//            Logger.i("请求成功");
//        }


    }

    @NonNull
    private StringBuffer getStringBuffer() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < selectTags.length; i++) {
            sb.append(selectTags[i]);
            if (i != selectTags.length - 1) {
                sb.append(" ");
            }
        }
        return sb;
    }

    private void updateUI() {
        if (goodsShowResponseModel != null) {
            simpleDraweeView.setImageURI(Uri.parse(goodsShowResponseModel.getResultData().getGoods_img()));
            price.setText("价格：￥" + goodsShowResponseModel.getResultData().getMarket_price().toString().trim());
            vipPrice.setText("批量采购价：￥" + goodsShowResponseModel.getResultData().getVip_price().toString().trim());
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置点击其他区域，退出当前Activity
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        setDataResult();
        finish();
        return super.onTouchEvent(event);
    }

    /**
     * 返回选择结果给 GoodsInfomationActivity
     */
    private void setDataResult() {
        Intent intent = new Intent();

        String goodsType = getStringBuffer().toString();
        intent.putExtra("goodsType", goodsType);

        int goodsNum = number.getText();
        intent.putExtra("goodsNum", goodsNum);
        if (goodsShowResponseModel != null) {
            intent.putExtra("gid", goodsShowResponseModel.getResultData().getGid());
            intent.putExtra("title", goodsShowResponseModel.getResultData().getTitle());
//            intent.putExtra("goods_id", goods_id);
        }

        setResult(RESULT_OK, intent);
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
}
