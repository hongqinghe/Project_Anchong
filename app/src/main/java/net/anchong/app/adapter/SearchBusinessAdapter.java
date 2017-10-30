package net.anchong.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.anchong.app.R;

import java.util.List;

/**
 * 适配器：适配商机界面筛选按钮的服务类型和区域选项
 * Created by baishixin on 16/4/19.
 */
public class SearchBusinessAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<String> types;

    public SearchBusinessAdapter(Context context, LayoutInflater mInflater) {
        this.context = context;
        this.mInflater = mInflater;
    }

    public SearchBusinessAdapter(Context context, LayoutInflater mInflater, List<String> types) {
        this.context = context;
        this.mInflater = mInflater;
        this.types = types;
    }

    public void setList(List<String> types) {
        this.types = types;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (types == null || types.size() <= 0) {
            return 0;
        }
        return types.size();
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
        convertView = mInflater.inflate(R.layout.item_gridview_search, null);
        TextView type = (TextView) convertView.findViewById(R.id.tv_business_seach_tag);
        type.setText(types.get(position).trim());
        return convertView;
    }


}
