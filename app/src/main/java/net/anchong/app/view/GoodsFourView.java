package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.GoodsAdvertResponse;
import net.anchong.app.ui.ShopIndexActivity;
import net.anchong.app.uitls.ImageUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 商城首页广告第一个模块
 * Created by baishixin on 16/2/11.
 */
public class GoodsFourView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.sdv_hot_business_pic1)
    private SimpleDraweeView pic1;


    public GoodsFourView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public GoodsFourView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_goods_four, this);
        x.view().inject(this);
    }

    public void setData(GoodsAdvertResponse.ResultDataEntity.FourEntity fourEntity, final int shopPrice) {

        final List<GoodsAdvertResponse.ResultDataEntity.FourEntity.ListEntity> pics = fourEntity.getList();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                switch (i) {
                    case 0:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
                            pic1.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            pic1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ShopIndexActivity.start(mContext, pics.get(0).getAd_link());
                                }
                            });
                        }
                        break;
                }
            }
        }
    }


}
