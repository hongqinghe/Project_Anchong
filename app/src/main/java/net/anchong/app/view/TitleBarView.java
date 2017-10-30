
package net.anchong.app.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.anchong.app.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by lishuai on 16/2/3.
 */
public class TitleBarView extends LinearLayout {
    private Context mContext;


    @ViewInject(R.id.titlebar_title)
    private TextView mTitlebarTitle;

    public TitleBarView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_titlebar, this);
        x.view().inject(this);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mTitlebarTitle.setText(title);
        }
    }


    public void setTitleBarClickListener(TitleBarClickListener listener) {
        mTitleBarClickListener = listener;
    }

    private TitleBarClickListener mTitleBarClickListener;

    public interface TitleBarClickListener {
        void leftClick();

        void rightClick();
    }

}
