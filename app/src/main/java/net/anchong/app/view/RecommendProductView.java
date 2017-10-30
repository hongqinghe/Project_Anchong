package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.CorrelationResponse;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 商品详情页推荐商品，配套商品自定义View
 * Created by baishixin on 16/2/11.
 */
public class RecommendProductView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.ll_recommend_one)
    private LinearLayout recommend1;
    @ViewInject(R.id.ll_recommend_two)
    private LinearLayout recommend2;

    @ViewInject(R.id.mpv_recommend_one_up)
    private MallProductView oneUp;
    @ViewInject(R.id.mpv_recommend_one_down)
    private MallProductView oneDown;
    @ViewInject(R.id.mpv_recommend_two_up)
    private MallProductView twoUp;
    @ViewInject(R.id.mpv_recommend_two_down)
    private MallProductView twoDown;

    public RecommendProductView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public RecommendProductView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
//        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = mInflater.inflate(R.layout.view_goods, null);

        View.inflate(mContext, R.layout.view_goods_info_recommend, this);

//        this.addView(v);
        x.view().inject(this);
    }

    public void setData(List<CorrelationResponse.ResultDataEntity.ListEntity> list) {
        oneUp.setVisibility(INVISIBLE);
        oneDown.setVisibility(INVISIBLE);
        twoUp.setVisibility(INVISIBLE);
        twoDown.setVisibility(INVISIBLE);
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                switch (i) {
                    case 0:
                        oneUp.setVisibility(VISIBLE);
                        oneUp.setData(list.get(i));
                        break;
                    case 1:
                        oneDown.setVisibility(VISIBLE);
                        oneDown.setData(list.get(i));
                        break;
                    case 2:
                        twoUp.setVisibility(VISIBLE);
                        twoUp.setData(list.get(i));
                        break;
                    case 3:
                        twoDown.setVisibility(VISIBLE);
                        twoDown.setData(list.get(i));
                        break;

                }
            }
        } else {
            recommend1.setVisibility(View.INVISIBLE);
            recommend2.setVisibility(View.INVISIBLE);
        }

    }


}
