package net.anchong.app.uitls;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成 md5 获取token的工具类
 * Created by baishixin on 16/2/24.
 */
public class ACRequestUtils {

    public static final String TOKEN = "";

    public static final int[][] hashTab = {{2, 3, 1, 17, 22, 28}, {0, 8, 19, 23, 30, 31}, {9, 15, 31, 1, 5, 7}, {11, 21, 31, 10, 12, 16},
            {30, 1, 12, 18, 25, 28}, {8, 14, 17, 27, 1, 4}, {2, 8, 13, 19, 20, 24}, {5, 16, 20, 29, 18, 22}};

    /**
     * 根据所给 info 字符串，获取md5加密结果
     *
     * @param info
     * @return
     */
    public static String getMD5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++) {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                } else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * @param toKen 服务器返回的Token或者默认Token（用户未登录使用默认Token）
     * @return 加密之后的Token
     */
    public static String getCryptToken(String toKen) {

        char[] chars = toKen.toCharArray();
        StringBuffer sb = new StringBuffer();
        sb.append(chars[2]).append(chars[5]).append(chars[8]);

        int index = Integer.valueOf(sb.toString(), 16) % 8;

        int[] hash = hashTab[index];

        sb = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            sb.append(chars[hash[i]]);
        }
        return sb.toString();
    }

    public static void isLogin(){

    }
}
