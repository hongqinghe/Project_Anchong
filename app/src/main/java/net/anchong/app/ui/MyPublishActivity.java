package net.anchong.app.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.cengalabs.flatui.views.FlatEditText;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.MyBusinessRequestModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.TypeTagResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.oss.utils.ImageService;
import net.anchong.app.oss.utils.OssService;
import net.anchong.app.oss.utils.STSGetter;
import net.anchong.app.oss.utils.UIDisplayer;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.AppUtils;
import net.anchong.app.uitls.ImageUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 商机发布
 * Created by baishixin on 16/4/11.
 */
public class MyPublishActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnKeyListener, View.OnClickListener {

    /**
     * 拍照上传头像
     */
    private static final int GET_IMAGE_VIA_CAMERA = 3;
    /**
     * 相册选取头像
     */
    private static final int CHOSE_IMAGE_VIA_CAMERA = 4;
    private static final int UPDATA_IMAGE = 5;

    @ViewInject(R.id.gtvv_mypublish_title)
    private GeneralTitleBarView generalTitleBarView;

    @ViewInject(R.id.et_mypublish_title)
    private FlatEditText mEditText_title;
    @ViewInject(R.id.et_mypublish_type)
    private FlatEditText mEditText_type;
    @ViewInject(R.id.et_mypublish_tag)
    private FlatEditText mEditText_tag;
    @ViewInject(R.id.et_mypublish_content)
    private FlatEditText mEditText_content;

    //自定义标签  最多六个
    @ViewInject(R.id.et_mypublish_tags1)
    private FlatEditText mEditText_tag1;
    @ViewInject(R.id.et_mypublish_tags2)
    private FlatEditText mEditText_tag2;
    @ViewInject(R.id.et_mypublish_tags3)
    private FlatEditText mEditText_tag3;
    @ViewInject(R.id.et_mypublish_tags4)
    private FlatEditText mEditText_tag4;
    @ViewInject(R.id.et_mypublish_tags5)
    private FlatEditText mEditText_tag5;
    @ViewInject(R.id.et_mypublish_tags6)
    private FlatEditText mEditText_tag6;

    //六个图片
    @ViewInject(R.id.sdv_mypublish_pic1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_mypublish_pic2)
    private SimpleDraweeView pic2;
    @ViewInject(R.id.sdv_mypublish_pic3)
    private SimpleDraweeView pic3;
    @ViewInject(R.id.sdv_mypublish_pic4)
    private SimpleDraweeView pic4;
    @ViewInject(R.id.sdv_mypublish_pic5)
    private SimpleDraweeView pic5;
    @ViewInject(R.id.sdv_mypublish_pic6)
    private SimpleDraweeView pic6;

    //请求时的自定义标签字符串
    private StringBuffer requestTagsStr = new StringBuffer();
    //存储图片地址的集合
    private List<String> pics = new ArrayList<>();
    //记录更新图片的标记变量
    private int imgFlag = -1;
    //图片的文件名
    private String localTempImgFileName = "";

    private TypeTagResponseModel typeTagResponseModel = null;

    private String[] types = null;
    private String[] tags = null;

    private ProgressDialog pd = null;
    //负责所有的界面更新
    private UIDisplayer UIDisplayer;

    //OSS的上传下载
    private OssService ossService;
    private ImageService imageService;
    private String picturePath = "";

    //缩放图片
    private File tempImg = null;


    public static void start(Context context) {
        Intent intent = new Intent(context, MyPublishActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updataUI();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// fresco
        Fresco.initialize(this);
        setContentView(R.layout.layout_activity_mypublish);
        x.view().inject(this);
        initData();
//        initView();
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        UIDisplayer = new UIDisplayer(pd, this);
        ossService = initOSS(MyApplication.ENDPOINT, "anchongres", UIDisplayer);
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


    private void initData() {
        RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", null);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.TYPETAG + requestModel.getTime() + requestModel.getGuid() + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.TYPETAG)
                .addParams("time", requestModel.getTime() + "")
                .addParams("version", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("guid", requestModel.getGuid())
                .addParams("signature", signature)
                .build()
                .execute(TypeTagResponseModel.class, new CommonCallback<TypeTagResponseModel>() {
                    @Override
                    public void onSuccess(TypeTagResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            typeTagResponseModel = response;
                            loadData();
                            initView();
                            initEvent();
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
    }

    private void initEvent() {

    }

    private void loadData() {
        if (typeTagResponseModel != null) {
            types = new String[typeTagResponseModel.getResultData().size()];
            for (int i = 0; i < types.length; i++) {
                types[i] = typeTagResponseModel.getResultData().get(i).getType();
            }
        }
    }

    private void loadTags(int index) {
        if (typeTagResponseModel != null) {
            TypeTagResponseModel.ResultDataEntity resultDataEntity = typeTagResponseModel.getResultData().get(index);
            List<String> datas = resultDataEntity.getTag();
            tags = new String[datas.size()];
            for (int i = 0; i < tags.length; i++) {
                tags[i] = datas.get(i);
            }
        }
    }

    private void initView() {
        generalTitleBarView.setData("我的发布", "保存");
        generalTitleBarView.setGeneralTitleBarOnclickListener(this);


//        customEditText = new FlatEditText(this,)


        mEditText_type.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // 实例化建造者
                    AlertDialog.Builder builder = new AlertDialog.Builder(MyPublishActivity.this);
                    builder.setItems(types,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    mEditText_type.setText(types[which].toString());
                                    loadTags(which);
                                    mEditText_tag.setText(tags[0]);
                                }
                            });
                    builder.show();
                }
                return false;
            }
        });
        mEditText_tag.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (tags == null) {
                        showMessage("请先选择发布类别");
                    } else {
                        // 实例化建造者
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyPublishActivity.this);
                        builder.setItems(tags,
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        mEditText_tag.setText(tags[which].toString());
                                    }
                                });
                        builder.show();
                    }
                }
                return false;
            }
        });

        mEditText_tag1.setOnKeyListener(this);
        mEditText_tag2.setOnKeyListener(this);
        mEditText_tag3.setOnKeyListener(this);
        mEditText_tag4.setOnKeyListener(this);
        mEditText_tag5.setOnKeyListener(this);
        mEditText_tag6.setOnKeyListener(this);

        pic1.setOnClickListener(this);
        pic2.setOnClickListener(this);
        pic3.setOnClickListener(this);
        pic4.setOnClickListener(this);
        pic5.setOnClickListener(this);
        pic6.setOnClickListener(this);

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        String title = mEditText_title.getText().toString().trim();
        if (TextUtils.isEmpty(title)) {
            showMessage("请填写标题");
            return;
        }
        String type = mEditText_type.getText().toString().trim();
        if (TextUtils.isEmpty(type)) {
            showMessage("请选择发布类型");
            return;
        }
        String tag = mEditText_tag.getText().toString().trim();
        if (TextUtils.isEmpty(type)) {
            showMessage("请选择工程类型");
            return;
        }
        String content = mEditText_content.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            showMessage("请填写内容");
            return;
        }
        if (pics.size() == 0) {
            showMessage("请选择图片");
            return;
        }
        int iType = -1;
        switch (type) {
            case "发布工程":
                iType = 1;
                break;
            case "承接工程":
                iType = 2;
                break;
            case "发布人才":
                iType = 3;
                break;
            case "招聘人才":
                iType = 4;
                break;
        }
        MyBusinessRequestModel myBusinessRequestModel = new MyBusinessRequestModel(iType, title, content, tag, requestTagsStr.toString(), pics);
        String requsetJson = new Gson().toJson(myBusinessRequestModel);
        Logger.i("请求的参数：" + requsetJson);
        releaseBusiness(myBusinessRequestModel);
    }

    private void releaseBusiness(MyBusinessRequestModel myBusinessRequestModel) {

        RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", myBusinessRequestModel);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String json = new Gson().toJson(myBusinessRequestModel);
        String signature = ACRequestUtils.getMD5(MyApplication.MYBUSINESSRELEASE + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);
        pd.setTitle("商机发布中...");
        pd.show();
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.MYBUSINESSRELEASE)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(TypeTagResponseModel.class, new CommonCallback<TypeTagResponseModel>() {
                    @Override
                    public void onSuccess(TypeTagResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            pd.dismiss();
                            finish();
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
    }

    private void showMessage(String msg) {
        Toast.makeText(MyPublishActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == MotionEvent.ACTION_DOWN) {
            switch (v.getId()) {
                case R.id.et_mypublish_tags1:
                    mEditText_tag2.setVisibility(View.VISIBLE);
                    mEditText_tag2.setFocusable(true);
                    //将自定义标签添加到 StringBuffer 中
                    requestTagsStr.append(mEditText_tag1.getText().toString().trim());
                    break;
                case R.id.et_mypublish_tags2:
                    mEditText_tag3.setVisibility(View.VISIBLE);
                    mEditText_tag3.setFocusable(true);
                    //将自定义标签添加到 StringBuffer 中
                    requestTagsStr.append(" ").append(mEditText_tag2.getText().toString().trim());
                    break;
                case R.id.et_mypublish_tags3:
                    mEditText_tag4.setVisibility(View.VISIBLE);
                    mEditText_tag4.setFocusable(true);
                    //将自定义标签添加到 StringBuffer 中
                    requestTagsStr.append(" ").append(mEditText_tag3.getText().toString().trim());
                    break;
                case R.id.et_mypublish_tags4:
                    mEditText_tag5.setVisibility(View.VISIBLE);
                    mEditText_tag5.setFocusable(true);
                    //将自定义标签添加到 StringBuffer 中
                    requestTagsStr.append(" ").append(mEditText_tag4.getText().toString().trim());
                    break;
                case R.id.et_mypublish_tags5:
                    mEditText_tag6.setVisibility(View.VISIBLE);
                    mEditText_tag6.setFocusable(true);
                    //将自定义标签添加到 StringBuffer 中
                    requestTagsStr.append(" ").append(mEditText_tag5.getText().toString().trim());
                    break;
                case R.id.et_mypublish_tags6:
                    //将自定义标签添加到 StringBuffer 中
                    requestTagsStr.append(" ").append(mEditText_tag6.getText().toString().trim());
                    break;
            }
        }

        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdv_mypublish_pic1:
                imgFlag = 1;
                getImage();
                break;
            case R.id.sdv_mypublish_pic2:
                imgFlag = 2;
                getImage();
                break;
            case R.id.sdv_mypublish_pic3:
                imgFlag = 3;
                getImage();
                break;
            case R.id.sdv_mypublish_pic4:
                imgFlag = 4;
                getImage();
                break;
            case R.id.sdv_mypublish_pic5:
                imgFlag = 5;
                getImage();
                break;
            case R.id.sdv_mypublish_pic6:
                imgFlag = 6;
                getImage();
                break;
        }
    }

    private void getImage() {
        AlertDialog.Builder headpicBuilder = new AlertDialog.Builder(this);
        headpicBuilder.setTitle("上传图片");
        final String[] items = {"拍照", "选择照片"};
        headpicBuilder.setItems(items,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                String status = Environment.getExternalStorageState();
                                if (status.equals(Environment.MEDIA_MOUNTED)) {
                                    try {
                                        File dir = new File(Environment.getExternalStorageDirectory() + "/anchong");
                                        if (!dir.exists()) dir.mkdirs();
                                        localTempImgFileName = System.currentTimeMillis() + ".jpg";
                                        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                        File f = new File(dir, localTempImgFileName);//localTempImgDir和localTempImageFileName是自己定义的名字
                                        Uri u = Uri.fromFile(f);
                                        intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                        intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
                                        startActivityForResult(intent, GET_IMAGE_VIA_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        showMessage("没有找到存储目录");
                                    }
                                } else {
                                    showMessage("没有存储卡");
                                }
                                break;
                            case 1:
                                Intent intent2 = new Intent();
                                intent2.setAction("android.intent.action.PICK");
                                intent2.setType("image/*");
                                startActivityForResult(intent2, CHOSE_IMAGE_VIA_CAMERA);
                                break;
                        }
                    }
                });
        headpicBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        headpicBuilder.show();
    }

    public void updataUI() {
        if (tempImg == null) {

        } else {
            String ur = MyApplication.BUSINESS_IMG_URL_HEAD + tempImg.getName();
            Uri uri = AppUtils.parse(ur);
            switch (imgFlag) {
                case 1:
                    pic1.setImageURI(uri);
                    pic2.setVisibility(View.VISIBLE);
                    if (pics.size() > 0) {
                        pics.remove(0);
                        pics.add(0, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 2:
                    pic2.setImageURI(uri);
                    pic3.setVisibility(View.VISIBLE);
                    if (pics.size() > 1) {
                        pics.remove(1);
                        pics.add(1, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 3:
                    pic3.setImageURI(uri);
                    pic4.setVisibility(View.VISIBLE);
                    if (pics.size() > 2) {
                        pics.remove(2);
                        pics.add(2, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 4:
                    pic4.setImageURI(uri);
                    pic5.setVisibility(View.VISIBLE);
                    if (pics.size() > 3) {
                        pics.remove(3);
                        pics.add(3, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 5:
                    pic5.setImageURI(uri);
                    pic6.setVisibility(View.VISIBLE);
                    if (pics.size() > 4) {
                        pics.remove(4);
                        pics.add(4, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
                case 6:
                    pic6.setImageURI(uri);
                    if (pics.size() > 5) {
                        pics.remove(5);
                        pics.add(5, ur);
                    } else {
                        pics.add(ur);
                    }
                    break;
            }
            imgFlag = -1;
            tempImg = null;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            File img = null;
            switch (requestCode) {
                case GET_IMAGE_VIA_CAMERA:
                    img = new File(Environment.getExternalStorageDirectory()
                            + "/anchong" + "/" + localTempImgFileName);
                    try {
                        Uri u =
                                Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),
                                        img.getAbsolutePath(), null, null));
                        //u就是拍摄获得的原始图片的uri，剩下的你想干神马坏事请便……
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
                case CHOSE_IMAGE_VIA_CAMERA:
                    //获取intent中返回的bitmap 的 uri
                    Uri uri = data.getData();
                    if (uri == null) {

                    } else {
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(uri,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        img = new File(picturePath);
                    }
                    break;
            }
            if (img != null) {
                tempImg = ImageUtils.scal(img);
                ossService.asyncPutImage(MyApplication.BUSINESS_IMG_DIR + tempImg.getName(), tempImg.getAbsolutePath());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }
}
