package net.anchong.app.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.anchong.app.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 *
 * Created by baishixin on 16/3/16.
 */
public class TransparentTitleBarView extends LinearLayout implements View.OnClickListener {

    private Context mContext;

    @ViewInject(R.id.titlebar_general_left)
    private ImageView mGeneralTitleBarLeft;

    @ViewInject(R.id.titlebar_general_title)
    private TextView mGeneralTitleBarTitle;

    @ViewInject(R.id.titlebar_general_right)
    private TextView mGeneralTitleBarRight;

    public TransparentTitleBarView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TransparentTitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_transparent_titlebar, this);
        x.view().inject(this);
        mGeneralTitleBarLeft.setOnClickListener(this);
        mGeneralTitleBarRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.titlebar_general_left:
                mGeneralTitleBarOnclickListener.leftClick();
                break;
            case R.id.titlebar_general_right:
                mGeneralTitleBarOnclickListener.rightClick();
                break;
        }
    }

    public void setGeneralTitleBarOnclickListener(TransparentTitleBarOnclickListener listener) {
        mGeneralTitleBarOnclickListener = listener;
    }

    private TransparentTitleBarOnclickListener mGeneralTitleBarOnclickListener;


    public interface TransparentTitleBarOnclickListener {
        void leftClick();

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
            mGeneralTitleBarTitle.setText(title.toString().trim());
        }
        if (!TextUtils.isEmpty(action)) {
            mGeneralTitleBarRight.setText(action.toString().trim());
        }
    }
}
