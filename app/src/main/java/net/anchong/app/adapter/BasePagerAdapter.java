
package net.anchong.app.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * <CODE>{@link ViewPager}</CODE>的基础适配器
 * <HR>
 * 作者: 白世鑫
 * <p/>
 * 时间: 2013年11月7日 下午3:19:21
 */
public class BasePagerAdapter extends PagerAdapter {
    private final List<PagerModel> childViews;

    public BasePagerAdapter(List<PagerModel> childViews) {
        this.childViews = childViews;
    }

    @Override
    public int getCount() {
        return childViews == null ? 0 : childViews.size();
    }

    public List<PagerModel> getChildViews() {
        return childViews;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return childViews.get(position).pageTitle;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(childViews.get(position).childView);
        return childViews.get(position).childView;
    }

    public View getChildAt(int position) {
        return childViews.get(position).childView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(childViews.get(position).childView);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    public final static class PagerModel {
        public String pageTitle;

        public SimpleDraweeView imageView;

        public View childView;
    }

}
