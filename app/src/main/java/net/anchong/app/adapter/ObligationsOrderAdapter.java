package net.anchong.app.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.OrderInfoResponse;
import net.anchong.app.fragment.ObligationsOrderFragment;
import net.anchong.app.ui.OrderInfoActivity;
import net.anchong.app.view.OrderGoodsItemView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baishixin on 16/5/18.
 */
public class ObligationsOrderAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ObligationsOrderFragment mFragmetn;
    private OrderInfoResponse orderInfoResponse;
    private List<OrderInfoResponse.ResultDataEntity.ListEntity> orders = new ArrayList<>();
    private OrderInfoActivity orderInfoActivity;

    public ObligationsOrderAdapter(Context mContext, LayoutInflater mInflater, ObligationsOrderFragment mFragmetn, OrderInfoResponse orderInfoResponse, List<OrderInfoResponse.ResultDataEntity.ListEntity> orders,OrderInfoActivity orderInfoActivity) {
        this.mContext = mContext;
        this.mInflater = mInflater;
        this.mFragmetn = mFragmetn;
        this.orderInfoResponse = orderInfoResponse;
        this.orders = orders;
        this.orderInfoActivity = orderInfoActivity;
    }

    @Override
    public int getCount() {
        if (orderInfoResponse == null) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_all_order, null);
            vh.shopName = (TextView) convertView.findViewById(R.id.tv_shop_name);
            vh.orderState = (TextView) convertView.findViewById(R.id.tv_order_state);
            vh.goodsList = (LinearLayout) convertView.findViewById(R.id.ll_goodslist);
            vh.showGoodsPriceTotal = (TextView) convertView.findViewById(R.id.tv_goods_showprice);
            vh.showGoodsNum = (TextView) convertView.findViewById(R.id.tv_goods_shownum);
            vh.action = (LinearLayout) convertView.findViewById(R.id.ll_order_action);
            vh.action1 = (TextView) convertView.findViewById(R.id.tv_action1);
            vh.action2 = (TextView) convertView.findViewById(R.id.tv_action2);

            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();
        vh.goodsList.removeAllViews();

        //先获取到每个订单
        final OrderInfoResponse.ResultDataEntity.ListEntity orderItem = orders.get(position);
        //设置显示的商铺名称
        if (TextUtils.isEmpty(orderItem.getSname())) {
            vh.shopName.setText(orderItem.getSname());
        }

        List<OrderInfoResponse.ResultDataEntity.ListEntity.GoodsEntity> goodsBeanList = orderItem.getGoods();
        int goodsTotalNum = 0;
        for (int i = 0; i < goodsBeanList.size(); i++) {
            OrderGoodsItemView ogiv = new OrderGoodsItemView(mContext);
            ogiv.setData(goodsBeanList.get(i));
            //累加每个商品的购买数量
            goodsTotalNum += Integer.parseInt(goodsBeanList.get(i).getGoods_num());
            vh.goodsList.addView(ogiv);
        }

        vh.showGoodsNum.setText("共" + goodsTotalNum + "件商品");
        vh.showGoodsPriceTotal.setText("合计：" + orders.get(position).getTotal_price());

                vh.orderState.setText("等待买家付款");
                vh.action.setVisibility(View.VISIBLE);
                vh.action1.setText("取消订单");
                vh.action1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "点击取消订单", Toast.LENGTH_SHORT).show();
                    }
                });
                vh.action2.setText("付款");
                vh.action2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orderInfoActivity.getOrderPayInfo(orderItem.getOrder_id(), orderItem.getBody(), orderItem.getTotal_price());
                    }
                });

        return convertView;
    }

    class ViewHolder {
        //店铺名称
        TextView shopName;
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
        TextView action1;
        //动作2
        TextView action2;
    }
}
