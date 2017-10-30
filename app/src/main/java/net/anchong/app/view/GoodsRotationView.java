package net.anchong.app.view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.CorrelationResponse;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;


/**
 * 商城首页轮播图 自定义View
 * Created by baishixin on 16/2/11.
 */
public class GoodsRotationView extends FrameLayout implements ViewPager.OnPageChangeListener {

    private static final String TAG = "RotationImageView";
    private Context mContext;

    /**
     * ViewPager
     */
    @ViewInject(R.id.viewPager_index_header)
    private ViewPager viewPager;

    /**
     * 装点点的ImageView数组
     */
    private ImageView[] tips;

    /**
     * 装ImageView数组
     */
    private RecommendProductView[] mImageViews;

    /**
     * 图片资源id
     */
    private int[] imgIdArray;
    @ViewInject(R.id.viewGroup_index_header)
    private ViewGroup group;


    public GoodsRotationView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public GoodsRotationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_custom_goods_rotation, this);
        x.view().inject(this);
    }


    /**
     * 商城首页轮播图
     *
     * @param picEntityList
     */
    public void setData(List<CorrelationResponse.ResultDataEntity.ListEntity> picEntityList) {
        //配套商品或者推荐商品的页数
        int size = 0;
        //首先根据数据数量计算页数，4条数据为一页。
        if (picEntityList != null && picEntityList.size() > 0) {
            size = picEntityList.size() % 4 == 0 ? picEntityList.size() / 4 : picEntityList.size() / 4 + 1;
            Logger.i("应该" + size + "页");
        }


        //将点点加入到ViewGroup中
        tips = new ImageView[size];
        for (int i = 0; i < tips.length; i++) {
            //设置每个小圆点的间隔为 6dp
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(6, 0, 0, 0);
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


        //将图片装载到数组中
        mImageViews = new RecommendProductView[size];
        for (int i = 0; i < mImageViews.length; i++) {
            RecommendProductView simpleDraweeView = new RecommendProductView(mContext);

            //组装数据，每页4个
            List<CorrelationResponse.ResultDataEntity.ListEntity> list = new ArrayList<>();
            list.add(picEntityList.get(i * 4));
            if (picEntityList.size() > (i * 4 + 1)) {
                list.add(picEntityList.get(i * 4 + 1));
            }
            if (picEntityList.size() > (i * 4 + 2)) {
                list.add(picEntityList.get(i * 4 + 2));
            }
            if (picEntityList.size() > (i * 4 + 3)) {
                list.add(picEntityList.get(i * 4 + 3));
            }
            mImageViews[i] = simpleDraweeView;
            simpleDraweeView.setData(list);

//            simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgThuUri(picEntityList.get(i).getAd_code())));
//            imageView.setBackgroundResource(imgIdArray[i]);
        }

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem(0);
//        viewPager.setCurrentItem(mImageViews.length);
//        viewPager.setCurrentItem((mImageViews.length) * 100);

    }


//    public void setBusinessAvdData(List<BusinessRotationResponse.ResultDataEntity> picEntityList) {
//
//        //将点点加入到ViewGroup中
//        tips = new ImageView[picEntityList.size()];
//        for (int i = 0; i < tips.length; i++) {
//            //设置每个小圆点的间隔为 6dp
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(6, 0, 0, 0);
//            ImageView imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new LayoutParams(10, 10));
//            tips[i] = imageView;
//            if (i == 0) {
//                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
//            } else {
//                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
//            }
//
//            group.addView(imageView, params);
//        }
//
//
//        //将图片装载到数组中
//        mImageViews = new SimpleDraweeView[picEntityList.size()];
//        for (int i = 0; i < mImageViews.length; i++) {
////            ImageView imageView = new ImageView(mContext);
//            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
//            simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
////            simpleDraweeView.setScaleType(ScalingUtils.ScaleType.FIT_XY);
//            mImageViews[i] = simpleDraweeView;
//            simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgThuUri(picEntityList.get(i).getAd_code())));
////            imageView.setBackgroundResource(imgIdArray[i]);
//        }
//
//        //设置Adapter
//        viewPager.setAdapter(new MyAdapter());
//        //设置监听，主要是设置点点的背景
//        viewPager.setOnPageChangeListener(this);
//        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
//        viewPager.setCurrentItem((mImageViews.length) * 100);
//
//    }


//    /**
//     * 商城首页轮播图
//     *
//     * @param picEntityList
//     */
//    public void setGoodsAvdData(List<GoodsAdvertResponse.ResultDataEntity.PicEntity> picEntityList) {
//
//        //将点点加入到ViewGroup中
//        tips = new ImageView[picEntityList.size()];
//        for (int i = 0; i < tips.length; i++) {
//            //设置每个小圆点的间隔为 6dp
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//            params.setMargins(6, 0, 0, 0);
//            ImageView imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new LayoutParams(10, 10));
//            tips[i] = imageView;
//            if (i == 0) {
//                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
//            } else {
//                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
//            }
//
//            group.addView(imageView, params);
//        }
//
//
//        //将图片装载到数组中
//        mImageViews = new SimpleDraweeView[picEntityList.size()];
//        for (int i = 0; i < mImageViews.length; i++) {
////            ImageView imageView = new ImageView(mContext);
//            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
//            simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
////            simpleDraweeView.setScaleType(ScalingUtils.ScaleType.FIT_XY);
//            mImageViews[i] = simpleDraweeView;
//
//
//            final GoodsAdvertResponse.ResultDataEntity.PicEntity picEntity = picEntityList.get(i);
//
//            if ("shop".equals(picEntity.getAd_name())) {
//                simpleDraweeView.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ShopIndexActivity.start(mContext, picEntity.getAd_link());
//                    }
//                });
//            } else {
//                simpleDraweeView.setOnClickListener(new OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Logger.i(picEntity.getAd_name());
//                        Logger.i(picEntity.getAd_link());
//                        GoodsInformationActivity.start(mContext, picEntity.getAd_link(), 1, picEntity.getAd_name(), "");
//                    }
//                });
//            }
//
//
//            simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgThuUri(picEntityList.get(i).getAd_code())));
////            imageView.setBackgroundResource(imgIdArray[i]);
//        }
//
//        //设置Adapter
//        viewPager.setAdapter(new MyAdapter());
//        //设置监听，主要是设置点点的背景
//        viewPager.setOnPageChangeListener(this);
//        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
//        viewPager.setCurrentItem((mImageViews.length) * 100);
//
//    }
//

    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImageViews.length;
//            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView(mImageViews[position % mImageViews.length]);

        }

        /**
         * 载入图片进去，用当前的position 除以 图片数组长度取余数是关键
         */
        @Override
        public Object instantiateItem(View container, int position) {
            Logger.i("获取视图");
//            if (((ViewPager) container) != null) {
//                ((ViewPager) container).removeAllViews();
//            }
            if(mImageViews[position % mImageViews.length].getParent()!=null){
                ((ViewPager) container).removeAllViews();
            }
            ((ViewPager) container).addView(mImageViews[position % mImageViews.length], 0);
            return mImageViews[position % mImageViews.length];
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
        setImageBackground(arg0 % mImageViews.length);
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


}
