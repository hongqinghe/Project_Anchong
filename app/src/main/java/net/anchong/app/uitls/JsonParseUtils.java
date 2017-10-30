package net.anchong.app.uitls;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 解析服务器返回的Json 数据的工具类
 * Created by baishixin on 16/3/28.
 */
public class JsonParseUtils {

    /**
     * @param response 服务器返回的 json 字符串
     * @return 请求的状态码
     */
    public static String getServerNo(String response) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            String serverNo = jsonObject.getString("ServerNo");
            return serverNo;
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }
}
