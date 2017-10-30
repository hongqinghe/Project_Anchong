package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.model.BusinessModel;
import net.anchong.app.entity.response.model.BusinessAdvertResponse;
import net.anchong.app.ui.BusinessDetaActivity;
import net.anchong.app.ui.BusinessHotProjectActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by baishixin on 16/2/11.
 */
public class HotProjectView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.ll_hot_business1)
    private LinearLayout business1;
    @ViewInject(R.id.ll_hot_business2)
    private LinearLayout business2;

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

    public HotProjectView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public HotProjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_hot_project, this);
        x.view().inject(this);

        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessHotProjectActivity.start(mContext, "1");
            }
        });
    }

    /**
     * 设置商机首页热门招标项目 数据源
     *
     * @param recommendEntityList
     * @param showPhone
     */
    public void setData(List<BusinessAdvertResponse.ResultDataEntity.RecommendEntity> recommendEntityList, final int showPhone) {
        if (recommendEntityList != null && recommendEntityList.size() > 0) {
            title1.setText(recommendEntityList.get(0).getTitle());
            if (recommendEntityList != null) {
                if (recommendEntityList.get(0).getPic() != null) {

                    pic1.setImageURI(Uri.parse(recommendEntityList.get(0).getPic().get(0)));
                }
            }

            final BusinessModel businessModel = new BusinessModel();
            businessModel.setPhone(recommendEntityList.get(0).getPhone());
            businessModel.setBid(recommendEntityList.get(0).getBid());
            businessModel.setContact(recommendEntityList.get(0).getContact());
            businessModel.setContent(recommendEntityList.get(0).getContent());
            businessModel.setCreated_at(recommendEntityList.get(0).getCreated_at());
            businessModel.setTag(recommendEntityList.get(0).getTag());
            businessModel.setTags(recommendEntityList.get(0).getTags());
            businessModel.setTitle(recommendEntityList.get(0).getTitle());
            businessModel.setPic(recommendEntityList.get(0).getPic());
            business1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    BusinessDetaActivity.start(mContext, businessModel, showPhone);
                }
            });

            if (recommendEntityList.size() > 1) {
                title2.setText(recommendEntityList.get(1).getTitle());
                if (recommendEntityList != null) {
                    if (recommendEntityList.get(0).getPic() != null) {
                        pic2.setImageURI(Uri.parse(recommendEntityList.get(1).getPic().get(0)));
                    }
                }
                final BusinessModel businessModel2 = new BusinessModel();
                businessModel2.setPhone(recommendEntityList.get(1).getPhone());
                businessModel2.setBid(recommendEntityList.get(1).getBid());
                businessModel2.setContact(recommendEntityList.get(1).getContact());
                businessModel2.setContent(recommendEntityList.get(1).getContent());
                businessModel2.setCreated_at(recommendEntityList.get(1).getCreated_at());
                businessModel2.setTag(recommendEntityList.get(1).getTag());
                businessModel2.setTags(recommendEntityList.get(1).getTags());
                businessModel2.setTitle(recommendEntityList.get(1).getTitle());
                businessModel2.setPic(recommendEntityList.get(1).getPic());
                business2.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BusinessDetaActivity.start(mContext, businessModel2, showPhone);
                    }
                });
            }
        }
    }


}
