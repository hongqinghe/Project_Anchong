package net.anchong.app.StickyHeaderListView.view;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.StickyHeaderListView.adapter.FilterLeftAdapter;
import net.anchong.app.StickyHeaderListView.adapter.FilterOneAdapter;
import net.anchong.app.StickyHeaderListView.adapter.FilterRightAdapter;
import net.anchong.app.StickyHeaderListView.model.FilterData;
import net.anchong.app.StickyHeaderListView.model.FilterEntity;
import net.anchong.app.StickyHeaderListView.model.FilterTwoEntity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by sunfusheng on 16/4/20.
 */
public class FilterView extends LinearLayout implements View.OnClickListener {

    @ViewInject(R.id.tv_category)
    TextView tvCategory;
    @ViewInject(R.id.iv_category_arrow)
    ImageView ivCategoryArrow;
    @ViewInject(R.id.tv_sort)
    TextView tvSort;
    @ViewInject(R.id.iv_sort_arrow)
    ImageView ivSortArrow;
    @ViewInject(R.id.tv_filter)
    TextView tvFilter;
    @ViewInject(R.id.iv_filter_arrow)
    ImageView ivFilterArrow;
    @ViewInject(R.id.ll_category)
    LinearLayout llCategory;
    @ViewInject(R.id.ll_sort)
    LinearLayout llSort;
    @ViewInject(R.id.ll_filter)
    LinearLayout llFilter;
    //    @ViewInject(R.id.lv_left)
//    ListView lvLeft;
//    @ViewInject(R.id.lv_right)
//    ListView lvRight;
    @ViewInject(R.id.ll_head_layout)
    LinearLayout llHeadLayout;
    //    @ViewInject(R.id.ll_content_list_view)
//    LinearLayout llContentListView;

    @ViewInject(R.id.ll_shop_index_tab)
    private LinearLayout tab;
    @ViewInject(R.id.tv_shop_index_tab_all)
    private TextView textViewAll;
    @ViewInject(R.id.tv_shop_index_tab_sale)
    private TextView textViewSale;
    @ViewInject(R.id.tv_shop_index_tab_new)
    private TextView textViewNew;
    @ViewInject(R.id.tv_shop_index_tab_price)
    private TextView textViewPrice;
    @ViewInject(R.id.ll_shop_index_tab_price)
    private LinearLayout linearLayoutPrice;
    @ViewInject(R.id.iv_shop_index_price)
    private ImageView imageViewPrice;

    private Context mContext;
    private Activity mActivity;
    private boolean isStickyTop = false; // 是否吸附在顶部
    private boolean isShowing = false;
    private int filterPosition = -1;
    private int panelHeight;
    private FilterData filterData;

    private FilterTwoEntity selectedCategoryEntity; // 被选择的分类项
    private FilterEntity selectedSortEntity; // 被选择的排序项
    private FilterEntity selectedFilterEntity; // 被选择的筛选项

    private FilterLeftAdapter leftAdapter;
    private FilterRightAdapter rightAdapter;
    private FilterOneAdapter sortAdapter;
    private FilterOneAdapter filterAdapter;

    private String defaultAction = "0";
    private String selectAction = "-1";

    public FilterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FilterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mContext = context;
//        View view = LayoutInflater.from(context).inflate(R.layout.view_filter_layout, this);
        View.inflate(context, R.layout.view_filter_layout, this);
//        ButterKnife.bind(this, view);
        x.view().inject(this);

        initData();
        initView();
        initListener();
    }

    private void initData() {

    }

    private void initView() {
//        viewMaskBg.setVisibility(GONE);
        tab.setVisibility(View.GONE);

        textViewAll.setOnClickListener(this);
        textViewSale.setOnClickListener(this);
        textViewNew.setOnClickListener(this);
        linearLayoutPrice.setOnClickListener(this);


//        llContentListView.setVisibility(GONE);
    }

    private void initListener() {
        llCategory.setOnClickListener(this);
        llSort.setOnClickListener(this);
        llFilter.setOnClickListener(this);
//        viewMaskBg.setOnClickListener(this);
//        llContentListView.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_category:
                filterPosition = 0;
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.ll_sort:
                filterPosition = 1;
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(filterPosition);
                }
                break;
            case R.id.ll_filter:
                filterPosition = 2;
                if (onFilterClickListener != null) {
                    onFilterClickListener.onFilterClick(filterPosition);
                }
                break;
//            case R.id.view_mask_bg:
//                hide();
//                break;
            case R.id.tv_shop_index_tab_all:
                selectAction = "0";
                if ("0".equals(defaultAction)) {
                    return;
                }
                defaultAction = "0";
                if (onGoodsTabClickListener != null) {
                    onGoodsTabClickListener.onGoodsTabClick(defaultAction);
                    textViewAll.setTextColor(getResources().getColor(R.color.theme));
                    textViewSale.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewNew.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewPrice.setTextColor(getResources().getColor(R.color.font_black_2));
                }
                break;
            case R.id.tv_shop_index_tab_sale:
                selectAction = "1";
                if ("1".equals(defaultAction)) {
                    return;
                }
                defaultAction = "1";
                if (onGoodsTabClickListener != null) {
                    onGoodsTabClickListener.onGoodsTabClick(defaultAction);
                    textViewAll.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewSale.setTextColor(getResources().getColor(R.color.theme));
                    textViewNew.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewPrice.setTextColor(getResources().getColor(R.color.font_black_2));
                }
                break;
            case R.id.tv_shop_index_tab_new:
                selectAction = "2";
                if ("2".equals(defaultAction)) {
                    return;
                }
                defaultAction = "2";
                if (onGoodsTabClickListener != null) {
                    onGoodsTabClickListener.onGoodsTabClick(defaultAction);
                    textViewAll.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewSale.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewNew.setTextColor(getResources().getColor(R.color.theme));
                    textViewPrice.setTextColor(getResources().getColor(R.color.font_black_2));
                }
                break;
            case R.id.ll_shop_index_tab_price:
                selectAction = "3";
                if ("3".equals(defaultAction)) {
                    selectAction = "4";
                    imageViewPrice.setImageResource(R.drawable.up);
                }
                if ("4".equals(defaultAction)) {
                    selectAction = "3";
                    imageViewPrice.setImageResource(R.drawable.down);
                }
                defaultAction = selectAction;

                if (onGoodsTabClickListener != null) {
                    onGoodsTabClickListener.onGoodsTabClick(defaultAction);
                    textViewAll.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewSale.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewNew.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewPrice.setTextColor(getResources().getColor(R.color.theme));
                }
                break;
        }

    }

    // 显示筛选布局
    public void showFilterLayout(int position) {
        Logger.i("显示布局界面");
        switch (position) {
            case 0:
                setCategoryAdapter();
                break;
            case 1:
                setSortAdapter();
                break;
            case 2:
                setFilterAdapter();
                break;
        }
    }

    // 设置分类数据
    private void setCategoryAdapter() {
        tab.setVisibility(View.GONE);
        ivCategoryArrow.setImageResource(R.drawable.shops);
        tvCategory.setTextColor(getResources().getColor(R.color.theme));

        tvSort.setTextColor(mActivity.getResources().getColor(R.color.font_black_2));
        ivSortArrow.setImageResource(R.drawable.all_products_default);

        tvFilter.setTextColor(mActivity.getResources().getColor(R.color.font_black_2));
        ivFilterArrow.setImageResource(R.drawable.new_default);
    }

    // 设置排序数据
    private void setSortAdapter() {

        tab.setVisibility(VISIBLE);
        ivCategoryArrow.setImageResource(R.drawable.shops_default);
        tvCategory.setTextColor(getResources().getColor(R.color.font_black_2));

        tvSort.setTextColor(mActivity.getResources().getColor(R.color.theme));
        ivSortArrow.setImageResource(R.drawable.all_products);

        tvFilter.setTextColor(mActivity.getResources().getColor(R.color.font_black_2));
        ivFilterArrow.setImageResource(R.drawable.new_default);
    }

    // 设置筛选数据
    private void setFilterAdapter() {
        tab.setVisibility(View.GONE);
        ivCategoryArrow.setImageResource(R.drawable.shops_default);
        tvCategory.setTextColor(getResources().getColor(R.color.font_black_2));

        tvSort.setTextColor(mActivity.getResources().getColor(R.color.font_black_2));
        ivSortArrow.setImageResource(R.drawable.all_products_default);

        tvFilter.setTextColor(mActivity.getResources().getColor(R.color.theme));
        ivFilterArrow.setImageResource(R.drawable.new_blut);

    }

    // 动画显示
    private void show() {
        isShowing = true;
//        viewMaskBg.setVisibility(VISIBLE);
        tab.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                tab.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                panelHeight = tab.getHeight();
                ObjectAnimator.ofFloat(tab, "translationY", -panelHeight, 0).setDuration(200).start();
            }
        });
    }

    // 隐藏动画
    public void hide() {
        isShowing = false;
//        viewMaskBg.setVisibility(View.GONE);
        ObjectAnimator.ofFloat(tab, "translationY", 0, -panelHeight).setDuration(200).start();
    }

    // 是否吸附在顶部
    public void setStickyTop(boolean stickyTop) {
        isStickyTop = stickyTop;
    }

    // 设置筛选数据
    public void setFilterData(Activity activity, FilterData filterData) {
        this.mActivity = activity;
        this.filterData = filterData;
    }

    // 是否显示
    public boolean isShowing() {
        return isShowing;
    }

    // 筛选视图点击
    private OnFilterClickListener onFilterClickListener;

    public void setOnFilterClickListener(OnFilterClickListener onFilterClickListener) {
        this.onFilterClickListener = onFilterClickListener;
    }

    public interface OnFilterClickListener {
        void onFilterClick(int position);
    }

    //商品分类点击
    private OnGoodsTabClickListener onGoodsTabClickListener;

    public void setOnGoodsTabClickListener(OnGoodsTabClickListener onGoodsTabClickListener) {
        this.onGoodsTabClickListener = onGoodsTabClickListener;
    }

    public interface OnGoodsTabClickListener {
        void onGoodsTabClick(String action);
    }

}
