package net.anchong.app.net;

import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import net.anchong.app.MainActivity;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.request.model.LoginParamModel;
import net.anchong.app.entity.response.model.LoginResponseModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.entity.request.model.SetHeadParamModel;
import net.anchong.app.okhttputils.OkHttpUtils;
import net.anchong.app.okhttputils.callback.StringCallback;
import net.anchong.app.uitls.ACRequestUtils;
import net.anchong.app.uitls.JsonParseUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;

/**
 * Created by baishixin on 16/3/25.
 */
public class RequestUtils {
    public static LoginResponseModel result;
    public static LoginResponseModel loginResponseModel = null;
    public static GetUserMessageResponseModel getUserMessageResponseModel = null;

    // 实例化 进度条对话框
    public static ProgressDialog pd = null;

    public static LoginResponseModel postRequest(final LoginParamModel loginParamModel, final Context context) throws IOException {
        //请求对象
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", "0", loginParamModel);
        String signature = ACRequestUtils.getMD5(MyApplication.LOGIN + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(loginParamModel) + "anchongnet");
        indexRequestModel.setSignature(signature);
        OkHttpUtils
                .post()
                .url(MyApplication.API + MyApplication.LOGIN)
                .addParams("time", indexRequestModel.getTime() + "")
                .addParams("version", indexRequestModel.getVersion() + "")
                .addParams("guid", indexRequestModel.getGuid() + "")
                .addParams("param", new Gson().toJson(indexRequestModel.getParam()))
                .addParams("signature", indexRequestModel.getSignature())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(context, "当前网络不稳定，请检查网络连接", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response) {
                        Logger.i("登录的结果：" + response);
                        String result = JsonParseUtils.getServerNo(response);
                        if (TextUtils.isEmpty(result)) {
                            //TODO:解析错误
                        } else {
                            //返回的状态码为0 代表请求正常
                            if (result.equals("0")) {
                                Gson gson = new Gson();
                                loginResponseModel = gson.fromJson(response, LoginResponseModel.class);
                            } else {
                                ResponseErrorModel responseErrorModel = new Gson().fromJson(response, ResponseErrorModel.class);
                                Toast.makeText(context, responseErrorModel.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        return loginResponseModel;
    }

    /**
     * 上传用户头像
     */
    public static void upLoadHeadPic(File file, final Context context) {
        pd = new ProgressDialog(context);
        pd.setTitle("上传头像");
        pd.show();
        //请求对象
        final SetHeadParamModel setHeadParamModel = new SetHeadParamModel(file.toString());
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), setHeadParamModel);
        String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.SETHEAD + indexRequestModel.getTime() + indexRequestModel.getGuid() + new Gson().toJson(setHeadParamModel) + crypToken);
        indexRequestModel.setSignature(signature);


        OkHttpUtils.post()//
                .addFile("headpic", file.getName(), file)//
                .url(MyApplication.API + MyApplication.SETHEAD)
                .addParams("time", indexRequestModel.getTime() + "")
                .addParams("version", indexRequestModel.getVersion())
                .addParams("guid", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("param", new Gson().toJson(setHeadParamModel))
                .addParams("signature", signature)
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Logger.i("错误信息 : " + e.toString());
                        Toast.makeText(context, "连接超时，请重试", Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                    }

                    @Override
                    public void onResponse(String response) {
                        String result = JsonParseUtils.getServerNo(response);
                        if (TextUtils.isEmpty(result)) {
                            //TODO:解析错误
                        } else {
                            //返回的状态码为0 代表请求正常
                            if (result.equals("0")) {
                                getUserMessage(context);
                                pd.dismiss();
                                Toast.makeText(context, "上传成功", Toast.LENGTH_SHORT).show();
                            } else {
                                ResponseErrorModel responseErrorModel = new Gson().fromJson(response, ResponseErrorModel.class);
                                Toast.makeText(context, responseErrorModel.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                            }
                        }
                    }
                });
    }


    public static GetUserMessageResponseModel getUserMessage(final Context context) {
        //请求对象
        RequestModel indexRequestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", "1.0", MainActivity.loginResponseModel.getResultData().getGuid(), null);
        String crypToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        String signature = ACRequestUtils.getMD5(MyApplication.GETUSERMESSAGE + indexRequestModel.getTime() + indexRequestModel.getGuid() + crypToken);
        indexRequestModel.setSignature(signature);
        OkHttpUtils
                .post()
                .url(MyApplication.API + MyApplication.GETUSERMESSAGE)
                .addParams("time", System.currentTimeMillis() / 1000 + "")
                .addParams("version", "1.0")
                .addParams("guid", MainActivity.loginResponseModel.getResultData().getGuid())
                .addParams("signature", signature)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {
                        Toast.makeText(context, "网络不稳定，请重试！", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(String response) {
                        String result = JsonParseUtils.getServerNo(response);
                        if (TextUtils.isEmpty(result)) {
                            //TODO:解析错误
                        } else {
                            //返回的状态码为0 代表请求正常
                            if (result.equals("0")) {
                                Logger.i("获取用户信息：" + response);
                                getUserMessageResponseModel = new Gson().fromJson(response, GetUserMessageResponseModel.class);
//                                Message msg = Message.obtain();
//                                msg.what = UPDATE_UI;
//                                msg.obj = getUserMessageResponseModel;
//                                handler.sendMessage(msg);
                            } else {
                                ResponseErrorModel responseErrorModel = new Gson().fromJson(response, ResponseErrorModel.class);
                                Toast.makeText(context, responseErrorModel.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
        return getUserMessageResponseModel;
    }
}
