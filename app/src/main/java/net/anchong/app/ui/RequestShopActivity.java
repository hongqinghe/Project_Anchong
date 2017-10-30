package net.anchong.app.ui;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.BrandUrlAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.CartBrandResponse;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.oss.utils.ImageService;
import net.anchong.app.oss.utils.OssService;
import net.anchong.app.oss.utils.STSGetter;
import net.anchong.app.oss.utils.UIDisplayer;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.ImageUtils;
import net.anchong.app.view.GeneralTitleBarView;
import net.anchong.app.view.SelectPicPopupWindow;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 店铺申请界面
 * Created by baishixin on 16/5/20.
 */
public class RequestShopActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private ProgressDialog pd;

    /**
     * 拍照上传头像
     */
    private static final int GET_IMAGE_VIA_CAMERA = 3;
    /**
     * 相册选取头像
     */
    private static final int CHOSE_IMAGE_VIA_CAMERA = 4;
    /**
     * 拍照商城品牌认证书
     */
    public static final int GET_IMAGE_BRANDPIC_CAMERA = 5;
    /**
     * 相册选取品牌认证书
     */
    public static final int CHOSE_IMAGE_BRANDPIC_CAMERA = 6;

    /**
     * 数据展示
     */
    @ViewInject(R.id.gtvv_shop_request)
    private GeneralTitleBarView mGeneralTitleBarView;
    //店铺头像
    @ViewInject(R.id.sdv_shop_request_headpic)
//    private ImageView img;
    private SimpleDraweeView shopHeadPic;
    //店铺名称
    @ViewInject(R.id.tv_shop_request_name)
    private TextView shopName;
    //店铺介绍
    @ViewInject(R.id.tv_shop_request_introduction)
    private TextView shopIntroduction;
    //店铺主营品牌
    @ViewInject(R.id.tv_shop_request_brandName)
    private TextView shopBrandName;
    //店铺主营类别
    @ViewInject(R.id.tv_shop_request_catName)
    private TextView shopCatName;
    //店铺经营地点
    @ViewInject(R.id.tv_shop_request_address)
    private TextView shopAddress;
    @ViewInject(R.id.gv_shop_request_brandUrl)
    private GridView brandPic;

    /**
     * 点击事件
     */
    //点击设置店铺名称
    @ViewInject(R.id.ll_activity_shoprequest_name)
    private LinearLayout linearLayout_shopName;
    //点击设置店铺介绍
    @ViewInject(R.id.ll_activity_shoprequest_introduction)
    private LinearLayout linearLayout_shopIntroduction;
    //点击设置主营品牌
    @ViewInject(R.id.ll_activity_shoprequest_brandName)
    private LinearLayout linearLayout_shopBrandName;
    //点击设置主营类别
    @ViewInject(R.id.ll_activity_shoprequest_catName)
    private LinearLayout linearLayout_shopCatName;
    //点击设置经营地址
    @ViewInject(R.id.ll_activity_shoprequest_address)
    private LinearLayout linearLayout_shopAddress;
    //自定义的弹出框类
    private SelectPicPopupWindow menuWindow;
    private BrandUrlAdapter adapter;

    /**
     * 数据存储
     */
    private String localTempImgFileName;
    //负责所有的界面更新
    private UIDisplayer UIDisplayer;
    //OSS的上传下载
    private OssService ossService;
    private ImageService imageService;
    private String picturePath = "";
    //缩放图片
    private File tempImg = null;
    //判断当前上传的图片是头像还是认证书   true为认证书
    private boolean isBrandPic = false;
    //请求商铺所有品牌类别的结果 Model
    private CartBrandResponse cartBrandResponse = null;
    //所有的主营品牌
    List<CartBrandResponse.ResultDataEntity.Brand> brandList = new ArrayList<>();
    String[] brandStrs = null;
    boolean[] brandIsCheck = null;
    private StringBuffer brandSB = new StringBuffer();
    //所有的主营类别
    List<CartBrandResponse.ResultDataEntity.Cat> catList = new ArrayList<>();
    String[] catStrs = null;
    boolean[] catIsCheck = null;
    private StringBuffer catSB = new StringBuffer();

    //可以上传图片的数量，默认为一个，数量的多少根据选择主营品牌的数量来决定。
    private int brandUrlNum = 1;
    //店铺品牌授权书图片地址
    private List<String> brandUrlList = new ArrayList<>();
    //已上传的图片的本地路径
    private List<String> brandPathList = new ArrayList<>();
    //当前添加第几个图片
    private int brandUrlIndex = 0;
    //当前应该显示的图片数量
    private int picNums = 1;


    /**
     *
     */

    public static void start(Context context) {
        Intent intent = new Intent(context, RequestShopActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_shop_request);
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setCanceledOnTouchOutside(false);
        initData();
        UIDisplayer = new UIDisplayer(pd, this);
        ossService = initOSS(MyApplication.ENDPOINT, "anchongres", UIDisplayer);
    }

    //初始化一个OssService用来上传下载
    public OssService initOSS(String endpoint, String bucket, UIDisplayer displayer) {
        //使用自己的获取STSToken的类
        OSSCredentialProvider credentialProvider = new STSGetter();

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);
        return new OssService(oss, bucket, displayer);
    }

    /**
     * 初始化店铺申请时选择的主营品牌和主营类别
     */
    private void initData() {
        pd.show();
        //判断用户是否登录
        if (MainActivity.isLogin) {
//            orderInfoRequestParam = new OrderInfoRequestParam(state, page + "");
//            String json = new Gson().toJson(orderInfoRequestParam);
            RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", null);
            String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());

            String signature = ACRequestUtils.getMD5(MyApplication.CATBRAND + requestModel.getTime() + requestModel.getGuid() + "" + copyToken);
            requestModel.setSignature(signature);
            HttpManager.getInstance()
                    .post(MyApplication.API + MyApplication.CATBRAND)
                    .addParams("time", requestModel.getTime())
                    .addParams("version", requestModel.getVersion())
                    .addParams("guid", requestModel.getGuid())
                    .addParams("param", "")
                    .addParams("signature", signature)
                    .build()
                    .execute(CartBrandResponse.class, new CommonCallback<CartBrandResponse>() {
                        @Override
                        public void onSuccess(CartBrandResponse response, Object... obj) {
                            if ("0".equals(response.getServerNo())) {
                                cartBrandResponse = response;
                                if (cartBrandResponse != null) {
                                    brandList = cartBrandResponse.getResultData().getBrand();
                                    catList = cartBrandResponse.getResultData().getCat();
                                    parserTag();
                                }
                                initView();
                            } else {
                                showMessage(getString(R.string.request_error_msg));
                            }
                        }

                        @Override
                        public void onError(Call call, Exception e) {
                            showMessage(getString(R.string.request_error_msg));
                            e.printStackTrace();
                        }
                    });
        } else {
            LoginActivity.start(this);
        }
    }

    /**
     * 将集合拆装为数组
     */
    private void parserTag() {
        if (brandList.size() > 0) {
            brandStrs = new String[brandList.size()];
            brandIsCheck = new boolean[brandList.size()];
            for (int i = 0; i < brandList.size(); i++) {
                brandStrs[i] = brandList.get(i).getBrand_name();
            }
        }
        if (catList.size() > 0) {
            catStrs = new String[catList.size()];
            catIsCheck = new boolean[catList.size()];
            for (int i = 0; i < catList.size(); i++) {
                catStrs[i] = catList.get(i).getCat_name();
            }
        }
    }

    /**
     *
     */
    private void initView() {
        pd.dismiss();
        mGeneralTitleBarView.setData("店铺申请", "");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        shopHeadPic.setOnClickListener(this);
        linearLayout_shopBrandName.setOnClickListener(this);
        linearLayout_shopCatName.setOnClickListener(this);
        linearLayout_shopAddress.setOnClickListener(this);
        brandPic.setOnItemClickListener(this);
        brandPathList.add("");
        adapter = new BrandUrlAdapter(this, getLayoutInflater(), brandPathList, this,picNums,brandUrlNum);
        brandPic.setAdapter(adapter);
//        img.setOnClickListener(this);

    }

    private void showMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void setArea(String area) {
        shopAddress.setText(area.toString().trim());
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sdv_shop_request_headpic:
                AlertDialog.Builder headpicBuilder = new AlertDialog.Builder(this);
                headpicBuilder.setTitle("上传店铺头像");
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
                                                startActivityForResult(intent, GET_IMAGE_VIA_CAMERA);
                                            } catch (ActivityNotFoundException e) {
                                                // TODO Auto-generated catch block
                                                Toast.makeText(RequestShopActivity.this, "没有找到储存目录", Toast.LENGTH_LONG).show();
                                            }
                                        } else {
                                            Toast.makeText(RequestShopActivity.this, "没有储存卡", Toast.LENGTH_LONG).show();
                                        }
                                        break;
                                    case 1:
                                        Intent intent2 = new Intent();
                                        intent2.setAction("android.intent.action.PICK");
                                        intent2.setType("image/*");
                                        startActivityForResult(intent2, CHOSE_IMAGE_VIA_CAMERA);
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
                break;
            /**
             * 选择主营品牌事件
             */
            case R.id.ll_activity_shoprequest_brandName:
                // 实例化建造者
                AlertDialog.Builder builder = new AlertDialog.Builder(RequestShopActivity.this);
                builder.setMultiChoiceItems(brandStrs, brandIsCheck, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            brandIsCheck[which] = true;
                        } else {
                            brandIsCheck[which] = false;
                        }
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        brandSB.delete(0, brandSB.length());
                        for (int i = 0; i < brandIsCheck.length; i++) {
                            if (brandIsCheck[i]) {
                                brandSB.append(brandList.get(i).getBrand_name());
                                if (i == brandIsCheck.length) {
                                    return;
                                }
                                brandSB.append(" ");
                            }
                        }
                        shopBrandName.setText(brandSB.toString());
                        brandUrlNum = brandIsCheck.length;
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
            /**
             * 选择主营类别事件
             */
            case R.id.ll_activity_shoprequest_catName:
                // 实例化建造者
                AlertDialog.Builder catName = new AlertDialog.Builder(RequestShopActivity.this);
                catName.setMultiChoiceItems(catStrs, catIsCheck, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            catIsCheck[which] = true;
                        } else {
                            catIsCheck[which] = false;
                        }
                    }
                });
                catName.setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        catSB.delete(0, catSB.length());
                        for (int i = 0; i < catIsCheck.length; i++) {
                            if (catIsCheck[i]) {
                                catSB.append(catList.get(i).getCat_name());
                                if (i == catIsCheck.length) {
                                    return;
                                }
                                catSB.append(" ");
                            }
                        }
                        shopCatName.setText(catSB.toString());
                    }
                });
                catName.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                catName.show();
                break;

            case R.id.ll_activity_shoprequest_address:
                //显示窗口
                menuWindow = new SelectPicPopupWindow(RequestShopActivity.this, this);
                //设置layout在PopupWindow中显示的位置
                menuWindow.showAtLocation(linearLayout_shopAddress, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            File img = null;
            switch (requestCode) {
                //店铺头像拍照上传
                case GET_IMAGE_VIA_CAMERA:
                    img = new File(Environment.getExternalStorageDirectory()
                            + "/anchong" + "/" + localTempImgFileName);
                    try {
                        Uri u =
                                Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),
                                        img.getAbsolutePath(), null, null));
                        //u就是拍摄获得的原始图片的uri，剩下的你想干神马坏事请便……
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (img != null) {
                        tempImg = ImageUtils.scal(img);
                        ossService.asyncPutImage(MyApplication.IMG_SHOP_HEADERPIC + tempImg.getName(), tempImg.getAbsolutePath());
                    }
                    break;
                //店铺头像相册上传
                case CHOSE_IMAGE_VIA_CAMERA:
                    //获取intent中返回的bitmap 的 uri
                    Uri uri = data.getData();
                    if (uri == null) {

                    } else {
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};

                        Cursor cursor = getContentResolver().query(uri,
                                filePathColumn, null, null, null);
                        cursor.moveToFirst();

                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        picturePath = cursor.getString(columnIndex);
                        cursor.close();

                        img = new File(picturePath);
                    }
                    if (img != null) {
                        tempImg = ImageUtils.scal(img);
                        ossService.asyncPutImage(MyApplication.IMG_SHOP_HEADERPIC + tempImg.getName(), tempImg.getAbsolutePath());
                    }
                    break;
                //资质认证书拍照上传
                case GET_IMAGE_BRANDPIC_CAMERA:
                    img = new File(Environment.getExternalStorageDirectory()
                            + "/anchong" + "/" + localTempImgFileName);
                    try {
                        Uri u =
                                Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),
                                        img.getAbsolutePath(), null, null));
                        //u就是拍摄获得的原始图片的uri，剩下的你想干神马坏事请便……
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    if (img != null) {
                        tempImg = ImageUtils.scal(img);
                        ossService.asyncPutImage(MyApplication.SHOP_BRAND_URL_HEAD + tempImg.getName(), tempImg.getAbsolutePath());
                    }

                    break;
            }

        }
        super.onActivityResult(requestCode, resultCode, data);


    }

    public void updateUI() {
        if (tempImg == null) {

        } else {
            if (isBrandPic) {
                brandPathList.add(brandUrlIndex, tempImg.getAbsolutePath());
                picNums += 1;
            } else {
                String ur = MyApplication.SHOP_IMG_URL_HEAD + tempImg.getName();
                Logger.i("店铺头像地址：" + ur);
                shopHeadPic.setImageURI(Uri.parse(ur));
//            Bitmap bitmap = BitmapFactory.decodeFile(tempImg.getAbsolutePath());
//            img.setImageBitmap(bitmap);
//            Logger.i("店铺头像地址：" + tempImg.getAbsolutePath());
                tempImg = null;
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        brandUrlIndex = position;
        AlertDialog.Builder headpicBuilder = new AlertDialog.Builder(this);
        headpicBuilder.setTitle("上传品牌认证书");
        final String[] items = {"拍照", "选择照片"};
        headpicBuilder.setItems(items,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                isBrandPic = true;
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
                                        startActivityForResult(intent, GET_IMAGE_BRANDPIC_CAMERA);
                                    } catch (ActivityNotFoundException e) {
                                        // TODO Auto-generated catch block
                                        Toast.makeText(RequestShopActivity.this, "没有找到储存目录", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(RequestShopActivity.this, "没有储存卡", Toast.LENGTH_LONG).show();
                                }
                                break;
                            case 1:
                                isBrandPic = true;
                                Intent intent2 = new Intent();
                                intent2.setAction("android.intent.action.PICK");
                                intent2.setType("image/*");
                                startActivityForResult(intent2, CHOSE_IMAGE_BRANDPIC_CAMERA);
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
}
