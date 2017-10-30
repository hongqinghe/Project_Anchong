package net.anchong.app.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.BusinessAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.BusinessInfoParamModel;
import net.anchong.app.entity.request.model.BusinessSearchParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.BusinessInfoResponseModel;
import net.anchong.app.entity.response.model.BusinessSearchResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.ui.SearchPopupActivity;
import net.anchong.app.uitls.ACRequestUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/4/18.
 */
public class BusinessChengJieFragment extends BaseFragment implements XListView.IXListViewListener, AdapterView.OnItemClickListener, View.OnClickListener {

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;

    private ProgressDialog pd = null;
    @ViewInject(R.id.xlistview_business)
    private XListView mListView;
    @ViewInject(R.id.bottombar)
    private ViewGroup bottombar;
    private BusinessAdapter adapter;
    //服务类别的筛选按钮
    @ViewInject(R.id.tv_headview_type)
    private TextView type;
    //区域的筛选按钮
    @ViewInject(R.id.tv_headview_tag)
    private TextView tag;


    /**
     * 请求参数部分
     */
    //请求是的筛选标签
    private String strTag = "";
    //当前第几页数据
    private int page = 1;
    private BusinessInfoResponseModel businessInfoResponseModel = null;
    private List<BusinessInfoResponseModel.ResultDataBean.ListBean> listBeans = new ArrayList<>();
    //    private List<FaBaoResponseModel.ResultDataEntity.ListEntity> listEntities = new ArrayList<>();
    //商机分类筛选
    private BusinessSearchResponse businessSearchResponse = null;
    private List<String> types = new ArrayList<>();
    private List<String> areas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /// fresco
        Fresco.initialize(mContext);
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_business_chengjie, container, false);
        x.view().inject(this, view);

        pd = new ProgressDialog(mContext);
        pd.setTitle("数据加载中..");
        initData();
//        initView();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onRefresh() {
        onLoad();
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onLoadMore() {
//        initData();
        initMore();
        adapter.notifyDataSetChanged();
        onLoad();
    }

    private void initData() {
        pd.show();
        page = 1;
        BusinessInfoParamModel businessInfoParamModel = new BusinessInfoParamModel("2", strTag, "", page + "");
        String requestParam = new Gson().toJson(businessInfoParamModel);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", businessInfoParamModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", businessInfoParamModel);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.BUSINESSINFO + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.BUSINESSINFO)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(BusinessInfoResponseModel.class, new CommonCallback<BusinessInfoResponseModel>() {
                    @Override
                    public void onSuccess(BusinessInfoResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            pd.dismiss();
                            businessInfoResponseModel = response;
                            listBeans.clear();
                            if (businessInfoResponseModel.getResultData().getList() != null && businessInfoResponseModel.getResultData().getList().size() > 0) {
                                listBeans.addAll(businessInfoResponseModel.getResultData().getList());
                                page++;
                            }
                            initView();
                        } else {
                            showMessage(getString(R.string.request_error_msg));
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        pd.dismiss();
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });
        /**
         * 请求商机筛选的条件
         */
        BusinessSearchParamModel businessSearchParamModel = new BusinessSearchParamModel("2");
        String searchParam = new Gson().toJson(businessSearchParamModel);
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", businessSearchParamModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", businessSearchParamModel);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        signature = ACRequestUtils.getMD5(MyApplication.BUSINESSSEARCH + requestModel.getTime() + requestModel.getGuid() + searchParam + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.BUSINESSSEARCH)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", searchParam)
                .addParams("signature", signature)
                .build()
                .execute(BusinessSearchResponse.class, new CommonCallback<BusinessSearchResponse>() {
                    @Override
                    public void onSuccess(BusinessSearchResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            businessSearchResponse = response;
                            types = businessSearchResponse.getResultData().getTag();
                            areas = businessSearchResponse.getResultData().getArea();
                        } else {
                            showMessage(getString(R.string.request_error_msg));
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        pd.dismiss();
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });
    }

    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private void initMore() {
        pd.show();
        BusinessInfoParamModel businessInfoParamModel = new BusinessInfoParamModel("2", strTag, "", page + "");
        String requestParam = new Gson().toJson(businessInfoParamModel);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            Logger.i("登录状态");
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", businessInfoParamModel);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            Logger.i("未登录状态");
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", businessInfoParamModel);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.BUSINESSINFO + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.BUSINESSINFO)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(BusinessInfoResponseModel.class, new CommonCallback<BusinessInfoResponseModel>() {
                    @Override
                    public void onSuccess(BusinessInfoResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            pd.dismiss();
                            Logger.i("商机检索类型：" + response);
                            businessInfoResponseModel = response;
                            if (businessInfoResponseModel.getResultData().getList() != null && businessInfoResponseModel.getResultData().getList().size() > 0) {
                                listBeans.addAll(businessInfoResponseModel.getResultData().getList());
                                setData();
                                page++;
                            }
                        } else {
                            showMessage(getString(R.string.request_error_msg));
                        }
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        pd.dismiss();
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });
    }

    private void setData() {
        if (adapter != null) {
            adapter.setList(listBeans);
            adapter.setShowPhone(businessInfoResponseModel.getResultData().getShowphone());
        }
    }

    private void initView() {
        pd.dismiss();
        adapter = new BusinessAdapter(mContext, mInflater, businessInfoResponseModel.getResultData().getShowphone(), listBeans);
        mListView.setAdapter(adapter);
//        mListView.setPullRefreshEnable(true);
        mListView.setPullLoadEnable(true);
        mListView.setXListViewListener(this);
        type.setOnClickListener(this);
        tag.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_headview_type:
                Intent intent = new Intent(getActivity(), SearchPopupActivity.class);
                intent.putExtra("key", "type");
                intent.putExtra("types", (Serializable) types);
                startActivityForResult(intent, Activity.RESULT_FIRST_USER);
                break;
            case R.id.tv_headview_tag:
                Intent intent2 = new Intent(getActivity(), SearchPopupActivity.class);
                intent2.putExtra("key", "area");
                intent2.putExtra("areas", (Serializable) areas);
                startActivityForResult(intent2, Activity.RESULT_FIRST_USER);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Activity.RESULT_FIRST_USER && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                strTag = data.getStringExtra("tag").trim();
                if (strTag != null && !"".equals(strTag)) {
                    initData();
                }
            }
        }
    }
}
