package net.anchong.app.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.LoginParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.response.model.LoginResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.FileUtils;
import net.anchong.app.view.TransparentTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * Created by baishixin on 16/3/24.
 */
public class LoginActivity extends Activity implements TransparentTitleBarView.TransparentTitleBarOnclickListener, View.OnClickListener {
    private static final int ERROR_MESSAGE = 1;
    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_activity_login)
    private TransparentTitleBarView mGeneralTitleBarView;

    //用户未登录需要的组件
    @ViewInject(R.id.btn_register)
    private Button btn_register;
    @ViewInject(R.id.btn_login)
    private Button btn_login;
    @ViewInject(R.id.et_login_username)
    private EditText userName;
    @ViewInject(R.id.et_login_password)
    private EditText password;
    @ViewInject(R.id.tv_login_forgetpassword)
    private TextView tv_forgetPass;

    private String phone;
    private String pass;

    public static void start(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        setContentView(R.layout.layout_fragment_login);
        x.view().inject(this);
        //初始化数据
        //初始化视图
        initView();

    }

    /**
     * 初始化视图组件
     */
    private void initView() {
        mGeneralTitleBarView.setData("登录", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_forgetPass.setOnClickListener(this);

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
            //打开注册界面
            case R.id.btn_register:
                RegisterActivity.start(this);
//                finish();
                break;
            //处理登录请求
            case R.id.btn_login:
                if (phoneIsOK()) {
                    //实例化登录是的 param 参数请求对象
                    final LoginParamModel loginParamModel = new LoginParamModel(pass, phone);
                    login(loginParamModel);
                } else {
                    Toast.makeText(LoginActivity.this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            //忘记密码按钮
            case R.id.tv_login_forgetpassword:
                ForgetPassActivity.start(this);
                break;
        }
    }

    private void login(final LoginParamModel loginParamModel) {
        //请求对象
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", loginParamModel);
        String signature = ACRequestUtils.getMD5(MyApplication.LOGIN + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(loginParamModel) + "anchongnet");
        indexRequestModel.setSignature(signature);
        Log.i("signature", "login: "+signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.LOGIN)
                .addParams("time", indexRequestModel.getTime() + "")
                .addParams("version", indexRequestModel.getVersion() + "")
                .addParams("guid", indexRequestModel.getGuid() + "")
                .addParams("param", new Gson().toJson(indexRequestModel.getParam()))
                .addParams("signature", indexRequestModel.getSignature())
                .build()
                .execute(LoginResponseModel.class, new CommonCallback<LoginResponseModel>() {
                    @Override
                    public void onSuccess(LoginResponseModel response, Object... obj) {
                        Log.i("loginactivity", "onSuccess: "+response.toString());
                        if ("0".equals(response.getServerNo())) {
                            LoginResponseModel loginResponseModel = response;
                            FileUtils.saveLoginResponse(loginResponseModel, LoginActivity.this);
                            FileUtils.savePhoneInfo(loginParamModel, LoginActivity.this);
//                            initUserMessage();
                            MainActivity.loginResponseModel = loginResponseModel;
                            MainActivity.isLogin = true;
                            //数据是使用Intent返回
                            Intent intent = new Intent();
                            //把返回数据存入Intent
                            intent.putExtra("result", true);
                            //设置返回数据
                            LoginActivity.this.setResult(RESULT_OK, intent);
                            //关闭Activity
                            LoginActivity.this.finish();
                        } else {
                            showMessage(getString(R.string.request_error_msg));
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Log.i("loginactivity", "onError: "+e.toString());
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });
    }

    //TODO:连接服务器，拉取会员信息表，填充界面。暂时是填充数据，
    private void initUserMessage() {
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
                        if ("0".equals(response.getServerNo())) {
                            GetUserMessageResponseModel getUserMessageResponseModel = response;
                            FileUtils.saveUserMessage(getUserMessageResponseModel, LoginActivity.this);

                        } else {
                            Toast.makeText(LoginActivity.this, "登录已失效，请重新登录", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(LoginActivity.this, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
    }

    private void showMessage(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    private boolean phoneIsOK() {
        phone = userName.getText().toString().trim();
        pass = password.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass)) {
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
