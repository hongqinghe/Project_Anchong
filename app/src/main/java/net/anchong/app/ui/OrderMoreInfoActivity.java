package net.anchong.app.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.OrderInfoResponse;
import net.anchong.app.view.GeneralTitleBarView;
import net.anchong.app.view.OrderGoodsItemView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by baishixin on 16/5/19.
 */
public class OrderMoreInfoActivity extends BaseActivity {

    /**
     * 页面数据展示
     */
    @ViewInject(R.id.gtbv_order_more_info)
    private GeneralTitleBarView mGeneralTitleBarView;

    //订单状态
    @ViewInject(R.id.tv_order_more_state)
    private TextView mTextView_orderState;

    //订单编号
    @ViewInject(R.id.tv_order_more_num)
    private TextView mTextView_orderNum;

    //支付时间
    @ViewInject(R.id.tv_order_more_date)
    private TextView mTextView_orderDate;

    //收货人
    @ViewInject(R.id.tv_order_more_name)
    private TextView mTextView_orderName;

    //联系电话
    @ViewInject(R.id.tv_order_more_phone)
    private TextView mTextView_orderPhone;

    //收货地址
    @ViewInject(R.id.tv_order_more_address)
    private TextView mTextView_orderAddress;

    //发票抬头
    @ViewInject(R.id.tv_order_more_invoice)
    private TextView mTextView_orderInvoice;

    //店铺名称
    @ViewInject(R.id.tv_order_more_shop_name)
    private TextView mTextView_orderShopName;

    //店铺显示订单状态
    @ViewInject(R.id.tv_order_more_shop_state)
    private TextView mTextView_orderShopState;

    //购买商品总数
    @ViewInject(R.id.tv_order_more_goods_num)
    private TextView mTextView_orderGoodsNum;

    //订单总价
    @ViewInject(R.id.tv_order_more_goods_price)
    private TextView mTextView_orderGoodsPrice;

    //运费
    @ViewInject(R.id.tv_order_more_freight)
    private TextView mTextView_orderFreight;

    @ViewInject(R.id.tv_action1)
    private TextView action1;
    @ViewInject(R.id.tv_action2)
    private TextView action2;

    //装商品信息的LinearLayout
    @ViewInject(R.id.ll_goodslist)
    private LinearLayout goodsList;

    /**
     * 数据存储部分
     */
    //订单实体
    private OrderInfoResponse.ResultDataEntity.ListEntity listBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_more_info);
        x.view().inject(this);

        mGeneralTitleBarView.setData("订单详情", "");
        listBean = (OrderInfoResponse.ResultDataEntity.ListEntity) getIntent().getSerializableExtra("model");
        //如果订单实体对象为空，加载空视图
        if (listBean == null) {

        } else if (listBean.getGoods() == null || listBean.getGoods().size() <= 0) {
            //如果订单实体下的货品列表为空，加载空视图。
        } else {
            initView();
        }
    }

    private void initView() {
        if (!TextUtils.isEmpty(listBean.getState())) {
            String state = listBean.getState();
            switch (state) {
                //待付款状态
                case "1":
                    mTextView_orderState.setText(getResources().getString(R.string.msg_obligations_order));
                    mTextView_orderShopState.setText(getResources().getString(R.string.msg_obligations_order));
                    action1.setText(getResources().getString(R.string.msg_cancle_order));
                    action1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("取消订单");
                        }
                    });
                    action2.setText(getResources().getString(R.string.msg_order_pay));
                    action2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("点击付款");
                        }
                    });
                    break;
                //待发货状态
                case "2":
                    mTextView_orderState.setText(getResources().getString(R.string.msg_send_order));
                    mTextView_orderShopState.setText(getResources().getString(R.string.msg_send_order));
                    action1.setText(getResources().getString(R.string.msg_cancle_order));
                    action1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("取消订单");
                        }
                    });
                    action2.setVisibility(View.GONE);
                    break;
                //待收货状态
                case "3":
                    mTextView_orderState.setText(getResources().getString(R.string.msg_receive_order));
                    mTextView_orderShopState.setText(getResources().getString(R.string.msg_receive_order));
                    action1.setText(getResources().getString(R.string.msg_order_look));
                    action1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("查看物流");
                        }
                    });
                    action2.setText(getResources().getString(R.string.msg_order_receive_ok));
                    action2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("确认收货");
                        }
                    });
                    break;
                //待审核状态
                case "4":
                    mTextView_orderState.setText(getResources().getString(R.string.msg_check_order));
                    mTextView_orderShopState.setText(getResources().getString(R.string.msg_check_order));
                    action1.setText(getResources().getString(R.string.msg_cancle_order));
                    action1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("取消订单");
                        }
                    });
                    action2.setVisibility(View.GONE);
                    break;
                case "5":
                    mTextView_orderState.setText(getResources().getString(R.string.msg_refund_order));
                    mTextView_orderShopState.setText(getResources().getString(R.string.msg_refund_order));
                    action1.setText(getResources().getString(R.string.msg_cancle_order));
                    action1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("取消订单");
                        }
                    });
                    action2.setVisibility(View.GONE);
                    break;
                case "6":
                    mTextView_orderState.setText(getResources().getString(R.string.msg_close_order));
                    mTextView_orderShopState.setText(getResources().getString(R.string.msg_close_order));
                    action1.setText(getResources().getString(R.string.msg_cancle_order));
                    action1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("取消订单");
                        }
                    });
                    action2.setVisibility(View.GONE);
                    break;
                case "7":
                    mTextView_orderState.setText(getResources().getString(R.string.msg_ok_order));
                    mTextView_orderShopState.setText(getResources().getString(R.string.msg_ok_order));
                    action1.setText(getResources().getString(R.string.msg_cancle_order));
                    action1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showMessage("取消订单");
                        }
                    });
                    action2.setVisibility(View.GONE);
                    break;
            }
        }
        //设置订单编号
        if (!TextUtils.isEmpty(listBean.getOrder_num())) {
            mTextView_orderNum.setText(listBean.getOrder_num());
        } else {
            mTextView_orderNum.setText("");
        }
        //设置订单创建时间
        if (!TextUtils.isEmpty(listBean.getCreated_at())) {
            mTextView_orderDate.setText(listBean.getCreated_at());
        } else {
            mTextView_orderDate.setText("");
        }
        //设置收货人姓名
        if (!TextUtils.isEmpty(listBean.getName())) {
            mTextView_orderName.setText("收货人：" + listBean.getName());
        } else {
            mTextView_orderName.setText("收货人：" + "");
        }
        //设置收货人电话
        if (!TextUtils.isEmpty(listBean.getPhone())) {
            mTextView_orderPhone.setText("联系电话：" + listBean.getPhone());
        } else {
            mTextView_orderPhone.setText("联系电话：" + "");
        }
        //设置收货人地址
        if (!TextUtils.isEmpty(listBean.getAddress())) {
            mTextView_orderAddress.setText("收货地址：" + listBean.getAddress());
        } else {
            mTextView_orderAddress.setText("收货地址：" + "");
        }
        //设置发票抬头
        if (!TextUtils.isEmpty(listBean.getInvoice())) {
            mTextView_orderInvoice.setText("发票抬头：" + listBean.getInvoice());
        } else {
            mTextView_orderInvoice.setText("发票抬头：" + "");
        }
        //设置店铺名称
        if (!TextUtils.isEmpty(listBean.getSname())) {
            mTextView_orderShopName.setText(listBean.getSname());
        } else {
            mTextView_orderShopName.setText("");
        }

        //货品总数量
        int goodsTotalNum = 0;
        //根据货品数量设置货品
        List<OrderInfoResponse.ResultDataEntity.ListEntity.GoodsEntity> goodsBeanList = listBean.getGoods();
        if (goodsBeanList != null && goodsBeanList.size() > 0) {
            for (int i = 0; i < goodsBeanList.size(); i++) {
                OrderGoodsItemView ogiv = new OrderGoodsItemView(this);
                ogiv.setData(goodsBeanList.get(i));
                //累加每个商品的购买数量
                goodsTotalNum += Integer.parseInt(goodsBeanList.get(i).getGoods_num());
                //将每个货品添加到LinearLayout中
                goodsList.addView(ogiv);
            }
        }

        //设置货品总数量
        mTextView_orderGoodsNum.setText("共" + goodsTotalNum + "件商品");
        //设置订单总价钱
        if (!TextUtils.isEmpty(listBean.getTotal_price())) {
            mTextView_orderGoodsPrice.setText("￥" + listBean.getTotal_price());
        } else {
            mTextView_orderGoodsPrice.setText("0.00");
        }

    }


    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
