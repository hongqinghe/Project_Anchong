package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.CorrelationResponse;
import net.anchong.app.entity.response.model.GoodsAdvertResponse;
import net.anchong.app.entity.response.model.GoodsListResponseModel;
import net.anchong.app.uitls.ImageUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/2/11.
 */
public class MallProductView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.sdv_goodslist_icon)
    private SimpleDraweeView simpleDraweeView;
    @ViewInject(R.id.tv_goodslist_title)
    private TextView title;
    @ViewInject(R.id.tv_goodslist_price)
    private TextView price;
    @ViewInject(R.id.tv_goodslist_vipprice)
    private TextView vipPrice;

    public MallProductView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public MallProductView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
//        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = mInflater.inflate(R.layout.view_goods, null);

        View.inflate(mContext, R.layout.view_goods, this);

//        this.addView(v);
        x.view().inject(this);
    }

    /**
     * 设置推荐商品
     *
     * @param listEntity
     */
    public void setData(CorrelationResponse.ResultDataEntity.ListEntity listEntity) {
        simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgUri(listEntity.getPic().toString().trim())));
        title.setText(listEntity.getTitle().toString().trim());
        price.setText(listEntity.getPrice());
        vipPrice.setText("请登录后查看");
    }

    public void setData(GoodsListResponseModel.ResultDataEntity.ListEntity listEntity, int showPrice) {
        simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgUri(listEntity.getPic().toString().trim())));
        title.setText(listEntity.getTitle().toString().trim());
        price.setText(listEntity.getPrice());
        if (showPrice == 0) {
//            tv_vipPrice.setVisibility(View.GONE);
            vipPrice.setText("请登录后查看");
        } else {
            vipPrice.setText(listEntity.getVip_price());
        }
    }

    public void setData(GoodsAdvertResponse.ResultDataEntity.GoodsEntity listEntity, int showPrice) {
        simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgUri(listEntity.getPic().toString().trim())));
        title.setText(listEntity.getTitle().toString().trim());
        price.setText(listEntity.getPrice());
        if (showPrice == 0) {
//            tv_vipPrice.setVisibility(View.GONE);
            vipPrice.setText("请登录后查看");
        } else {
            vipPrice.setText(listEntity.getVip_price());
        }
    }


}
