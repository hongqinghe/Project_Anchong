package net.anchong.app.oss.utils;

import android.text.TextUtils;

import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;
import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.STSModel;
import net.anchong.app.entity.response.model.STSResponseModel;
import net.anchong.app.okhttputils.OkHttpUtils;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.JsonParseUtils;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by Administrator on 2015/12/9 0009.
 * 重载OSSFederationCredentialProvider生成自己的获取STS的功能
 */
public class STSGetter extends OSSFederationCredentialProvider {

    private String stsServer = "";

    public STSGetter() {
        this("");
    }

    public STSGetter(String stsServer) {
        this.stsServer = stsServer;
    }

    public OSSFederationToken getFederationToken() {
        String stsJson;

        //请求对象
        final RequestModel StsRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), null);
        String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.STS + StsRequestModel.getTime() + StsRequestModel.getGuid() + crypToken);
        StsRequestModel.setSignature(signature);
        Response response = null;
        try {
            response = OkHttpUtils
                    .post()
                    .url(MyApplication.API + MyApplication.STS)
                    .addParams("time", StsRequestModel.getTime() + "")
                    .addParams("version", MainActivity.loginResponseModel.getResultData().getGuid())
                    .addParams("guid", StsRequestModel.getGuid())
                    .addParams("signature", signature)
                    .build()
                    .execute();
            if (response.isSuccessful()) {
                stsJson = response.body().string();
                String result = JsonParseUtils.getServerNo(stsJson);
                if (TextUtils.isEmpty(result)) {
                    //TODO:解析错误
                } else {
                    //返回的状态码为0 代表请求正常
                    if (result.equals("0")) {
                        stsJson.replace("\\", "");
                        STSResponseModel responseModel = new Gson().fromJson(stsJson, STSResponseModel.class);
                        String tempStr = responseModel.getResultData().toString();
                        tempStr = tempStr.substring(1, tempStr.length() - 1);
                        STSModel stsModel = new Gson().fromJson(tempStr, STSModel.class);
                        String ak = stsModel.getAccessKeyId();
                        String sk = stsModel.getAccessKeySecret();
                        String token = stsModel.getSecurityToken();
                        String expiration = stsModel.getExpiration() + "";
                        return new OSSFederationToken(ak, sk, token, expiration);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}


