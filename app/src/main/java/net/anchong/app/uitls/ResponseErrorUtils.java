package net.anchong.app.uitls;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.entity.response.model.ResponseErrorModel;

/**
 * 服务器返回的错误信息集中处理工具类
 * Created by baishixin on 16/4/14.
 */
public class ResponseErrorUtils {

    public static void parseErrorMessage(Context context, String response) {
        ResponseErrorModel responseErrorModel = new Gson().fromJson(response, ResponseErrorModel.class);
        String msg = responseErrorModel.getResultData().getMessage();
        //登录超时的错误原因为：签名错误。
        if ("登陆超时，请重新登陆".equals(msg)) {
            
        }
        Toast.makeText(context, responseErrorModel.getResultData().getMessage(), Toast.LENGTH_SHORT).show();
    }
}
