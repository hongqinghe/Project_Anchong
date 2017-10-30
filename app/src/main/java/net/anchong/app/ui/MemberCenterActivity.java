package net.anchong.app.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.anchong.app.R;
import net.anchong.app.adapter.MemberCenterAdapter;
import net.anchong.app.view.GeneralTitleBarView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 会员中心的Activity
 * Created by baishixin on 16/3/16.
 */
public class MemberCenterActivity extends BaseActivity implements GeneralTitleBarView.GeneralTitleBarOnclickListener, AdapterView.OnItemClickListener {

    @ViewInject(R.id.titlebar_membercenter)
    private GeneralTitleBarView mGeneralTitleBarView;

    @ViewInject(R.id.lv_member_center)
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_center);
        x.view().inject(this);
        initView();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        
    }

    private void initView() {
        mGeneralTitleBarView.setData("会员中心", "修改");
        mGeneralTitleBarView.setGeneralTitleBarOnclickListener(this);

        mListView.setAdapter(new MemberCenterAdapter(this, getLayoutInflater()));
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {
        Toast.makeText(this, "保存信息", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TextView textView = (TextView) view.findViewById(R.id.tv_member_center_content);
        String content = textView.getText().toString();
        switch (content) {
            case "基本资料":
                BasicInfoActivity.start(this);
                break;
            case "收货地址":
                AddressActivity.start(this);
                break;
            case "银行账户":
                TheBankAccountActivity.start(this);
                break;
            case "商家认证":
                AttestationActivity.start(this);
                break;
            case "商铺申请":
                Toast.makeText(this, textView.getText().toString(), Toast.LENGTH_SHORT).show();
                break;
        }
    }

}
