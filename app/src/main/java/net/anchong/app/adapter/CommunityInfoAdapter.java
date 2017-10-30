package net.anchong.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.CommunityComResponse;
import net.anchong.app.ui.CommunityInfoActivity;
import net.anchong.app.ui.ImagePagerActivity;

import java.util.List;

/**
 * Created by baishixin on 16/4/18.
 */
public class CommunityInfoAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private CommunityInfoActivity communityInfoActivity;
    private List<CommunityComResponse.ResultDataEntity.ListEntity> listBeans = null;

    public CommunityInfoAdapter(Context context, LayoutInflater mInflater, CommunityInfoActivity communityInfoActivity, List<CommunityComResponse.ResultDataEntity.ListEntity> listBeans) {
        this.context = context;
        this.mInflater = mInflater;
        this.communityInfoActivity = communityInfoActivity;
        this.listBeans = listBeans;
    }

    public CommunityInfoAdapter(Context context, LayoutInflater mInflater) {
        this.context = context;
        this.mInflater = mInflater;
    }

    public void setList(List<CommunityComResponse.ResultDataEntity.ListEntity> listBeans) {
        this.listBeans = listBeans;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (listBeans == null) {
            return 1;
        }
        if (listBeans.size() <= 0) {
            return 1;
        }
        return listBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (listBeans == null || listBeans.size() <= 0) {
            convertView = mInflater.inflate(R.layout.view_empty, null);
            TextView tv = (TextView) convertView.findViewById(R.id.tv_placeholder_tip);
            tv.setText("没有更多评论");
        } else {
            ViewHoler vh = null;
            if (convertView == null) {
                vh = new ViewHoler();
                convertView = mInflater.inflate(R.layout.item_community_info_adapter, null);
                //用户头像
                vh.headpic = (SimpleDraweeView) convertView.findViewById(R.id.sdv_com_headpic);
                //用户昵称
                vh.name = (TextView) convertView.findViewById(R.id.tv_username);
                //发布时间
                vh.created_at = (TextView) convertView.findViewById(R.id.tv_created_at);

                vh.comnum = (ImageView) convertView.findViewById(R.id.iv_comnum);
                //聊聊正文
                vh.content = (TextView) convertView.findViewById(R.id.tv_community_content);

                vh.reply1 = (LinearLayout) convertView.findViewById(R.id.ll_reply1);
                vh.reply2 = (LinearLayout) convertView.findViewById(R.id.ll_reply2);

                //以下为回复的内容
                //回复的用户名称
                vh.replyName1 = (TextView) convertView.findViewById(R.id.tv_reply_name1);
                //被回复的用户名称
                vh.replyComName1 = (TextView) convertView.findViewById(R.id.tv_reply_comname1);
                //回复的具体内容
                vh.replyContent1 = (TextView) convertView.findViewById(R.id.tv_reply_content1);
                //回复的用户名称
                vh.replyName2 = (TextView) convertView.findViewById(R.id.tv_reply_name2);
                //被回复的用户名称
                vh.replyComName2 = (TextView) convertView.findViewById(R.id.tv_reply_comname2);
                //回复的具体内容
                vh.replyContent2 = (TextView) convertView.findViewById(R.id.tv_reply_content2);

                vh.replyComMore = (TextView) convertView.findViewById(R.id.tv_reply_more);
                convertView.setTag(vh);
            }
            vh = (ViewHoler) convertView.getTag();


            final CommunityComResponse.ResultDataEntity.ListEntity listBean = listBeans.get(position);
            vh.headpic.setImageURI(Uri.parse(listBean.getHeadpic()));
            vh.name.setText(listBean.getName());
            vh.created_at.setText(listBean.getCreated_at());
            vh.content.setText(listBean.getContent());

            vh.reply1.setVisibility(View.GONE);
            vh.reply2.setVisibility(View.GONE);
            vh.replyComMore.setVisibility(View.GONE);
            //该条评论是否有回复
            List<CommunityComResponse.ResultDataEntity.ListEntity.ReplyEntity> replyEntities = listBean.getReply();
            if (replyEntities != null || replyEntities.size() > 0) {
                CommunityComResponse.ResultDataEntity.ListEntity.ReplyEntity replyEntity = null;

                for (int i = 0; i < replyEntities.size(); i++) {
                    switch (i) {
                        case 0:
                            vh.reply1.setVisibility(View.VISIBLE);
                            vh.reply2.setVisibility(View.GONE);
                            vh.replyComMore.setVisibility(View.GONE);
                            replyEntity = replyEntities.get(i);

                            vh.replyName1.setText(replyEntity.getName());
                            vh.replyComName1.setText(replyEntity.getComname());
                            vh.replyContent1.setText(replyEntity.getContent());
                            break;
                        case 1:
                            vh.reply2.setVisibility(View.VISIBLE);
                            vh.replyComMore.setVisibility(View.VISIBLE);
                            replyEntity = replyEntities.get(i);

                            vh.replyName2.setText(replyEntity.getName());
                            vh.replyComName2.setText(replyEntity.getComname());
                            vh.replyContent2.setText(replyEntity.getContent());
                            break;
                    }
                }
            }

            vh.comnum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logger.i("回复评论");
                    communityInfoActivity.clickeCom(listBean.getComid(),listBean.getName());
                }
            });

        }

        return convertView;
    }

    private void imageBrower(int position, String[] urls) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_URLS, urls);
        intent.putExtra(ImagePagerActivity.EXTRA_IMAGE_INDEX, position);
        context.startActivity(intent);
    }

    class ViewHoler {
        SimpleDraweeView headpic;
        TextView name;
        //        TextView comnum;
        TextView created_at;
        TextView title;
        TextView content;

        ImageView comnum;

        TextView replyName1;
        TextView replyComName1;
        TextView replyContent1;

        TextView replyName2;
        TextView replyComName2;
        TextView replyContent2;

        TextView replyComMore;

        LinearLayout reply1;
        LinearLayout reply2;
    }
}
