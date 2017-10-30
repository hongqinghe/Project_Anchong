package net.anchong.app.uitls;

import android.content.Context;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import net.anchong.app.entity.response.model.GetUserMessageResponseModel;
import net.anchong.app.entity.request.model.LoginParamModel;
import net.anchong.app.entity.response.model.LoginResponseModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by baishixin on 16/3/30.
 */
public class FileUtils {

    public static GetUserMessageResponseModel mgetUserMessageResponseModel = null;

    /**
     * 登录之后保存用户的手机号码
     *
     * @param loginParamModel 用户登录的请求对象模型
     * @param context
     */
    public static void savePhoneInfo(LoginParamModel loginParamModel, Context context) {
        //将手机号保存到当前应用程序目录下
        File file = new File(context.getFilesDir(), "phoneInfo.obj");
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(loginParamModel);
            oos.close();
        } catch (IOException e) {
            Toast.makeText(context, "保存手机号码失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static LoginParamModel getPhoneInfo(Context context) {
        File file = new File(context.getFilesDir(), "phoneInfo.obj");
        if (file != null && file.length() > 0) {

            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                LoginParamModel loginParamModel = (LoginParamModel) ois.readObject();
                ois.close();
                return loginParamModel;
            } catch (IOException e) {
                Toast.makeText(context, "获取手机号码失败，请重新登录", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                Toast.makeText(context, "获取手机号码失败，请重新登录", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 用户登录之后，将服务器返回的 token guid 保存
     *
     * @param loginResponseModel 登录之后服务器返回的数据model
     * @param context            上下文对象
     */
    public static void saveLoginResponse(LoginResponseModel loginResponseModel, Context context) {
        //将用户资料保存到当前应用程序目录下
        File file = new File(context.getFilesDir(), "loginResponse.obj");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(loginResponseModel);
            oos.close();
        } catch (IOException e) {
            Toast.makeText(context, "保存用户信息失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    /**
     * 获取用户登录之后的 respon model 对象
     *
     * @param context
     */
    public static LoginResponseModel getLoginResponse(Context context) {
        File file = new File(context.getFilesDir(), "loginResponse.obj");
        if (file != null && file.length() > 0) {

            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                LoginResponseModel loginResponseModel = (LoginResponseModel) ois.readObject();
                ois.close();
                return loginResponseModel;
            } catch (IOException e) {
                Toast.makeText(context, "获取登录信息失败，请重新登录", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                Toast.makeText(context, "获取登录信息失败，请重新登录", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将个人资料保存到文件中
     *
     * @param getUserMessageResponseModel 服务器返回的用户资料信息
     * @param context                     上下文对象
     */
    public static void saveUserMessage(GetUserMessageResponseModel getUserMessageResponseModel, Context context) {
        mgetUserMessageResponseModel = getUserMessageResponseModel;

        //将文件保存到当前应用程序目录下
        File file = new File(context.getFilesDir(), "userMessage.obj");

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(getUserMessageResponseModel);
            oos.close();
        } catch (IOException e) {
            Toast.makeText(context, "保存用户信息失败", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static void setHeadPic(String headPic) {
        mgetUserMessageResponseModel.getResultData().setHeadpic(headPic);
    }

    /**
     * 从文件中获取个人资料
     *
     * @param context 上下文对象
     * @return 个人资料
     */
    public static GetUserMessageResponseModel getUserMessage(Context context) {
        File file = new File(context.getFilesDir(), "userMessage.obj");
        if (file != null && file.length() > 0) {

            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(new FileInputStream(file));
                GetUserMessageResponseModel getUserMessageResponseModel = (GetUserMessageResponseModel) ois.readObject();
                ois.close();
                return getUserMessageResponseModel;
            } catch (IOException e) {
                Toast.makeText(context, "获取登录信息失败，请重新登录", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                Toast.makeText(context, "获取登录信息失败，请重新登录", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void clearFile(Context context) {
        //将手机号保存到当前应用程序目录下
        File phoneInfo = new File(context.getFilesDir(), "phoneInfo.obj");
        if (phoneInfo.exists()) {
            boolean isPhone = phoneInfo.delete();
            Logger.i("删除电话号码：" + isPhone);
        }
        File loginResponse = new File(context.getFilesDir(), "loginResponse.obj");
        if (loginResponse.exists()) {
            boolean isLogin = loginResponse.delete();
            Logger.i("删除登录结果：" + isLogin);
        }
        File userMessage = new File(context.getFilesDir(), "userMessage.obj");
        if (userMessage.exists()) {
            boolean isMessage = userMessage.delete();
            Logger.i("删除用户信息：" + isMessage);
        }
    }
}
