package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatButton;
import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.EditAddressParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.UpdateAddressParam;
import net.anchong.app.entity.response.model.EditAddressResponseModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;
import net.anchong.app.view.SelectPicPopupWindow;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * 编辑修改收货地址的界面
 * Created by baishixin on 16/4/21.
 */
public class EditAddressActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    /**
     * 视图展示部分
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_basicinfo_title)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.ll_edit_address_area)
    private LinearLayout area;

    /**
     * 请求数据获取组件
     */
    @ViewInject(R.id.et_editaddress_name)
    private EditText mEditText_name;
    @ViewInject(R.id.et_editaddress_phone)
    private EditText mEditText_phone;
    @ViewInject(R.id.et_editaddress_area)
    private TextView mTextView_area;
    @ViewInject(R.id.et_edit_address)
    private EditText mEditText_address;
    @ViewInject(R.id.btn_add_address)
    private FlatButton btn_add;

    /**
     * 数据存储部分
     */
    private EditAddressResponseModel responseModel = null;

    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;


    public static void start(Context context) {
        Intent intent = new Intent(context, EditAddressActivity.class);
        context.startActivity(intent);
    }


    /**
     * 数据请求部分
     */
    private String aid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_edit);
        x.view().inject(this);

        Intent intent = getIntent();
        aid = intent.getStringExtra("aid");


        //初始化数据
        initData();
        //初始化视图
//        initView();
    }

    /**
     * 初始化视图组件
     */
    private void initView() {
        mGeneralTitleBarView.setData("编辑收货地址", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        area.setOnClickListener(this);
        btn_add.setOnClickListener(this);

        //为组件赋值
        mEditText_name.setText(responseModel.getResultData().getMessage().getAdd_name().toString().trim());
        mEditText_phone.setText(responseModel.getResultData().getMessage().getPhone().toString().trim());
        mTextView_area.setText(responseModel.getResultData().getMessage().getRegion().toString().trim());
        mEditText_address.setText(responseModel.getResultData().getMessage().getAddress().toString().trim());

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
            case R.id.ll_edit_address_area:
                //显示窗口
                menuWindow = new SelectPicPopupWindow(EditAddressActivity.this, this);
                //设置layout在PopupWindow中显示的位置
                menuWindow.showAtLocation(area, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.btn_add_address:
                updateAddress();
                break;
        }
    }

    /**
     * 提交数据
     */
    private void updateAddress() {
        //检查数据是否合法
        String name = mEditText_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showMessage("请填写收货人");
            return;
        }
        String phone = mEditText_phone.getText().toString().trim();
        if (TextUtils.isEmpty(phone)) {
            showMessage("请填写联系电话");
            return;
        }
        String area = mTextView_area.getText().toString().trim();
        if (TextUtils.isEmpty(area)) {
            showMessage("请选择所在地区");
            return;
        }
        String address = mEditText_address.getText().toString().trim();
        if (TextUtils.isEmpty(address)) {
            showMessage("请填写详细地址");
            return;
        }

        //数据检查完成，可以提交数据
        UpdateAddressParam updateAddressParam = new UpdateAddressParam(aid, area, name, phone, address);
        RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), updateAddressParam);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String json = new Gson().toJson(updateAddressParam);
        String signature = ACRequestUtils.getMD5(MyApplication.USERUPDATEADDRESS + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.USERUPDATEADDRESS)
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
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });
    }

    private void showMessage(String msg) {
        Toast.makeText(EditAddressActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setArea(String area) {
        mTextView_area.setText(area.toString().trim());
    }
}
