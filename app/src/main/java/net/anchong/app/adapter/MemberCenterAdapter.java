package net.anchong.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.anchong.app.R;

/**
 * Created by baishixin on 16/3/16.
 */
public class MemberCenterAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mInflater;
    private String[] titles = {"基本资料", "收货地址", "银行账户", "商家认证", "商铺申请"};

    public MemberCenterAdapter(Context context, LayoutInflater inflater) {
        mContext = context;
        mInflater = inflater;
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
            convertView = mInflater.inflate(R.layout.item_member_center, null);
            vh.mTextViewContent = (TextView) convertView.findViewById(R.id.tv_member_center_content);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        vh.mTextViewContent.setText(titles[position].toString().trim());
        return convertView;
    }

    class ViewHolder {
        TextView mTextViewContent;
    }
}
