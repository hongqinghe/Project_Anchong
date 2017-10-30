package net.anchong.app.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.cengalabs.flatui.views.FlatEditText;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.entity.CertificationModel;
import net.anchong.app.ui.CertificationActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 商家认证界面，添加认证的自定义View
 * Created by baishixin on 16/4/5.
 */
public class AddCertificationView extends LinearLayout implements View.OnClickListener {

    private static final int DISPLAY_IMAGE = 1;
    private Context mContext;
    private int index;
    @ViewInject(R.id.et_view_certification_name)
    private FlatEditText mEditText_name;
    @ViewInject(R.id.et_view_certification_info)
    private FlatEditText mEditText_info;
    @ViewInject(R.id.sdv_view_certification)
    private SimpleDraweeView mImageView_icon;

    private Handler handler = null;

    public void setIndex(int index) {
        this.index = index;
    }

    public AddCertificationView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public AddCertificationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }


    /**
     * 加载界面
     */
    private void initView() {
        //填充布局
        View.inflate(mContext, R.layout.view_add_certification, this);
        //初始化注解加载组件
        x.view().inject(this);
        mImageView_icon.setOnClickListener(this);

        mEditText_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CertificationActivity.certificationModels.get(index).setName(mEditText_name.getText().toString().trim());
            }
        });
//        mEditText_name.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    CertificationActivity.flag = index;
//                }
//                return_back true;
//            }
//        });
        mEditText_info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                CertificationActivity.certificationModels.get(index).setDesc(mEditText_info.getText().toString().trim());
            }
        });
//        mEditText_info.setOnTouchListener(new OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    CertificationActivity.flag = index;
//                }
//                return_back true;
//            }
//        });
    }

    public void setData(CertificationModel certificationModel) {
        if (certificationModel.getName() == null || "".equals(certificationModel.getName())) {
            mEditText_name.setText("");
        } else {
            mEditText_name.setText(certificationModel.getName());
        }
        if (certificationModel.getDesc() == null || "".equals(certificationModel.getDesc())) {
            mEditText_info.setText("");
        } else {
            mEditText_info.setText(certificationModel.getDesc());
        }
        if (certificationModel.getImg_url() == null || "".equals(certificationModel.getImg_url())) {
            GenericDraweeHierarchy hierarchy = mImageView_icon.getHierarchy();
            hierarchy.setPlaceholderImage(R.drawable.icon_placeholder);
//            mImageView_icon.setId(R.drawable.icon_placeholder);
//            mImageView_icon.setImageURI(Uri.parse(""));
        } else {
            mImageView_icon.setImageURI(Uri.parse(certificationModel.getImg_url()));
            Logger.i("图片的地址：" + certificationModel.getImg_url());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdv_view_certification:
                CertificationActivity.flag = index;
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                CertificationActivity.flag = index;
                ((CertificationActivity) mContext).startActivityForResult(i, CertificationActivity.CHOSE_IMAGE_VIA_CAMERA);
                break;
        }
    }

    //计算图片缩放比例
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


}
