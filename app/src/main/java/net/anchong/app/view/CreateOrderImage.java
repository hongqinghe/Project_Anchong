package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/5/16.
 */
public class CreateOrderImage extends LinearLayout {

    private Context context;

    @ViewInject(R.id.sdv)
    private SimpleDraweeView sdv;

    public CreateOrderImage(Context context) {
        super(context);
        this.context = context;
        initView();
    }

    public CreateOrderImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    public CreateOrderImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView();
    }

    private void initView() {
        View.inflate(context, R.layout.layout_img, this);
        x.view().inject(this);
    }

    public void setData(String path) {
        if (!TextUtils.isEmpty(path)) {
            sdv.setImageURI(Uri.parse(path));
        }
    }
}
