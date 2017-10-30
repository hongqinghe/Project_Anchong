package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 聊聊界面的标题栏
 * Created by baishixin on 16/3/16.
 */
public class TalkTitleBarView extends LinearLayout implements View.OnClickListener {

    private Context mContext;

    @ViewInject(R.id.sdv_talk_headpic)
    private SimpleDraweeView headPic;

    @ViewInject(R.id.iv_center)
    private ImageView center;

    @ViewInject(R.id.titlebar_general_right)
    private TextView mGeneralTitleBarRight;

    public TalkTitleBarView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TalkTitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_talk_titlebar, this);
        Fresco.initialize(mContext);
        x.view().inject(this);
        headPic.setOnClickListener(this);
        center.setOnClickListener(this);
        mGeneralTitleBarRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdv_talk_headpic:
                mGeneralTitleBarOnclickListener.leftClick();
                break;
            case R.id.iv_center:
                mGeneralTitleBarOnclickListener.centerClick();
                break;
            case R.id.titlebar_general_right:
                mGeneralTitleBarOnclickListener.rightClick();
                break;
        }
    }

    public void setGeneralTitleBarOnclickListener(GeneralTitleBarOnclickListener listener) {
        mGeneralTitleBarOnclickListener = listener;
    }

    private GeneralTitleBarOnclickListener mGeneralTitleBarOnclickListener;


    public interface GeneralTitleBarOnclickListener {
        void leftClick();

        void centerClick();

        void rightClick();
    }

    /**
     * 为通用的标题栏设置标题和动作
     *
     * @param title  页面标题
     * @param action 相应动作的文字说明
     */
    public void setData(String title, String action) {
        if (!TextUtils.isEmpty(title)) {
//            mGeneralTitleBarTitle.setText(title.toString().trim());
        }
        if (!TextUtils.isEmpty(action)) {
            mGeneralTitleBarRight.setText(action.toString().trim());
        }
    }

    public void setHead(String url) {
        if (!TextUtils.isEmpty(url)) {
            headPic.setImageURI(Uri.parse(url));
        }
    }
}
