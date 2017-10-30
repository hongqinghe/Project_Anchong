package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.GoodsListAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.GoodsAllRequest;
import net.anchong.app.entity.request.model.GoodsListParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GoodsListResponseModel;
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
 * Created by baishixin on 16/5/6.
 */
public class GoodsListFragment extends BaseFragment implements XListView.IXListViewListener {


    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;
    private String cid = "";
    private String other_id = "";

    @ViewInject(R.id.xlistview_goods)
    private XListView listView;
    @ViewInject(R.id.rl_root_view)
    private RelativeLayout emptyView;

    private ProgressDialog pd;
    /**
     * 数据请求部分
     */
    //分页加载时当前应该请求的页数标记,默认请求第一页
    private int page = 1;

    /**
     * 数据存储部分
     */
    private GoodsListResponseModel goodsListResponseModel = null;
    private List<GoodsListResponseModel.ResultDataEntity.ListEntity> list = new ArrayList<>();
    private GoodsListAdapter adapter;


    public static GoodsListFragment newInstance(String cid, String other_id) {
        GoodsListFragment fragment = new GoodsListFragment();
        fragment.cid = cid;
        fragment.other_id = other_id;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_goods_list, container, false);
        x.view().inject(this, view);
        pd = new ProgressDialog(mContext);
        initData();
        return view;
    }

    private void initData() {
        if (cid != null && !"".equals(cid)) {
            pd.setCanceledOnTouchOutside(false);
            pd.show();
            //数据检查完成，可以提交数据
            page = 1;

            RequestModel requestModel = null;
            String copyToken = null;
            String json = null;
            String api = "";
            if ("0".equals(cid)) {
                GoodsAllRequest goodsAllRequest = new GoodsAllRequest(other_id, page + "");
                json = new Gson().toJson(goodsAllRequest);
                //判断用户是否登录
                //登录状态
                if (MainActivity.isLogin == true) {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                            MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsAllRequest);
                    copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                } else {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", goodsAllRequest);
                    copyToken = MyApplication.DEFAULT_TOKEN;
                }
                api = MyApplication.GOODSGOODSALL;
            } else {
                GoodsListParamModel goodsListParamModel = new GoodsListParamModel(cid, "", page + "");
                json = new Gson().toJson(goodsListParamModel);

                //判断用户是否登录
                //登录状态
                if (MainActivity.isLogin == true) {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                            MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsListParamModel);
                    copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                } else {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", goodsListParamModel);
                    copyToken = MyApplication.DEFAULT_TOKEN;
                }
                api = MyApplication.GOODSGOODSLIST;
            }


            String signature = ACRequestUtils.getMD5(api + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + api)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(GoodsListResponseModel.class, new CommonCallback<GoodsListResponseModel>() {
                        @Override
                        public void onSuccess(GoodsListResponseModel response, Object... obj) {
                            Log.e("MainActivity", response.toString());
                            pd.dismiss();
                            if ("0".equals(response.getServerNo())) {
                                goodsListResponseModel = response;
                                if (list.size() > 0) {
                                    list.clear();
                                }
                                list = goodsListResponseModel.getResultData().getList();
                                if (list.size() < goodsListResponseModel.getResultData().getTotal()) {
                                    page++;
                                }
                                initView();
                            } else if ("10".equals(response.getServerNo())) {
//                                showMessage("暂无商品");
                            } else {
                                showMessage(getString(R.string.request_error_msg));
//                                showMessage(response.getResultData().toString());
                            }
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            pd.dismiss();
                            e.printStackTrace();
                        }
                    });
        }
    }

    private void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        if (list == null || list.size() == 0 || list.size() < 0) {
            emptyView.setVisibility(View.VISIBLE);
            listView.setVisibility(View.GONE);
        } else {
            emptyView.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter = new GoodsListAdapter(mContext, mInflater, list, Integer.parseInt(goodsListResponseModel.getResultData().getShowprice()));
            listView.setAdapter(adapter);
            listView.setPullRefreshEnable(false);
            listView.setPullLoadEnable(true);

            if (list != null && list.size() > 0 && goodsListResponseModel != null) {
                if (list.size() == goodsListResponseModel.getResultData().getTotal()) {
                    listView.setPullLoadEnable(false);
                }
            }
            listView.setXListViewListener(this);
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

    @Override
    public void onRefresh() {
        onLoad();
    }

    private void onLoad() {
        listView.stopLoadMore();
        listView.setRefreshTime("刚刚");
    }

    @Override
    public void onLoadMore() {
        initMore();
    }

    private void initMore() {
        if (cid != null && !"".equals(cid)) {
            RequestModel requestModel = null;
            String copyToken = null;
            String json = null;
            String api = "";
            if ("0".equals(cid)) {
                GoodsAllRequest goodsAllRequest = new GoodsAllRequest(other_id, page + "");
                json = new Gson().toJson(goodsAllRequest);
                //判断用户是否登录
                //登录状态
                if (MainActivity.isLogin == true) {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                            MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsAllRequest);
                    copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                } else {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", goodsAllRequest);
                    copyToken = MyApplication.DEFAULT_TOKEN;
                }
                api = MyApplication.GOODSGOODSALL;
            } else {
                GoodsListParamModel goodsListParamModel = new GoodsListParamModel(cid, "", page + "");
                json = new Gson().toJson(goodsListParamModel);

                //判断用户是否登录
                //登录状态
                if (MainActivity.isLogin == true) {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                            MainActivity.loginResponseModel.getResultData().getGuid() + "", goodsListParamModel);
                    copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                } else {
                    requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", goodsListParamModel);
                    copyToken = MyApplication.DEFAULT_TOKEN;
                }
                api = MyApplication.GOODSGOODSLIST;
            }


            String signature = ACRequestUtils.getMD5(api + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + api)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(GoodsListResponseModel.class, new CommonCallback<GoodsListResponseModel>() {
                        @Override
                        public void onSuccess(GoodsListResponseModel response, Object... obj) {
                            Log.e("MainActivity", response.toString());
                            //返回的状态码为0 代表请求正常
                            if (response.getServerNo().equals("0")) {
                                goodsListResponseModel = response;
                                if (goodsListResponseModel.getResultData().getList().size() > 0) {
                                    list.addAll(goodsListResponseModel.getResultData().getList());
                                }
                                if (list.size() < goodsListResponseModel.getResultData().getTotal()) {
                                    page++;
                                }
                                if (list.size() == goodsListResponseModel.getResultData().getTotal()) {
                                    listView.setPullLoadEnable(false);
                                }
                                onLoad();
//                                initView();
                                adapter.setData(list);
                            } else {
                                showMessage(response.getResultData().toString());
                                onLoad();
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
}
