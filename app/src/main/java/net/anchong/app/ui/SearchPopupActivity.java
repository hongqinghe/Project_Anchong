package net.anchong.app.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import net.anchong.app.R;
import net.anchong.app.adapter.SearchBusinessAdapter;
import net.anchong.app.uitls.SystemBarTintManager;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by baishixin on 16/4/19.
 */
public class SearchPopupActivity extends Activity implements AdapterView.OnItemClickListener {

    @ViewInject(R.id.gv_search_business)
    private GridView gridView;
    private SearchBusinessAdapter adapter;

    private ArrayList<String> types = new ArrayList<>();
    private ArrayList<String> areas = new ArrayList<>();
    private String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_business);
        x.view().inject(this);
        initView();

    }

    private void initView() {
        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.transparent_title);// 通知栏所需颜色

        Intent intent = getIntent();
        key = intent.getStringExtra("key");
        if ("type".equals(key)) {

            types = (ArrayList<String>) intent.getSerializableExtra("types");
            adapter = new SearchBusinessAdapter(this, getLayoutInflater(), types);
            gridView.setAdapter(adapter);
        }
        if ("area".equals(key)) {
            areas = (ArrayList<String>) intent.getSerializableExtra("areas");
            adapter = new SearchBusinessAdapter(this, getLayoutInflater(), areas);
            gridView.setAdapter(adapter);
        }
        gridView.setOnItemClickListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        finish();
        return super.onTouchEvent(event);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        if ("type".equals(key)) {
            intent.putExtra("tag", types.get(position));
        }
        if ("area".equals(key)) {
            intent.putExtra("tag", areas.get(position));
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
