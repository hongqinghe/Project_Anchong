package net.anchong.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.anchong.app.R;
import net.anchong.app.adapter.CategoryThreeAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

/**
 * 商品二级分类 View
 * Created by baishixin on 16/4/21.
 */
public class CategoryTwoView extends LinearLayout {

    private Context mContext;

    //二级分类名称
    @ViewInject(R.id.tv_category_two_name)
    private TextView name;
    //三级分类的GridView
    @ViewInject(R.id.gv_category_three)
    private MyGridView three;
    private CategoryThreeAdapter categoryThreeAdapter;

    private LayoutInflater inflater;


    public CategoryTwoView(Context context) {
        super(context);
        this.mContext = context;
        initData();
        initView();
    }

    private void initData() {

    }

    public CategoryTwoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initData();
        initView();
    }


    private void initView() {
        View.inflate(mContext, R.layout.viw_list_category_two, this);
        x.view().inject(this);


    }

    public void setInflater(LayoutInflater inflater) {
        this.inflater = inflater;
    }

//    public void setData(final GoodsTwoThreeResponse.ResultDataBean resultDataBean) {
//        if (resultDataBean != null) {
//            name.setText(resultDataBean.getName().getCat_name().toString().trim());
//        }
//        categoryThreeAdapter = new CategoryThreeAdapter(mContext, inflater, resultDataBean.getList());
//        three.setAdapter(categoryThreeAdapter);
//        three.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(mContext, GoodsListActivity.class);
//                intent.putExtra("cat_id", resultDataBean.getList().get(position).getCid());
//                mContext.startActivity(intent);
//            }
//        });
//    }

}
