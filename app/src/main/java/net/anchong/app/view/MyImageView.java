package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 商机展示列表中六个图片
 * Created by baishixin on 16/4/18.
 */
public class MyImageView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.sdv_mypublish_pic1)
    private SimpleDraweeView simpleDraweeView1;
    @ViewInject(R.id.sdv_mypublish_pic2)
    private SimpleDraweeView simpleDraweeView2;
    @ViewInject(R.id.sdv_mypublish_pic3)
    private SimpleDraweeView simpleDraweeView3;
    @ViewInject(R.id.sdv_mypublish_pic4)
    private SimpleDraweeView simpleDraweeView4;
    @ViewInject(R.id.sdv_mypublish_pic5)
    private SimpleDraweeView simpleDraweeView5;
    @ViewInject(R.id.sdv_mypublish_pic6)
    private SimpleDraweeView simpleDraweeView6;

//    private List<String> pics = null;


    public MyImageView(Context context) {
        super(context);
        mContext = context;
        initView();
    }


    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        /// fresco
        Fresco.initialize(mContext);
        View.inflate(mContext, R.layout.view_myimage, this);
        x.view().inject(this);

    }

    public void setData(List<String> pics) {
        if (pics != null && pics.size() > 0) {

            simpleDraweeView1.setVisibility(View.INVISIBLE);
            simpleDraweeView2.setVisibility(View.INVISIBLE);
            simpleDraweeView3.setVisibility(View.INVISIBLE);
            simpleDraweeView4.setVisibility(View.INVISIBLE);
            simpleDraweeView5.setVisibility(View.INVISIBLE);
            simpleDraweeView6.setVisibility(View.INVISIBLE);

            if (pics.size() < 3) {
                simpleDraweeView4.setVisibility(View.GONE);
                simpleDraweeView5.setVisibility(View.GONE);
                simpleDraweeView6.setVisibility(View.GONE);
            }

            for (int i = 0; i < pics.size(); i++) {
                switch (i) {
                    case 0:
                        simpleDraweeView1.setVisibility(View.VISIBLE);
                        simpleDraweeView1.setImageURI(Uri.parse(pics.get(i)));
                        break;
                    case 1:
                        simpleDraweeView2.setVisibility(View.VISIBLE);
                        simpleDraweeView2.setImageURI(Uri.parse(pics.get(i)));
                        break;
                    case 2:
                        simpleDraweeView3.setVisibility(View.VISIBLE);
                        simpleDraweeView3.setImageURI(Uri.parse(pics.get(i)));
                        break;
                    case 3:
                        simpleDraweeView4.setVisibility(View.VISIBLE);
                        simpleDraweeView4.setImageURI(Uri.parse(pics.get(i)));
                        break;
                    case 4:
                        simpleDraweeView5.setVisibility(View.VISIBLE);
                        simpleDraweeView5.setImageURI(Uri.parse(pics.get(i)));
                        break;
                    case 5:
                        simpleDraweeView6.setVisibility(View.VISIBLE);
                        simpleDraweeView6.setImageURI(Uri.parse(pics.get(i)));
                        break;
                }
            }
        }
    }
}
