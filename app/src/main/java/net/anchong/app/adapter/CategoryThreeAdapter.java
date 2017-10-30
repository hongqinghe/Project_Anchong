package net.anchong.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;

/**
 * 商品三级分类
 * Created by baishixin on 16/4/21.
 */
public class CategoryThreeAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
//    private List<GoodsTwoThreeResponse.ResultDataBean.ListBean> listBeans = new ArrayList<>();

    public CategoryThreeAdapter(Context context, LayoutInflater mInflater) {
        this.context = context;
        this.mInflater = mInflater;
//        this.listBeans = listBeans;
    }

    @Override
    public int getCount() {
//        if (listBeans == null || listBeans.size() <= 0) {
//            return 0;
//        }
//        return listBeans.size();
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
        convertView = mInflater.inflate(R.layout.item_gridview_category_three, null);
        SimpleDraweeView icon = (SimpleDraweeView) convertView.findViewById(R.id.sdv_gridview_three);
        TextView name = (TextView) convertView.findViewById(R.id.tv_gridview_name);
        return convertView;
    }


}







