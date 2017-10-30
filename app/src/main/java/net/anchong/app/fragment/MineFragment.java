package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.MineFragmentAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.EditAddressParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.response.model.ShopCarMountResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.ui.BasicInfoActivity;
import net.anchong.app.ui.CertificationActivity;
import net.anchong.app.ui.LoginActivity;
import net.anchong.app.ui.MyShopActivity;
import net.anchong.app.ui.OrderInfoActivity;
import net.anchong.app.ui.PublishActivity;
import net.anchong.app.ui.RequestShopActivity;
import net.anchong.app.ui.ShopCarActivity;
import net.anchong.app.uitls.ACRequestUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * Created by baishixin on 16/3/8.
 */
public class MineFragment extends BaseFragment implements View.OnClickListener {

    //用户已登录需要的组件
    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;

    @ViewInject(R.id.tv_fragment_mine_nickname)
    private TextView mTextView_mine_nickname;
    @ViewInject(R.id.sdv_fragment_mine_headpic)
    private SimpleDraweeView mSimpleDraweeView_headpic;

    private ProgressDialog pd;

    /**
     * 我的界面选项卡的按钮 目前八个
     */
    /**
     * 我的发布按钮
     */
    @ViewInject(R.id.ll_mine_tab_publish)
    private LinearLayout publish;
    /**
     * 商家认证按钮
     */
    @ViewInject(R.id.ll_mine_tab_identification)
    private LinearLayout identification;
    /**
     * 商铺申请按钮
     */
    @ViewInject(R.id.ll_mine_tab_shops)
    private LinearLayout shops;
    /**
     * 订单按钮
     */
    @ViewInject(R.id.ll_mine_tab_order)
    private LinearLayout order;
    /**
     * 钱袋按钮
     */
    @ViewInject(R.id.ll_mine_tab_money)
    private LinearLayout money;
    /**
     * 购物车按钮
     */
    @ViewInject(R.id.ll_mine_tab_car)
    private RelativeLayout car;
    /**
     * 粉丝按钮
     */
    @ViewInject(R.id.ll_mine_tab_fans)
    private LinearLayout fans;
    /**
     * 联系我们按钮
     */
    @ViewInject(R.id.ll_mine_tab_help)
    private LinearLayout help;
    /**
     * 查看全部订单
     */
    @ViewInject(R.id.ll_tab_allorder)
    private LinearLayout allOrder;
    /**
     * 查看待付款订单
     */
    @ViewInject(R.id.ll_mine_tab_obligation)
    private RelativeLayout obligation;
    /**
     * 查看待发货订单
     */
    @ViewInject(R.id.ll_mine_tab_overhang)
    private RelativeLayout overhang;
    /**
     * 查看待收货订单
     */
    @ViewInject(R.id.ll_mine_tab_receiving)
    private RelativeLayout receiving;
    /**
     * 查看售后订单
     */
    @ViewInject(R.id.ll_mine_tab_after_sales)
    private RelativeLayout afterSales;
    /**
     * 待付款订单数量（红色圆圈TextView）
     */
    @ViewInject(R.id.tv_mine_obligationsOrderNum)
    private TextView mTextView_obligationsOrderNum;
    /**
     * 待发货订单数量（红色圆圈TextView）
     */
    @ViewInject(R.id.tv_mine_sendOrderNum)
    private TextView mTextView_sendOrderNum;
    /**
     * 待收货订单数量（红色圆圈TextView）
     */
    @ViewInject(R.id.tv_mine_receiveOrderNum)
    private TextView mTextView_receiveOrderNum;
    /**
     * 售后状态订单数量（红色圆圈TextView）
     */
    @ViewInject(R.id.tv_mine_afterOrderNum)
    private TextView mTextView_afterOrderNum;
    /**
     * 购物车数量
     */
    @ViewInject(R.id.tv_mine_shopcar_num)
    private TextView mTextView_shopCarNum;
    private int shopCarGoodsNum = 0;
    /**
     * 显示店铺按钮文字的TextView
     */
    @ViewInject(R.id.tv_fragment_mine_shop)
    private TextView mTextView_shopState;

    /**
     * 设计按钮，点击进入个人资料界面
     */
    @ViewInject(R.id.iv_mine_setting)
    private ImageView setting;
    /**
     * 消息按钮，点击查看消息
     */
    @ViewInject(R.id.iv_mine_message)
    private ImageView message;

//    @ViewInject(R.id.btn_fragment_mine_checkout)
//    private Button mButton_checkout;

    private GetUserMessageResponseModel getUserMessageResponseModel;

    private MineFragmentAdapter mMineFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /// fresco
        Fresco.initialize(mContext);
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_mine, container, false);
        SystemBarTintManager tintManager = new SystemBarTintManager((MainActivity) mContext);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.button_bord);// 通知栏所需颜色
        x.view().inject(this, view);
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        pd.show();
        //请求对象
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", "1.0", MainActivity.loginResponseModel.getResultData().getGuid(), null);
        String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.GETUSERMESSAGE + indexRequestModel.getTime() + indexRequestModel.getGuid() + crypToken);
        indexRequestModel.setSignature(signature);
        Logger.i(MainActivity.loginResponseModel.getResultData().getGuid());


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.GETUSERMESSAGE)
                .addParams("time", System.currentTimeMillis() / 1000 + "")
                .addParams("version", "1.0")
                .addParams("guid", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("signature", signature)
                .build()
                .execute(GetUserMessageResponseModel.class, new CommonCallback<GetUserMessageResponseModel>() {
                    @Override
                    public void onSuccess(GetUserMessageResponseModel response, Object... obj) {
                        Log.e("MainActivity", response.toString());
                        if ("0".equals(response.getServerNo())) {
                            Logger.i(response.toString());
                            getUserMessageResponseModel = response;
                            getShopCarNum();
                        } else {
                            Toast.makeText(mContext, "登录已失效，请重新登录", Toast.LENGTH_SHORT).show();
                            MainActivity ma = (MainActivity) mContext;
                            ma.checkOut();
                            LoginActivity.start(mContext);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(mContext, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        pd = new ProgressDialog(mContext);
        pd.setCanceledOnTouchOutside(true);
//        initView();
        initData();
    }

    private void initView() {
        pd.dismiss();
        SystemBarTintManager tintManager = new SystemBarTintManager((MainActivity) mContext);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.button_bord);// 通知栏所需颜色

        if (getUserMessageResponseModel == null) {
            Toast.makeText(mContext, "获取登录信息失败", Toast.LENGTH_SHORT).show();
        } else {
            mTextView_mine_nickname.setText(getUserMessageResponseModel.getResultData().getNickname() + "");
            Uri uri = Uri.parse(getUserMessageResponseModel.getResultData().getHeadpic() + "");
            mSimpleDraweeView_headpic.setImageURI(uri);

            if ("2".equals(getUserMessageResponseModel.getResultData().getShopstatus())) {
                mTextView_shopState.setText("我的店铺");
            }

            //设置待付款数量
            if (getUserMessageResponseModel.getResultData().getWaitforcash() == 0) {
                mTextView_obligationsOrderNum.setVisibility(View.GONE);
            } else {
                mTextView_obligationsOrderNum.setVisibility(View.VISIBLE);
                mTextView_obligationsOrderNum.setText(getUserMessageResponseModel.getResultData().getWaitforcash() + "");
            }
            //设置待发货数量
            if (getUserMessageResponseModel.getResultData().getWaitforsend() == 0) {
                mTextView_sendOrderNum.setVisibility(View.GONE);
            } else {
                mTextView_sendOrderNum.setVisibility(View.VISIBLE);
                mTextView_sendOrderNum.setText(getUserMessageResponseModel.getResultData().getWaitforsend() + "");
            }
            //设置待收货数量
            if (getUserMessageResponseModel.getResultData().getWaitforreceive() == 0) {
                mTextView_receiveOrderNum.setVisibility(View.GONE);
            } else {
                mTextView_receiveOrderNum.setVisibility(View.VISIBLE);
                mTextView_receiveOrderNum.setText(getUserMessageResponseModel.getResultData().getWaitforreceive() + "");
            }
            //设置售后状态订单数量
            if (getUserMessageResponseModel.getResultData().getAftermarket() == 0) {
                mTextView_afterOrderNum.setVisibility(View.GONE);
            } else {
                mTextView_afterOrderNum.setVisibility(View.VISIBLE);
                mTextView_afterOrderNum.setText(getUserMessageResponseModel.getResultData().getAftermarket() + "");
            }
            //设置购物车数量
            if (shopCarGoodsNum == 0) {
                mTextView_shopCarNum.setVisibility(View.GONE);
            } else {
                mTextView_shopCarNum.setVisibility(View.VISIBLE);
                mTextView_shopCarNum.setText(shopCarGoodsNum + "");
            }
        }
        publish.setOnClickListener(this);
        identification.setOnClickListener(this);
        shops.setOnClickListener(this);
        order.setOnClickListener(this);
        money.setOnClickListener(this);
        car.setOnClickListener(this);
        fans.setOnClickListener(this);
        help.setOnClickListener(this);
        setting.setOnClickListener(this);
        message.setOnClickListener(this);
        allOrder.setOnClickListener(this);
        obligation.setOnClickListener(this);
        overhang.setOnClickListener(this);
        receiving.setOnClickListener(this);
        afterSales.setOnClickListener(this);
    }


    //TODO:连接服务器，拉取会员信息表，填充界面。暂时是填充数据，
    private void getUserMessage() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            //我的发布按钮事件处理,进入我的发布界面
            case R.id.ll_mine_tab_publish:
                PublishActivity.start(mContext);
                break;
            //商家认证按钮事件处理，进入商家认证界面
            case R.id.ll_mine_tab_identification:
                CertificationActivity.start(mContext);
                break;
            case R.id.ll_mine_tab_shops:
                startMyShop();
                break;
            case R.id.ll_mine_tab_order:
                showMessage("订单查看功能还未开通，敬请期待后续版本");
                break;
            case R.id.ll_mine_tab_money:
                showMessage("钱袋功能还未开通，敬请期待后续版本");
                break;
            case R.id.ll_mine_tab_car:
                ShopCarActivity.start(mContext);
                break;
            case R.id.ll_mine_tab_fans:
                showMessage("粉丝功能还未开通，敬请期待后续版本");
                break;
            case R.id.ll_mine_tab_help:
                showMessage("我们是安虫！");
                break;
            //设置按钮，点击进入个人资料界面
            case R.id.iv_mine_setting:
                BasicInfoActivity.start(mContext);
                break;
            case R.id.iv_mine_message:
                showMessage("没有更多消息");
                break;
            case R.id.ll_tab_allorder:
                Intent all = new Intent(mContext, OrderInfoActivity.class);
                all.putExtra("state", "0");
                mContext.startActivity(all);
                break;
            case R.id.ll_mine_tab_obligation:
                Intent obligation = new Intent(mContext, OrderInfoActivity.class);
                obligation.putExtra("state", "1");
                mContext.startActivity(obligation);
                break;
            case R.id.ll_mine_tab_overhang:
                Intent send = new Intent(mContext, OrderInfoActivity.class);
                send.putExtra("state", "2");
                mContext.startActivity(send);
                break;
            case R.id.ll_mine_tab_receiving:
                Intent receive = new Intent(mContext, OrderInfoActivity.class);
                receive.putExtra("state", "3");
                mContext.startActivity(receive);
                break;
            case R.id.ll_mine_tab_after_sales:
                Intent saleAfter = new Intent(mContext, OrderInfoActivity.class);
                saleAfter.putExtra("state", "4");
                mContext.startActivity(saleAfter);
                break;

        }
    }

    /**
     * 打开我的商铺
     */
    private void startMyShop() {
        //首先检查用户是否通过商铺审核
        // ("店铺审核状态"："待审核（1）"，"审核已通过（2）"，"审核未通过（3）") (店铺的名称和头像地址只有在审核状态为2（已通过）的时候才会有值，否则为空值)
        String shopState = getUserMessageResponseModel.getResultData().getShopstatus();
        Logger.i("状态：" + shopState);
        if (!TextUtils.isEmpty(shopState)) {

            if ("1".equals(shopState)) {
                showMessage("店铺正在审核中");

            } else if ("2".equals(shopState)) {

                MyShopActivity.start(mContext, getUserMessageResponseModel.getResultData()
                        .getShopname(), getUserMessageResponseModel.getResultData().getShoplogo()
                        , getUserMessageResponseModel.getResultData().getShopid());
            } else {
                RequestShopActivity.start(mContext);
            }
//            switch (shopState) {
//                case "1":
//                    break;
//                case "2":
//                    break;
//                case "3":
//                    showMessage("审核未通过");
//                    break;
//            }
        }
    }

    /**
     * 获取购物车数量
     */
    private void getShopCarNum() {

        //数据检查完成，可以提交数据
        EditAddressParamModel editAddressParamModel = new EditAddressParamModel("");
        String requestJson = new Gson().toJson(editAddressParamModel);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", editAddressParamModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            String signature = ACRequestUtils.getMD5(MyApplication.CARTCARTAMOUNT + requestModel.getTime() + requestModel.getGuid() + requestJson + copyToken);
            requestModel.setSignature(signature);


            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.CARTCARTAMOUNT)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", requestJson)
                    .addParams("signature", signature)
                    .build()
                    .execute(ShopCarMountResponse.class, new CommonCallback<ShopCarMountResponse>() {
                        @Override
                        public void onSuccess(ShopCarMountResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                ShopCarMountResponse shopCarMountResponse = response;
                                shopCarGoodsNum = shopCarMountResponse.getResultData().getCartamount();
                                Logger.i("购物车数量：" + shopCarGoodsNum);
                                initView();
                            } else {
                                pd.dismiss();
                                Toast.makeText(mContext, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            pd.dismiss();
                            Toast.makeText(mContext, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    });
        } else {
            initView();
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
