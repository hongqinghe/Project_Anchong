package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.BusinessAdvertResponse;
import net.anchong.app.entity.response.model.GoodsListResponseModel;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by baishixin on 16/2/11.
 */
public class BusinessListHeaderView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.ll_hot_business1)
    private LinearLayout business1;
    @ViewInject(R.id.ll_hot_business2)
    private LinearLayout business2;

    //商机首页轮播图
    @ViewInject(R.id.riv_business_header)
    private RotationImageView rotationImageView;
    //热门招标项目
    @ViewInject(R.id.hpv_business)
    private HotProjectView hotProjectView;
    //最新招标项目
    @ViewInject(R.id.npv_business)
    private NewProjectView newProjectView;

    @ViewInject(R.id.iv_hot_business_more)
    private ImageView more;

    @ViewInject(R.id.tv_hot_business_title1)
    private TextView title1;
    @ViewInject(R.id.tv_hot_business_title2)
    private TextView title2;

    @ViewInject(R.id.sdv_hot_business_pic1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_hot_business_pic2)
    private SimpleDraweeView pic2;

    public BusinessListHeaderView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public BusinessListHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_business_list_header, this);
        x.view().inject(this);


    }

    /**
     * 设置轮播图
     *
     * @param picList
     */
    public void setPic(List<BusinessAdvertResponse.ResultDataEntity.PicEntity> picList) {
        if (picList != null && picList.size() > 0) {
            rotationImageView.setBusinessData(picList);
        }
    }

    /**
     * 设置最新招标项目
     *
     * @param recentEntityList
     */
    public void setRecent(List<BusinessAdvertResponse.ResultDataEntity.RecentEntity> recentEntityList,int showPhone) {
        if (recentEntityList != null && recentEntityList.size() > 0) {
            newProjectView.setData(recentEntityList,showPhone);
        }
    }

    /**
     * 设置热门招标项目
     *
     * @param recommendEntityList
     */
    public void setRecommend(List<BusinessAdvertResponse.ResultDataEntity.RecommendEntity> recommendEntityList, int showPhone) {
        if (recommendEntityList != null && recommendEntityList.size() > 0) {
            hotProjectView.setData(recommendEntityList, showPhone);
        }
    }


    public void setData(GoodsListResponseModel.ResultDataEntity.ListEntity listEntity, int showPrice) {

    }


}
