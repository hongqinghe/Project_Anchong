package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.BusinessAdvertResponse;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by baishixin on 16/2/11.
 */
public class ShopIndexTabView extends LinearLayout implements View.OnClickListener {

    @ViewInject(R.id.tv_category)
    private TextView tvCategory;
    @ViewInject(R.id.iv_category_arrow)
    private ImageView ivCategoryArrow;
    @ViewInject(R.id.tv_sort)
    private TextView tvSort;
    @ViewInject(R.id.iv_sort_arrow)
    private ImageView ivSortArrow;
    @ViewInject(R.id.tv_filter)
    private TextView tvFilter;
    @ViewInject(R.id.iv_filter_arrow)
    private ImageView ivFilterArrow;
    @ViewInject(R.id.ll_category)
    private LinearLayout llCategory;
    @ViewInject(R.id.ll_sort)
    private LinearLayout llSort;
    @ViewInject(R.id.ll_filter)
    private LinearLayout llFilter;
    @ViewInject(R.id.ll_head_layout)
    private LinearLayout llHeadLayout;


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

    private int indexTab = -1;
    private String childTab ="-1";

    private Context mContext;

    public ShopIndexTabView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public ShopIndexTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_filter_layout, this);
        x.view().inject(this);

        initEvent();
    }


    private void initEvent() {

        llCategory.setOnClickListener(this);
        llSort.setOnClickListener(this);
        llFilter.setOnClickListener(this);
        textViewAll.setOnClickListener(this);
        textViewSale.setOnClickListener(this);
        textViewNew.setOnClickListener(this);
        linearLayoutPrice.setOnClickListener(this);

    }

    public void setData(List<BusinessAdvertResponse.ResultDataEntity.RecommendEntity> recommendEntityList, final int showPhone) {

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_category:
                if (indexTab == -1 || indexTab != 1) {
                    indexTab = 1;
                    shopIndexTabOnclickListener.indexClick();
                    //TODO:改变颜色，隐藏下选项卡。
                    //隐藏第二个选项卡
                    tab.setVisibility(View.GONE);
                    tvCategory.setTextColor(getResources().getColor(R.color.theme));
                    ivCategoryArrow.setImageResource(R.drawable.shops);
                    tvSort.setTextColor(getResources().getColor(R.color.font_black_2));
                    ivSortArrow.setImageResource(R.drawable.all_products_default);
                    tvFilter.setTextColor(getResources().getColor(R.color.font_black_2));
                    ivFilterArrow.setImageResource(R.drawable.new_default);

                }

                break;
            case R.id.ll_sort:
                switch (childTab) {
                    case "0":

                        break;
                    case "1":

                        break;
                    case "2":

                        break;
                    case "3":

                        break;
                    case "4":

                        break;

                }
                if (indexTab == -1 || indexTab != 2) {
                    indexTab = 2;
                    if ("-1".equals(childTab)) {
                        childTab = "0";
                    }
                    shopIndexTabOnclickListener.allClick(childTab);
                    tab.setVisibility(View.VISIBLE);
                    tvCategory.setTextColor(getResources().getColor(R.color.font_black_2));
                    ivCategoryArrow.setImageResource(R.drawable.shops_default);
                    tvSort.setTextColor(getResources().getColor(R.color.theme));
                    ivSortArrow.setImageResource(R.drawable.all_products);
                    tvFilter.setTextColor(getResources().getColor(R.color.font_black_2));
                    ivFilterArrow.setImageResource(R.drawable.new_default);
                }
                break;
            case R.id.ll_filter:
                if (indexTab == -1 || indexTab != 3) {
                    indexTab = 3;
                    shopIndexTabOnclickListener.newClick();
                    tab.setVisibility(View.GONE);
                    tvCategory.setTextColor(getResources().getColor(R.color.font_black_2));
                    ivCategoryArrow.setImageResource(R.drawable.shops_default);
                    tvSort.setTextColor(getResources().getColor(R.color.font_black_2));
                    ivSortArrow.setImageResource(R.drawable.all_products_default);
                    tvFilter.setTextColor(getResources().getColor(R.color.theme));
                    ivFilterArrow.setImageResource(R.drawable.new_blut);
                }
                break;
            //全部
            case R.id.tv_shop_index_tab_all:
                if (indexTab == 2) {
                    if (!"0".equals(childTab)) {
                        childTab = "0";
                        textViewAll.setTextColor(getResources().getColor(R.color.theme));
                        textViewSale.setTextColor(getResources().getColor(R.color.font_black_2));
                        textViewNew.setTextColor(getResources().getColor(R.color.font_black_2));
                        textViewPrice.setTextColor(getResources().getColor(R.color.font_black_2));
                        shopIndexTabOnclickListener.allClick(childTab);
                    }
                }
                break;
            //销量
            case R.id.tv_shop_index_tab_sale:
                if (indexTab == 2) {
                    if (!"1".equals(childTab)) {
                        childTab = "1";
                        textViewAll.setTextColor(getResources().getColor(R.color.font_black_2));
                        textViewSale.setTextColor(getResources().getColor(R.color.theme));
                        textViewNew.setTextColor(getResources().getColor(R.color.font_black_2));
                        textViewPrice.setTextColor(getResources().getColor(R.color.font_black_2));
                        shopIndexTabOnclickListener.allClick(childTab);
                    }
                }
                break;
            //新品
            case R.id.tv_shop_index_tab_new:
                if (indexTab == 2) {
                    if (!"2".equals(childTab)) {
                        childTab = "2";
                        textViewAll.setTextColor(getResources().getColor(R.color.font_black_2));
                        textViewSale.setTextColor(getResources().getColor(R.color.font_black_2));
                        textViewNew.setTextColor(getResources().getColor(R.color.theme));
                        textViewPrice.setTextColor(getResources().getColor(R.color.font_black_2));
                        shopIndexTabOnclickListener.allClick(childTab);
                    }
                }
                break;
            //价格
            case R.id.ll_shop_index_tab_price:
                if (indexTab == 2) {
                    if ("3".equals(childTab)) {
                        childTab = "4";
                        imageViewPrice.setImageResource(R.drawable.up);
                    }else if ("4".equals(childTab)) {
                        childTab = "3";
                        imageViewPrice.setImageResource(R.drawable.down);
                    }
                    if (!"3".equals(childTab) && !"4".equals(childTab)) {
                        childTab = "3";
                        imageViewPrice.setImageResource(R.drawable.down);
                    }

                    textViewAll.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewSale.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewNew.setTextColor(getResources().getColor(R.color.font_black_2));
                    textViewPrice.setTextColor(getResources().getColor(R.color.theme));
                    shopIndexTabOnclickListener.allClick(childTab);
                }
                break;

        }
    }
    private ShopIndexTabOnclickListener shopIndexTabOnclickListener;

    public void setShopIndexTabOnclickListener(ShopIndexTabOnclickListener shopIndexTabOnclickListener) {
        this.shopIndexTabOnclickListener = shopIndexTabOnclickListener;
    }

    public interface ShopIndexTabOnclickListener {
        void indexClick();

        void allClick(String action);

        void newClick();
    }
}
