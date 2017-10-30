package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.GoodsListResponseModel;
import net.anchong.app.ui.CommunityMyCollecActivity;
import net.anchong.app.uitls.FileUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * Created by baishixin on 16/2/11.
 */
public class MyCommunityHeadView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.iv_community_my_back)
    private ImageView back;
    @ViewInject(R.id.sdv_community_my_headpic)
    private SimpleDraweeView headpic;
    @ViewInject(R.id.iv_community_my_shoucang)
    private ImageView shoucang;
    @ViewInject(R.id.tv_community_my_name)
    private TextView name;


    public MyCommunityHeadView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public MyCommunityHeadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
//        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = mInflater.inflate(R.layout.view_goods, null);

        View.inflate(mContext, R.layout.view_my_community_head, this);

//        this.addView(v);
        x.view().inject(this);
        initEvent();
        if (FileUtils.mgetUserMessageResponseModel != null) {
            headpic.setImageURI(Uri.parse(FileUtils.mgetUserMessageResponseModel.getResultData().getHeadpic()));
            if (!TextUtils.isEmpty(FileUtils.mgetUserMessageResponseModel.getResultData().getNickname())) {
                name.setText(FileUtils.mgetUserMessageResponseModel.getResultData().getNickname());
            }
        }

    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                communityHeadListener.communityHeadBack();
            }
        });

        shoucang.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.i("个人收藏");
                CommunityMyCollecActivity.start(mContext);
            }
        });
    }

    public interface CommunityHeadListener {
        void communityHeadBack();
    }

    private CommunityHeadListener communityHeadListener;

    public void setCommunityHeadListener(CommunityHeadListener communityHeadListener) {
        this.communityHeadListener = communityHeadListener;
    }

    public void setData(GoodsListResponseModel.ResultDataEntity.ListEntity listEntity) {

    }


}
