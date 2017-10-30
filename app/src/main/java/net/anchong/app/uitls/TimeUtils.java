package net.anchong.app.uitls;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间格式处理的工具类
 * Created by baishixin on 16/3/18.
 */
public class TimeUtils {

    /**
     * 获取当前系统时间
     *
     * @return 按照 2000-10-10 10:10:10 格式返回当前系统时间
     */
    public static String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String ctime = simpleDateFormat.format(date);
        return ctime;
    }
}
