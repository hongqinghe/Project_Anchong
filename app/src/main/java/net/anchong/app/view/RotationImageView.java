package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.model.BusinessModel;
import net.anchong.app.entity.request.model.BusinessIndexRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.BusinessAdvertResponse;
import net.anchong.app.entity.response.model.BusinessIndexResponse;
import net.anchong.app.entity.response.model.BusinessRotationResponse;
import net.anchong.app.entity.response.model.GoodsAdvertResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.ui.BusinessDetaActivity;
import net.anchong.app.ui.GoodsInformationActivity;
import net.anchong.app.ui.NewsDetaActivity;
import net.anchong.app.ui.ShopIndexActivity;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.ImageUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import okhttp3.Call;


/**
 * 商城首页轮播图 自定义View
 * Created by baishixin on 16/2/11.
 */
public class RotationImageView extends FrameLayout implements ViewPager.OnPageChangeListener {

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
    private SimpleDraweeView[] mImageViews;

    /**
     * 图片资源id
     */
    private int[] imgIdArray;
    @ViewInject(R.id.viewGroup_index_header)
    private ViewGroup group;


    public RotationImageView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public RotationImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_custom_rotation, this);
        x.view().inject(this);
    }

    /**
     * 商机首页轮播图
     *
     * @param picEntityList
     */
    public void setBusinessData(final List<BusinessAdvertResponse.ResultDataEntity.PicEntity> picEntityList) {

        //将点点加入到ViewGroup中
        tips = new ImageView[picEntityList.size()];
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
        mImageViews = new SimpleDraweeView[picEntityList.size()];
        for (int i = 0; i < mImageViews.length; i++) {
            index = i;
//            ImageView imageView = new ImageView(mContext);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
            simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
//            simpleDraweeView.setScaleType(ScalingUtils.ScaleType.FIT_XY);
            mImageViews[i] = simpleDraweeView;
            simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgThuUri(picEntityList.get(i).getAd_code())));

            final BusinessAdvertResponse.ResultDataEntity.PicEntity picEntity = picEntityList.get(i);

            if ("business".equals(picEntity.getAd_name())) {
                simpleDraweeView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startBusinessInfo(picEntity.getAd_link());
                    }
                });
            } else if ("news".equals(picEntity.getAd_name())) {
                simpleDraweeView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startNews(MyApplication.AD_URL_BEFORE + picEntity.getAd_link());
                    }
                });
            }
        }

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);

        //activity启动两秒钟后，发送一个message，用来将viewPager中的图片切换到下一个
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }

    /**
     * 打开新闻视图
     */
    private void startNews(String url) {
        NewsDetaActivity.start(mContext, url);
    }

    /**
     * 根据 bid 打开商机详情页
     *
     * @param ad_link
     */
    private void startBusinessInfo(String ad_link) {

        BusinessIndexRequest businessIndexRequest = new BusinessIndexRequest(ad_link);
        String requestParam = new Gson().toJson(businessIndexRequest);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", businessIndexRequest);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", businessIndexRequest);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(MyApplication.BUSINESSBUSINESSINDEX + requestModel.getTime() + requestModel.getGuid() + requestParam + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.BUSINESSBUSINESSINDEX)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", requestParam)
                .addParams("signature", signature)
                .build()
                .execute(BusinessIndexResponse.class, new CommonCallback<BusinessIndexResponse>() {
                    @Override
                    public void onSuccess(BusinessIndexResponse response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            BusinessModel businessModel = new BusinessModel();
                            businessModel.setPhone(response.getResultData().getData().getPhone());
                            businessModel.setBid(response.getResultData().getData().getBid());
                            businessModel.setContact(response.getResultData().getData().getContact());
                            businessModel.setContent(response.getResultData().getData().getContent());
                            businessModel.setCreated_at(response.getResultData().getData().getCreated_at());
                            businessModel.setTag(response.getResultData().getData().getTag());
                            businessModel.setTags(response.getResultData().getData().getTags());
                            businessModel.setTitle(response.getResultData().getData().getTitle());
                            businessModel.setPic(response.getResultData().getData().getPic());
                            BusinessDetaActivity.start(mContext, businessModel, response.getResultData().getShowphone());
                        } else {
                            Toast.makeText(mContext, mContext.getString(R.string.request_error_msg), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(mContext, mContext.getString(R.string.request_error_msg), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                });

    }

    private int index = 0;

    public void setBusinessAvdData(List<BusinessRotationResponse.ResultDataEntity> picEntityList) {

        //将点点加入到ViewGroup中
        tips = new ImageView[picEntityList.size()];
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
        mImageViews = new SimpleDraweeView[picEntityList.size()];
        for (int i = 0; i < mImageViews.length; i++) {
//            ImageView imageView = new ImageView(mContext);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
            simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
//            simpleDraweeView.setScaleType(ScalingUtils.ScaleType.FIT_XY);
            mImageViews[i] = simpleDraweeView;
            simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgThuUri(picEntityList.get(i).getAd_code())));
//            imageView.setBackgroundResource(imgIdArray[i]);
        }

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);

        //activity启动两秒钟后，发送一个message，用来将viewPager中的图片切换到下一个
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }


    /**
     * 商城首页轮播图
     *
     * @param picEntityList
     */
    public void setGoodsAvdData(List<GoodsAdvertResponse.ResultDataEntity.PicEntity> picEntityList) {

        //将点点加入到ViewGroup中
        tips = new ImageView[picEntityList.size()];
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
        mImageViews = new SimpleDraweeView[picEntityList.size()];
        for (int i = 0; i < mImageViews.length; i++) {
//            ImageView imageView = new ImageView(mContext);
            SimpleDraweeView simpleDraweeView = new SimpleDraweeView(mContext);
            simpleDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY);
//            simpleDraweeView.setScaleType(ScalingUtils.ScaleType.FIT_XY);
            mImageViews[i] = simpleDraweeView;


            final GoodsAdvertResponse.ResultDataEntity.PicEntity picEntity = picEntityList.get(i);

            if ("shop".equals(picEntity.getAd_name())) {
                simpleDraweeView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ShopIndexActivity.start(mContext, picEntity.getAd_link());
                    }
                });
            } else {
                simpleDraweeView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Logger.i(picEntity.getAd_name());
                        Logger.i(picEntity.getAd_link());
                        GoodsInformationActivity.start(mContext, picEntity.getAd_link(), 1, picEntity.getAd_name(), "");
                    }
                });
            }


            simpleDraweeView.setImageURI(Uri.parse(ImageUtils.getImgThuUri(picEntityList.get(i).getAd_code())));
//            imageView.setBackgroundResource(imgIdArray[i]);
        }

        //设置Adapter
        viewPager.setAdapter(new MyAdapter());
        //设置监听，主要是设置点点的背景
        viewPager.setOnPageChangeListener(this);
        //设置ViewPager的默认项, 设置为长度的100倍，这样子开始就能往左滑动
        viewPager.setCurrentItem((mImageViews.length) * 100);

        //activity启动两秒钟后，发送一个message，用来将viewPager中的图片切换到下一个
        mHandler.sendEmptyMessageDelayed(1, 2000);
    }


    public class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
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

    private Handler mHandler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    int totalcount = mImageViews.length;//autoChangeViewPager.getChildCount();
                    int currentItem = viewPager.getCurrentItem();

                    int toItem = currentItem + 1 == totalcount ? 0 : currentItem + 1;

//                    Log.i(TAG, "totalcount: " + totalcount + "   currentItem: " + currentItem + "   toItem: " + toItem);

                    viewPager.setCurrentItem(toItem, true);

                    //每两秒钟发送一个message，用于切换viewPager中的图片
                    this.sendEmptyMessageDelayed(1, 3000);
            }
        }
    };


}
