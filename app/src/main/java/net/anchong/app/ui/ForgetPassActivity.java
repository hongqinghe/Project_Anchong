package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.PhoneParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.SMSAuthResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * 忘记密码界面
 * Created by baishixin on 16/4/15.
 */
public class ForgetPassActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    @ViewInject(R.id.gtbv_forget_pass)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.et_forget_pass)
    private EditText mEditText_forget;
    @ViewInject(R.id.btn_forget_get)
    private FlatButton btn_forget;

    public static void start(Context context) {
        Intent intent = new Intent(context, ForgetPassActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        x.view().inject(this);
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mGeneralTitleBarView.setData("忘记密码", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        btn_forget.setOnClickListener(this);
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
            //点击验证按钮，想服务器发送请求，如果验证码发送成功，跳转到密码重置界面
            case R.id.btn_forget_get:
                final String number = mEditText_forget.getText().toString().trim();
                if (TextUtils.isEmpty(number)) {
                    showMessage("请填写手机号码");
                    return;
                }
                PhoneParamModel phoneParamModel = new PhoneParamModel("2", number);

                RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", phoneParamModel);
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
                        .execute(SMSAuthResponse.class, new CommonCallback<SMSAuthResponse>() {
                            @Override
                            public void onSuccess(SMSAuthResponse response, Object... obj) {
                                if ("0".equals(response.getServerNo())) {
                                    Logger.i("进来");
                                    Intent intent = new Intent(ForgetPassActivity.this, ResetPasswordActivity.class);
                                    intent.putExtra("phone", number);
                                    startActivity(intent);
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
                break;
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(ForgetPassActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
