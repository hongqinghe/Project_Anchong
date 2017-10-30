package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.CommunityInfoResponse;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * 聊聊详情头部
 * Created by baishixin on 16/4/19.
 */
public class MyCommunityInfoHeaderView extends LinearLayout {

    private Context mContext;

    @ViewInject(R.id.sdv_talk_headpic)
    private SimpleDraweeView headpic;
    @ViewInject(R.id.tv_username)
    private TextView username;
    @ViewInject(R.id.tv_created_at)
    private TextView created_at;
    @ViewInject(R.id.tv_community_title)
    private TextView title;
    @ViewInject(R.id.tv_community_content)
    private TextView content;
    @ViewInject(R.id.sdv_community_info_head1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_community_info_head2)
    private SimpleDraweeView pic2;
    @ViewInject(R.id.sdv_community_info_head3)
    private SimpleDraweeView pic3;
    @ViewInject(R.id.sdv_community_info_head4)
    private SimpleDraweeView pic4;
    @ViewInject(R.id.sdv_community_info_head5)
    private SimpleDraweeView pic5;
    @ViewInject(R.id.sdv_community_info_head6)
    private SimpleDraweeView pic6;

    @ViewInject(R.id.tv_community_del)
    private TextView del;

    private int chart_id;

//    @ViewInject(R.id.tv_headview_type)
//    private TextView type;
//    @ViewInject(R.id.tv_headview_tag)
//    private TextView tag;


    public MyCommunityInfoHeaderView(Context context) {
        super(context);
        this.mContext = context;
        initData();
        initView();
    }

    private void initData() {

    }

    public MyCommunityInfoHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initData();
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_headerview_my_community_info, this);
        Fresco.initialize(mContext);
        x.view().inject(this);

        del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chart_id > 0) {
                    delCommunityListener.clickDelCommunity();
                }
            }
        });
    }

    private DelCommunityListener delCommunityListener;

    public void setDelCommunityListener(DelCommunityListener delCommunityListener) {
        this.delCommunityListener = delCommunityListener;
    }

    public interface DelCommunityListener {
        void clickDelCommunity();
    }


    public void setData(CommunityInfoResponse communityInfoResponse) {
        if (communityInfoResponse != null) {
            chart_id = communityInfoResponse.getResultData().getChat_id();
            if (!TextUtils.isEmpty(communityInfoResponse.getResultData().getHeadpic())) {
                headpic.setImageURI(Uri.parse(communityInfoResponse.getResultData().getHeadpic()));
            }
            if (!TextUtils.isEmpty(communityInfoResponse.getResultData().getName())) {
                username.setText(communityInfoResponse.getResultData().getName());
            }
            if (!TextUtils.isEmpty(communityInfoResponse.getResultData().getCreated_at())) {
                created_at.setText(communityInfoResponse.getResultData().getCreated_at());
            }
            if (!TextUtils.isEmpty(communityInfoResponse.getResultData().getTitle())) {
                title.setText(communityInfoResponse.getResultData().getTitle());
            }
            if (!TextUtils.isEmpty(communityInfoResponse.getResultData().getContent())) {
                content.setText(communityInfoResponse.getResultData().getContent());
            }
            List<String> pics = communityInfoResponse.getResultData().getPic();
            if (pics != null) {
                for (int i = 0; i < pics.size(); i++) {
                    switch (i) {
                        case 0:
                            if (!TextUtils.isEmpty(pics.get(i))) {
                                pic1.setVisibility(VISIBLE);
                                pic1.setImageURI(Uri.parse(pics.get(i)));
                            }
                            break;
                        case 1:
                            if (!TextUtils.isEmpty(pics.get(i))) {
                                pic2.setVisibility(VISIBLE);
                                pic2.setImageURI(Uri.parse(pics.get(i)));
                            }
                            break;
                        case 2:
                            if (!TextUtils.isEmpty(pics.get(i))) {
                                pic3.setVisibility(VISIBLE);
                                pic3.setImageURI(Uri.parse(pics.get(i)));
                            }
                            break;
                        case 3:
                            if (!TextUtils.isEmpty(pics.get(i))) {
                                pic4.setVisibility(VISIBLE);
                                pic4.setImageURI(Uri.parse(pics.get(i)));
                            }
                            break;
                        case 4:
                            if (!TextUtils.isEmpty(pics.get(i))) {
                                pic5.setVisibility(VISIBLE);
                                pic5.setImageURI(Uri.parse(pics.get(i)));
                            }
                            break;
                        case 5:
                            if (!TextUtils.isEmpty(pics.get(i))) {
                                pic6.setVisibility(VISIBLE);
                                pic6.setImageURI(Uri.parse(pics.get(i)));
                            }
                            break;
                    }
                }
            }
        }
    }
}
