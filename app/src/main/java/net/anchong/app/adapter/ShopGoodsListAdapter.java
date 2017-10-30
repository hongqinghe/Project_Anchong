package net.anchong.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.GoodsAdvertResponse;
import net.anchong.app.ui.GoodsInformationActivity;
import net.anchong.app.view.MallProductView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by baishixin on 16/4/22.
 */
public class ShopGoodsListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInlater;
    private List<GoodsAdvertResponse.ResultDataEntity.GoodsEntity> listBeans = new ArrayList<>();
    private int showPrice = 0;
    private boolean isNoData;
    private int ratio = 0;
    private int increase = 0;

    public static final int ONE_SCREEN_COUNT = 4; // 一屏能显示的个数，这个根据屏幕高度和各自的需求定
    public static final int ONE_REQUEST_COUNT = 20; // 一次请求的个数


    MallProductView mpv2;

    public ShopGoodsListAdapter(Context context, LayoutInflater mInlater, List<GoodsAdvertResponse.ResultDataEntity.GoodsEntity> listBeans, int showprice) {
        this.context = context;
        this.mInlater = mInlater;
        this.listBeans = listBeans;
        this.showPrice = showprice;
    }

    // 设置数据
    public void setData(List<GoodsAdvertResponse.ResultDataEntity.GoodsEntity> list) {
//        if(list == null)return;
//        clearAll();
//        addALL(list);

        isNoData = false;
        if (list.size() == 1) {
            // 暂无数据布局
            isNoData = true;
//            mHeight = list.get(0).getHeight();
        } else {
            // 添加空数据
            if (list.size() < ONE_SCREEN_COUNT) {
                addALL(createEmptyList(ONE_SCREEN_COUNT - list.size()));
            }
        }
        notifyDataSetChanged();
    }

    public void clearAll() {
        listBeans.clear();
    }

    public void addALL(List<GoodsAdvertResponse.ResultDataEntity.GoodsEntity> listBeans) {
        if (listBeans == null || listBeans.size() == 0) {
            return;
        }
        this.listBeans.addAll(listBeans);
    }

    // 创建不满一屏的空数据
    public List<GoodsAdvertResponse.ResultDataEntity.GoodsEntity> createEmptyList(int size) {
        List<GoodsAdvertResponse.ResultDataEntity.GoodsEntity> emptyList = new ArrayList<>();
        if (size <= 0) return emptyList;
        for (int i = 0; i < size; i++) {
            emptyList.add(new GoodsAdvertResponse.ResultDataEntity.GoodsEntity());
        }
        return emptyList;
    }

    @Override
    public int getCount() {
        if (listBeans == null) {
            return 0;
        }
        if (listBeans.size() <= 0) {
            return 0;
        }
        if (listBeans.size() % 2 == 0) {
            return listBeans.size() / 2;
        } else {
            return ((listBeans.size() / 2) + 1);
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    MallProductView mpv1;

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * ListView 双列显示，实现思路，左边放偶数下标，右边放奇数下标
     * 0              1
     * 2              3
     * 4              5
     * 6              7
     * .              .
     * .              .
     * 10             11
     * 20             21
     * (position*2)    (position*2+1)
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        // 暂无数据
        if (isNoData) {
            convertView = mInlater.inflate(R.layout.item_no_data_layout, null);
            AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 100);
            RelativeLayout rootView = ButterKnife.findById(convertView, R.id.rl_root_view);
            rootView.setLayoutParams(params);
            return convertView;
        }


        ViewHolder vh = null;
        if (convertView == null) {
            convertView = mInlater.inflate(R.layout.item_fragment_goods_list, null);
            vh = new ViewHolder();
            vh.mpv1 = (MallProductView) convertView.findViewById(R.id.mpv_list_item1);
            vh.mpv2 = (MallProductView) convertView.findViewById(R.id.mpv_list_item2);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        vh.mpv1.setData(listBeans.get(position * 2), showPrice);
        vh.mpv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoodsInformationActivity.start(context, listBeans.get(position * 2).getGid(),
                        showPrice, listBeans.get(position * 2).getGoods_id(), listBeans.get(position * 2).getTitle());
            }
        });

        vh.mpv2.setVisibility(View.VISIBLE);
        if (listBeans.size() % 2 != 0 && position == (listBeans.size() - 1) / 2) {
            vh.mpv2.setVisibility(View.INVISIBLE);
        } else {
            if ((position * 2 + 1) < listBeans.size()) {
                vh.mpv2.setData(listBeans.get(position * 2 + 1), showPrice);

                vh.mpv2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GoodsInformationActivity.start(context, listBeans.get(position * 2 + 1).getGid(),
                                showPrice, listBeans.get(position * 2 + 1).getGoods_id(), listBeans.get(position * 2 + 1).getTitle());
                    }
                });
            }
        }
        return convertView;
    }

    class ViewHolder {
        MallProductView mpv1;
        MallProductView mpv2;
    }
}
















