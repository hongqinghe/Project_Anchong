package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.ExpandListAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.ShopGoodsTypeResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by baishixin on 16/6/6.
 */
public class ShopGoodsTypeActivity extends BaseActivity {

    @ViewInject(R.id.expand_activity_goods_type)
    private ExpandableListView mainlistview;
    List<String> parent = null;
    Map<String, List<String>> map = null;
    private ShopGoodsTypeResponse shopGoodsTypeResponse;

    public static void start(Context context) {
        Intent intent = new Intent(context, ShopGoodsTypeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_goods_type);
        x.view().inject(this);
        initData();

    }

    /**
     * 加载数据
     */
    private void initData() {

        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", null);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", null);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.SHOPSGOODSTYPE + requestModel.getTime() + requestModel.getGuid() + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.SHOPSGOODSTYPE)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", "")
                .addParams("signature", signature)
                .build()
                .execute(ShopGoodsTypeResponse.class, new CommonCallback<ShopGoodsTypeResponse>() {
                    @Override
                    public void onSuccess(ShopGoodsTypeResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            shopGoodsTypeResponse = response;
                            initView();
//                            goodsListResponseModel = response;
//                            if (goodsListResponseModel.getResultData().getList().size() > 0) {
//                                list.addAll(goodsListResponseModel.getResultData().getList());
//                                if (list.size() < goodsListResponseModel.getResultData().getTotal()) {
//                                    page++;
//                                }
//                                if (list.size() == goodsListResponseModel.getResultData().getTotal()) {
//                                    xListView.setPullLoadEnable(false);
//                                }
//                                adapter.setData(list);
//                            }
//                            onLoad();
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

    private void initView() {

        if (shopGoodsTypeResponse != null) {
            Logger.i("进来了");
            map = new HashMap<String, List<String>>();
            parent = new ArrayList<String>();
            for (int i = 0; i < shopGoodsTypeResponse.getResultData().size(); i++) {
                List<String> child = new ArrayList<>();
                for (int j = 0; j < shopGoodsTypeResponse.getResultData().get(i).getList().size(); j++) {
                    child.add(shopGoodsTypeResponse.getResultData().get(i).getList().get(j).getCat_name());
                }
                parent.add(shopGoodsTypeResponse.getResultData().get(i).getParent_name());
                map.put(shopGoodsTypeResponse.getResultData().get(i).getParent_name(), child);
            }
            mainlistview.setAdapter(new ExpandListAdapter(this, getLayoutInflater(), parent, map));
            mainlistview.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                @Override
                public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                    String info = shopGoodsTypeResponse.getResultData().get(groupPosition).getList().get(childPosition).toString();
                    showMessage(info);
                    return false;
                }
            });
        }

//        parent.add("parent1");
//        parent.add("parent2");
//        parent.add("parent3");
//
//
//        List<String> list1 = new ArrayList<String>();
//        list1.add("child1-1");
//        list1.add("child1-2");
//        list1.add("child1-3");
//        map.put("parent1", list1);
//
//        List<String> list2 = new ArrayList<String>();
//        list2.add("child2-1");
//        list2.add("child2-2");
//        list2.add("child2-3");
//        map.put("parent2", list2);
//
//        List<String> list3 = new ArrayList<String>();
//        list3.add("child3-1");
//        list3.add("child3-2");
//        list3.add("child3-3");
//        map.put("parent3", list3);
    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
