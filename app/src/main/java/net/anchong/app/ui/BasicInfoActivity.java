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
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.LoginParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.SetHeadParamModel;
import net.anchong.app.entity.request.model.SetUserMessageModel;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.okhttputils.OkHttpUtils;
import net.anchong.app.okhttputils.callback.StringCallback;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.AppUtils;
import net.anchong.app.uitls.FileUtils;
import net.anchong.app.uitls.JsonParseUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;

import okhttp3.Call;

/**
 * 个人资料的展示界面
 * Created by baishixin on 16/3/18.
 */
public class BasicInfoActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    private static final int UPDATE_UI = 1;
    private static final int UPDATE_HEADPIC = 2;
    /**
     * 拍照上传头像
     */
    private static final int GET_IMAGE_VIA_CAMERA = 3;
    /**
     * 相册选取头像
     */
    private static final int CHOSE_IMAGE_VIA_CAMERA = 4;

    @ViewInject(R.id.btn_fragment_mine_checkout)
    private Button mButton_checkout;

    private static Context mContext;

    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_basicinfo_title)
    private GeneralTitleBarView mGeneralTitleBarView;

    @ViewInject(R.id.sdv_basic_info_headpic)
    private SimpleDraweeView mSimpleDraweeView_headpic;
    @ViewInject(R.id.tv_basic_info_contact)
    private TextView mTextView_contact;
    @ViewInject(R.id.tv_basic_info_account)
    private TextView mTextView_account;
    @ViewInject(R.id.tv_basic_info_nickname)
    private TextView mTextView_nickname;
    @ViewInject(R.id.tv_basic_info_qq)
    private TextView mTextView_qq;
    @ViewInject(R.id.tv_basic_info_email)
    private TextView mTextView_email;


    /**
     * 修改个人资料所需组件
     */
    @ViewInject(R.id.ll_fragment_info_contact)
    private LinearLayout mLinearLayout_contact;
    @ViewInject(R.id.ll_fragment_info_nickname)
    private LinearLayout mLinearLayout_nickname;
    @ViewInject(R.id.ll_fragment_info_qq)
    private LinearLayout mLinearLayout_qq;
    @ViewInject(R.id.ll_fragment_info_email)
    private LinearLayout mLinearLayout_email;
    @ViewInject(R.id.ll_fragment_info_address)
    private LinearLayout mLinearLayout_address;


    private ProgressDialog pd = null;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case UPDATE_UI:
                    GetUserMessageResponseModel getUserMessageResponseModel = (GetUserMessageResponseModel) msg.obj;
                    setData(getUserMessageResponseModel);
                    FileUtils.saveUserMessage(getUserMessageResponseModel, BasicInfoActivity.this);
                    break;
                case UPDATE_HEADPIC:

                    break;
            }
        }
    };
    private String localTempImgFileName;

    public static void start(Context context) {
        mContext = context;
        Intent intent = new Intent(context, BasicInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //初始化数据
        initData();
        //初始化视图
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// fresco
        Fresco.initialize(this);
        setContentView(R.layout.layout_activity_basicinfo);
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
    }


    /**
     * 初始化视图组件
     */
    private void initView() {
        mGeneralTitleBarView.setData("基本资料", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        mSimpleDraweeView_headpic.setOnClickListener(this);
        mLinearLayout_nickname.setOnClickListener(this);
        mLinearLayout_qq.setOnClickListener(this);
        mLinearLayout_email.setOnClickListener(this);
        mLinearLayout_contact.setOnClickListener(this);
        mLinearLayout_address.setOnClickListener(this);
        mButton_checkout.setOnClickListener(this);
        LoginParamModel loginParamModel = FileUtils.getPhoneInfo(this);
        mTextView_contact.setText(loginParamModel.getUsername().toString().trim());
    }

    //TODO:连接服务器，拉取会员信息表，填充界面。暂时是填充数据，
    private void initData() {
        pd.show();
        //请求对象
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", "1.0", MainActivity.loginResponseModel.getResultData().getGuid(), null);
        String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.GETUSERMESSAGE + indexRequestModel.getTime() + indexRequestModel.getGuid() + crypToken);
        indexRequestModel.setSignature(signature);


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
                        pd.dismiss();
                        if ("0".equals(response.getServerNo())) {
                            GetUserMessageResponseModel getUserMessageResponseModel = response;

//                            GetUserMessageResponseModel getUserMessageResponseModel = (GetUserMessageResponseModel) msg.obj;
                            setData(getUserMessageResponseModel);
                            FileUtils.saveUserMessage(getUserMessageResponseModel, BasicInfoActivity.this);
                            initView();

//                            Message msg = Message.obtain();
//                            msg.what = UPDATE_UI;
//                            msg.obj = getUserMessageResponseModel;
//                            handler.sendMessage(msg);
                        } else {
                            Toast.makeText(BasicInfoActivity.this, "登录已失效，请重新登录", Toast.LENGTH_SHORT).show();
                            MainActivity ma = (MainActivity) mContext;
                            ma.checkOut();
                            LoginActivity.start(BasicInfoActivity.this);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(BasicInfoActivity.this, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                        pd.dismiss();
                    }
                });
    }

    /**
     * 点击头像，上传头像事件处理
     *
     * @param v
     */
    public void showMenu(View v) {

    }

    public void setData(GetUserMessageResponseModel getUserMessageResponseModel) {
        Uri uri = AppUtils.parse(getUserMessageResponseModel.getResultData().getHeadpic());
        Logger.i("头像地址：" + getUserMessageResponseModel.getResultData().getHeadpic());
        mSimpleDraweeView_headpic.setImageURI(uri);
        mTextView_contact.setText(getUserMessageResponseModel.getResultData().getContact());
        mTextView_account.setText(FileUtils.getPhoneInfo(BasicInfoActivity.this).getUsername() + "");
        mTextView_nickname.setText(getUserMessageResponseModel.getResultData().getNickname());
        mTextView_qq.setText(getUserMessageResponseModel.getResultData().getQq());
        mTextView_email.setText(getUserMessageResponseModel.getResultData().getEmail());
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        Toast.makeText(this, "保存", Toast.LENGTH_SHORT).show();
    }

    private EditText info;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //退出登录
            case R.id.btn_fragment_mine_checkout:
//                MainActivity ma = (MainActivity) mContext;
//                ma.checkOut();
                checkOut();
                break;
            case R.id.sdv_basic_info_headpic:

                AlertDialog.Builder headpicBuilder = new AlertDialog.Builder(this);
                headpicBuilder.setTitle("修改头像");
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
                                                Toast.makeText(BasicInfoActivity.this, "没有找到储存目录", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(BasicInfoActivity.this, "没有储存卡", Toast.LENGTH_LONG).show();
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
                break;
            case R.id.ll_fragment_info_contact:
                AlertDialog.Builder cbuilder = new AlertDialog.Builder(this);
                cbuilder.setTitle("修改联系人");
                //实例化布局
                final View coontact = getLayoutInflater().inflate(R.layout.layout_user_message, null);
                cbuilder.setView(coontact);

                info = (EditText) coontact.findViewById(R.id.et_user);
                info.setText(mTextView_contact.getText().toString().trim());
                cbuilder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = info.getText().toString();
                        setUserMessage("", "", "", user);
                    }
                });
                cbuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                cbuilder.show();
                break;
            case R.id.ll_fragment_info_nickname:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("修改昵称");
                //实例化布局
                final View view = getLayoutInflater().inflate(R.layout.layout_user_message, null);
                builder.setView(view);

                info = (EditText) view.findViewById(R.id.et_user);
                info.setText(mTextView_nickname.getText().toString().trim());
                builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = info.getText().toString();
                        setUserMessage(user, "", "", "");
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.ll_fragment_info_qq:
                AlertDialog.Builder qqBuilder = new AlertDialog.Builder(this);
                qqBuilder.setTitle("修改邮箱");
                //实例化布局
                final View qq = getLayoutInflater().inflate(R.layout.layout_user_message, null);
                qqBuilder.setView(qq);

                info = (EditText) qq.findViewById(R.id.et_user);
                info.setText(mTextView_qq.getText().toString().trim());
                qqBuilder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = info.getText().toString();
                        setUserMessage("", user, "", "");
                    }
                });
                qqBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                qqBuilder.show();
                break;
            case R.id.ll_fragment_info_email:
                AlertDialog.Builder emailBuilder = new AlertDialog.Builder(this);
                emailBuilder.setTitle("修改邮箱");
                //实例化布局
                final View email = getLayoutInflater().inflate(R.layout.layout_user_message, null);
                emailBuilder.setView(email);

                info = (EditText) email.findViewById(R.id.et_user);
                info.setText(mTextView_email.getText().toString().trim());
                emailBuilder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String user = info.getText().toString();
                        setUserMessage("", "", user, "");
                    }
                });
                emailBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                emailBuilder.show();
                break;
            case R.id.ll_fragment_info_address:
                AddressManagerActivity.start(this);
                break;
        }

    }

    private void setUserMessage(final String nickname, String qq, String email, String contact) {
        //请求对象
        SetUserMessageModel setUserMessageModel = new SetUserMessageModel(nickname, qq, email, contact);
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), setUserMessageModel);
        String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.SETUSERMESSAGE + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(setUserMessageModel) + crypToken);
        indexRequestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.SETUSERMESSAGE)
                .addParams("time", indexRequestModel.getTime() + "")
                .addParams("version", indexRequestModel.getVersion())
                .addParams("guid", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("param", new Gson().toJson(setUserMessageModel))
                .addParams("signature", signature)
                .build()
                .execute(GetUserMessageResponseModel.class, new CommonCallback<GetUserMessageResponseModel>() {
                    @Override
                    public void onSuccess(GetUserMessageResponseModel response, Object... obj) {
                        Log.e("MainActivity", response.toString());
                        if ("0".equals(response.getServerNo())) {
                            initData();
                        } else {
                            Toast.makeText(BasicInfoActivity.this, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(BasicInfoActivity.this, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            Toast.makeText(BasicInfoActivity.this, "获取图片失败，请重试！", Toast.LENGTH_SHORT).show();
        } else {
            if (resultCode == RESULT_OK) {
                File img = null;
                pd.setTitle("上传头像");
                pd.show();
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
                            String picturePath = cursor.getString(columnIndex);
                            cursor.close();

                            img = new File(picturePath);
                        }
                        break;
                }
                if (img != null) {
//                img = ImageUtils.scal(img);
                    final SetHeadParamModel setHeadParamModel = new SetHeadParamModel(img.toString());
                    RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), setHeadParamModel);
                    String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                    String signature = ACRequestUtils.getMD5(MyApplication.SETHEAD + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(setHeadParamModel) + crypToken);
                    indexRequestModel.setSignature(signature);


                    OkHttpUtils.post()//
                            .addFile("headpic", img.getName(), img)//
                            .url(MyApplication.API + MyApplication.SETHEAD)
                            .addParams("time", indexRequestModel.getTime() + "")
                            .addParams("version", indexRequestModel.getVersion())
                            .addParams("guid", MainActivity.loginResponseModel.getResultData().getGuid())
                            .addParams("param", new Gson().toJson(setHeadParamModel))
                            .addParams("signature", signature)
                            .build()//
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e) {
                                    Toast.makeText(BasicInfoActivity.this, "连接超时，请重试", Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                }

                                @Override
                                public void onResponse(String response) {
                                    Logger.i("上传头像：" + response);
                                    String result = JsonParseUtils.getServerNo(response);
                                    if (TextUtils.isEmpty(result)) {
                                        //TODO:解析错误
                                    } else {
                                        //返回的状态码为0 代表请求正常
                                        if (result.equals("0")) {
                                            initData();
                                            pd.dismiss();
                                        } else {
                                            ResponseErrorModel responseErrorModel = new Gson().fromJson(response, ResponseErrorModel.class);
                                            Toast.makeText(BasicInfoActivity.this, responseErrorModel.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                                            pd.dismiss();
                                        }
                                    }
                                }
                            });
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);


    }


    public void checkOut() {
        MainActivity.isLogin = false;
        MainActivity.isFromFile = false;
//        switchConent(mBusinessFragment);
//        resetImg();
//        mProvide.setImageResource(R.drawable.home);
        FileUtils.clearFile(this);
        MainActivity.start(this);
    }
}
