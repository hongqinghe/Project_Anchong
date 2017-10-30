
package net.anchong.app.uitls;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by lishuai on 16/1/29.
 */
public class AppUtils {

    /**
     * fresco 设置图片非空判断
     * 
     * @param urlStr
     * @return
     */
    public static Uri parse(String urlStr) {
        Uri uri = null;
        if (urlStr == null) {
            uri = Uri.parse("");
        } else {
            uri = Uri.parse(urlStr);
        }
        return uri;
    }

    public static String getPicturePath(Context context) {
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()))
            return context.getFilesDir().getAbsolutePath();
        File file = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (file != null)
            return file.getAbsolutePath();
        file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (file != null)
            return file.getAbsolutePath();
        return context.getFilesDir().getAbsolutePath();
    }
}
