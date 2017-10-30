package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.GoodsTwoThreeResponse;
import net.anchong.app.fragment.GoodsListFragment;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import in.srain.cube.util.CLog;
import in.srain.cube.views.pager.TabPageIndicator;

/**
 * Created by baishixin on 16/5/6.
 */
public class GoodsListPagerActivity extends FragmentActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener {
//    private static final String[] CONTENT = new String[]{"Recent", "Artists", "Albums", "Songs", "Playlists", "Genres"};


    @ViewInject(R.id.gtvv_categorypage_title)
    private GeneralTitleBarView mGeneralTitleBarView;

    private List<GoodsTwoThreeResponse.ResultDataEntity> resultDataList = new ArrayList<>();
    private String cid = "";

    public static void start(Context context, List<GoodsTwoThreeResponse.ResultDataEntity> resultData, String cid) {
        Intent intent = new Intent(context, GoodsListPagerActivity.class);
        intent.putExtra("resultData", (Serializable) resultData);
        intent.putExtra("cid", cid);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        x.view().inject(this);
        cid = getIntent().getStringExtra("cid");
        switch (cid) {
            case "1":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_intelligent_gate), "");
                break;
            case "2":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_video_surveillance), "");
                break;
            case "3":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_alert), "");
                break;
            case "4":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_polling), "");
                break;
            case "5":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_parking_management), "");
                break;
            case "6":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_building_talkback), "");
                break;
            case "7":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_dining_system), "");
                break;
            case "8":
                mGeneralTitleBarView.setData(getResources().getString(R.string.str_categorylist_security), "");
                break;
        }
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        resultDataList = (List<GoodsTwoThreeResponse.ResultDataEntity>) getIntent().getSerializableExtra("resultData");
        GoodsTwoThreeResponse.ResultDataEntity entity = new GoodsTwoThreeResponse.ResultDataEntity();
        entity.setCat_name("所有");
        entity.setCat_id("0");
        resultDataList.add(0, entity);


        FragmentPagerAdapter adapter = new GoogleMusicAdapter(getSupportFragmentManager());

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabPageIndicator indicator = (TabPageIndicator) findViewById(R.id.indicator);
        indicator.setViewHolderCreator(new TabPageIndicator.ViewHolderCreator() {
            @Override
            public TabPageIndicator.ViewHolderBase createViewHolder() {
                return new DemoViewHolder();
            }
        });
//        indicator.setViewPager(pager, resultDataList.size() - 1);
        indicator.setViewPager(pager, 0);
    }

    class GoogleMusicAdapter extends FragmentPagerAdapter {
        public GoogleMusicAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            CLog.d("test", "getItem:%s", position);
//            Fragment fragment = TestFragment.newInstance(CONTENT[position % CONTENT.length]);
//            Fragment fragment = new SheQuFragment();
            Fragment fragment = GoodsListFragment.newInstance(resultDataList.get(position).getCat_id().toString().trim(), cid);
            return fragment;
        }

        @Override
        public int getCount() {
            return resultDataList.size();
        }
    }

    private class DemoViewHolder extends TabPageIndicator.ViewHolderBase {

        private TextView mTitleTextView;
        private View mViewSelected;
        private final int COLOR_TEXT_SELECTED = Color.parseColor("#1DABD8");
        private final int COLOR_TEXT_NORMAL = Color.parseColor("#000000");

        @Override
        public View createView(LayoutInflater layoutInflater, int position) {
            View view = layoutInflater.inflate(R.layout.ht_views_bimai_cat_item, null);
            mTitleTextView = (TextView) view.findViewById(R.id.tv_ht_bimai_cat_item_title);
            mViewSelected = view.findViewById(R.id.tv_ht_bimai_cat_item_selected);
            return view;
        }

        @Override
        public void updateView(int position, boolean isCurrent) {
//            mTitleTextView.setText(CONTENT[position]);
            mTitleTextView.setText(resultDataList.get(position).getCat_name().toString().trim());
            if (isCurrent) {
                mTitleTextView.setTextColor(COLOR_TEXT_SELECTED);
                mViewSelected.setVisibility(View.VISIBLE);
            } else {
                mTitleTextView.setTextColor(COLOR_TEXT_NORMAL);
                mViewSelected.setVisibility(View.INVISIBLE);
            }
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
    }

}
