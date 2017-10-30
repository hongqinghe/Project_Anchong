package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liangfeizc.flowlayout.FlowLayout;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.GoodsParamResponseModel;
import net.anchong.app.ui.GoodsSetParamActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/5/4.
 */
public class GoodsParamView extends LinearLayout implements View.OnClickListener {

    private Context context;
    private GoodsSetParamActivity goodsSetParamActivity;
    @ViewInject(R.id.tv_view_goodsparam_name)
    private TextView tv_name;
    @ViewInject(R.id.flow_layout_tags)
    private FlowLayout flowTags;
    //记录当前标签组是第几个选项
    private int index;


    public GoodsParamView(Context context) {
        super(context);
        this.context = context;
        initView();
    }


    public GoodsParamView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public GoodsParamView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.view_goods_param, this);
        x.view().inject(this);
    }

    public void setData(GoodsParamResponseModel.ResultDataEntity resultDataEntity, String defaultTag, int index, GoodsSetParamActivity goodsSetParamActivity) {
        this.goodsSetParamActivity = goodsSetParamActivity;
        this.index = index;
        if (resultDataEntity != null && resultDataEntity.getValue().size() > 0) {
            tv_name.setText(resultDataEntity.getName().toString().trim());
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            for (int i = 0; i < resultDataEntity.getValue().size(); i++) {
                final TextView tv = new TextView(context);
                tv.setBackgroundResource(R.drawable.text_blue_state_default);
                tv.setPadding(20, 10, 20, 10);
                tv.setText(resultDataEntity.getValue().get(i).toString().trim());
                tv.setTextColor(getResources().getColor(R.color.black));
                tv.setTag(false);
                if (defaultTag.trim().equals(resultDataEntity.getValue().get(i).toString().trim())) {
                    tv.setBackgroundResource(R.drawable.text_red_state_pressed);
                    tv.setTextColor(getResources().getColor(R.color.red));
                    tv.setTag(true);
                }

                tv.setOnClickListener(this);

//                tv.setTag(false);
//                tv.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        boolean tag = (boolean) tv.getTag();
//                        if (tag) {
//                            tv.setBackgroundResource(R.drawable.text_blue_state_default);
//                            tv.setTextColor(getResources().getColor(R.color.black));
//                            tv.setTag(false);
//                        } else {
//                            tv.setBackgroundResource(R.drawable.text_red_state_pressed);
//                            tv.setTextColor(getResources().getColor(R.color.red));
//                            tv.setTag(true);
//                        }
//                    }
//                });
//            tv.setTextSize(40);
                flowTags.addView(tv, params);
            }
        }
    }

    @Override
    public void onClick(View v) {
        TextView tv = (TextView) v;
        boolean tag = (boolean) tv.getTag();
        if (tag) {//如果当前标签已经被选中，什么都不做。

        } else {//如果当前标签为被选中，选中当前标签，撤销其他标签的选中状态
            for (int i = 0; i < flowTags.getChildCount(); i++) {
                TextView t = (TextView) flowTags.getChildAt(i);
                t.setBackgroundResource(R.drawable.text_blue_state_default);
                t.setTextColor(getResources().getColor(R.color.black));
                t.setTag(false);
            }
//            flowTags.getChildCount();
            setTagSelect(tv);
        }
    }

    private void setTagSelect(TextView tv) {
        tv.setBackgroundResource(R.drawable.text_red_state_pressed);
        tv.setTextColor(getResources().getColor(R.color.red));
        tv.setTag(true);
        if (goodsSetParamActivity != null) {
            goodsSetParamActivity.updateByTags(tv.getText().toString(), index);
        }
    }
}
