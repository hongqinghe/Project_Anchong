package net.anchong.app.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baishixin on 16/4/22.
 */
public class GoodsMoreInfoAdapter extends BaseAdapter {

    private static final int CHANGE_IMG_HEIGHT = 1;
    private Context context;
    private LayoutInflater mInlater;
    private List<String> peis = new ArrayList<>();

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CHANGE_IMG_HEIGHT:
                    SimpleDraweeView sdv = (SimpleDraweeView) msg.obj;
                    sdv.setAspectRatio(1.33f);
                    break;
            }
        }
    };

    public GoodsMoreInfoAdapter(Context context, LayoutInflater mInlater, List<String> peis) {
        this.context = context;
        this.mInlater = mInlater;
        this.peis = peis;
    }

    @Override
    public int getCount() {
        if (peis == null) {
            return 0;
        }
        if (peis.size() <= 0) {
            return 0;
        }
        return peis.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    ViewHolder vh = null;
    ImageView iv;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInlater.inflate(R.layout.item_goods_more_info, null);
            vh.pic = (SimpleDraweeView) convertView.findViewById(R.id.sdv_item_more_info);
            convertView.setTag(vh);
        }

        vh = (ViewHolder) convertView.getTag();
        vh.pic.setImageURI(Uri.parse(peis.get(position).toString().trim()));
        return convertView;
    }

    private void setImage(SimpleDraweeView pic, int position, Bitmap bitmap) {

        float width = bitmap.getWidth();
        float height = bitmap.getHeight();
        float scle = width / height;

        pic.setAspectRatio(scle);
        pic.setImageURI(Uri.parse(peis.get(position)));

    }

    class ViewHolder {
        SimpleDraweeView pic;
    }
}
















