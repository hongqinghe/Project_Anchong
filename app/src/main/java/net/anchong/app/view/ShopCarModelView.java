package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.ShopCarResponseModel;
import net.anchong.app.ui.ShopCarActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 购物车界面的每个商品
 * Created by baishixin on 16/4/28.
 */
public class ShopCarModelView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.image)
    private SimpleDraweeView image;
    @ViewInject(R.id.goods_name)
    private TextView goodsName;
    @ViewInject(R.id.price)
    private TextView goodsPrice;
    @ViewInject(R.id.order_edit)
    private ShopCarOrderEditTextView goodsNumber;
    @ViewInject(R.id.checkbox)
    private CheckBox checkBox;

    private ShopCarResponseModel.ResultDataBean.GoodsBean goodsBean;

    private CheckBox shopCheck;
    private LinearLayout linearLayout;


    //当前商品是否被选中
    private boolean isSelect = false;

    public ShopCarModelView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ShopCarModelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ShopCarModelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.shoppingcar_listviewitem, this);
        x.view().inject(this);
        checkBox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                float price = Float.parseFloat(goodsPrice.getText().toString().trim());
                int number = goodsNumber.getText();
                boolean isChangeCheck = false;
                if (isSelect) {
                    checkBox.setChecked(false);
                    ((ShopCarActivity) mContext).delPrice(price * number, number);
                    isSelect = false;
                    goodsBean.setIsSelect(false);
                    shopCheck.setTag("");
                    //
                    for (int i = 0; i < linearLayout.getChildCount(); i++) {
                        ShopCarModelView scmv = (ShopCarModelView) linearLayout.getChildAt(i);
                        if (scmv.getClildCheckBox().isChecked()) {
                            isChangeCheck = true;
                            return;
                        }
                    }
                    if (!isChangeCheck) {
                        shopCheck.setTag("fromChild");
                        shopCheck.setChecked(false);
                    }

                } else {
                    checkBox.setChecked(true);
                    //当前商品处于选中状态，需要将店铺的选择框置为选中状态。
                    if (shopCheck.isChecked()) {
                        shopCheck.setTag("");
                    } else {
                        shopCheck.setTag("fromChild");
                        shopCheck.setChecked(true);
                    }

                    ((ShopCarActivity) mContext).addPrice(price * number, number);
                    isSelect = true;
                    goodsBean.setIsSelect(true);
                }
            }
        });
    }

    private void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }


    public void setData(ShopCarResponseModel.ResultDataBean.GoodsBean goodsBean, CheckBox shopCheck, LinearLayout linearLayout) {
        this.goodsBean = goodsBean;
        this.shopCheck = shopCheck;
        this.linearLayout = linearLayout;
        //判断当前商品是否处于选中状态
        if (goodsBean.isSelect()) {
            Logger.i("选中状态");
            checkBox.setChecked(true);
            isSelect = true;
        }
        if (TextUtils.isEmpty(goodsBean.getImg())) {
            goodsName.setText("");
        } else {
            image.setImageURI(Uri.parse(goodsBean.getImg().toString().trim()));
        }
        if (TextUtils.isEmpty(goodsBean.getGoods_name())) {
            goodsName.setText("");
        } else {
            goodsName.setText(goodsBean.getGoods_name().toString().trim());
        }
        if (TextUtils.isEmpty(goodsBean.getGoods_price())) {
            goodsPrice.setText("");
        } else {
            goodsPrice.setText(goodsBean.getGoods_price().toString().trim());
        }
        if (TextUtils.isEmpty(goodsBean.getGoods_num())) {
            goodsNumber.setText("", goodsBean.getCart_id());
        } else {
            goodsNumber.setText(goodsBean.getGoods_num().toString().trim(),goodsBean.getCart_id());
        }
    }

    public void setCheckBox(boolean isChecked) {
        checkBox.setChecked(isChecked);
        goodsBean.setIsSelect(isChecked);
    }

    public CheckBox getClildCheckBox() {
        return checkBox;
    }

    public float getPrice() {
        float price = Float.parseFloat(goodsPrice.getText().toString().trim());
        int number = goodsNumber.getText();
        return price * number;
    }

    public int getNumber() {
        int number = goodsNumber.getText();
        return number;
    }
}
