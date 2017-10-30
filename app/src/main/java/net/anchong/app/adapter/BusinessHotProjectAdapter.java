package net.anchong.app.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.HotProjectResponse;
import net.anchong.app.uitls.ImageUtils;

import java.util.List;

/**
 * Created by baishixin on 16/6/22.
 */
public class BusinessHotProjectAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<HotProjectResponse.ResultDataEntity.ListEntity> hotprojectList;
    private int showPhone;

    public BusinessHotProjectAdapter(Context mContext, LayoutInflater mInflater, List<HotProjectResponse.ResultDataEntity.ListEntity> hotprojectList, int showPhone) {
        this.mContext = mContext;
        this.mInflater = mInflater;
        this.hotprojectList = hotprojectList;
        this.showPhone = showPhone;
    }

    public void setList(List<HotProjectResponse.ResultDataEntity.ListEntity> hotprojectList) {
        this.hotprojectList = hotprojectList;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (hotprojectList == null) {
            return 1;
        }
        if (hotprojectList.size() <= 0) {
            return 1;
        }
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
            TextView tv = (TextView) convertView.findViewById(R.id.tv_placeholder_tip);
            tv.setText("暂无更多商机");
        } else {
            ViewHolder vh = null;
            if (convertView == null) {
                vh = new ViewHolder();
                convertView = mInflater.inflate(R.layout.item_business_hot_project_adapter, null);
                vh.sdv = (SimpleDraweeView) convertView.findViewById(R.id.sdv_business_index_pic);
                vh.title = (TextView) convertView.findViewById(R.id.tv_item_business_index_title);
                vh.time = (TextView) convertView.findViewById(R.id.tv_item_business_index_time);
                convertView.setTag(vh);
            }
            vh = (ViewHolder) convertView.getTag();
            HotProjectResponse.ResultDataEntity.ListEntity hotProject = hotprojectList.get(position);
            if (hotProject.getPic() != null) {
                if (!TextUtils.isEmpty(hotProject.getPic().get(0))) {
                    vh.sdv.setImageURI(Uri.parse(ImageUtils.getImgUri(hotProject.getPic().get(0))));
                }
            }
            vh.title.setText(hotProject.getTitle());
            vh.time.setText("发布时间：" + hotProject.getCreated_at());
        }


        return convertView;
    }

    class ViewHolder {
        SimpleDraweeView sdv;
        TextView title;
        TextView time;
    }
}
