package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.GoodsListResponseModel;
import net.anchong.app.ui.BusinessInfoActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/2/11.
 */
public class BusinessNavigationView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.ll_business_project)
    private LinearLayout project;
    @ViewInject(R.id.ll_business_rencai)
    private LinearLayout rencai;
    @ViewInject(R.id.ll_business_product)
    private LinearLayout product;

    public BusinessNavigationView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public BusinessNavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_bussiness_avigation, this);
        x.view().inject(this);

        project.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "工程", Toast.LENGTH_SHORT).show();
                BusinessInfoActivity.start(mContext,"工程");
            }
        });
        rencai.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "人才", Toast.LENGTH_SHORT).show();
                BusinessInfoActivity.start(mContext, "人才");
            }
        });
        product.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext, "找货", Toast.LENGTH_SHORT).show();
                BusinessInfoActivity.start(mContext, "找货");
            }
        });


    }

    public void setData(GoodsListResponseModel.ResultDataEntity.ListEntity listEntity, int showPrice) {

    }


}
