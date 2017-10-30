package net.anchong.app.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import net.anchong.app.R;
import net.anchong.app.fragment.ImageDetailFragment;
import net.anchong.app.okhttputils.OkHttpUtils;
import net.anchong.app.okhttputils.callback.FileCallBack;
import net.anchong.app.uitls.HackyViewPager;

import java.io.File;
import java.io.FileNotFoundException;

import okhttp3.Call;

public class ImagePagerActivity extends FragmentActivity implements View.OnClickListener {
    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";


    private static final String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
            ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//保存到SD卡


    private HackyViewPager mPager;
    private int pagerPosition;
    private TextView indicator;
    private String[] urls;
    private ProgressDialog pd;
    private TextView downImg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_detail_pager);
        pd = new ProgressDialog(this);
        pd.setTitle("图片保存中，请稍等..");
        pd.setCanceledOnTouchOutside(false);

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        urls = getIntent().getStringArrayExtra(EXTRA_IMAGE_URLS);


        mPager = (HackyViewPager) findViewById(R.id.pager);
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(
                getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        indicator = (TextView) findViewById(R.id.indicator);
        downImg = (TextView) findViewById(R.id.tv_down_img);

        CharSequence text = getString(R.string.viewpager_indicator, 1, mPager
                .getAdapter().getCount());
        indicator.setText(text);
        // 更新下标
        mPager.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                CharSequence text = getString(R.string.viewpager_indicator,
                        arg0 + 1, mPager.getAdapter().getCount());
                pagerPosition = arg0;
                indicator.setText(text);
            }

        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);
        downImg.setOnClickListener(this);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_down_img:

                String fileName = urls[pagerPosition].substring(urls[pagerPosition].lastIndexOf("/"));
                pd.show();
                OkHttpUtils//
                        .get()//
                        .url(urls[pagerPosition])//
                        .build()//
                        .execute(new FileCallBack(Environment.getExternalStorageDirectory() + "/anchong", fileName)//
                        {
                            @Override
                            public void inProgress(float progress) {
//                                mProgressBar.setProgress((int) (100 * progress));
                            }

                            @Override
                            public void onError(Call call, Exception e) {
//                                Log.e(TAG, "onError :" + e.getMessage());
                                Logger.i("onError:" + e.getMessage());
                            }

                            @Override
                            public void onResponse(File file) {
//                                Log.e(TAG, "onResponse :" + file.getAbsolutePath());
                                Logger.i("onResponse:" + file.getAbsolutePath());
                                updateImage(file);
                            }
                        });

                break;
        }
    }

    /**
     * 图片下载完成后，发送广播，通知图库更新
     *
     * @param file
     */
    private void updateImage(File file) {
        pd.dismiss();
        // 把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),
                    file.getAbsolutePath(), file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri uri = Uri.fromFile(file);
        intent.setData(uri);
        this.sendBroadcast(intent);
        Toast.makeText(this, "图片保存成功", Toast.LENGTH_SHORT).show();
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public String[] fileList;

        public ImagePagerAdapter(FragmentManager fm, String[] fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.length;
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList[position];
            return ImageDetailFragment.newInstance(url);
        }

    }
}