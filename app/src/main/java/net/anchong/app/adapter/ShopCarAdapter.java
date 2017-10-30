package net.anchong.app.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.ShopCarResponseModel;
import net.anchong.app.ui.ShopCarActivity;
import net.anchong.app.view.ShopCarModelView;

import java.util.List;

/**
 * Created by baishixin on 16/4/28.
 */
public class ShopCarAdapter extends BaseAdapter {

    private ShopCarActivity shopCarActivity;
    private List<ShopCarResponseModel.ResultDataBean> shopList;
    private LayoutInflater mInflater;

    public ShopCarAdapter(ShopCarActivity shopCarActivity, LayoutInflater mInflater, List<ShopCarResponseModel.ResultDataBean> shopList) {
        this.shopCarActivity = shopCarActivity;
        this.mInflater = mInflater;
        this.shopList = shopList;
    }

    public void setShopList(List<ShopCarResponseModel.ResultDataBean> shopList) {
        this.shopList = shopList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (shopList == null || shopList.size() <= 0) {
            return 0;
        }
        return shopList.size();
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

//        if (convertView == null) {
        convertView = mInflater.inflate(R.layout.item_listview_shopcar, null);
//        }

        final CheckBox shopCheck = (CheckBox) convertView.findViewById(R.id.checkbox_shop_all);
        TextView shopName = (TextView) convertView.findViewById(R.id.tv_shop_name);
        final ShopCarResponseModel.ResultDataBean shop = shopList.get(position);
        //设置店铺名称
        if (TextUtils.isEmpty(shop.getSname())) {
            shopName.setText("");
        } else {
            shopName.setText(shop.getSname().toString().trim());
        }
        if (shop.isSelect()) {
            shopCheck.setChecked(true);
        }

        //组装每个商品视图的LinearLayout
        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.ll_shopcar_model);

        //解析购物车中每个店铺下面有多少个商品在购物车中
        List<ShopCarResponseModel.ResultDataBean.GoodsBean> goodsList = shop.getGoods();
        for (int i = 0; i < goodsList.size(); i++) {
            ShopCarResponseModel.ResultDataBean.GoodsBean goods = goodsList.get(i);
            ShopCarModelView shopCarModelView = new ShopCarModelView(shopCarActivity);
            shopCarModelView.setData(goods, shopCheck, linearLayout);
            linearLayout.addView(shopCarModelView);
        }

        shopCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Logger.i("状态改变");
                float price = 0.0f;
                int number = 0;
                if (isChecked) {//选中状态
                    String flag = (String) shopCheck.getTag();
                    if ("fromChild".equals(flag)) {
                        shopCheck.setTag("");
                        return;
                    } else {
                        for (int i = 0; i < linearLayout.getChildCount(); i++) {
                            ShopCarModelView scmv = (ShopCarModelView) linearLayout.getChildAt(i);
                            if (!scmv.getClildCheckBox().isChecked()) {
                                price += scmv.getPrice();
                                number += scmv.getNumber();
                            }
                            scmv.setCheckBox(isChecked);
                            scmv.setIsSelect(true);
                        }
                        shop.setIsSelect(isChecked);
                        shopCarActivity.addPrice(price, number);
                        price = 0.0f;
                        number = 0;
                    }
                } else {//取消选中所有商品
                    String flag = (String) shopCheck.getTag();
                    if ("fromChild".equals(flag)) {
                        shopCheck.setTag("");
                        return;
                    } else {
                        for (int i = 0; i < linearLayout.getChildCount(); i++) {
                            ShopCarModelView scmv = (ShopCarModelView) linearLayout.getChildAt(i);
                            if (!scmv.getClildCheckBox().isChecked()) {
                                continue;
                            }
                            price += scmv.getPrice();
                            number += scmv.getNumber();
                            scmv.setCheckBox(isChecked);
                            scmv.setIsSelect(false);
                        }
                        shop.setIsSelect(isChecked);
                        shopCarActivity.delPrice(price, number);
                        price = 0.0f;
                        number = 0;
                    }
                }
            }
        });

        return convertView;
    }


}
