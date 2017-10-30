package net.anchong.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.PhoneParamModel;
import net.anchong.app.entity.request.model.RegisterParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.http.domain.ResponseResult;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.TransparentTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * Created by baishixin on 16/3/23.
 */
public class RegisterActivity extends Activity implements TransparentTitleBarView.TransparentTitleBarOnclickListener, View.OnClickListener {
    private static final int START_TIMER = 1;
    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_basicinfo_title)
    private TransparentTitleBarView mGeneralTitleBarView;

    @ViewInject(R.id.et_register_phone)
    private EditText mEditText_phone;
    @ViewInject(R.id.et_register_captcha)
    private EditText mEditText_captcha;
    @ViewInject(R.id.et_register_password)
    private EditText mEditText_password;
    @ViewInject(R.id.btn_register_submit)
    private Button mButton_register_submit;
    @ViewInject(R.id.btn_register_captcha)
    private Button mButton_register_captcha;

    private String phone;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_TIMER:
                    String time = (String) msg.obj;
                    mButton_register_captcha.setText(time + "");
                    break;
            }
        }
    };

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.layout_fragment_register);
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
        mGeneralTitleBarView.setData("用户注册", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        mButton_register_submit.setOnClickListener(this);
        mButton_register_captcha.setOnClickListener(this);


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
            case R.id.btn_register_submit:
                phone = phoneIsOK();

                String password = mEditText_password.getText().toString().trim();
                String captcha = mEditText_captcha.getText().toString().trim();

                if (TextUtils.isEmpty(password) || password.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "请填写登录密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(captcha) || captcha.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {

                } else {
                    RegisterParamModel registerParamModel = new RegisterParamModel(phone, password, captcha);

                    RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", "1.0", "0", registerParamModel);
                    String sss = ACRequestUtils.getMD5(MyApplication.REGISTER + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(registerParamModel) + "anchongnet");

                    indexRequestModel.setSignature(sss);
                    String gg = new Gson().toJson(indexRequestModel);


                    HttpManager.getInstance()
                            .post(MyApplication.API + MyApplication.REGISTER)
                            .addParams("time", indexRequestModel.getTime() + "")
                            .addParams("version", indexRequestModel.getVersion() + "")
                            .addParams("guid", indexRequestModel.getGuid() + "")
                            .addParams("param", new Gson().toJson(indexRequestModel.getParam()))
                            .addParams("signature", indexRequestModel.getSignature())
                            .build()
                            .execute(ResponseResult.class, new CommonCallback<ResponseResult>() {
                                @Override
                                public void onSuccess(ResponseResult response, Object... obj) {
                                    if ("0".equals(response.getServerNo())) {
                                        showMessage(getString(R.string.register_success_msg));
                                        LoginActivity.start(RegisterActivity.this);
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
                break;
            case R.id.btn_register_captcha:
                phone = phoneIsOK();

                String pass = mEditText_password.getText().toString().trim();

                if (TextUtils.isEmpty(pass) || pass.length() <= 0) {
                    Toast.makeText(RegisterActivity.this, "请填写登录密码", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(phone)) {

                } else {
                    PhoneParamModel phoneParamModel = new PhoneParamModel("1", phone);

                    RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", "1.0", "0", phoneParamModel);
                    String sss = ACRequestUtils.getMD5(MyApplication.SMSAUTH + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(phoneParamModel) + "anchongnet");

                    indexRequestModel.setSignature(sss);
                    String gg = new Gson().toJson(indexRequestModel);


                    HttpManager.getInstance()
                            .post(MyApplication.API + MyApplication.SMSAUTH)
                            .addParams("time", indexRequestModel.getTime() + "")
                            .addParams("version", indexRequestModel.getVersion() + "")
                            .addParams("guid", indexRequestModel.getGuid() + "")
                            .addParams("param", new Gson().toJson(indexRequestModel.getParam()))
                            .addParams("signature", indexRequestModel.getSignature())
                            .build()
                            .execute(ResponseResult.class, new CommonCallback<ResponseResult>() {
                                @Override
                                public void onSuccess(ResponseResult response, Object... obj) {
                                    showMessage("验证码发送成功！");
                                    mButton_register_captcha.setClickable(false);
                                    startTimer();
                                }

                                @Override
                                public void onError(Call call, Exception e) {
                                    showMessage(getString(R.string.request_error_msg));
                                    e.printStackTrace();
                                }
                            });
                }
                break;
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void startTimer() {
        new Thread() {
            @Override
            public void run() {
                for (int i = 30; i >= 0; i--) {
                    if (i == 0) {
                        mButton_register_captcha.setClickable(true);
                        Message msg = Message.obtain();
                        msg.what = START_TIMER;
                        msg.obj = "点击获取";
                        handler.sendMessage(msg);
                    } else {
                        try {
                            Message msg = Message.obtain();
                            msg.what = START_TIMER;
                            msg.obj = i + "s";
                            handler.sendMessage(msg);
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();
    }

    private String phoneIsOK() {
        phone = mEditText_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "手机号码或密码不能为空！", Toast.LENGTH_SHORT).show();
            return "";
        }
        return phone;
    }
}







