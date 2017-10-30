package net.anchong.app.ui;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.BusinessHotProjectAdapter;
import net.anchong.app.adapter.SearchBusinessAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.model.BusinessModel;
import net.anchong.app.entity.request.model.BusinessInfoParamModel;
import net.anchong.app.entity.request.model.BusinessRotationRequest;
import net.anchong.app.entity.request.model.BusinessSearchParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.BusinessRotationResponse;
import net.anchong.app.entity.response.model.BusinessSearchResponse;
import net.anchong.app.entity.response.model.HotProjectResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.third.xlistview.XListView;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.view.MyGridView;
import net.anchong.app.view.RotationImageView;
import net.anchong.app.view.WhiteGeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;


/**
 * Created by baishixin on 16/4/18.
 */
public class BusinessInfoActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener, XListView.IXListViewListener, WhiteGeneralTitleBarView.GeneralTitleBarOnclickListener {

    /**
     *
     */
    @ViewInject(R.id.wgtbv_project)
    private WhiteGeneralTitleBarView titleBarView;
    @ViewInject(R.id.ll_business_project1)
    private LinearLayout project1;
    @ViewInject(R.id.ll_business_project2)
    private LinearLayout project2;
    @ViewInject(R.id.tv_business_project_title1)
    private TextView title1;
    @ViewInject(R.id.tv_business_project_title2)
    private TextView title2;
    @ViewInject(R.id.ll_business_project_tab)
    private LinearLayout tab;
    @ViewInject(R.id.line_project1)
    private View line1;
    @ViewInject(R.id.line_project2)
    private View line2;
    @ViewInject(R.id.xlv_project)
    private XListView xListView;
    private BusinessHotProjectAdapter adapter;
    private ProgressDialog pd = null;
    private RotationImageView rotationImageView;


    private PopupWindow popupWindow;

    /**
     *
     */

    /**
     *
     */
    //请求是的筛选标签
    private String strTag = "";
    //当前第几页数据
    private int page = 1;
    //
    private String type = "1";
    private String title;
    private HotProjectResponse businessInfoResponseModel = null;
    private List<HotProjectResponse.ResultDataEntity.ListEntity> listBeans = new ArrayList<>();
    private BusinessRotationResponse businessRotationResponse = null;
    private List<BusinessRotationResponse.ResultDataEntity> resultDataEntityList = new ArrayList<>();
    //商机分类筛选
    private BusinessSearchResponse businessSearchResponse = null;
    private List<String> types = new ArrayList<>();
    private List<String> areas = new ArrayList<>();
    //筛选列表是否展开
    private boolean isExpand = false;
    //筛选界面服务类别Adapter
    private SearchBusinessAdapter typeAdapter;
    //筛选节目区域Adapter
    private SearchBusinessAdapter searchBusinessAdapter;
    private String searchType = "";

    private BusinessSearchParamModel businessSearchParamModel;

    public static void start(Context context, String title) {
        Intent intent = new Intent(context, BusinessInfoActivity.class);
        intent.putExtra("title", title);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_info2);
        x.view().inject(this);
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.tab_bg);// 通知栏所需颜色
        title = getIntent().getStringExtra("title");
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        titleBarView.setData(title, "筛选");
        titleBarView.setGeneralTitleBarOnclickListener(this);
        switch (title) {
            case "工程":
                title1.setText("工程招标");
                title2.setText("承接工程");
                type = "1";
                break;
            case "人才":
                title1.setText("人才发布");
                title2.setText("人才招聘");
                type = "3";
                break;
            case "找货":
                tab.setVisibility(View.GONE);
                type = "5";
                break;

        }
        initAvd();
    }

    /**
     * 初始化视图
     */
    private void initView() {

        project1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (title) {
                    case "工程":
                        type = "1";
                        line1.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        initData();
                        break;
                    case "人才":
                        type = "3";
                        line1.setVisibility(View.VISIBLE);
                        line2.setVisibility(View.INVISIBLE);
                        initData();
                        break;
                }

            }
        });
        project2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (title) {
                    case "工程":
                        type = "2";
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.VISIBLE);
                        initData();
                        break;
                    case "人才":
                        type = "4";
                        line1.setVisibility(View.INVISIBLE);
                        line2.setVisibility(View.VISIBLE);
                        initData();
                        break;
                }
            }
        });

        if (listBeans == null || listBeans.size() <= 0) {
            adapter = new BusinessHotProjectAdapter(this, getLayoutInflater(), null, 0);
            xListView.setAdapter(adapter);
            xListView.setPullLoadEnable(false);
            xListView.setPullRefreshEnable(false);
        } else {
            if (rotationImageView == null) {
                rotationImageView = new RotationImageView(this);
                rotationImageView.setBusinessAvdData(resultDataEntityList);
                xListView.addHeaderView(rotationImageView);
            }
            adapter = new BusinessHotProjectAdapter(this, getLayoutInflater(), listBeans, businessInfoResponseModel.getResultData().getShowphone());
            xListView.setAdapter(adapter);
            xListView.setPullLoadEnable(true);
            if (listBeans.size() == businessInfoResponseModel.getResultData().getTotal()) {
                xListView.setPullLoadEnable(false);
            }
            xListView.setPullRefreshEnable(false);
            xListView.setXListViewListener(this);

            xListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BusinessModel businessModel = new BusinessModel();
                    businessModel.setPhone(listBeans.get(position - 2).getPhone());
                    businessModel.setBid(listBeans.get(position - 2).getBid());
                    businessModel.setContact(listBeans.get(position - 2).getContact());
                    businessModel.setContent(listBeans.get(position - 2).getContent());
                    businessModel.setCreated_at(listBeans.get(position - 2).getCreated_at());
                    businessModel.setTag(listBeans.get(position - 2).getTag());
                    businessModel.setTags(listBeans.get(position - 2).getTags());
                    businessModel.setTitle(listBeans.get(position - 2).getTitle());
                    businessModel.setPic(listBeans.get(position - 2).getPic());
                    BusinessDetaActivity.start(BusinessInfoActivity.this, businessModel, businessInfoResponseModel.getResultData().getShowphone());
                }
            });
        }


        //初始化筛选界面的Adapter
        typeAdapter = new SearchBusinessAdapter(this, getLayoutInflater());
        //初始化筛选界面的Adapter
        if (areas != null && areas.size() > 0) {
            searchBusinessAdapter = new SearchBusinessAdapter(this, getLayoutInflater(), areas);
        }


    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 加载轮播图
     */
    private void initAvd() {
        pd.show();
        String avdType = "1";
        switch (title) {
            case "工程":
                avdType = "1";
                break;
            case "人才":
                avdType = "2";
                break;
            case "找货":
                avdType = "3";
                break;

        }
        final BusinessRotationRequest businessRotationRequest = new BusinessRotationRequest(avdType);
        String requestParam = new Gson().toJson(businessRotationRequest);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", businessRotationRequest);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", businessRotationRequest);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.ADVERTPROJECTADVERT + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.ADVERTPROJECTADVERT)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(BusinessRotationResponse.class, new CommonCallback<BusinessRotationResponse>() {
                    @Override
                    public void onSuccess(BusinessRotationResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            pd.dismiss();
                            businessRotationResponse = response;
                            resultDataEntityList = businessRotationResponse.getResultData();
                            initData();
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


    private void initData() {

//        pd.show();
        page = 1;
        if (listBeans != null) listBeans.clear();
        BusinessInfoParamModel businessInfoParamModel = new BusinessInfoParamModel(type, strTag, "", page + "");
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
                .execute(HotProjectResponse.class, new CommonCallback<HotProjectResponse>() {
                    @Override
                    public void onSuccess(HotProjectResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
//                            pd.dismiss();
                            businessInfoResponseModel = response;
                            listBeans.clear();
                            if (businessInfoResponseModel.getResultData().getList() != null && businessInfoResponseModel.getResultData().getList().size() > 0) {
                                listBeans.addAll(businessInfoResponseModel.getResultData().getList());
                            }
                            if (listBeans.size() == businessInfoResponseModel.getResultData().getTotal()) {
                                xListView.setPullLoadEnable(false);
                            }
                            if (listBeans.size() < businessInfoResponseModel.getResultData().getTotal()) {
                                page++;
                            }
                            initSearchType();
//                            initView();
                        } else {
                            showMessage(getString(R.string.request_error_msg));
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        pd.dismiss();
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });


    }

    /**
     * 请求商机筛选的条件
     */
    private void initSearchType() {
        RequestModel requestModel;
        String copyToken = "";
        String signature = "";
        BusinessSearchParamModel businessSearchParamModel = new BusinessSearchParamModel(type);
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
                            Logger.i("type = " + type);
                        } else {
                            showMessage(getString(R.string.request_error_msg));
                        }
                        initView();
                        pd.dismiss();
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        initView();
                        pd.dismiss();
                        showMessage(getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });

    }


    private void initMore() {
        BusinessInfoParamModel businessInfoParamModel = new BusinessInfoParamModel(type, strTag, "", page + "");
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
                .execute(HotProjectResponse.class, new CommonCallback<HotProjectResponse>() {
                    @Override
                    public void onSuccess(HotProjectResponse response, Object... obj) {
                        onLoad();
                        if ("0".equals(response.getServerNo())) {
                            businessInfoResponseModel = response;
                            if (businessInfoResponseModel.getResultData().getList() != null && businessInfoResponseModel.getResultData().getList().size() > 0) {
                                listBeans.addAll(businessInfoResponseModel.getResultData().getList());
                                adapter.setList(listBeans);
                            }
                            if (listBeans.size() == businessInfoResponseModel.getResultData().getTotal()) {
                                xListView.setPullLoadEnable(false);
                            }
                            if (listBeans.size() < businessInfoResponseModel.getResultData().getTotal()) {
                                page++;
                            }
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

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_general_left:
                finish();
                break;
        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onLoadMore() {
        initMore();
    }

    private void onLoad() {
        xListView.stopRefresh();
        xListView.stopLoadMore();
        xListView.setRefreshTime("刚刚");
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        showPopup(titleBarView);
    }

    /**
     * 显示 PopupWindow
     */
    private MyGridView gv_type;
    private MyGridView gv_area;

    private void showPopup(View v) {
        isExpand = true;
        if (!searchType.equals(type)) {
            popupWindow = null;
        }
        if (popupWindow == null) {
            searchType = type;
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.view_business_search, null);
            gv_type = (MyGridView) view.findViewById(R.id.gv_business_search_type);
            final TextView action = (TextView) view.findViewById(R.id.tv_business_search_action);
            final ImageView icon = (ImageView) view.findViewById(R.id.iv_business_search_icon);
            //设置服务类别筛选条件
            if (types != null && types.size() > 0) {
                if (types.size() > 12) {
                    if (isExpand) {
                        List<String> startList = types.subList(0, 12);
                        typeAdapter.setList(startList);
                        gv_type.setAdapter(typeAdapter);
                        isExpand = false;
                        action.setText("展开");
                        icon.setImageResource(R.drawable.business_search_down);
                    } else {
                        typeAdapter.setList(types);
                        gv_type.setAdapter(typeAdapter);
                        isExpand = true;
                        action.setText("收起");
                        icon.setImageResource(R.drawable.business_search_up);
                    }
                } else {
                    typeAdapter.setList(types);
                    gv_type.setAdapter(typeAdapter);
                    action.setVisibility(View.INVISIBLE);
                    icon.setVisibility(View.INVISIBLE);
                }
                gv_type.setOnItemClickListener(this);

                //设置点击展开更多事件
                LinearLayout more = (LinearLayout) view.findViewById(R.id.ll_business_search_more);
                more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (isExpand) {
                            List<String> startList = types.subList(0, 12);
                            typeAdapter.setList(startList);
                            gv_type.setAdapter(typeAdapter);
                            isExpand = false;
                            action.setText("展开");
                            icon.setImageResource(R.drawable.business_search_down);
                        } else {
                            typeAdapter.setList(types);
                            gv_type.setAdapter(typeAdapter);
                            isExpand = true;
                            action.setText("收起");
                            icon.setImageResource(R.drawable.business_search_up);
                        }


                    }
                });
            }


            gv_area = (MyGridView) view.findViewById(R.id.gv_business_search_eare);
            //设置区域筛选条件的GridView
            if (searchBusinessAdapter != null) {
                gv_area.setAdapter(searchBusinessAdapter);
                gv_area.setOnItemClickListener(this);
            }

            //获取屏幕的宽度和高度
            int width = getWindowManager().getDefaultDisplay().getWidth();
            int height = (int) (getWindowManager().getDefaultDisplay().getHeight() * 0.6);
            popupWindow = new PopupWindow(view, getWindowManager().getDefaultDisplay().getWidth(), height);
        }

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                - popupWindow.getWidth() / 2;

        Log.i("coder", "windowManager.getDefaultDisplay().getWidth()/2:"
                + windowManager.getDefaultDisplay().getWidth() / 2);
        //
        Log.i("coder", "popupWindow.getWidth()/2:" + popupWindow.getWidth() / 2);

        Log.i("coder", "xPos:" + xPos);

        popupWindow.showAsDropDown(v, 0, 4);
    }

    /**
     * MyGridview item 点击事件
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.gv_business_search_eare:
                strTag = areas.get(position);
                initData();
                break;
            case R.id.gv_business_search_type:
                strTag = types.get(position);
                initData();
                break;

        }
        popupWindow.dismiss();
    }
}
