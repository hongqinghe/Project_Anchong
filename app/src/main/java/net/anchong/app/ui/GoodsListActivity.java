package net.anchong.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.GoodsListAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.GoodsListParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.GoodsListResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 商品列表界面
 * Created by baishixin on 16/4/22.
 */
public class GoodsListActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, AdapterView.OnItemClickListener {

    /**
     * 界面展示部分
     */
    //页面上方通用的标题栏
//    @ViewInject(R.id.gtvv_goodslist_title)
//    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.xlistview_goods)
    private XListView mListView;
    private GoodsListAdapter adapter;

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


    //需要获取商品列表的三级分类ID
    private String cat_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// fresco
        Fresco.initialize(this);
        setContentView(R.layout.fragment_goods_list);
        x.view().inject(this);

        //从Intent中获取三级分类ID
        Intent intent = getIntent();
        cat_id = intent.getStringExtra("cat_id");

        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (cat_id != null && !"".equals(cat_id)) {
            //数据检查完成，可以提交数据
            page = 1;
            GoodsListParamModel goodsListParamModel = new GoodsListParamModel(cat_id, "", page + "");
            String json = new Gson().toJson(goodsListParamModel);

            RequestModel requestModel = null;
            String copyToken = null;
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
            String signature = ACRequestUtils.getMD5(MyApplication.GOODSGOODSLIST + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
            requestModel.setSignature(signature);


            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.GOODSGOODSLIST)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", json)
                    .addParams("signature", signature)
                    .build()
                    .execute(GoodsListResponseModel.class, new CommonCallback<GoodsListResponseModel>() {
                        @Override
                        public void onSuccess(GoodsListResponseModel response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                goodsListResponseModel = response;
                                if (list.size() > 0) {
                                    list.clear();
                                }
                                list = goodsListResponseModel.getResultData().getList();
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
    }

    private void initView() {
//        mGeneralTitleBarView.setData("商品列表", "");
//        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        adapter = new GoodsListAdapter(this, getLayoutInflater(), list, Integer.parseInt(goodsListResponseModel.getResultData().getShowprice()));
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(this);

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

    }

    /**
     * 点击商品列表的事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showMessage("position = " + position);
        GoodsInformationActivity.start(this, list.get(position - 1).getGid(),
                Integer.parseInt(goodsListResponseModel.getResultData().getShowprice()),
                list.get(position-1).getGoods_id(),
                list.get(position-1).getTitle());
    }
}











