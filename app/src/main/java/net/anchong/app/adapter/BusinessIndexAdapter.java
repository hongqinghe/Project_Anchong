package net.anchong.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.BusinessAdvertResponse;

import java.util.List;

/**
 * Created by baishixin on 16/6/22.
 */
public class BusinessIndexAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<BusinessAdvertResponse.ResultDataEntity.HotprojectEntity> hotprojectList;
    private int showPhone;

    public BusinessIndexAdapter(Context mContext, LayoutInflater mInflater, List<BusinessAdvertResponse.ResultDataEntity.HotprojectEntity> hotprojectList, int showPhone) {
        this.mContext = mContext;
        this.mInflater = mInflater;
        this.hotprojectList = hotprojectList;
        this.showPhone = showPhone;
    }


    @Override
    public int getCount() {
        if (hotprojectList == null || hotprojectList.size() <= 0) return 1;
        return hotprojectList.size();
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
        if (hotprojectList == null || hotprojectList.size() <= 0) {
            convertView = mInflater.inflate(R.layout.view_empty, null);
        } else {
            ViewHolder vh = null;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_business_index_adapter, null);
                vh.sdv = (SimpleDraweeView) convertView.findViewById(R.id.sdv_business_index_pic);
                vh.title = (TextView) convertView.findViewById(R.id.tv_item_business_index_title);
                vh.content = (TextView) convertView.findViewById(R.id.tv_item_business_index_content);
                vh.time = (TextView) convertView.findViewById(R.id.tv_item_business_index_time);
                convertView.setTag(vh);
            }

            vh = (ViewHolder) convertView.getTag();
            BusinessAdvertResponse.ResultDataEntity.HotprojectEntity hotProject = hotprojectList.get(position);
            if (hotProject.getPic() != null) {
                vh.sdv.setImageURI(Uri.parse(hotProject.getPic().get(0)));
            }
            vh.title.setText(hotProject.getTitle());
            vh.content.setText(hotProject.getContent());
            vh.time.setText("发布时间：" + hotProject.getCreated_at());
        }


        return convertView;
    }

    class ViewHolder {
        SimpleDraweeView sdv;
        TextView title;
        TextView content;
        TextView time;
    }
}
