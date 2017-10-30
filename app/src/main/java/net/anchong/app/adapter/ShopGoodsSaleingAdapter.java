package net.anchong.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.ShopGoodsShowResponse;
import net.anchong.app.fragment.SaleingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baishixin on 16/5/18.
 */
public class ShopGoodsSaleingAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ShopGoodsShowResponse.GoodsList.Goods> goodsList = new ArrayList<>();
    private SaleingFragment saleingFragment;

    public ShopGoodsSaleingAdapter(Context mContext, LayoutInflater mInflater, List<ShopGoodsShowResponse.GoodsList.Goods> goodsList, SaleingFragment saleingFragment) {
        this.mContext = mContext;
        this.mInflater = mInflater;
        this.goodsList = goodsList;
        this.saleingFragment = saleingFragment;
    }

    @Override
    public int getCount() {
        if (goodsList == null) {
            return 0;
        }

        if (goodsList.size() > 0) {
            return goodsList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_shop_goods_manager_saleing, null);
            vh.sdv = (SimpleDraweeView) convertView.findViewById(R.id.sdv_shop_goods_saleing_img);
            vh.title = (TextView) convertView.findViewById(R.id.tv_goods_title);
            vh.price = (TextView) convertView.findViewById(R.id.tv_shop_goods_saleing_price);
            vh.vip_price = (TextView) convertView.findViewById(R.id.tv_shop_goods_saleing_vip_price);
            vh.saleNum = (TextView) convertView.findViewById(R.id.tv_shop_goods_saleing_salenum);
            vh.goodsNum = (TextView) convertView.findViewById(R.id.tv_shop_goods_saleing_goodsnum);
            vh.undercarriage = (ImageView) convertView.findViewById(R.id.iv_undercarriage);
            vh.delete_red = (ImageView) convertView.findViewById(R.id.iv_delete_red);


            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();
        vh.sdv.setImageURI(Uri.parse(""));
        final ShopGoodsShowResponse.GoodsList.Goods goods = goodsList.get(position);
        if (goods.getGoods_img() != null && !"".equals(goods.getGoods_img())) {
            vh.sdv.setImageURI(Uri.parse(goods.getGoods_img()));
        }
        vh.title.setText(goods.getTitle());
        vh.price.setText("市场价:" + goods.getMarket_price());
        vh.vip_price.setText("批量采购价:" + goods.getVip_price());
        vh.saleNum.setText("已售:" + goods.getSales());
        vh.goodsNum.setText("库存:" + goods.getGoods_num());
        vh.undercarriage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saleingFragment.goodsAction("1",goods.getGid()+"");
            }
        });
        vh.delete_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saleingFragment.goodsAction("2",goods.getGid()+"");
            }
        });


        return convertView;
    }

    class ViewHolder {
        //商品图片
        SimpleDraweeView sdv;
        //商品名称
        TextView title;
        //商品价格
        TextView price;
        //批量采购价
        TextView vip_price;
        //已售数量
        TextView saleNum;
        //库存数量
        TextView goodsNum;
        //商品下架操作
        ImageView undercarriage;
        //商品删除操作
        ImageView delete_red;
    }
}
