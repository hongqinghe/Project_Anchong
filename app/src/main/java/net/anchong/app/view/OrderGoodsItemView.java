package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.OrderInfoResponse;
import net.anchong.app.entity.response.model.ShopOrderResponse;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 订单界面的每个商品
 * Created by baishixin on 16/4/28.
 */
public class OrderGoodsItemView extends LinearLayout {

    private Context mContext;

    //货品图片
    @ViewInject(R.id.goods_image)
    private SimpleDraweeView image;
    //货品标题
    @ViewInject(R.id.tv_goods_title)
    private TextView goodsTitle;
    //颜色分类
    @ViewInject(R.id.tv_goods_typetag)
    private TextView goodsTypeTag;
    //商品总价
    @ViewInject(R.id.tv_goods_pricetotal)
    private TextView goodsPriceTotal;
    //商品数量
    @ViewInject(R.id.tv_goods_num)
    private TextView goodsNum;

    private OrderInfoResponse.ResultDataEntity.ListEntity goods;


    public OrderGoodsItemView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public OrderGoodsItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public OrderGoodsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        Fresco.initialize(mContext);
        View.inflate(mContext, R.layout.view_list_order_goods, this);
        x.view().inject(this);

    }

    private void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void setData(OrderInfoResponse.ResultDataEntity.ListEntity.GoodsEntity goods) {
        if (goods != null) {
            if (!TextUtils.isEmpty(goods.getImg())) {
                image.setImageURI(Uri.parse(goods.getImg()));
            }
            if (!TextUtils.isEmpty(goods.getGoods_name())) {
                goodsTitle.setText(goods.getGoods_name());
            } else {
                goodsTitle.setText("");
            }
            if (!TextUtils.isEmpty(goods.getGoods_type())) {
                goodsTypeTag.setText("颜色分类：" + goods.getGoods_type());
            } else {
                goodsTypeTag.setText("颜色分类：");
            }
            if (!TextUtils.isEmpty(goods.getGoods_price())) {
                goodsPriceTotal.setText("￥" + goods.getGoods_price());
            } else {
                goodsPriceTotal.setText("");
            }
            if (!TextUtils.isEmpty(goods.getGoods_num())) {
                goodsNum.setText("x" + goods.getGoods_num());
            } else {
                goodsNum.setText("");
            }
        }
    }

    public void setData(ShopOrderResponse.ResultDataEntity.Order.Goods goods) {
        if (goods != null) {
            if (!TextUtils.isEmpty(goods.getImg())) {
                image.setImageURI(Uri.parse(goods.getImg()));
            }
            if (!TextUtils.isEmpty(goods.getGoods_name())) {
                goodsTitle.setText(goods.getGoods_name());
            } else {
                goodsTitle.setText("");
            }
            if (!TextUtils.isEmpty(goods.getGoods_type())) {
                goodsTypeTag.setText("颜色分类：" + goods.getGoods_type());
            } else {
                goodsTypeTag.setText("颜色分类：");
            }
            if (!TextUtils.isEmpty(goods.getGoods_price())) {
                goodsPriceTotal.setText("￥" + goods.getGoods_price());
            } else {
                goodsPriceTotal.setText("");
            }
            if (!TextUtils.isEmpty(goods.getGoods_num())) {
                goodsNum.setText("x" + goods.getGoods_num());
            } else {
                goodsNum.setText("");
            }
        }
    }

}
