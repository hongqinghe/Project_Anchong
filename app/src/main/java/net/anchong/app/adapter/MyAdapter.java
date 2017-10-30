package net.anchong.app.adapter;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cengalabs.flatui.views.FlatEditText;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.CertificationModel;
import net.anchong.app.ui.CertificationActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by baishixin on 16/4/14.
 */
public class MyAdapter extends BaseAdapter {
    private List<CertificationModel> list = new ArrayList<CertificationModel>();

    private Context context;
    private LayoutInflater mInflater;
    private OnListRemovedListener mListener;

    //下拉列表的适配器
    private ArrayAdapter<String> arrayAdapter;

    public void setOnListRemovedListener(OnListRemovedListener listener) {
        this.mListener = listener;
    }

    public MyAdapter(List<CertificationModel> list, LayoutInflater inflater, Context context) {
        this.context = context;
        this.mInflater = inflater;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup arg2) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.view_add_certification, null);
            holder.mEditText_name = (FlatEditText) convertView.findViewById(R.id.et_view_certification_name);
            holder.mEditText_info = (FlatEditText) convertView.findViewById(R.id.et_view_certification_info);
            holder.mImageView_icon = (SimpleDraweeView) convertView.findViewById(R.id.sdv_view_certification);
            holder.mImageView_icon.setTag(position);
            holder.mImageView_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CertificationActivity.flag = position;
                    AlertDialog.Builder headpicBuilder = new AlertDialog.Builder(context);
                    headpicBuilder.setTitle("上传图片");
                    final String[] items = {"拍照", "选择照片"};
                    headpicBuilder.setItems(items,
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            String status = Environment.getExternalStorageState();
                                            if (status.equals(Environment.MEDIA_MOUNTED)) {
                                                try {
                                                    File dir = new File(Environment.getExternalStorageDirectory() + "/anchong");
                                                    if (!dir.exists()) dir.mkdirs();
//                                                    localTempImgFileName = "anchong_zizhi.jpg";
                                                    Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                                    File f = new File(dir, "anchong_zizhi.jpg");//localTempImgDir和localTempImageFileName是自己定义的名字
                                                    Uri u = Uri.fromFile(f);
                                                    intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                                    intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
                                                    ((CertificationActivity) context).startActivityForResult(intent, CertificationActivity.GET_IMAGE_VIA_CAMERA);
                                                } catch (ActivityNotFoundException e) {
                                                    // TODO Auto-generated catch block
                                                    Toast.makeText(context, "没有找到存储目录", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(context, "没有存储卡", Toast.LENGTH_SHORT).show();
                                            }
                                            break;
                                        case 1:
                                            Intent intent2 = new Intent();
                                            intent2.setAction("android.intent.action.PICK");
                                            intent2.setType("image/*");
                                            ((CertificationActivity) context).startActivityForResult(intent2, CertificationActivity.CHOSE_IMAGE_VIA_CAMERA);
                                            break;
                                    }
                                }
                            });
                    headpicBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    headpicBuilder.show();
                }
            });
            holder.mEditText_name.setTag(position);
            holder.mEditText_name.addTextChangedListener(new MyTextWatcher(holder) {
                @Override
                public void afterTextChanged(Editable s, ViewHolder holder) {
                    int position = (Integer) holder.mEditText_name.getTag();
                    CertificationModel n = list.get(position);
                    n.setName(s.toString());
                    list.set(position, n);
                }
            });
            holder.mEditText_info.setTag(position);
            holder.mEditText_info.addTextChangedListener(new MyTextWatcher(holder) {
                @Override
                public void afterTextChanged(Editable s, ViewHolder holder) {
                    int position = (Integer) holder.mEditText_info.getTag();
                    CertificationModel n = list.get(position);
                    n.setDesc(s.toString());
                    list.set(position, n);
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
            holder.mEditText_name.setTag(position);
            holder.mEditText_info.setTag(position);
            holder.mImageView_icon.setTag(position);
        }
        CertificationModel n = list.get(position);
        holder.mEditText_name.setText(n.getName());
        holder.mEditText_info.setText(n.getDesc());
        if (n.getImg_url() == null || "".equals(n.getImg_url())) {
            GenericDraweeHierarchy hierarchy = holder.mImageView_icon.getHierarchy();
            hierarchy.setPlaceholderImage(R.drawable.icon_placeholder);
        } else {
            holder.mImageView_icon.setImageURI(Uri.parse(n.getImg_url()));
        }
        return convertView;
    }

    //动态添加List里面数据
    public void addItem(CertificationModel n) {
        list.add(n);
    }

    private abstract class MyTextWatcher implements TextWatcher {
        private ViewHolder mHolder;

        public MyTextWatcher(ViewHolder holder) {
            this.mHolder = holder;
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                  int arg3) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            afterTextChanged(s, mHolder);
        }

        public abstract void afterTextChanged(Editable s, ViewHolder holder);
    }

    private abstract class MyOnClickListener implements View.OnClickListener {

        private ViewHolder mHolder;

        public MyOnClickListener(ViewHolder holder) {
            this.mHolder = holder;
        }

        @Override
        public void onClick(View v) {
            onClick(v, mHolder);
        }

        public abstract void onClick(View v, ViewHolder holder);

    }

    private class ViewHolder {
        TextView tv_position;
        FlatEditText mEditText_name;
        FlatEditText mEditText_info;
        SimpleDraweeView mImageView_icon;
    }

    //删除操作回调
    public interface OnListRemovedListener {
        public void onRemoved();
    }

}