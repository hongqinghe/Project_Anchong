package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import net.anchong.app.R;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.view.BusinessProjectTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;


/**
 * Created by baishixin on 16/6/27.
 */
public class NewsDetaActivity extends BaseActivity implements BusinessProjectTitleBarView.GeneralTitleBarOnclickListener {

    /**
     * UI
     */
    @ViewInject(R.id.title_business_deta)
    private BusinessProjectTitleBarView titleBarView;
    @ViewInject(R.id.wv_ad_content)
    private WebView mWebContent;


    /**
     * 数据存储
     */
    private String url;

    /**
     *
     */

    public static void start(Context context, String url) {
        Intent intent = new Intent(context, NewsDetaActivity.class);
        intent.putExtra("url", url);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_deta);
        x.view().inject(this);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.tab_bg);// 通知栏所需颜色
        url = getIntent().getStringExtra("url");
        initView();

    }


    /**
     * 加载数据
     */
    private void initData() {
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


//        final Activity activity = (Activity) mContext;
//        mWebContent.setWebChromeClient(new WebChromeClient() {
//            public void onProgressChanged(WebView view, int progress) {
//                // Activities and WebViews measure progress with different scales.
//                // The progress meter will automatically disappear when we reach 100%
////                activity.setProgress(progress * 1000);
//                if (progress == 100) {
//                    pd.dismiss();
//                    Toast.makeText(mContext, progress + "   进度", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        mWebContent.setWebViewClient(new WebViewClient() {
//            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                Toast.makeText(mContext, "Oh no! " + description, Toast.LENGTH_SHORT).show();
//            }
//        });


        initView();
    }


    private void initView() {

        titleBarView.setData("4", "");
        titleBarView.setGeneralTitleBarOnclickListener(this);

        if (url != null && !"".equals(url)) {
            mWebContent.loadUrl(url);

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

    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
