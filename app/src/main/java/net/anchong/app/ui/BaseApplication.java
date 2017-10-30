package net.anchong.app.ui;

import android.app.Application;

import net.anchong.app.BuildConfig;
import net.anchong.app.okhttputils.OkHttpUtils;

import org.xutils.x;

import java.util.concurrent.TimeUnit;

import in.srain.cube.Cube;

/**
 * Created by baishixin on 16/5/4.
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
//        x.view().inject(this);
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.
        // other code
        // ..
//        initFresco(this);
        Cube.onCreate(this);


        initOkhttp();

    }

    private void initOkhttp() {
        OkHttpUtils.getInstance().setConnectTimeout(30000, TimeUnit.MILLISECONDS);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        // other code
        // ...


    }


}
