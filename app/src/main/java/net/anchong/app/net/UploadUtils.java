package net.anchong.app.net;

import android.content.Context;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.orhanobut.logger.Logger;

import java.io.File;

/**
 * 上传文件的工具类
 * Created by baishixin on 16/3/15.
 */
public class UploadUtils {
    /**
     * 头像是否上传成功
     */
    static boolean isUploadSuccess;

    public static boolean uploadHeadIcon(Context context, String endpoint, File file) {
        isUploadSuccess = false;
        // 明文设置secret的方式建议只在测试时使用，更多鉴权模式请参考后面的`访问控制`章节
        OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider("HJjYLnySPG4TBdFp", "Ifv0SNWwch5sgFcrM1bDthqyy4BmOa");

        OSS oss = new OSSClient(context.getApplicationContext(), endpoint, credentialProvider);


        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest("anchongres", file.getName(), file.getAbsolutePath());

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Logger.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                Logger.d("PutObject", "UploadSuccess");
                isUploadSuccess = true;
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Logger.e("ErrorCode", serviceException.getErrorCode());
                    Logger.e("RequestId", serviceException.getRequestId());
                    Logger.e("HostId", serviceException.getHostId());
                    Logger.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });

        // task.cancel(); // 可以取消任务

        // task.waitUntilFinished(); // 可以等待直到任务完成

        return isUploadSuccess;
    }
}
