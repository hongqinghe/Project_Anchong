package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.CommunityShowAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.CommunityShowRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.CommunityShowResponse;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.ui.CommnuityActivity;
import net.anchong.app.ui.MyCommunityActivity;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.FileUtils;
import net.anchong.app.view.TalkTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/3/8.
 */
public class SheQuFragment extends BaseFragment implements TalkTitleBarView.GeneralTitleBarOnclickListener, XListView.IXListViewListener, OnMenuItemClickListener, OnMenuItemLongClickListener {

    @ViewInject(R.id.tbv_shequ)
    private TalkTitleBarView mTitleBarView;

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;

    @ViewInject(R.id.xlv_shequ)
    private XListView xListView;
    private CommunityShowAdapter adapter;

    private int page = 1;
    private String tags = "";
    private ProgressDialog pd;

    private CommunityShowResponse communityShowResponse;
    private List<CommunityShowResponse.ResultDataEntity.ListEntity> list;

    /**
     * 筛选按钮所需
     */
//    public static final String TAG = ContextMenuDialogFragment.class.getSimpleName();
    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        Fresco.initialize(mContext);
        view = inflater.inflate(R.layout.fragment_shequ, container, false);
        SystemBarTintManager tintManager = new SystemBarTintManager((MainActivity) mContext);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.talk_back);// 通知栏所需颜色
        x.view().inject(this, view);
        fragmentManager = getFragmentManager();
        pd = new ProgressDialog(mContext);
        initData();
        initMenuFragment();
//        initView();
        return view;
    }

    /**
     * 加载社区首页聊聊展示信息
     */
    private void initData() {

        //已登录状态，加载用户头像
        if (MainActivity.isLogin == true) {
            GetUserMessageResponseModel getUserMessageResponseModel = FileUtils.getUserMessage(mContext);
            if (getUserMessageResponseModel != null) {
                if (getUserMessageResponseModel.getResultData().getHeadpic() != null && !"".equals(getUserMessageResponseModel.getResultData().getHeadpic())) {
                    mTitleBarView.setHead(getUserMessageResponseModel.getResultData().getHeadpic());
                }

            }
        }
        //未登录状态
        pd.show();
        page = 1;
        CommunityShowRequest communityShowRequest = new CommunityShowRequest(tags, page + "");
        String requestParam = new Gson().toJson(communityShowRequest);
        RequestModel requestModel = null;
        String copyToken = null;
        requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", communityShowRequest);
        copyToken = MyApplication.DEFAULT_TOKEN;
//        }
        String signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYSHOW + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYCOMMUNITYSHOW)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(CommunityShowResponse.class, new CommonCallback<CommunityShowResponse>() {
                    @Override
                    public void onSuccess(CommunityShowResponse response, Object... obj) {
                        Log.e("MainActivity", response.toString());
                        //返回的状态码为0 代表请求正常
                        if ("0".equals(response.getServerNo())) {
                            pd.dismiss();
                            Logger.i("聊聊信息：" + response.toString());
                            communityShowResponse = response;
                            if (list != null) {
                                list.clear();
                            }
                            list = response.getResultData().getList();
                            if (list != null && list.size() < communityShowResponse.getResultData().getTotal()) {
                                page++;
                            }
                            initView();
                        } else {
                            pd.dismiss();
                            Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private void initView() {
        SystemBarTintManager tintManager = new SystemBarTintManager((MainActivity) mContext);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.talk_back);// 通知栏所需颜色
        mTitleBarView.setGeneralTitleBarOnclickListener(this);
//        initMenuFragment();

        adapter = new CommunityShowAdapter(mContext, mInflater, communityShowResponse.getResultData().getList());
        xListView.setAdapter(adapter);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(false);
        xListView.setXListViewListener(this);
        if (list.size() == communityShowResponse.getResultData().getTotal()) {
            xListView.setPullLoadEnable(false);
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
    public void leftClick() {
        MyCommunityActivity.start(mContext);
    }

    @Override
    public void centerClick() {
        CommnuityActivity.start(mContext);
    }

    @Override
    public void rightClick() {
        showMessage("筛选");
        if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
            mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
        }
    }

    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        initMore();
    }

    private void initMore() {
        final CommunityShowRequest communityShowRequest = new CommunityShowRequest(tags, page + "");
        String requestParam = new Gson().toJson(communityShowRequest);
        RequestModel requestModel = null;
        String copyToken = null;
        requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", communityShowRequest);
        copyToken = MyApplication.DEFAULT_TOKEN;
//        }
        String signature = ACRequestUtils.getMD5(MyApplication.COMMUNITYCOMMUNITYSHOW + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.COMMUNITYCOMMUNITYSHOW)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(CommunityShowResponse.class, new CommonCallback<CommunityShowResponse>() {
                    @Override
                    public void onSuccess(CommunityShowResponse response, Object... obj) {
                        //返回的状态码为0 代表请求正常
                        if ("0".equals(response.getServerNo())) {
                            communityShowResponse = response;
                            if (communityShowResponse.getResultData().getList() != null && communityShowResponse.getResultData().getList().size() > 0) {
                                list.addAll(communityShowResponse.getResultData().getList());
                            }
                            if (list != null && list.size() < communityShowResponse.getResultData().getTotal()) {
                                page++;
                            }
                            if (list.size() == communityShowResponse.getResultData().getTotal()) {
                                xListView.setPullLoadEnable(false);
                            }
                            onLoad();
                            adapter.setList(list);
                        } else {
                            pd.dismiss();
                            Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(mContext, "网络错误", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });
    }

    private void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime("刚刚");
    }

    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject addFav = new MenuObject("所有");
        addFav.setResource(R.drawable.icn_4);

        MenuObject send = new MenuObject("闲聊");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("问问");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.icn_2);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("活动");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);


//        MenuObject block = new MenuObject("Block user");
//        block.setResource(R.drawable.icn_5);

        menuObjects.add(addFav);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(close);
//        menuObjects.add(block);
        return menuObjects;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }


    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    @Override
    public void onMenuItemClick(View clickedView, int position) {
        switch (position) {
            case 0:
                tags = "";
                initData();
                break;
            case 1:
                tags = "闲聊";
                initData();
                break;
            case 2:
                tags = "问问";
                initData();
                break;
            case 3:
                tags = "活动";
                initData();
                break;
            case 4:
                break;

        }
//        Toast.makeText(mContext, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
//        Toast.makeText(mContext, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }
}
