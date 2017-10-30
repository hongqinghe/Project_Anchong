package net.anchong.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import net.anchong.app.R;

import java.util.List;
import java.util.Map;

/**
 * Created by baishixin on 16/6/7.
 */
public class ExpandListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<String> parent = null;
    private Map<String, List<String>> map = null;

    public ExpandListAdapter(Context context, LayoutInflater inflater, List<String> parent, Map<String, List<String>> map) {
        this.context = context;
        this.inflater = inflater;
        this.parent = parent;
        this.map = map;
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String key = parent.get(groupPosition);
        return (map.get(key).get(childPosition));
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        String key = this.parent.get(groupPosition);
        String info = map.get(key).get(childPosition);
        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater)
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_child_goods_type, null);
        }
        TextView tv = (TextView) convertView
                .findViewById(R.id.second_textview);
        tv.setText(info);
        return convertView;
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        String key = parent.get(groupPosition);
        int size = map.get(key).size();
        return size;
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return parent.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return parent.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) MainActivity.this
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_parent_expend_type, null);
        }
        TextView tv = (TextView) convertView
                .findViewById(R.id.parent_shop_goods_type);
        tv.setText(this.parent.get(groupPosition));
        return tv;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
