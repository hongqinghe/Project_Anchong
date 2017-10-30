package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.GoodsAdvertResponse;
import net.anchong.app.ui.ShopIndexActivity;
import net.anchong.app.uitls.ImageUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 商城首页广告第二个模块
 * Created by baishixin on 16/2/11.
 */
public class GoodsTwoView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.tv_goods_two_name)
    private TextView name;
    @ViewInject(R.id.sdv_hot_business_pic1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_hot_business_pic2)
    private SimpleDraweeView pic2;
    @ViewInject(R.id.sdv_hot_business_pic3)
    private SimpleDraweeView pic3;

    public GoodsTwoView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public GoodsTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_goods_two, this);
        x.view().inject(this);
    }

    int index = -1;

    public void setData(GoodsAdvertResponse.ResultDataEntity.TwoEntity twoEntity, final int shopPrice) {
        if (!TextUtils.isEmpty(twoEntity.getName())) {
            name.setText(twoEntity.getName());
        }

        final List<GoodsAdvertResponse.ResultDataEntity.TwoEntity.ListEntity> pics = twoEntity.getList();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                switch (i) {
                    case 0:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
                            pic1.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            index = i;
                            pic1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ShopIndexActivity.start(mContext, pics.get(0).getAd_link());
                                }
                            });
                        }
                        break;
                    case 1:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
                            pic2.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            index = i;
                            pic2.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ShopIndexActivity.start(mContext, pics.get(1).getAd_link());
                                }
                            });
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
                            pic3.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            index = i;
                            pic3.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ShopIndexActivity.start(mContext, pics.get(2).getAd_link());
                                }
                            });
                        }
                        break;
                }
            }
        }
    }


}
