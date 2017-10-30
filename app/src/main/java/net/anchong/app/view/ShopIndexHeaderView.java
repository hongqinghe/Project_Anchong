package net.anchong.app.view;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.CollectRequest;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.MyShopsResponse;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.uitls.ACRequestUtils;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import okhttp3.Call;

/**
 * Created by baishixin on 16/2/11.
 */
public class ShopIndexHeaderView extends LinearLayout {

    private static final int CHANGE_IMG = 1010;
    private Context mContext;


    @ViewInject(R.id.iv_shop_index_collection)
    private ImageView collection;

    @ViewInject(R.id.tv_shop_index_header_name)
    private TextView name;
    @ViewInject(R.id.tv_shop_index_num)
    private TextView num;


    @ViewInject(R.id.sdv_shop_index_header_pic)
    private SimpleDraweeView pic;
    private String collresult = "0";
    private String sid;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case CHANGE_IMG:
                    if ("0".equals(collresult)) {
                        collection.setImageResource(R.drawable.shop_clection);
//                        collresult = "1";
                    } else if ("1".equals(collresult)) {
                        collection.setImageResource(R.drawable.shop_clected);
//                        collresult = "0";
                    }
                    break;
            }
        }
    };

    public ShopIndexHeaderView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public ShopIndexHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        View.inflate(mContext, R.layout.view_shop_index_header, this);
        x.view().inject(this);

        collection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                collection();
            }
        });
    }

    public void setData(MyShopsResponse myShopsResponse, String sid) {
        if (myShopsResponse != null) {
            this.sid = sid;
            collresult = myShopsResponse.getResultData().getCollresult();
            if ("0".equals(collresult)) {
                collection.setImageResource(R.drawable.shop_clection);
            } else if ("1".equals(collresult)) {
                collection.setImageResource(R.drawable.shop_clected);
            }
            if (!TextUtils.isEmpty(myShopsResponse.getResultData().getCollect())) {
                num.setText(myShopsResponse.getResultData().getCollect() + "人关注");
            }
            if (!TextUtils.isEmpty(myShopsResponse.getResultData().getShops().getImg())) {
                pic.setImageURI(Uri.parse(myShopsResponse.getResultData().getShops().getImg()));
            }
            if (!TextUtils.isEmpty(myShopsResponse.getResultData().getShops().getName())) {
                name.setText(myShopsResponse.getResultData().getShops().getName());
            }
        }
    }

    private void collection() {
        String api = "";
        if ("0".equals(collresult)) {
            api = MyApplication.COLLECTADDCOLLECT;
            collresult = "1";
        } else if ("1".equals(collresult)) {
            api = MyApplication.COLLECTDELCOLLECT;
            collresult = "0";
        }

        CollectRequest collectRequest = new CollectRequest(sid, "2");
        String json = new Gson().toJson(collectRequest);

        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", collectRequest);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", collectRequest);
            copyToken = MyApplication.DEFAULT_TOKEN;
        }
        String signature = ACRequestUtils.getMD5(api + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);
        HttpManager.getInstance()
                .post(MyApplication.API + api)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                    @Override
                    public void onSuccess(ResponseErrorModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            handler.sendEmptyMessage(CHANGE_IMG);
                            showMessage(response.getResultData().getMessage());
                        } else {
                            showMessage(response.getResultData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage("网络错误");
                        e.printStackTrace();
                    }
                });


    }

    private void showMessage(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
