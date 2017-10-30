package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatEditText;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.isseiaoki.simplecropview.util.Utils;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.CommunityReleaseRequest;
import net.anchong.app.entity.request.model.EditAddressParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.EditAddressResponseModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.oss.utils.ImageService;
import net.anchong.app.oss.utils.OssService;
import net.anchong.app.oss.utils.STSGetter;
import net.anchong.app.oss.utils.UIDisplayer;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.AppUtils;
import net.anchong.app.uitls.ImageUtils;
import net.anchong.app.view.SelectPicPopupWindow;
import net.anchong.app.view.WhiteGeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 发布聊聊界面
 * Created by baishixin on 16/4/21.
 */
public class CommnuityActivity extends BaseActivity implements WhiteGeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    /**
     * 拍照上传头像
     */
    private static final int GET_IMAGE_VIA_CAMERA = 3;
    /**
     * 相册选取头像
     */
    private static final int CHOSE_IMAGE_VIA_CAMERA = 4;
    /**
     * 相册选取头像
     */
    public static final int CROP_IMAGE = 5;


    /**
     * 视图展示部分
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.wgtvv_commnuity_title)
    private WhiteGeneralTitleBarView titleBarView;

    /**
     * 请求数据获取组件
     */
    //聊聊标题
    @ViewInject(R.id.et_community_release_title)
    private EditText title;
    //三个聊聊标签按钮    闲聊、问问、活动
    @ViewInject(R.id.tv_select_release_xianliao)
    private TextView xianliao;
    @ViewInject(R.id.tv_select_release_wenwen)
    private TextView wenwen;
    @ViewInject(R.id.tv_select_release_huodong)
    private TextView huodong;

    //聊聊正文
    @ViewInject(R.id.et_community_release_content)
    private FlatEditText content;

    @ViewInject(R.id.ll_iv1)
    private LinearLayout iv1;
    @ViewInject(R.id.ll_iv2)
    private LinearLayout iv2;


    //最多六张图片
    @ViewInject(R.id.sdv_community_release_pic1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_community_release_pic2)
    private SimpleDraweeView pic2;
    @ViewInject(R.id.sdv_community_release_pic3)
    private SimpleDraweeView pic3;
    @ViewInject(R.id.sdv_community_release_pic4)
    private SimpleDraweeView pic4;
    @ViewInject(R.id.sdv_community_release_pic5)
    private SimpleDraweeView pic5;
    @ViewInject(R.id.sdv_community_release_pic6)
    private SimpleDraweeView pic6;

    //添加照片按钮
    @ViewInject(R.id.ll_community_add_pic)
    private LinearLayout addPic;
    //发布聊聊
    @ViewInject(R.id.btn_community_release)
    private FlatButton release;

    private ProgressDialog pd = null;

    /**
     * 数据存储部分
     */
    private EditAddressResponseModel responseModel = null;

    //图片的文件名
    private String localTempImgFileName = "";
    //负责所有的界面更新
    private UIDisplayer UIDisplayer;

    //OSS的上传下载
    private OssService ossService;
    private ImageService imageService;
    private String picturePath = "";
    //存储图片地址的集合
    private List<String> pics = new ArrayList<>();
    //记录更新图片的标记变量
    private int imgFlag = -1;
    //缩放图片
    private File tempImg = null;

    private String tags = "闲聊";

    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;


    public static void start(Context context) {
        Intent intent = new Intent(context, CommnuityActivity.class);
        context.startActivity(intent);
    }

    //初始化一个OssService用来上传下载
    public OssService initOSS(String endpoint, String bucket, UIDisplayer displayer) {
        //使用自己的获取STSToken的类
        OSSCredentialProvider credentialProvider = new STSGetter();

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
        return new OssService(oss, bucket, displayer);
    }


    /**
     * 数据请求部分
     */
    private String aid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commnuity_release);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.tab_bg);// 通知栏所需颜色
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        UIDisplayer = new UIDisplayer(pd, this);
        ossService = initOSS(MyApplication.ENDPOINT, "anchongres", UIDisplayer);
        //初始化数据
//        initData();
        //初始化视图
//        initView();
        initEvent();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        titleBarView.setData("发布聊聊", "");
        titleBarView.setGeneralTitleBarOnclickListener(this);
        addPic.setOnClickListener(this);

        xianliao.setOnClickListener(this);
        wenwen.setOnClickListener(this);
        huodong.setOnClickListener(this);
        release.setOnClickListener(this);
    }

    /**
     * 初始化视图组件
     */
    private void initView() {
    }

    /**
     * 获取需要修改的收货地址信息
     */
    private void initData() {
        //数据检查完成，可以提交数据
        EditAddressParamModel editAddressParamModel = new EditAddressParamModel(aid);
        RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), editAddressParamModel);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String json = new Gson().toJson(editAddressParamModel);
        String signature = ACRequestUtils.getMD5(MyApplication.USEREDITADDRESS + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.USEREDITADDRESS)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(EditAddressResponseModel.class, new CommonCallback<EditAddressResponseModel>() {
                    @Override
                    public void onSuccess(EditAddressResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            responseModel = response;
                            initView();
                        } else {
                            showMessage(response.getResultData().toString());
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                        showMessage("网络不稳定，请重试");
                    }
                });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击添加照片
            case R.id.ll_community_add_pic:
                if (pics.size() >= 6) {
//                    showMessage("最多只能上传6张");
                } else {
                    getImage();
                }
                break;
            case R.id.tv_select_release_xianliao:
                tags = "闲聊";
                xianliao.setBackgroundResource(R.drawable.text_white_gb_red_font);
                wenwen.setBackgroundResource(R.drawable.text_rount_default);
                huodong.setBackgroundResource(R.drawable.text_rount_default);
                break;
            case R.id.tv_select_release_wenwen:
                tags = "问问";
                xianliao.setBackgroundResource(R.drawable.text_rount_default);
                wenwen.setBackgroundResource(R.drawable.text_white_gb_red_font);
                huodong.setBackgroundResource(R.drawable.text_rount_default);
                break;
            case R.id.tv_select_release_huodong:
                tags = "活动";
                xianliao.setBackgroundResource(R.drawable.text_rount_default);
                wenwen.setBackgroundResource(R.drawable.text_rount_default);
                huodong.setBackgroundResource(R.drawable.text_white_gb_red_font);
                break;
            case R.id.btn_community_release:
                updateAddress();
                break;
        }
    }

    /**
     * 提交数据
     */
    private void updateAddress() {
        //检查数据是否合法
        String titleName = title.getText().toString().trim();
        if (TextUtils.isEmpty(titleName)) {
            showMessage("请填写聊聊标题");
            return;
        }
        Logger.i("聊聊标题：" + titleName);
        String textContent = content.getText().toString().trim();
        if (TextUtils.isEmpty(textContent)) {
            showMessage("请填聊聊内容");
            return;
        }
        Logger.i("聊聊内容：" + textContent);
        Logger.i("聊聊标签：" + tags);
        for (int i = 0; i < pics.size(); i++) {
            Logger.i("第" + (i + 1) + "个图片地址：" + pics.get(i));
        }

        //数据检查完成，可以提交数据
        CommunityReleaseRequest communityReleaseRequest = new CommunityReleaseRequest(titleName, textContent, tags, pics);
        RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), communityReleaseRequest);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String json = new Gson().toJson(communityReleaseRequest);
        String signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYRELEASE + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYRELEASE)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                    @Override
                    public void onSuccess(ResponseErrorModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            showMessage(response.getResultData().getMessage());
                            finish();
                        } else {
                            showMessage(response.getResultData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                        showMessage("网络不稳定，请重试");
                    }
                });
    }

    private void getImage() {
        Intent intent2 = new Intent();
        intent2.setAction("android.intent.action.PICK");
        intent2.setType("image/*");
        startActivityForResult(intent2, CHOSE_IMAGE_VIA_CAMERA);
    }

    private void showMessage(String msg) {
        Toast.makeText(CommnuityActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CHOSE_IMAGE_VIA_CAMERA) {
                CropImageActivity.start(this, data.getData());
            }
        }
        if (resultCode == CROP_IMAGE) {
            File img = null;
            Uri uri = data.getData();
            tempUri = data.getData();
            if (uri == null) {

            } else {
                InputStream is = null;
                try {
                    is = getContentResolver().openInputStream(uri);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = Utils.calculateInSampleSize(this, uri, 0);
                options.inJustDecodeBounds = false;
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                try {
                    img = new File(getCacheDir(), System.currentTimeMillis() + ".png");
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(img));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (img != null) {
                tempImg = ImageUtils.scal(img);
                ossService.asyncPutImage(MyApplication.BUSINESS_IMG_DIR + tempImg.getName(), tempImg.getAbsolutePath());
            }
        }
    }

    private Uri tempUri;

    public void updateUI() {
        if (tempImg == null) {

        } else {
            String ur = MyApplication.BUSINESS_IMG_URL_HEAD + tempImg.getName();
            Uri uri = AppUtils.parse(ur);
            int size = pics.size();
            switch (size) {
                case 0:
                    iv1.setVisibility(View.VISIBLE);
                    pic1.setVisibility(View.VISIBLE);
                    pic1.setImageURI(tempUri);
                    if (pics.size() > 0) {
                        pics.remove(0);
                        pics.add(0, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 1:
                    pic2.setImageURI(uri);
                    pic2.setVisibility(View.VISIBLE);
                    if (pics.size() > 1) {
                        pics.remove(1);
                        pics.add(1, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 2:
                    pic3.setImageURI(uri);
                    pic3.setVisibility(View.VISIBLE);
                    if (pics.size() > 2) {
                        pics.remove(2);
                        pics.add(2, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 3:
                    iv2.setVisibility(View.VISIBLE);
                    pic4.setImageURI(uri);
                    pic4.setVisibility(View.VISIBLE);
                    if (pics.size() > 3) {
                        pics.remove(3);
                        pics.add(3, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 4:
                    pic5.setImageURI(uri);
                    pic5.setVisibility(View.VISIBLE);
                    if (pics.size() > 4) {
                        pics.remove(4);
                        pics.add(4, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 5:
                    pic6.setImageURI(uri);
                    pic6.setVisibility(View.VISIBLE);
                    if (pics.size() > 5) {
                        pics.remove(5);
                        pics.add(5, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
            }
//            imgFlag = -1;
            tempImg = null;
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
