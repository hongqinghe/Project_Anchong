package net.anchong.app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.anchong.app.R;

/**
 * 商城主页，我的 界面的适配器
 * Created by baishixin on 16/3/16.
 */
public class MineFragmentAdapter extends BaseAdapter {

    private String[] titles;
    private LayoutInflater mInflater;

    public MineFragmentAdapter(String[] titles, LayoutInflater mInflater) {
        this.titles = titles;
        this.mInflater = mInflater;
    }

    @Override
     public int getCount() {
        return titles.length;
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
            convertView = mInflater.inflate(R.layout.item_grid, null);
            vh.mTextView = (TextView) convertView.findViewById(R.id.tv_minefragment_labletitle);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        vh.mTextView.setText(titles[position].toString().trim());
        return convertView;
    }


    class ViewHolder {
        TextView mTextView;
    }
}










