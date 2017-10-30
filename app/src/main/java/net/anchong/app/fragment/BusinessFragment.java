package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.BusinessIndexAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.model.BusinessModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.BusinessAdvertResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.ui.BusinessDetaActivity;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.BusinessListHeaderView;
import net.anchong.app.view.TitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/3/8.
 */
public class BusinessFragment extends BaseFragment implements View.OnClickListener {

    @ViewInject(R.id.tbv_business)
    private TitleBarView mTltleBarView;
    //    @ViewInject(R.id.tv_start_chengjie)
//    private TextView textView_startChengJie;
//加载数据滚动条
    private ProgressDialog pd = null;
    /**
     * 视图
     */
    @ViewInject(R.id.xlv_business)
    private XListView xListView;
    private BusinessIndexAdapter adapter;


    /**
     * 数据存储
     */
    //热门工程
    private List<BusinessAdvertResponse.ResultDataEntity.HotprojectEntity> hotprojectList;
    //资讯
    private BusinessAdvertResponse.ResultDataEntity.InformationEntity information;
    //地区
    private List<BusinessAdvertResponse.ResultDataEntity.RecentEntity> recentList;
    //轮播图
    private List<BusinessAdvertResponse.ResultDataEntity.PicEntity> picList;
    //地区
    private List<BusinessAdvertResponse.ResultDataEntity.RecommendEntity> recommendList;


    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;

    private BusinessAdvertResponse businessAdvertResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_provide, container, false);
        SystemBarTintManager tintManager = new SystemBarTintManager((MainActivity) mContext);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.theme);// 通知栏所需颜色
        x.view().inject(this, view);
        pd = new ProgressDialog(mContext);
        pd.setCanceledOnTouchOutside(false);
        initData();
//        initView();
        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        pd.show();
        RequestModel requestModel = null;
        String signature = "";
        if (MainActivity.isLogin) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", null);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

            signature = ACRequestUtils.getMD5(MyApplication.ADVERTBUSINESSADVERT + requestModel.getTime() + requestModel.getGuid() + "" + copyToken);

        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    "0", null);
//            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            signature = ACRequestUtils.getMD5(MyApplication.ADVERTBUSINESSADVERT + requestModel.getTime() + requestModel.getGuid() + "" + "anchongnet");
        }
        requestModel.setSignature(signature);

        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.ADVERTBUSINESSADVERT)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", "")
                .addParams("signature", signature)
                .build()
                .execute(BusinessAdvertResponse.class, new CommonCallback<BusinessAdvertResponse>() {
                    @Override
                    public void onSuccess(BusinessAdvertResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            businessAdvertResponse = response;
                            hotprojectList = businessAdvertResponse.getResultData().getHotproject() != null ? businessAdvertResponse.getResultData().getHotproject() : null;
                            information = businessAdvertResponse.getResultData().getInformation() != null ? businessAdvertResponse.getResultData().getInformation() : null;
                            picList = businessAdvertResponse.getResultData().getPic() != null ? businessAdvertResponse.getResultData().getPic() : null;
                            recentList = businessAdvertResponse.getResultData().getRecent() != null ? businessAdvertResponse.getResultData().getRecent() : null;
                            recommendList = businessAdvertResponse.getResultData().getRecommend() != null ? businessAdvertResponse.getResultData().getRecommend() : null;
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

    private void initView() {
        mTltleBarView.setTitle("商机");

        if (businessAdvertResponse != null) {

            BusinessListHeaderView businessListHeaderView = new BusinessListHeaderView(mContext);
            //设置轮播图
            businessListHeaderView.setPic(picList);
            //设置热门招标项目
            businessListHeaderView.setRecommend(recommendList, businessAdvertResponse.getResultData().getShowphone());
            //设置最新招标项目
            businessListHeaderView.setRecent(recentList, businessAdvertResponse.getResultData().getShowphone());

            xListView.addHeaderView(businessListHeaderView);
            adapter = new BusinessIndexAdapter(mContext, mInflater, hotprojectList, businessAdvertResponse.getResultData().getShowphone());
            xListView.setAdapter(adapter);
            xListView.setPullLoadEnable(false);
            xListView.setPullRefreshEnable(false);
            xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    /**
                     * 不知道为什么这里 position 是从2开始的
                     *
                     */
                    BusinessModel businessModel = new BusinessModel();
                    businessModel.setPhone(hotprojectList.get(position - 2).getPhone());
                    businessModel.setBid(hotprojectList.get(position - 2).getBid());
                    businessModel.setContact(hotprojectList.get(position - 2).getContact());
                    businessModel.setContent(hotprojectList.get(position - 2).getContent());
                    businessModel.setCreated_at(hotprojectList.get(position - 2).getCreated_at());
                    businessModel.setTag(hotprojectList.get(position - 2).getTag());
                    businessModel.setTags(hotprojectList.get(position - 2).getTags());
                    businessModel.setTitle(hotprojectList.get(position - 2).getTitle());
                    businessModel.setPic(hotprojectList.get(position - 2).getPic());
                    BusinessDetaActivity.start(mContext, businessModel, businessAdvertResponse.getResultData().getShowphone());
                }
            });
        }


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected void lazyLoad() {

    }

    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.tv_start_chengjie:
//                BusinessInfoActivity.start(mContext);
//                break;
        }
    }
}
