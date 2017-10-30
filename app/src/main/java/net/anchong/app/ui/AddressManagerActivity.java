package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.AddressListAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.AddressListResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/4/20.
 */
public class AddressManagerActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, AdapterView.OnItemClickListener {

    /**
     * 视图展示部分
     */
    //页面上方通用的标题栏
    @ViewInject(R.id.gtvv_addressmanager_title)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.lv_address)
    private ListView listView;
    private AddressListAdapter adapter;

    @ViewInject(R.id.fl_empty_view)
    private FrameLayout empty_view;
    @ViewInject(R.id.fl_list)
    private FrameLayout fl_list;
    //加载数据滚动条
    private ProgressDialog pd = null;


    /**
     * 网络请求部分
     */


    /**
     * 数据存储部分
     */
    //存储收货地址的List
    private List<AddressListResponse.ResultDataBean> addressList = new ArrayList<>();


    public static void start(Context context) {
        Intent intent = new Intent(context, AddressManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_manager);
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
//        initData();
    }

    /**
     * 获取用户收货地址列表
     */
    private void initData() {
        pd.show();
        pd.setCanceledOnTouchOutside(false);
        //请求对象
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), null);
        String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.USERADDRESS + indexRequestModel.getTime() + indexRequestModel.getGuid() + crypToken);
        indexRequestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.USERADDRESS)
                .addParams("time", indexRequestModel.getTime())
                .addParams("version", indexRequestModel.getVersion())
                .addParams("guid", indexRequestModel.getGuid())
                .addParams("signature", signature)
                .build()
                .execute(AddressListResponse.class, new CommonCallback<AddressListResponse>() {
                    @Override
                    public void onSuccess(AddressListResponse response, Object... obj) {
                        Log.e("MainActivity", response.toString());
                        pd.dismiss();
                        //返回的状态码为0 代表请求正常
                        if (response.getServerNo().equals("0")) {
                            AddressListResponse addressListResponse = response;
                            addressList = addressListResponse.getResultData();
                            initView();
                        } else {
                            showMessage("网络不稳定，请重试！");
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                        pd.dismiss();
                        showMessage("网络不稳定，请重试！");
                    }
                });
    }

    private void initView() {
        mGeneralTitleBarView.setData("地址管理", "新建");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        if (addressList == null || addressList.size() <= 0) {
            fl_list.setVisibility(View.GONE);
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
            fl_list.setVisibility(View.VISIBLE);
            adapter = new AddressListAdapter(this, getLayoutInflater(), addressList);
            listView.setAdapter(adapter);
        }

    }

    public void selectByPosition(int position) {
        String tag = getIntent().getStringExtra("tag");
        if ("select".equals(tag)) {
            AddressListResponse.ResultDataBean address = addressList.get(position);
            Intent intent = getIntent();
            intent.putExtra("name", address.getAdd_name());
            intent.putExtra("address", address.getAddress());
            intent.putExtra("region", address.getRegion());
            intent.putExtra("phone", address.getPhone());
            setResult(MyApplication.SELECT_ADDRESS, intent);
            finish();
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        AddressActivity.start(this);
    }

    /**
     * 修改默认收货地址之后更新界面
     */
    public void updateDefaultAddress() {

        initData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        AddressListResponse.ResultDataBean address = addressList.get(position);
        Logger.i("收货地址：" + address.getAddress());
        Logger.i("联系人" + address.getAdd_name());
        Logger.i("电话" + address.getPhone());
    }
}
