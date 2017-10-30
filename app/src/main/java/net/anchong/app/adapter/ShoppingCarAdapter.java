
package net.anchong.app.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.model.ShoppingCarModel;
import net.anchong.app.uitls.AppUtils;
import net.anchong.app.uitls.ViewHolder;
import net.anchong.app.view.OrderEditTextView;

import java.util.List;


/**
 * Created by lihaiyi on 15/12/3.
 */
public class ShoppingCarAdapter extends BaseAdapter {

    private List<ShoppingCarModel> list;

    private Context context;

    public ShoppingCarAdapter(Context context, List<ShoppingCarModel> list) {
        this.list = list;
        this.context = context;
    }

    public List<ShoppingCarModel> getList() {
        return list;
    }

    public void setList(List<ShoppingCarModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        switch (list.get(position).viewType) {
            case 0:
                return 0;
            case 1:
                return 1;
        }
        return super.getItemViewType(position);

    }

    @Override
    public int getCount() {
        return list == null || list.size() == 0 ? 0 : list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            switch (getItemViewType(position)) {
                case 0:
                    convertView = LayoutInflater.from(context)
                            .inflate(R.layout.shoppingcar_listviewitem, null);
                    break;
                case 1:
                    convertView = LayoutInflater.from(context)
                            .inflate(R.layout.shoppingcar_listviewitem_top, null);
                    break;
            }
        }
        switch (getItemViewType(position)) {
            case 0: {
                LinearLayout layout = ViewHolder.get(convertView, R.id.checkbox_layout);
                CheckBox checkbox = ViewHolder.get(convertView, R.id.checkbox);
                SimpleDraweeView image = ViewHolder.get(convertView, R.id.image);
                TextView goods_name = ViewHolder.get(convertView, R.id.goods_name);
                TextView goods_property1 = ViewHolder.get(convertView, R.id.goods_property1);
                TextView goods_property2 = ViewHolder.get(convertView, R.id.goods_property2);
                TextView price = ViewHolder.get(convertView, R.id.price);
                TextView old_price = ViewHolder.get(convertView, R.id.price);
                OrderEditTextView edit = ViewHolder.get(convertView, R.id.order_edit);
                RelativeLayout goods_bottom = ViewHolder.get(convertView, R.id.goods_bottom);

                setCheckBoxState(checkbox, checkbox, position);
                setCheckBoxState(layout, checkbox, position);

                if (list.get(position).enableEdit) {
                    showEdit(edit, true);
                    old_price.setVisibility(View.GONE);
                } else {
                    showEdit(edit, false);
                    old_price.setVisibility(View.VISIBLE);
                }

                goods_name.setText(list.get(position).title);
                if (TextUtils.isEmpty(list.get(position).sale_price)) {
                    price.setText("");
                } else {
                    price.setText(
                            context.getString(R.string.goods_price, list.get(position).sale_price));
                }
                if (TextUtils.isEmpty(list.get(position).list_price)) {
                    old_price.setText("");
                } else {
                    old_price.setText(
                            context.getString(R.string.goods_price, list.get(position).list_price));
                    old_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                }

                if (!TextUtils.isEmpty(list.get(position).list_price)
                        && !TextUtils.isEmpty(list.get(position).sale_price)) {
                    double listPrice = Double.parseDouble(list.get(position).list_price);
                    double salePrice = Double.parseDouble(list.get(position).sale_price);
                    if (salePrice >= listPrice) {
                        old_price.setVisibility(View.GONE);
                    } else {
                        old_price.setVisibility(View.VISIBLE);
                    }
                }

                image.setImageURI(AppUtils.parse(list.get(position).img_url + ""));

                break;
            }
            case 1: {
                LinearLayout layout = ViewHolder.get(convertView, R.id.checkbox_layout);
                CheckBox checkbox = ViewHolder.get(convertView, R.id.checkbox);
                TextView warehouse = ViewHolder.get(convertView, R.id.warehouse);
                warehouse.setText(list.get(position).warehouse);

                setCheckBoxState(checkbox, checkbox, position);
                setCheckBoxState(layout, checkbox, position);
                break;
            }

        }
        return convertView;
    }

    public void showEdit(OrderEditTextView edit, boolean show) {
        if (show) {
            edit.setVisibility(View.VISIBLE);
            edit.canToast = true;
        } else {
            edit.setVisibility(View.GONE);
            edit.canToast = false;
        }

    }

    private void setCheckBoxState(View view, CheckBox checkbox, int position) {
        if (list.get(position).isSelect)
            checkbox.setChecked(true);
        else
            checkbox.setChecked(false);

        view.setOnClickListener(new CheckBoxClickListener(position));
    }

    class CheckBoxClickListener implements View.OnClickListener {
        private int position;

        public CheckBoxClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            list.get(position).isSelect = !list.get(position).isSelect;

            int warehousePosition = 0;
            for (int i = position; i >= 0; i--) {
                if (list.get(i).viewType == 1) {
                    warehousePosition = i;
                    break;
                }
            }

            if (list.get(position).isSelect) {

                boolean isAllSelect = true;
                if (list.get(position).viewType == 1) {
                    setWarehouseStatus(true);
                } else {
                    boolean warehouseSelect = true;
                    // 设置仓库全选按钮为的状态
                    for (int i = warehousePosition + 1; i < list.size(); i++) {
                        if (list.get(i).viewType == 1) {
                            break;
                        } else {
                            if (!list.get(i).isSelect && list.get(i).viewType == 0) {
                                warehouseSelect = false;
                                break;
                            }
                        }
                    }
                    list.get(warehousePosition).isSelect = warehouseSelect;
                }

                // 设置购物车全选按钮为的状态
                for (ShoppingCarModel model : list) {
                    if (!model.isSelect && model.viewType == 0 && isAllSelect) {
                        isAllSelect = false;
                        break;
                    }
                }
            } else {
                // 设置仓库全选按钮为的状态
                list.get(warehousePosition).isSelect = false;
                // 设置同一个仓库的按钮状态
                if (list.get(position).viewType == 1) {
                    setWarehouseStatus(false);
                }
            }
        }

        public void setWarehouseStatus(boolean select) {
            for (int i = position + 1; i < list.size(); i++) {
                if (!TextUtils.isEmpty(list.get(i).warehouse_id)
                        && list.get(i).warehouse_id.equals(list.get(position).warehouse_id)) {
                    list.get(i).isSelect = select;
                } else {
                    break;
                }
            }
        }

    }

}
