package net.anchong.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import net.anchong.app.R;
import net.anchong.app.ui.RequestShopActivity;

import java.util.List;

/**
 * 商家店铺品牌授权书上传的额Adapter
 * Created by baishixin on 16/5/23.
 */
public class BrandUrlAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater;
    private List<String> urlList;
    private RequestShopActivity requestShopActivity;
    //当前应该显示几个组件
    private int picNums;
    //需要上传图片的总数
    private int brandUrlNum;

    public BrandUrlAdapter(Context context, LayoutInflater inflater, List<String> urlList, RequestShopActivity requestShopActivity, int picNums, int brandUrlNum) {
        this.context = context;
        this.inflater = inflater;
        this.urlList = urlList;
        this.requestShopActivity = requestShopActivity;
        this.picNums = picNums;
        this.brandUrlNum = brandUrlNum;
    }

    @Override
    public int getCount() {
//        if (brandUrlNum == null) {
//            return 0;
//        }
        if (brandUrlNum > 0) {
            return brandUrlNum;
        }
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
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.view_item_shop_request, null);
            vh.iv = (ImageView) convertView.findViewById(R.id.iv_item_shop_request);
            vh.del = (ImageView) convertView.findViewById(R.id.iv_del_shop_request);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        com.orhanobut.logger.Logger.i("position：" + position);
        String url = urlList.get(position);
        if (url != null && !"".equals(url)) {

        }
        int W = requestShopActivity.getWindowManager().getDefaultDisplay().getWidth();//获取屏幕高度
        int uiLength = (W - 30) / 3;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(uiLength, uiLength);
        vh.iv.setLayoutParams(params);
        vh.iv.setVisibility(View.VISIBLE);

        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        ImageView del;
//        BrandPicView bpv;
    }
}












