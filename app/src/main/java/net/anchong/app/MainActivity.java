package net.anchong.app;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.response.model.LoginResponseModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.fragment.BusinessFragment;
import net.anchong.app.fragment.MineFragment;
import net.anchong.app.fragment.SheQuFragment;
import net.anchong.app.fragment.ShopFragment;
import net.anchong.app.okhttputils.OkHttpUtils;
import net.anchong.app.okhttputils.callback.StringCallback;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.ui.LoginActivity;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.FileUtils;
import net.anchong.app.uitls.JsonParseUtils;
import net.anchong.app.view.TitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class MainActivity extends FragmentActivity implements
        android.view.View.OnClickListener, TitleBarView.TitleBarClickListener {

    public static boolean isLogin = false;
    //判断用户登录信息是否来源于文件
    public static boolean isFromFile = false;
    public static LoginResponseModel loginResponseModel = null;

    //    @ViewInject(R.id.titlebar)
//    private TitleBarView mTitleBar;
    private Fragment mContent;
    // 用来存放Tab01-04
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    // 五个Tab，每个Tab包含一个按钮
    //商机
    @ViewInject(R.id.layout_tab_business)
    private LinearLayout mTabProvide;
    //商城
    @ViewInject(R.id.layout_tab_shop)
    private LinearLayout mTabShop;
    //社区
    @ViewInject(R.id.layout_tab_shequ)
    private LinearLayout mTabSheQu;
    //我的
    @ViewInject(R.id.layout_tab_mine)
    private LinearLayout mTabMine;
    // 五个按钮
    @ViewInject(R.id.iv_tab_provide_img)
    private ImageView mProvide;
    @ViewInject(R.id.iv_tab_shop_img)
    private ImageView mShop;
    @ViewInject(R.id.iv_tab_shequ_img)
    private ImageView mSheQu;
    @ViewInject(R.id.iv_tab_mine_img)
    private ImageView mMine;

    //需要显示的fragment
    private BusinessFragment mBusinessFragment;
    private ShopFragment mShopFragment;
    private SheQuFragment mSheQuFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        /// fresco
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        initView();
        x.view().inject(this);
        initData();
        initViewPage();
        initEvent();
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    /**
     * 初始化数据
     */
    private void initData() {

        //检查用户是否已经登录
        this.loginResponseModel = FileUtils.getLoginResponse(this);
        if (loginResponseModel == null) {
            isFromFile = false;
            isLogin = false;
        } else {
            GetUserMessageResponseModel getUserMessageResponseModel = FileUtils.getUserMessage(this);
            if (getUserMessageResponseModel == null) {
                //获取用户已经登录的信息失败

            } else {
//                Toast.makeText(this, getUserMessageResponseModel.toString(), Toast.LENGTH_SHORT).show();
                isFromFile = true;
                createMineFragment();
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
//        initView();
    }

    public void load(View v) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.PICK");
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //为标题栏设计事件监听器
//        mTitleBar.setTitleBarClickListener(this);
        mTabProvide.setOnClickListener(this);
        mTabShop.setOnClickListener(this);
        mTabSheQu.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
    }

    /**
     * 初始化设置 沉浸式状态栏
     */
    private void initView() {
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.theme);// 通知栏所需颜色

        mShopFragment = new ShopFragment();
        mSheQuFragment = new SheQuFragment();
        mBusinessFragment = new BusinessFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, mBusinessFragment).commit();
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
     * 初始化ViewPage
     */
    private void initViewPage() {

    }

    /**
     * 判断哪个要显示，及设置按钮图片
     */
    @Override
    public void onClick(View arg0) {

        switch (arg0.getId()) {
            case R.id.layout_tab_business:
                switchConent(mBusinessFragment);
                resetImg();
                mProvide.setImageResource(R.drawable.home);
                break;
            case R.id.layout_tab_shop:
                switchConent(mShopFragment);
                resetImg();
                mShop.setImageResource(R.drawable.shop);
                break;
            case R.id.layout_tab_shequ:
                switchConent(mSheQuFragment);
                resetImg();
                mSheQu.setImageResource(R.drawable.sq);
                break;
            case R.id.layout_tab_mine:
                if (isLogin) {
                    if (mMineFragment == null) {
                        createMineFragment();
                    }
                    switchConent(mMineFragment);
                    resetImg();
                    mMine.setImageResource(R.drawable.my);
                } else {
                    //未登录状态，跳转登录界面
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivityForResult(intent, 100);
                }
                break;
            default:
                break;
        }
    }

    /**
     * 把所有图片变暗
     */
    private void resetImg() {
        mProvide.setImageResource(R.drawable.home_tab);
        mShop.setImageResource(R.drawable.shop_tab);
        mSheQu.setImageResource(R.drawable.sq_tab);
        mMine.setImageResource(R.drawable.my_tab);
    }

    /**
     * 标题栏左边按钮的单击事件
     */
    @Override
    public void leftClick() {
        //TODO:应该是归属地的选择
        Toast.makeText(this, "左边应该有掌声", Toast.LENGTH_LONG).show();

    }

    /**
     * 标题栏右边按钮的单击事件
     */
    @Override
    public void rightClick() {
        Toast.makeText(this, "我的", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            boolean isTrue = data.getExtras().getBoolean("result");
            if (isTrue) {
                createMineFragment();
            }
        }
    }

    private void createMineFragment() {
        if (isFromFile) {
            mMineFragment = new MineFragment();
            isLogin = true;
//            switchConent(mMineFragment);
//            resetImg();
//            mMine.setImageResource(R.drawable.my);
        } else {
            //请求个人中心的数据
            //请求对象
            RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", "1.0", MainActivity.loginResponseModel.getResultData().getGuid(), null);
            String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            String signature = ACRequestUtils.getMD5(MyApplication.GETUSERMESSAGE + indexRequestModel.getTime() + indexRequestModel.getGuid() + crypToken);
            indexRequestModel.setSignature(signature);

            OkHttpUtils
                    .post()
                    .url(MyApplication.API + MyApplication.GETUSERMESSAGE)
                    .addParams("time", System.currentTimeMillis() / 1000 + "")
                    .addParams("version", "1.0")
                    .addParams("guid", MainActivity.loginResponseModel.getResultData().getGuid())
                    .addParams("signature", signature)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response) {
                            String result = JsonParseUtils.getServerNo(response);
                            if (TextUtils.isEmpty(result)) {
                                //TODO:解析错误
                            } else {
                                //返回的状态码为0 代表请求正常
                                if (result.equals("0")) {
                                    Logger.i("用户信息：" + response);
                                    GetUserMessageResponseModel getUserMessageResponseModel = new Gson().fromJson(response, GetUserMessageResponseModel.class);
                                    //保存用户信息
                                    FileUtils.saveUserMessage(getUserMessageResponseModel, MainActivity.this);
                                    mMineFragment = new MineFragment();
                                    switchConent(mMineFragment);
                                    resetImg();
                                    mMine.setImageResource(R.drawable.my);
                                    isLogin = true;
                                } else {
                                    ResponseErrorModel responseErrorModel = new Gson().fromJson(response, ResponseErrorModel.class);
                                    Toast.makeText(MainActivity.this, responseErrorModel.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });
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
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
    }

    public void checkOut() {
        isLogin = false;
        isFromFile = false;
        switchConent(mBusinessFragment);
        resetImg();
        mProvide.setImageResource(R.drawable.home);
        FileUtils.clearFile(this);
    }

    public void testUpload(View v) {
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/Camera/hah.jpg");
        Toast.makeText(this, "File name = " + file.getName(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, "File path = " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        Toast.makeText(this, file.getPath(), Toast.LENGTH_LONG).show();
        Logger.i("File name = " + file.getName());
        Logger.i("File path = " + file.getAbsolutePath());
    }
}
