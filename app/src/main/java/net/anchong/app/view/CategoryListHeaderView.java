package net.anchong.app.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.GoodsTwoThreeParam;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GoodsAdvertResponse;
import net.anchong.app.entity.response.model.GoodsTwoThreeResponse;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.okhttputils.OkHttpUtils;
import net.anchong.app.okhttputils.callback.StringCallback;
import net.anchong.app.ui.GoodsListPagerActivity;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.JsonParseUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * Created by baishixin on 16/5/6.
 */
public class CategoryListHeaderView extends LinearLayout implements View.OnClickListener {

    private Context context;

    @ViewInject(R.id.riv_index_listview_header_rotation)
    private RotationImageView rotationImageView;


    @ViewInject(R.id.view_goods_one)
    private GoodsOneView oneView;
    @ViewInject(R.id.view_goods_two)
    private GoodsTwoView twoView;
    @ViewInject(R.id.view_goods_three)
    private GoodsThreeView threeView;
    @ViewInject(R.id.view_goods_four)
    private GoodsFourView fourView;
    @ViewInject(R.id.view_goods_five)
    private GoodsFiveView fiveView;
    @ViewInject(R.id.view_goods_six)
    private GoodsSixView sixView;

    //商城一级分类 八个按钮
    @ViewInject(R.id.ll_categorylist_intelligent_gate)
    private LinearLayout intelligent_gate;
    @ViewInject(R.id.ll_categorylist_video_surveillance)
    private LinearLayout video_surveillance;
    @ViewInject(R.id.ll_categorylist_alert)
    private LinearLayout alert;
    @ViewInject(R.id.ll_categorylist_polling)
    private LinearLayout polling;
    @ViewInject(R.id.ll_categorylist_parking_management)
    private LinearLayout parking_management;
    @ViewInject(R.id.ll_categorylist_building_talkback)
    private LinearLayout building_talkback;
    @ViewInject(R.id.ll_categorylist_dining_system)
    private LinearLayout dining_system;
    @ViewInject(R.id.ll_categorylist_security)
    private LinearLayout security;


    public CategoryListHeaderView(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public CategoryListHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public CategoryListHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.view_categorylist_header, this);
        x.view().inject(this);

        intelligent_gate.setOnClickListener(this);
        video_surveillance.setOnClickListener(this);
        alert.setOnClickListener(this);
        polling.setOnClickListener(this);
        parking_management.setOnClickListener(this);
        building_talkback.setOnClickListener(this);
        dining_system.setOnClickListener(this);
        security.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_categorylist_intelligent_gate:
                getTwoCategory("1");
                break;
            case R.id.ll_categorylist_video_surveillance:
                getTwoCategory("2");
                break;
            case R.id.ll_categorylist_alert:
                getTwoCategory("3");
                break;
            case R.id.ll_categorylist_polling:
                getTwoCategory("4");
                break;
            case R.id.ll_categorylist_parking_management:
                getTwoCategory("5");
                break;
            case R.id.ll_categorylist_building_talkback:
                getTwoCategory("6");
                break;
            case R.id.ll_categorylist_dining_system:
                getTwoCategory("7");
                break;
            case R.id.ll_categorylist_security:
                getTwoCategory("8");
                break;
        }
    }


    public void setUIData(GoodsAdvertResponse goodsAdvertResponse) {
        int showPrice = Integer.parseInt(goodsAdvertResponse.getResultData().getShowprice());
        if (goodsAdvertResponse != null) {
            rotationImageView.setGoodsAvdData(goodsAdvertResponse.getResultData().getPic());
            oneView.setData(goodsAdvertResponse.getResultData().getOne(), showPrice);
            twoView.setData(goodsAdvertResponse.getResultData().getTwo(), showPrice);
            threeView.setData(goodsAdvertResponse.getResultData().getThree(), showPrice);
            fourView.setData(goodsAdvertResponse.getResultData().getFour(), showPrice);
            fiveView.setData(goodsAdvertResponse.getResultData().getFive(), showPrice);
            sixView.setData(goodsAdvertResponse.getResultData().getSix(), showPrice);
        }
    }


    public void getTwoCategory(final String cid) {
        GoodsTwoThreeParam goodsTwoThreeParam = new GoodsTwoThreeParam(cid);
        String param = new Gson().toJson(goodsTwoThreeParam);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        if (MainActivity.isLogin) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsTwoThreeParam);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", goodsTwoThreeParam);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.GOODSCATINFO + requestModel.getTime() + requestModel.getGuid() + param + copyToken);
        requestModel.setSignature(signature);

        OkHttpUtils
                .post()
                .url(MyApplication.API + MyApplication.GOODSCATINFO)
                .addParams("time", requestModel.getTime() + "")
                .addParams("version", requestModel.getGuid())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", param)
                .addParams("signature", signature)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(context, "网络连接错误，请稍后再试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        String result = JsonParseUtils.getServerNo(response);
                        if (TextUtils.isEmpty(result)) {
                            //TODO:解析错误
                        } else {
                            //返回的状态码为0 代表请求正常
                            if (result.equals("0")) {
                                GoodsTwoThreeResponse goodsTwoThreeResponse = new Gson().fromJson(response, GoodsTwoThreeResponse.class);
                                GoodsListPagerActivity.start(context, goodsTwoThreeResponse.getResultData(), cid);
                            } else {

                                ResponseErrorModel responseErrorModel = new Gson().fromJson(response, ResponseErrorModel.class);
                                Toast.makeText(context, responseErrorModel.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

}
