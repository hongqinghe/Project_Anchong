package net.anchong.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.response.model.CommunityShowResponse;
import net.anchong.app.ui.CommunityInfoActivity;
import net.anchong.app.ui.ImagePagerActivity;
import net.anchong.app.uitls.ImageUtils;

import java.util.List;

/**
 * Created by baishixin on 16/4/18.
 */
public class CommunityShowAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater mInflater;
    private List<CommunityShowResponse.ResultDataEntity.ListEntity> listBeans = null;

    public CommunityShowAdapter(Context context, LayoutInflater mInflater, List<CommunityShowResponse.ResultDataEntity.ListEntity> listBeans) {
        this.context = context;
        this.mInflater = mInflater;
        this.listBeans = listBeans;
    }

    public CommunityShowAdapter(Context context, LayoutInflater mInflater) {
        this.context = context;
        this.mInflater = mInflater;
    }


    public void setList(List<CommunityShowResponse.ResultDataEntity.ListEntity> listBeans) {
        this.listBeans = listBeans;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (listBeans == null) {
            return 0;
        }
        if (listBeans.size() <= 0) {
            return 0;
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
        ViewHoler vh = null;
        if (convertView == null) {
            vh = new ViewHoler();
            convertView = mInflater.inflate(R.layout.item_community_adapter, null);
            vh.community = (LinearLayout) convertView.findViewById(R.id.ll_community);
            //用户头像
            vh.headpic = (SimpleDraweeView) convertView.findViewById(R.id.sdv_talk_headpic);
            //用户昵称
            vh.name = (TextView) convertView.findViewById(R.id.tv_username);
            //发布时间
            vh.created_at = (TextView) convertView.findViewById(R.id.tv_created_at);
            //评论数
            vh.comnum = (TextView) convertView.findViewById(R.id.tv_community_comnum);
            //聊聊标题
            vh.title = (TextView) convertView.findViewById(R.id.tv_community_title);
            //聊聊正文
            vh.content = (TextView) convertView.findViewById(R.id.tv_community_content);
            vh.iv1 = (LinearLayout) convertView.findViewById(R.id.ll_iv1);
            vh.iv2 = (LinearLayout) convertView.findViewById(R.id.ll_iv2);
            vh.simpleDraweeView1 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_mypublish_pic1);
            vh.simpleDraweeView2 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_mypublish_pic2);
            vh.simpleDraweeView3 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_mypublish_pic3);
            vh.simpleDraweeView4 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_mypublish_pic4);
            vh.simpleDraweeView5 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_mypublish_pic5);
            vh.simpleDraweeView6 = (SimpleDraweeView) convertView.findViewById(R.id.sdv_mypublish_pic6);
            convertView.setTag(vh);
        }
        vh = (ViewHoler) convertView.getTag();
        vh.simpleDraweeView1.setVisibility(View.INVISIBLE);
        vh.simpleDraweeView2.setVisibility(View.INVISIBLE);
        vh.simpleDraweeView3.setVisibility(View.INVISIBLE);
        vh.simpleDraweeView4.setVisibility(View.INVISIBLE);
        vh.simpleDraweeView5.setVisibility(View.INVISIBLE);
        vh.simpleDraweeView6.setVisibility(View.INVISIBLE);


        final CommunityShowResponse.ResultDataEntity.ListEntity listBean = listBeans.get(position);
        vh.headpic.setImageURI(Uri.parse(listBean.getHeadpic()));
        vh.name.setText(listBean.getName());
        vh.created_at.setText(listBean.getCreated_at());
        vh.comnum.setText(listBean.getComnum());
        vh.title.setText(listBean.getTitle());
//        vh.tag.setText(listBean.getTag());
        vh.content.setText(listBean.getContent());

        if (listBean.getPic() == null || listBean.getPic().size() == 0 || listBean.getPic().size() < 0) {
            vh.iv1.setVisibility(View.GONE);
            vh.iv2.setVisibility(View.GONE);
        } else if (listBean.getPic().size() <= 3) {
            vh.iv1.setVisibility(View.VISIBLE);
        } else {
            vh.iv1.setVisibility(View.VISIBLE);
            vh.iv2.setVisibility(View.VISIBLE);
        }

        List<String> pics = listBean.getPic();
        if (pics != null && pics.size() > 0) {
            final String[] uris = new String[pics.size()];
            if (pics.size() <= 3) {
                vh.simpleDraweeView4.setVisibility(View.GONE);
                vh.simpleDraweeView5.setVisibility(View.GONE);
                vh.simpleDraweeView6.setVisibility(View.GONE);
            }

            for (int i = 0; i < pics.size(); i++) {
                uris[i] = pics.get(i);
                switch (i) {
                    case 0:
                        vh.simpleDraweeView1.setVisibility(View.VISIBLE);
                        vh.simpleDraweeView1.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 1:
                        vh.simpleDraweeView2.setVisibility(View.VISIBLE);
                        vh.simpleDraweeView2.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 2:
                        vh.simpleDraweeView3.setVisibility(View.VISIBLE);
                        vh.simpleDraweeView3.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 3:
                        vh.simpleDraweeView4.setVisibility(View.VISIBLE);
                        vh.simpleDraweeView4.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 4:
                        vh.simpleDraweeView5.setVisibility(View.VISIBLE);
                        vh.simpleDraweeView5.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 5:
                        vh.simpleDraweeView6.setVisibility(View.VISIBLE);
                        vh.simpleDraweeView6.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                }
            }


            vh.simpleDraweeView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageBrower(0, uris);
                }
            });
            vh.simpleDraweeView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageBrower(1, uris);
                }
            });
            vh.simpleDraweeView3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageBrower(2, uris);
                }
            });
            vh.simpleDraweeView4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageBrower(3, uris);
                }
            });
            vh.simpleDraweeView5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageBrower(4, uris);
                }
            });
            vh.simpleDraweeView6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imageBrower(5, uris);
                }
            });

        }
        vh.community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommunityInfoActivity.start(context, listBean.getChat_id());
            }
        });


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
        TextView comnum;
        TextView created_at;
        TextView title;
        TextView content;
        //        MyImageView miv;
        SimpleDraweeView simpleDraweeView1;
        SimpleDraweeView simpleDraweeView2;
        SimpleDraweeView simpleDraweeView3;
        SimpleDraweeView simpleDraweeView4;
        SimpleDraweeView simpleDraweeView5;
        SimpleDraweeView simpleDraweeView6;

        LinearLayout community;
        LinearLayout iv1;
        LinearLayout iv2;
    }
}
