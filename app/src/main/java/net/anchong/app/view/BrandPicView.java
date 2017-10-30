package net.anchong.app.view;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import net.anchong.app.R;
import net.anchong.app.ui.RequestShopActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

/**
 * 店铺申请：品牌认证书
 * Created by baishixin on 16/4/28.
 */
public class BrandPicView extends RelativeLayout {

    private Context mContext;
    private int index = 0;

    @ViewInject(R.id.iv_item_shop_request)
    private ImageView imageView;
    @ViewInject(R.id.iv_del_shop_request)
    private ImageView del;

    private RequestShopActivity requestShopActivity;

    private String localTempImgFileName;
    private String picturePath = "";
    //缩放图片
    private File tempImg = null;


    public BrandPicView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public BrandPicView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public BrandPicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_item_shop_request, this);
        x.view().inject(this);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder headpicBuilder = new AlertDialog.Builder(requestShopActivity);
                headpicBuilder.setTitle("修改头像");
                final String[] items = {"拍照", "选择照片"};
                headpicBuilder.setItems(items,
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        String status = Environment.getExternalStorageState();
                                        if (status.equals(Environment.MEDIA_MOUNTED)) {
                                            try {
                                                File dir = new File(Environment.getExternalStorageDirectory() + "/anchong");
                                                if (!dir.exists()) dir.mkdirs();
                                                localTempImgFileName = System.currentTimeMillis() + ".jpg";
                                                Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                                File f = new File(dir, localTempImgFileName);//localTempImgDir和localTempImageFileName是自己定义的名字
                                                Uri u = Uri.fromFile(f);
                                                intent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                                                intent.putExtra(MediaStore.EXTRA_OUTPUT, u);
                                                requestShopActivity.startActivityForResult(intent, RequestShopActivity.GET_IMAGE_BRANDPIC_CAMERA);
                                            } catch (ActivityNotFoundException e) {
                                                // TODO Auto-generated catch block
                                                Toast.makeText(requestShopActivity, "没有找到储存目录", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(requestShopActivity, "没有储存卡", Toast.LENGTH_LONG).show();
                                        }
                                        break;
                                    case 1:
                                        Intent intent2 = new Intent();
                                        intent2.setAction("android.intent.action.PICK");
                                        intent2.setType("image/*");
                                        requestShopActivity.startActivityForResult(intent2, RequestShopActivity.CHOSE_IMAGE_BRANDPIC_CAMERA);
                                        break;
                                }
                            }
                        });
                headpicBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                headpicBuilder.show();
            }
        });
    }

    private void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public void setData(String url, RequestShopActivity requestShopActivity, int index) {
        this.requestShopActivity = requestShopActivity;
        if (url != null && !"".equals(url)) {
            Bitmap bm = BitmapFactory.decodeFile(url);
            imageView.setImageBitmap(bm);
            del.setVisibility(VISIBLE);
            del.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }

}
