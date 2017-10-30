package net.anchong.app.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import net.anchong.app.R;

import java.util.List;

/**
 * 商城商品列表一级分类
 */

public class TextAdapter extends ArrayAdapter<String> {

    private Context mContext;
    private List<String> mListData;
    private int selectPos;

    public void setSelectPos(int pos) {
        this.selectPos = pos;
    }

    public TextAdapter(Context context, List<String> listData) {
        super(context, R.string.app_name, listData);
        mContext = context;
        mListData = listData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view;
        if (convertView == null) {
            view = (TextView) LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        } else {
            view = (TextView) convertView;
        }
        view.setText(mListData.get(position));
        if (selectPos == position) {
            view.setBackgroundColor(Color.WHITE);
            view.setTextColor(Color.RED);
        } else {
            view.setBackgroundColor(Color.parseColor("#f4f4f4"));
            view.setTextColor(Color.BLACK);
        }
        return view;
    }

}
