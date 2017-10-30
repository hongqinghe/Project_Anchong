package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.CorrelationResponse;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * 商品详情页 推荐商品配套商品 自定义View
 * Created by baishixin on 16/2/11.
 */
public class GoodsRotationImageView extends FrameLayout {

    private static final String TAG = "RotationImageView";
    private Context mContext;

    @ViewInject(R.id.grv_goods_info)
    private GoodsRotationView goodsRotationView;

    public GoodsRotationImageView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public GoodsRotationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_goods_info_rotation, this);
        x.view().inject(this);
    }

    /**
     * 设置推荐商品
     */
    public void setData(List<CorrelationResponse.ResultDataEntity.ListEntity> list) {
        if (list != null && list.size() > 0) {
            goodsRotationView.setData(list);
        }
    }


}
