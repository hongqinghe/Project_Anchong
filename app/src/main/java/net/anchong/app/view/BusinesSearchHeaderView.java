package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.anchong.app.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 商机筛选时候ListView 顶部自动显示隐藏的筛选view
 * Created by baishixin on 16/4/19.
 */
public class BusinesSearchHeaderView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.tv_headview_type)
    private TextView type;
    @ViewInject(R.id.tv_headview_tag)
    private TextView tag;


    public BusinesSearchHeaderView(Context context) {
        super(context);
        this.mContext = context;
        initData();
        initView();
    }

    private void initData() {

    }

    public BusinesSearchHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initData();
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_headerview_business_screen, this);
        x.view().inject(this);

    }


}
