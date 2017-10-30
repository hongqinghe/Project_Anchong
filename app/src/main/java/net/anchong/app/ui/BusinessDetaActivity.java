package net.anchong.app.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import net.anchong.app.R;
import net.anchong.app.entity.model.BusinessModel;
import net.anchong.app.third.SystemBarTintManager;
import net.anchong.app.uitls.ImageUtils;
import net.anchong.app.view.BusinessProjectTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;


/**
 * Created by baishixin on 16/6/27.
 */
public class BusinessDetaActivity extends BaseActivity implements BusinessProjectTitleBarView.GeneralTitleBarOnclickListener {

    /**
     * UI
     */
    @ViewInject(R.id.title_business_deta)
    private BusinessProjectTitleBarView titleBarView;
    @ViewInject(R.id.tv_business_title)
    private TextView title;
    @ViewInject(R.id.tv_business_created_at)
    private TextView created_at;
    @ViewInject(R.id.tv_business_tag)
    private TextView tag;
    @ViewInject(R.id.tv_business_tags)
    private TextView tags;
    @ViewInject(R.id.tv_business_phone)
    private TextView phone;
    @ViewInject(R.id.tv_business_content)
    private TextView content;
    @ViewInject(R.id.sdv_business_deta_pic1)
    private SimpleDraweeView pic1;
    @ViewInject(R.id.sdv_business_deta_pic2)
    private SimpleDraweeView pic2;
    @ViewInject(R.id.sdv_business_deta_pic3)
    private SimpleDraweeView pic3;
    @ViewInject(R.id.sdv_business_deta_pic4)
    private SimpleDraweeView pic4;
    @ViewInject(R.id.sdv_business_deta_pic5)
    private SimpleDraweeView pic5;
    @ViewInject(R.id.sdv_business_deta_pic6)
    private SimpleDraweeView pic6;


    /**
     * 数据存储
     */
    private BusinessModel project;
    private int showPhone;

    /**
     *
     */

    public static void start(Context context, BusinessModel project, int showPhone) {
        Intent intent = new Intent(context, BusinessDetaActivity.class);
        intent.putExtra("showPhone", showPhone);
        intent.putExtra("project", project);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_deta);
        x.view().inject(this);
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.tab_bg);// 通知栏所需颜色
        showPhone = getIntent().getIntExtra("showPhone", 0);
        project = (BusinessModel) getIntent().getSerializableExtra("project");
        if (project != null) {
            initView();
        }

    }

    private void initView() {

        titleBarView.setData("4", "");
        titleBarView.setGeneralTitleBarOnclickListener(this);
        //设置标题
        if (!TextUtils.isEmpty(project.getTitle())) {
            title.setText(project.getTitle());
        }
        //设置标题
        if (!TextUtils.isEmpty(project.getCreated_at())) {
            created_at.setText(project.getCreated_at());
        }
        //设置标题
        if (!TextUtils.isEmpty(project.getTag())) {
            tag.setText(project.getTag());
        }
        //设置标题
        if (!TextUtils.isEmpty(project.getTags())) {
            tags.setText(project.getTags());
        }
        //显示发布人信息
        if (showPhone == 1) {
            String name = project.getContact();
            String phoneNum = project.getPhone();
            phone.setText(phoneNum + "  " + name);
        } else {
            phone.setText("注册会员和认证才能查看");
        }

        List<String> pics = project.getPic();
        if (pics != null && pics.size() > 0) {
            String[] uris = new String[pics.size()];
            pic1.setVisibility(View.INVISIBLE);
            pic2.setVisibility(View.INVISIBLE);
            pic3.setVisibility(View.INVISIBLE);
            if (pics.size() > 3) {
                pic4.setVisibility(View.INVISIBLE);
                pic5.setVisibility(View.INVISIBLE);
                pic6.setVisibility(View.INVISIBLE);
            }

            for (int i = 0; i < pics.size(); i++) {
                uris[i] = pics.get(i);
                switch (i) {
                    case 0:
                        pic1.setVisibility(View.VISIBLE);
                        pic1.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 1:
                        pic2.setVisibility(View.VISIBLE);
                        pic2.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 2:
                        pic3.setVisibility(View.VISIBLE);
                        pic3.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 3:
                        pic4.setVisibility(View.VISIBLE);
                        pic4.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 4:
                        pic5.setVisibility(View.VISIBLE);
                        pic5.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                    case 5:
                        pic6.setVisibility(View.VISIBLE);
                        pic6.setImageURI(Uri.parse(ImageUtils.getImgUri(pics.get(i))));
                        break;
                }
            }


        }


        if (!TextUtils.isEmpty(project.getContent())) {
            content.setText(project.getContent());
        }
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
