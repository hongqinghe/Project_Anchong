package net.anchong.app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cengalabs.flatui.views.FlatEditText;
import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.CertificationModel;
import net.anchong.app.ui.CertificationActivity;

import java.util.List;

/**
 * Created by baishixin on 16/4/8.
 */
public class CertificationAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater mInflater;
    private List<CertificationModel> certificationModelList;
    private CertificationModel certificationModel;

    public CertificationAdapter(Activity activity, LayoutInflater inflater, List<CertificationModel> certificationModelList) {
        this.activity = activity;
        mInflater = inflater;
        this.certificationModelList = certificationModelList;
    }

    @Override
    public int getCount() {
        return CertificationActivity.certificationModels.size();
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
            convertView = mInflater.inflate(R.layout.view_add_certification, null);
            vh.mEditText_name = (FlatEditText) convertView.findViewById(R.id.et_view_certification_name);
            vh.mEditText_info = (FlatEditText) convertView.findViewById(R.id.et_view_certification_info);
            vh.mImageView_icon = (SimpleDraweeView) convertView.findViewById(R.id.sdv_view_certification);

            convertView.setTag(vh);

        }
//        vh.addCertificationView = (AddCertificationView) convertView.findViewById(R.id.acfv_item);
//        convertView.setTag(vh);
//        vh = (ViewHolder) convertView.getTag();
//        certificationModel = CertificationActivity.certificationModels.get(position);
//        vh.addCertificationView.setIndex(position);
//        vh.addCertificationView.setData(certificationModel);
        return convertView;
    }

    class ViewHolder {
        FlatEditText mEditText_name;
        FlatEditText mEditText_info;
        SimpleDraweeView mImageView_icon;
    }
}
