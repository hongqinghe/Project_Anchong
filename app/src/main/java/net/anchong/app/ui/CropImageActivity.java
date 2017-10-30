package net.anchong.app.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.fragment.CropImageFragment;


public class CropImageActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    // Lifecycle Method ////////////////////////////////////////////////////////////////////////////

    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_image);
        uri = getIntent().getData();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, CropImageFragment.getInstance(uri)).commit();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void startResultActivity(Uri uri) {
        if (isFinishing()) return;
        Intent intent = new Intent();
        intent.setData(uri);
        setResult(CommnuityActivity.CROP_IMAGE, intent);
        finish();
        // Start ResultActivity
//        startActivity(ResultActivity.createIntent(this, uri));
    }

    public static void start(CommnuityActivity commnuityActivity, Uri data) {
        Intent intent = new Intent(commnuityActivity, CropImageActivity.class);
        intent.setData(data);
        commnuityActivity.startActivityForResult(intent, CommnuityActivity.CROP_IMAGE);
    }
}
