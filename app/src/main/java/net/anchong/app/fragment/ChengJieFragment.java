package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.FaBaoAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.MybusinessinfoRequestParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.FaBaoResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 承接过程的Fragment
 * Created by baishixin on 16/3/8.
 */
public class ChengJieFragment extends BaseFragment implements XListView.IXListViewListener {

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;

    private ProgressDialog pd = null;
    //当前第几页数据
    private int page = 1;
    private FaBaoResponseModel faBaoResponseModel = null;
    private List<FaBaoResponseModel.ResultDataEntity.ListEntity> listEntities = new ArrayList<>();
    @ViewInject(R.id.lv_chengjie)
    private XListView mListView;
    private FaBaoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_chengjie, container, false);
        x.view().inject(this, view);

        pd = new ProgressDialog(mContext);
        pd.setTitle("数据加载中..");
        initData();

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
        initMore();
        adapter.notifyDataSetChanged();
        onLoad();
    }

    private void initData() {
        pd.show();
        page = 1;
        listEntities.clear();
        MybusinessinfoRequestParamModel requestParamModel = new MybusinessinfoRequestParamModel("2", "", page + "");
        String param = new Gson().toJson(requestParamModel);
        final RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", requestParamModel);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.MYBUSINESSINFO + requestModel.getTime() + requestModel.getGuid() + param + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.MYBUSINESSINFO)
                .addParams("time", requestModel.getTime() + "")
                .addParams("version", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", param)
                .addParams("signature", signature)
                .build()
                .execute(FaBaoResponseModel.class, new CommonCallback<FaBaoResponseModel>() {
                    @Override
                    public void onSuccess(FaBaoResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            faBaoResponseModel = response;
                            if (faBaoResponseModel.getResultData().getList() != null && faBaoResponseModel.getResultData().getList().size() > 0) {
                                listEntities.addAll(faBaoResponseModel.getResultData().getList());
                                page++;
                                initView();
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

    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    private void initMore() {
        MybusinessinfoRequestParamModel requestParamModel = new MybusinessinfoRequestParamModel("2", "", page + "");
        String param = new Gson().toJson(requestParamModel);
        final RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", requestParamModel);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.MYBUSINESSINFO + requestModel.getTime() + requestModel.getGuid() + param + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.MYBUSINESSINFO)
                .addParams("time", requestModel.getTime() + "")
                .addParams("version", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", param)
                .addParams("signature", signature)
                .build()
                .execute(FaBaoResponseModel.class, new CommonCallback<FaBaoResponseModel>() {
                    @Override
                    public void onSuccess(FaBaoResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            faBaoResponseModel = response;
                            if (faBaoResponseModel.getResultData().getList() != null && faBaoResponseModel.getResultData().getList().size() > 0) {
                                listEntities.addAll(faBaoResponseModel.getResultData().getList());
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

    private void initView() {
        pd.dismiss();
        adapter = new FaBaoAdapter(mContext, this, mInflater, listEntities, "承接工程");
        mListView.setPullRefreshEnable(false);
        mListView.setPullLoadEnable(true);
        mListView.setAdapter(adapter);
        mListView.setXListViewListener(this);
    }

    public void refreshData() {
        pd.show();
        if (page != 1) {
            page = 1;
        }
        MybusinessinfoRequestParamModel requestParamModel = new MybusinessinfoRequestParamModel("2", "", page + "");
        String param = new Gson().toJson(requestParamModel);
        final RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", requestParamModel);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.MYBUSINESSINFO + requestModel.getTime() + requestModel.getGuid() + param + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.MYBUSINESSINFO)
                .addParams("time", requestModel.getTime() + "")
                .addParams("version", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", param)
                .addParams("signature", signature)
                .build()
                .execute(FaBaoResponseModel.class, new CommonCallback<FaBaoResponseModel>() {
                    @Override
                    public void onSuccess(FaBaoResponseModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            faBaoResponseModel = response;
                            listEntities.clear();
                            listEntities.addAll(faBaoResponseModel.getResultData().getList());
                            page++;
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
    }
}
