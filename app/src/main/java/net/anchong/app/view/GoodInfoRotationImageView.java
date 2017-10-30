package net.anchong.app.view;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.adapter.BasePagerAdapter;
import net.anchong.app.uitls.AppUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.LinkedList;
import java.util.List;


/**
 * 商城首页轮播图 自定义View
 * Created by baishixin on 16/2/11.
 */
public class GoodInfoRotationImageView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private static final String TAG = "RotationImageView";
    private Context mContext;

    /**
     * ViewPager
     */
    @ViewInject(R.id.viewPager_index_header)
    private FootballViewPager mViewPager;

    private boolean isRotation = false;//是否轮播

    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;
    /**
     * 图片资源id
     */
    private int[] imgIdArray;
    @ViewInject(R.id.viewGroup_index_header)
    private ViewGroup group;


    private List<String> slidesModels;
    private BasePagerAdapter mPagerAdapter;


    public GoodInfoRotationImageView(Context context) {
        super(context);
    }

    public GoodInfoRotationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_goods_rotation, this);
        x.view().inject(this);
    }

    public void setData(List<String> slidesModels, boolean isRotation) {
        this.isRotation = isRotation;
        if (this.slidesModels != null) {
            if (this.slidesModels.size() > 0) {
                this.slidesModels.clear();
                this.mHandler.removeMessages(1);
                group.removeAllViews();
            }
        }
//        this.slidesModels = null;
        this.tips = null;
        this.slidesModels = slidesModels;
        //将点点加入到ViewGroup中
        tips = new ImageView[slidesModels.size()];
        for (int i = 0; i < tips.length; i++) {
            //设置每个小圆点的间隔为 6dp
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 0, 0, 0);
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LayoutParams(10, 10));
            tips[i] = imageView;
            if (i == 0) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }

            group.addView(imageView, params);
        }
        setChildViewData();
    }


    /**
     * 设置viewpager的childView
     */
    private void setChildViewData() {
        LinkedList<BasePagerAdapter.PagerModel> childViewList = new LinkedList();
        if (slidesModels == null) {
            return;
        }
        int size = slidesModels.size();
        for (int i = 0; i < size; i++) {
            BasePagerAdapter.PagerModel pagerModel = new BasePagerAdapter.PagerModel();
            View childView = LayoutInflater.from(getContext())
                    .inflate(R.layout.layout_goods_detail_head_item, null);
            pagerModel.childView = childView;
            pagerModel.imageView = (SimpleDraweeView) childView
                    .findViewById(R.id.sdv_mall_head_item);
            pagerModel.imageView.setAspectRatio(0.94f);
            pagerModel.imageView.setId(i);
            setupChildViews(pagerModel, slidesModels.get(i));
            childViewList.add(pagerModel);
        }
        setViewPagerAdapter(childViewList);
    }

    private void setupChildViews(BasePagerAdapter.PagerModel pager,
                                 String pic) {
        pager.imageView.setTag(pic);

//        pager.imageView.setAspectRatio(1);
        pager.imageView.getHierarchy().setActualImageFocusPoint(new PointF(0.5f, 0.5f));
        if (!TextUtils.isEmpty(pic))
            pager.imageView.setImageURI(AppUtils.parse(pic));
    }


    public void setViewPagerAdapter(LinkedList<BasePagerAdapter.PagerModel> mPagerModels) {
        this.mPagerAdapter = new BasePagerAdapter(mPagerModels);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(0);
//        mViewPager.setCurrentItem((slidesModels.size()) * 100);
        if (isRotation) {
            mHandler.sendEmptyMessageDelayed(1, 2000);
        }
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {

    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {

    }

    @Override
    public void onPageSelected(int arg0) {
        setImageBackground(arg0 % slidesModels.size());
    }

    /**
     * 设置选中的tip的背景
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < tips.length; i++) {
            if (i == selectItems) {
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            } else {
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    //autoChangeViewPager.getChildCount();
                    int totalcount = slidesModels.size();
                    int currentItem = mViewPager.getCurrentItem();
                    int toItem = currentItem + 1 == totalcount ? 0 : currentItem + 1;
                    mViewPager.setCurrentItem(toItem, true);
                    //每两秒钟发送一个message，用于切换viewPager中的图片
                    this.sendEmptyMessageDelayed(1, 2000);
            }
        }
    };


}
