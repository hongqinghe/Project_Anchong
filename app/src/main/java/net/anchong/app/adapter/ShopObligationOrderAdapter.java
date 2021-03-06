package net.anchong.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.ShopOrderResponse;
import net.anchong.app.fragment.ShopObligationOrderFragment;
import net.anchong.app.view.OrderGoodsItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baishixin on 16/5/18.
 */
public class ShopObligationOrderAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ShopObligationOrderFragment shopObligationOrderFragment;
    private ShopOrderResponse shopOrderResponse;
    private List<ShopOrderResponse.ResultDataEntity.Order> orders = new ArrayList<>();

    public ShopObligationOrderAdapter(Context mContext, LayoutInflater mInflater, ShopObligationOrderFragment shopObligationOrderFragment, ShopOrderResponse shopOrderResponse, List<ShopOrderResponse.ResultDataEntity.Order> orders) {
        this.mContext = mContext;
        this.mInflater = mInflater;
        this.shopObligationOrderFragment = shopObligationOrderFragment;
        this.shopOrderResponse = shopOrderResponse;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        if (shopOrderResponse == null) {
            return 0;
        }
        if (orders == null) {
            return 0;
        }
        if (orders.size() > 0) {
            return orders.size();
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_shop_all_order, null);
            vh.shopName = (TextView) convertView.findViewById(R.id.tv_shop_name);
            vh.orderNumber = (TextView) convertView.findViewById(R.id.tv_order_number);
            vh.orderState = (TextView) convertView.findViewById(R.id.tv_order_state);
            vh.goodsList = (LinearLayout) convertView.findViewById(R.id.ll_goodslist);
            vh.showGoodsPriceTotal = (TextView) convertView.findViewById(R.id.tv_goods_showprice);
            vh.showGoodsNum = (TextView) convertView.findViewById(R.id.tv_goods_shownum);
            vh.action = (LinearLayout) convertView.findViewById(R.id.ll_order_action);
            vh.action1 = (ImageView) convertView.findViewById(R.id.iv_action1);
            vh.action2 = (ImageView) convertView.findViewById(R.id.iv_action2);

            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();
        vh.goodsList.removeAllViews();

        //先获取到每个订单
        ShopOrderResponse.ResultDataEntity.Order orderItem = orders.get(position);
        //设置显示的商铺名称
        if (!TextUtils.isEmpty(orderItem.getName())) {
            vh.shopName.setText(orderItem.getName());
        }

        List<ShopOrderResponse.ResultDataEntity.Order.Goods> goodsBeanList = orderItem.getGoods();
        int goodsTotalNum = 0;
        for (int i = 0; i < goodsBeanList.size(); i++) {
            OrderGoodsItemView ogiv = new OrderGoodsItemView(mContext);
            ogiv.setData(goodsBeanList.get(i));
            //累加每个商品的购买数量
            goodsTotalNum += Integer.parseInt(goodsBeanList.get(i).getGoods_num());
            vh.goodsList.addView(ogiv);
        }
        //设置显示订单编号
        if (!TextUtils.isEmpty(orderItem.getOrder_num())) {
            vh.orderNumber.setText(orderItem.getOrder_num());
        } else {
            vh.orderNumber.setText("");
        }

        vh.showGoodsNum.setText("共" + goodsTotalNum + "件商品");
        vh.showGoodsPriceTotal.setText("合计：" + orders.get(position).getTotal_price());


        vh.orderState.setText("等待买家付款");
        vh.action.setVisibility(View.VISIBLE);
        vh.action1.setVisibility(View.VISIBLE);
        vh.action2.setVisibility(View.VISIBLE);
        vh.action1.setImageDrawable(mContext.getResources().getDrawable(R.drawable.close_order));
        vh.action2.setVisibility(View.GONE);
        vh.action1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopObligationOrderFragment.orderAction("6", orders.get(position));
            }
        });


        return convertView;
    }

    class ViewHolder {
        //店铺名称
        TextView shopName;
        //订单编号
        TextView orderNumber;
        //订单状态
        TextView orderState;
        //当前店铺下所有的货品
        LinearLayout goodsList;
        //展示商品总价
        TextView showGoodsPriceTotal;
        //展示商品数量
        TextView showGoodsNum;
        //订单操纵
        LinearLayout action;
        //动作1
        ImageView action1;
        //动作2
        ImageView action2;
    }
}
