package net.anchong.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import net.anchong.app.view.CategoryTwoView;

/**
 * Created by baishixin on 16/4/21.
 */
public class CategoryTwoAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
//    private List<GoodsTwoThreeResponse.ResultDataBean> list = new ArrayList<>();

    public CategoryTwoAdapter(Context context, LayoutInflater mInflater) {
        this.context = context;
        this.mInflater = mInflater;
//        this.list = list;
    }

    @Override
    public int getCount() {
//        if (list == null || list.size() <= 0) {
//            return 0;
//        }
//        return list.size();
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
//        convertView = mInflater.inflate(R.layout.viw_list_category_two, null);
        CategoryTwoView categoryTwoView = new CategoryTwoView(context);
        categoryTwoView.setInflater(mInflater);
//        categoryTwoView.setData(list.get(position));
        return categoryTwoView;
    }
}






