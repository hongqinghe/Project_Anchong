package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.BusinessAdvertResponse;
import net.anchong.app.ui.BusinessHotProjectActivity;
import net.anchong.app.ui.BusinessHotSearchActivity;
import net.anchong.app.uitls.ImageUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by baishixin on 16/2/11.
 */
public class NewProjectView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.rl_new_business1)
    private RelativeLayout business1;
    @ViewInject(R.id.rl_new_business2)
    private RelativeLayout business2;
    @ViewInject(R.id.rl_new_business3)
    private RelativeLayout business3;
    @ViewInject(R.id.rl_new_business4)
    private RelativeLayout business4;


    @ViewInject(R.id.iv_new_business_more)
    private ImageView more;
    @ViewInject(R.id.iv_new_business_more2)
    private ImageView more2;

    @ViewInject(R.id.tv_new_business_city1)
    private TextView city1;
    @ViewInject(R.id.tv_new_business_city2)
    private TextView city2;
    @ViewInject(R.id.tv_new_business_city3)
    private TextView city3;
    @ViewInject(R.id.tv_new_business_city4)
    private TextView city4;


    @ViewInject(R.id.sdv_new_business_pic1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_new_business_pic2)
    private SimpleDraweeView pic2;
    @ViewInject(R.id.sdv_new_business_pic3)
    private SimpleDraweeView pic3;
    @ViewInject(R.id.sdv_new_business_pic4)
    private SimpleDraweeView pic4;


    public NewProjectView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public NewProjectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();

        more.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessHotProjectActivity.start(mContext, "2");
            }
        });

        more2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                BusinessHotProjectActivity.start(mContext, "3");
            }
        });
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_new_project, this);
        x.view().inject(this);


    }

    public void setData(final List<BusinessAdvertResponse.ResultDataEntity.RecentEntity> recentEntityList, int showPrice) {
        if (recentEntityList != null && recentEntityList.size() > 0) {

            for (int i = 0; i < recentEntityList.size(); i++) {
                switch (i) {
                    case 0:
                        pic1.setImageURI(Uri.parse(ImageUtils.getImgUri(recentEntityList.get(i).getAd_code())));
                        city1.setText(recentEntityList.get(i).getAd_name());
                        business1.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BusinessHotSearchActivity.start(mContext, "2", recentEntityList.get(0).getAd_name());
                            }
                        });
                        break;
                    case 1:
                        pic2.setImageURI(Uri.parse(ImageUtils.getImgUri(recentEntityList.get(i).getAd_code())));
                        city2.setText(recentEntityList.get(i).getAd_name());
                        business2.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BusinessHotSearchActivity.start(mContext, "2", recentEntityList.get(1).getAd_name());
                            }
                        });
                        break;
                    case 2:
                        pic3.setImageURI(Uri.parse(ImageUtils.getImgUri(recentEntityList.get(i).getAd_code())));
                        city3.setText(recentEntityList.get(i).getAd_name());
                        business3.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BusinessHotSearchActivity.start(mContext, "2", recentEntityList.get(2).getAd_name());
                            }
                        });
                        break;
                    case 3:
                        pic4.setImageURI(Uri.parse(ImageUtils.getImgUri(recentEntityList.get(i).getAd_code())));
                        city4.setText(recentEntityList.get(i).getAd_name());
                        business4.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                BusinessHotSearchActivity.start(mContext, "2", recentEntityList.get(3).getAd_name());
                            }
                        });
                        break;

                }
            }


        }
    }


}
