package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import net.anchong.app.entity.request.model.AddAddressParam;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.http.domain.Product;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;
import net.anchong.app.view.SelectPicPopupWindow;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * Created by baishixin on 16/3/18.
 */
public class AddressActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener {

    /**
     * 页面展示部分
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_basicinfo_title)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.ll_add_address_area)
    private LinearLayout area;

    /**
     * 请求数据获取组件
     */
    @ViewInject(R.id.et_address_name)
    private EditText mEditText_name;
    @ViewInject(R.id.et_address_phone)
    private EditText mEditText_phone;
    @ViewInject(R.id.et_address_area)
    private TextView mTextView_area;
    @ViewInject(R.id.et_xiangxi_address)
    private EditText mEditText_address;
    @ViewInject(R.id.btn_add_address)
    private FlatButton btn_add;

    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddressActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_address);
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
        mGeneralTitleBarView.setData("新建收货地址", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        area.setOnClickListener(this);
        btn_add.setOnClickListener(this);
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
            case R.id.ll_add_address_area:
                //显示窗口
                menuWindow = new SelectPicPopupWindow(AddressActivity.this, this);
                //设置layout在PopupWindow中显示的位置
                menuWindow.showAtLocation(area, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.btn_add_address:
                addAddress();
                break;
        }
    }

    /**
     * 提交数据
     */
    private void addAddress() {
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
        AddAddressParam addAddressParam = new AddAddressParam(area, name, phone, address);
        RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), addAddressParam);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String json = new Gson().toJson(addAddressParam);
        String signature = ACRequestUtils.getMD5(MyApplication.USERSTOREADDRESS + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);

        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.USERSTOREADDRESS)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(Product.class, new CommonCallback<Product>() {
                    @Override
                    public void onSuccess(Product response, Object... obj) {
                        Log.e("MainActivity", response.toString());

                        if ("0".equals(response.getServerNo())) {
                            finish();
                        } else {
                            showMessage("网络不稳定，请重试");
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                        showMessage("网络不稳定，请重试");
                    }
                });
    }

    private void showMessage(String msg) {
        Toast.makeText(AddressActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setArea(String area) {
        mTextView_area.setText(area.toString().trim());
    }
}
