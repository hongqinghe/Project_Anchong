package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.facebook.drawee.backends.pipeline.Fresco;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.adapter.MyAdapter;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.CertificationModel;
import net.anchong.app.entity.request.model.CertificationRequestParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.oss.utils.ImageService;
import net.anchong.app.oss.utils.OssService;
import net.anchong.app.oss.utils.PauseableUploadTask;
import net.anchong.app.oss.utils.STSGetter;
import net.anchong.app.oss.utils.UIDisplayer;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.ImageUtils;
import net.anchong.app.uitls.JsonObjectUtils;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * 商家资质认证的界面
 * Created by baishixin on 16/4/7.
 */
public class CertificationActivity extends BaseActivity implements AdapterView.OnItemClickListener, GeneralTitleBarView.GeneralTitleBarOnclickListener {

    /**
     * 拍照上传头像
     */
    public static final int GET_IMAGE_VIA_CAMERA = 3;
    /**
     * 相册选取头像
     */
    public static final int CHOSE_IMAGE_VIA_CAMERA = 4;
    private static final int UPDATA_IMAGE = 5;
    private ProgressDialog pd = null;
    //图片的文件名
    private String localTempImgFileName = "";
    //缩放图片
    private File tempImg = null;

    @ViewInject(R.id.gtvv_certification_title)
    private GeneralTitleBarView mGeneralTitleBarView;
    @ViewInject(R.id.lv_certification)
    private SwipeMenuListView listView;

    //资质名称
    @ViewInject(R.id.et_certification_zzname)
    private EditText mEditText_zzname;

    //适配器
    private MyAdapter myAdapter;

    //存放资质认证对象的list
    public static List<CertificationModel> certificationModels;
    private CertificationModel certificationModel;

    public static int flag = -1;

    //负责所有的界面更新
    private UIDisplayer UIDisplayer;

    //OSS的上传下载
    private OssService ossService;
    private ImageService imageService;
    private String picturePath = "";
    private WeakReference<PauseableUploadTask> task;

    public static final int RESULT_LOAD_IMAGE = 1;

    public static void start(Context context) {
        Intent intent = new Intent(context, CertificationActivity.class);
        context.startActivity(intent);
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /// fresco
        Fresco.initialize(this);
        setContentView(R.layout.layout_activity_certification);
        x.view().inject(this);
        pd = new ProgressDialog(this);
        pd.setTitle("图片上传中");
        pd.setCanceledOnTouchOutside(false);
        UIDisplayer = new UIDisplayer(pd, this);
        ossService = initOSS(MyApplication.ENDPOINT, "anchongres", UIDisplayer);

        initView();

    }

    private void initView() {
        mGeneralTitleBarView.setData("商家认证", "提交");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);
        certificationModel = new CertificationModel();
        certificationModels = new ArrayList<>();
        certificationModels.add(certificationModel);

        myAdapter = new MyAdapter(certificationModels, getLayoutInflater(), this);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(90);
                // set item title
                openItem.setTitle("删除");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.WHITE);
                // add to menu
                menu.addMenuItem(openItem);
            }
        };

        // set creator
        listView.setMenuCreator(creator);
        listView.smoothOpenMenu(myAdapter.getCount());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(this);
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        Toast.makeText(CertificationActivity.this, "删除该项", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
        flag++;
    }


    public void upload(View v) {
        certificationModel = new CertificationModel();
        certificationModels.add(certificationModel);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            File img = null;
//            pd.setTitle("图片上传中..");
//            pd.show();
            switch (requestCode) {
                case GET_IMAGE_VIA_CAMERA:
                    img = new File(Environment.getExternalStorageDirectory()
                            + "/anchong" + "/" + localTempImgFileName);
                    try {
                        Uri u =
                                Uri.parse(android.provider.MediaStore.Images.Media.insertImage(getContentResolver(),
                                        img.getAbsolutePath(), null, null));
                        //u就是拍摄获得的原始图片的uri
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    break;
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
                    break;
            }
            if (img != null) {
                tempImg = ImageUtils.scal(img);
                ossService.asyncPutImage(MyApplication.IMG_DIR + tempImg.getName(), tempImg.getAbsolutePath());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "测试", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        String auth_name = mEditText_zzname.getText().toString().trim();
        if (TextUtils.isEmpty(auth_name)) {
            showMessage("请填写认证名称");
            return;
        }

        for (CertificationModel certificationModel : certificationModels) {
            if (TextUtils.isEmpty(certificationModel.getName())) {
                showMessage("请填写资质名称");
                return;
            }
            if (TextUtils.isEmpty(certificationModel.getDesc())) {
                showMessage("请填写资质说明");
                return;
            }
            if (TextUtils.isEmpty(certificationModel.getImg_url())) {
                showMessage("请选择上传图片");
                return;
            }
        }

        ArrayList<String> qua_name = new ArrayList<>();
        ArrayList<String> explanation = new ArrayList<>();
        ArrayList<String> credentials = new ArrayList<>();

        for (CertificationModel certificationModel : certificationModels) {
            qua_name.add(certificationModel.getName());
            explanation.add(certificationModel.getDesc());
            credentials.add(certificationModel.getImg_url());
        }

        CertificationRequestParamModel certificationRequestParamModel = new CertificationRequestParamModel();
        certificationRequestParamModel.setAuth_name(auth_name);
        certificationRequestParamModel.setQua_name(qua_name);
        certificationRequestParamModel.setExplanation(explanation);
        certificationRequestParamModel.setCredentials(credentials);

        final String request = JsonObjectUtils.getCertificationJson(auth_name, certificationModels);

        //请求对象
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), certificationRequestParamModel);
        String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.INDIVI + indexRequestModel.getTime() + indexRequestModel.getGuid() + request + copyToken);
        indexRequestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.INDIVI)
                .addParams("time", indexRequestModel.getTime() + "")
                .addParams("version", indexRequestModel.getVersion() + "")
                .addParams("guid", indexRequestModel.getGuid() + "")
                .addParams("param", request)
                .addParams("signature", indexRequestModel.getSignature())
                .build()
                .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                    @Override
                    public void onSuccess(ResponseErrorModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            Toast.makeText(CertificationActivity.this, response.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(CertificationActivity.this, response.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    private void showMessage(String msg) {
        Toast.makeText(CertificationActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public void updataUI() {
        if (tempImg == null) {

        } else {
            String ur = MyApplication.IMG_URL_HEAD + tempImg.getName();
            CertificationModel c = certificationModels.get(flag);
            c.setImg_url(ur);
            certificationModels.set(flag, c);
            myAdapter.notifyDataSetChanged();
            tempImg = null;
        }
    }
}














