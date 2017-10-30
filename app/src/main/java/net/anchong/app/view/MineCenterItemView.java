package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.anchong.app.R;
import net.anchong.app.ui.BasicInfoActivity;
import net.anchong.app.ui.CertificationActivity;
import net.anchong.app.ui.PublishActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/3/28.
 */
public class MineCenterItemView extends LinearLayout implements View.OnClickListener {

    private Context mContext;
    private LayoutInflater mInflater;

    @ViewInject(R.id.ll_mine_item)
    private LinearLayout mineItem;
    @ViewInject(R.id.tv_mine_item_title)
    private TextView mTextView_title;
    @ViewInject(R.id.tv_mine_item_content)
    private TextView mTextView_content;

    public MineCenterItemView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public MineCenterItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_mine_item, this);
        x.view().inject(this);
        mineItem.setOnClickListener(this);
    }

    public void setData(String title, String content) {
        mTextView_title.setText(title);
        mTextView_content.setText(content);
    }

    @Override
    public void onClick(View v) {
        String title = mTextView_title.getText().toString().trim();
        switch (title) {
            case "个人资料":
                BasicInfoActivity.start(mContext);
                break;
            case "收货地址":
                Toast.makeText(mContext, "收货地址管理", Toast.LENGTH_SHORT).show();
                break;
            case "商家认证":
                CertificationActivity.start(mContext);
                break;
            case "商铺申请":
                Toast.makeText(mContext, "商铺申请", Toast.LENGTH_SHORT).show();
                break;
            case "我的发布":
                PublishActivity.start(mContext);
                break;
        }
    }
}











