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
import net.anchong.app.ui.GoodsInformationActivity;
import net.anchong.app.uitls.ImageUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 商城首页广告第一个模块
 * Created by baishixin on 16/2/11.
 */
public class GoodsOneView extends LinearLayout {

    private Context mContext;
    @ViewInject(R.id.tv_goods_one_name)
    private TextView name;
    @ViewInject(R.id.sdv_hot_business_pic1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_hot_business_pic2)
    private SimpleDraweeView pic2;
    @ViewInject(R.id.sdv_hot_business_pic3)
    private SimpleDraweeView pic3;
    @ViewInject(R.id.sdv_hot_business_pic4)
    private SimpleDraweeView pic4;

    private GoodsAdvertResponse.ResultDataEntity.OneEntity oneEntity;


    public GoodsOneView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public GoodsOneView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_goods_one, this);
        x.view().inject(this);

    }

    public void setData(GoodsAdvertResponse.ResultDataEntity.OneEntity oneEntity, final int shopPrice) {
        this.oneEntity = oneEntity;
        if (!TextUtils.isEmpty(oneEntity.getName())) {
            name.setText(oneEntity.getName());
        }

        final List<GoodsAdvertResponse.ResultDataEntity.OneEntity.ListEntity> pics = oneEntity.getList();
        if (pics != null) {
            for (int i = 0; i < pics.size(); i++) {
                switch (i) {
                    case 0:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
//                            pic1.setImageURI(Uri.parse(pics.get(i).getAd_code()));
                            pic1.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            pic1.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    GoodsInformationActivity.start(mContext, pics.get(0).getAd_link(),
                                            shopPrice, pics.get(0).getAd_name(), "");
                                }
                            });
                        }
                        break;
                    case 1:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
//                            pic2.setImageURI(Uri.parse(pics.get(i).getAd_code()));
                            pic2.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            pic2.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    GoodsInformationActivity.start(mContext, pics.get(1).getAd_link(),
                                            shopPrice, pics.get(1).getAd_name(), "");
                                }
                            });
                        }
                        break;
                    case 2:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
//                            pic3.setImageURI(Uri.parse(pics.get(i).getAd_code()));
                            pic3.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            pic3.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    GoodsInformationActivity.start(mContext, pics.get(2).getAd_link(),
                                            shopPrice, pics.get(2).getAd_name(), "");
                                }
                            });
                        }
                        break;
                    case 3:
                        if (!TextUtils.isEmpty(pics.get(i).getAd_code())) {
//                            pic4.setImageURI(Uri.parse(pics.get(i).getAd_code()));
                            pic4.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i).getAd_code())));
                            pic4.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    GoodsInformationActivity.start(mContext, pics.get(3).getAd_link(),
                                            shopPrice, pics.get(3).getAd_name(), "");
                                }
                            });
                        }
                        break;
                }
            }
        }
    }


}
