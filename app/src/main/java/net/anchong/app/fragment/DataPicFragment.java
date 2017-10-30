package net.anchong.app.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.facebook.drawee.backends.pipeline.Fresco;

import net.anchong.app.R;
import net.anchong.app.ui.GoodsInformationActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 发包工程
 * Created by baishixin on 16/3/8.
 */
public class DataPicFragment extends BaseFragment {

    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;
    private ProgressDialog pd;
    private String urlPath = "";

    @ViewInject(R.id.wv_content)
    private WebView mWebContent;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /// fresco
        Fresco.initialize(mContext);
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_paramweb, container, false);
        x.view().inject(this, view);
        pd = new ProgressDialog(mContext);
        pd.setTitle("加载中..");
        pd.setCanceledOnTouchOutside(false);
        initData();
        return view;
    }

    /**
     * 加载数据
     */
    int i = 0;

    private void initData() {
        pd.show();
        urlPath = GoodsInformationActivity.goodsINfoResultModel.getResultData().getDatapic();

        WebSettings settings = mWebContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(false);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setDefaultTextEncodingName("UTF-8");
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 设置可以支持缩放
        mWebContent.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mWebContent.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        mWebContent.getSettings().setUseWideViewPort(true);
     //自适应屏幕
        mWebContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebContent.getSettings().setLoadWithOverviewMode(true);


        initView();
    }


    private void initView() {
        if (urlPath != null && !"".equals(urlPath)) {
            mWebContent.loadUrl(urlPath);

            // 屏蔽某些手机长按事件,复制奔溃(另一种代替方法初始化webview传getApplicationContext()替换成NewsDetailActivity.this)
            mWebContent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (Build.VERSION.SDK_INT <= 17) {
                        return true;
                    }
                    return false;
                }
            });
        }
        pd.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void lazyLoad() {

    }
}
