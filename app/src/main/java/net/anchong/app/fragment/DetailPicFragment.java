package net.anchong.app.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.ui.GoodsInformationActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 发包工程
 * Created by baishixin on 16/3/8.
 */
public class DetailPicFragment extends BaseFragment {

    private static final int INITVIEW = 1;
    private LayoutInflater mInflater;
    private View view = null;
    private Context mContext;
    public static ProgressDialog pd;

    private String picPath = "";

    //    @ViewInject(R.id.imageView)
//    private LargeImageView imageView;
    @ViewInject(R.id.wv_content)
    private WebView mWebContent;

//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case INITVIEW:
//                    InputStream is = (InputStream) msg.obj;
//                    initView(is);
//                    break;
//            }
//        }
//    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /// fresco
        Fresco.initialize(mContext);
        mInflater = inflater;
        view = inflater.inflate(R.layout.fragment_detailpic, container, false);
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
    private void initData() {
        pd.show();
        picPath = GoodsInformationActivity.goodsINfoResultModel.getResultData().getDetailpic();
//        Logger.i("商品详情页：" + picPath);
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


        final Activity activity = (Activity) mContext;
        mWebContent.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                // Activities and WebViews measure progress with different scales.
                // The progress meter will automatically disappear when we reach 100%
//                activity.setProgress(progress * 1000);
                if (progress == 100) {
                    pd.dismiss();
                    Toast.makeText(mContext, progress + "   进度", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mWebContent.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(mContext, "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });


        initView();
    }

    public static String getFileName(String path) {
        int start = path.lastIndexOf("/");
        return path.substring(start);
    }

    private void initView() {

        if (picPath != null && !"".equals(picPath)) {
            mWebContent.loadUrl(picPath);

            // 屏蔽某些手机长按事件,复制奔溃(另一种代替方法初始化webview传getApplicationContext()替换成NewsDetailActivity.this)
            mWebContent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (Build.VERSION.SDK_INT <= 17) {
                        return true;
                    }
                    Logger.i("应该下载图片");
                    return false;
                }
            });
        }

//        pd.dismiss();
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
