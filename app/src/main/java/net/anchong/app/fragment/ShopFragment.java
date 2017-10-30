package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.ShopGoodsListAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GoodsAdvertResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.CategoryListHeaderView;
import net.anchong.app.view.TitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;

/**
 * 商城的 Fragment
 * Created by baishixin on 16/3/7.
 */
public class ShopFragment extends BaseFragment {

    @ViewInject(R.id.tbv_shop)
    private TitleBarView mTitleBarView;
    //加载数据滚动条
    private ProgressDialog pd = null;

    @ViewInject(R.id.listview_goodscate)
    private ListView listView;
    private ShopGoodsListAdapter adapter;

    /**
     * 数据存储部分
     */
    private GoodsAdvertResponse goodsAdvertResponse;
    //轮播图
    private List<GoodsAdvertResponse.ResultDataEntity.PicEntity> pics;
    //第一个广告栏
    private List<GoodsAdvertResponse.ResultDataEntity.OneEntity.ListEntity> oneList;
    //第二个广告栏
    private List<GoodsAdvertResponse.ResultDataEntity.TwoEntity.ListEntity> twoList;
    //第三个广告栏
    private List<GoodsAdvertResponse.ResultDataEntity.ThreeEntity.ListEntity> threeList;
    //第四个广告栏
    private List<GoodsAdvertResponse.ResultDataEntity.FourEntity.ListEntity> fourList;
    //第五个广告栏
    private List<GoodsAdvertResponse.ResultDataEntity.FiveEntity.ListEntity> fiveList;
    //第六个广告栏
    private List<GoodsAdvertResponse.ResultDataEntity.SixEntity.ListEntity> sixList;


    /**
     * 请求参数部分
     */
    //一级分类ID
    private String cat_id = "";


    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_home, container, false);
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
     * 加载商城一级分类
     */
    private void initData() {
        pd.show();
        RequestModel requestModel = null;
        String signature = "";
        if (MainActivity.isLogin) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", null);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

            signature = ACRequestUtils.getMD5(MyApplication.ADVERTGOODSADVERT + requestModel.getTime() + requestModel.getGuid() + "" + copyToken);

        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    "0", null);
//            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
            signature = ACRequestUtils.getMD5(MyApplication.ADVERTGOODSADVERT + requestModel.getTime() + requestModel.getGuid() + "" + "anchongnet");
        }
        requestModel.setSignature(signature);
        Logger.i("请求数据");
        Logger.i("time = " + requestModel.getTime());
        Logger.i("version = " + requestModel.getVersion());
        Logger.i("guid = " + requestModel.getGuid());
        Logger.i("param = ");
        Logger.i("signature = " + signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.ADVERTGOODSADVERT)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", "")
                .addParams("signature", signature)
                .build()
                .execute(GoodsAdvertResponse.class, new CommonCallback<GoodsAdvertResponse>() {
                    @Override
                    public void onSuccess(GoodsAdvertResponse response, Object... obj) {
                        pd.dismiss();
                        if ("0".equals(response.getServerNo())) {
                            goodsAdvertResponse = response;
                            pics = goodsAdvertResponse.getResultData().getPic();
                            oneList = goodsAdvertResponse.getResultData().getOne().getList();
                            initView();
                        } else {
                            showMessage(getString(R.string.request_error_msg));
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                        pd.dismiss();
                    }
                });
    }


    private void initView() {
        mTitleBarView.setTitle("商城");

        if (goodsAdvertResponse != null) {
            CategoryListHeaderView categoryListHeaderView = new CategoryListHeaderView(mContext);
            categoryListHeaderView.setUIData(goodsAdvertResponse);

            listView.addHeaderView(categoryListHeaderView);
            adapter = new ShopGoodsListAdapter(mContext, mInflater, goodsAdvertResponse.getResultData().getGoods(), Integer.parseInt(goodsAdvertResponse.getResultData().getShowprice()));
            listView.setAdapter(adapter);

        }


    }

    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    protected void lazyLoad() {

    }


}
