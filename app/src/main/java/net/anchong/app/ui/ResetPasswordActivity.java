package net.anchong.app.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.google.gson.Gson;

import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.ResetPassParamModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.http.domain.ResponseResult;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * 密码重置界面
 * Created by baishixin on 16/4/15.
 */
public class ResetPasswordActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    @ViewInject(R.id.gtbv_reset_pass)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.et_reset_captcha)
    private EditText mEditText_captcha;
    @ViewInject(R.id.et_reset_pass)
    private EditText mEditText_pass;
    @ViewInject(R.id.et_reset_pass2)
    private EditText mEditText_pass2;
    @ViewInject(R.id.btn_reset_ok)
    private FlatButton btn_reset_ok;

    private String phone = "";

//    public static void start(Context content) {
//        Intent intent = new Intent(content, ResetPasswordActivity.class);
//        content.startActivity(intent);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        x.view().inject(this);
        phone = getIntent().getStringExtra("phone");
        initView();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        mGeneralTitleBarView.setData("重置密码", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        btn_reset_ok.setOnClickListener(this);
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
            case R.id.btn_reset_ok:
                String captcha = mEditText_captcha.getText().toString().trim();
                if (TextUtils.isEmpty(captcha)) {
                    showMessage("请填写验证码");
                    return;
                }
                String pass = mEditText_pass.getText().toString().trim();
                if (TextUtils.isEmpty(pass)) {
                    showMessage("请填写新密码");
                    return;
                }
                String pass2 = mEditText_pass2.getText().toString().trim();
                if (TextUtils.isEmpty(pass)) {
                    showMessage("请填写重复确认密码");
                    return;
                }
                //判断重复密码是否正确
                if (!pass.equals(pass2)) {
                    showMessage("密码与确认密码不一致");
                    return;
                }
                ResetPassParamModel resetPassParamModel = new ResetPassParamModel(phone, pass, captcha);
                RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", resetPassParamModel);
                String sss = ACRequestUtils.getMD5(MyApplication.FORGETPASSWORD + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(resetPassParamModel) + "anchongnet");

                indexRequestModel.setSignature(sss);
                String gg = new Gson().toJson(indexRequestModel);
                HttpManager.getInstance()
                        .post(MyApplication.API + MyApplication.FORGETPASSWORD)
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
                                    showMessage("密码重置成功");
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
        Toast.makeText(ResetPasswordActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
