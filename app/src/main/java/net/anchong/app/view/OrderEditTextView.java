
package net.anchong.app.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import net.anchong.app.R;

/**
 * 带±的输入框 Created by 白世鑫 on 15/12/3.
 */
public class OrderEditTextView extends FrameLayout implements View.OnClickListener {

    private View add;

    private View del;

    private EditText edit;

    private OnOrderEditCallBack callback;

    //添加商品是默认的最大添加数量
    private int max = 255;

    private final int MIN = 1;

    public boolean canToast = true;

    /**
     * 申请退货提示
     */
    private boolean isReturnGoodsToast;

    public OrderEditTextView(Context context) {
        super(context);
        init();
    }

    public OrderEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.order_edit, null);
        this.addView(view);
        add = findViewById(R.id.add);
        del = findViewById(R.id.delete);
        edit = (EditText) findViewById(R.id.edit);
        edit.setText("1");
        add.setOnClickListener(this);
        del.setOnClickListener(this);
        edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int num = Integer.parseInt(TextUtils.isEmpty(edit.getText().toString()) ? "1"
                        : edit.getText().toString());
                if (max < MIN) {
                    max = MIN;
                }
                if (num > max) {
                    edit.setText(String.valueOf(max));
                    if (edit.getVisibility() == View.VISIBLE) {
                        if (canToast) {
                            Toast.makeText(getContext(), "已到达商品最大购买数量", Toast.LENGTH_SHORT).show();
                        }

                    }

                } else if (num < MIN) {
                    edit.setText(String.valueOf(MIN));
                }

                if (callback != null) {
                    callback.OnCallBack(getText());
                }
            }
        });

    }

    public void setRetrunGoodsToast(boolean isReturnGoodsToast) {
        this.isReturnGoodsToast = isReturnGoodsToast;
    }

    @Override
    public void onClick(View view) {
        int num = Integer.parseInt(
                TextUtils.isEmpty(edit.getText().toString()) ? "1" : edit.getText().toString());
        switch (view.getId()) {
            case R.id.add:
                if (num < max) {
                    edit.setText(String.valueOf(num + 1));
                } else {
                    if (!isReturnGoodsToast) {
                        Toast.makeText(getContext(), "已达到商品可购买最大数量了哦", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "已达到可申请最大数量了哦", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.delete:
                int n = num - 1;
                if (num > MIN) {
                    edit.setText(String.valueOf(num - 1));
                }
                if (n < MIN) {
                    Toast.makeText(getContext(), "不能再减少啦", Toast.LENGTH_SHORT).show();
                }
                break;
        }

        edit.setSelection(edit.getText().length());
    }

    public int getText() {
        try {
            return Integer.parseInt(edit.getText().toString());
        } catch (Exception e) {
            return 1;
        }
    }

    public void setText(String text) {
        try {
            edit.setText(TextUtils.isEmpty(text) ? "1" : String.valueOf(Integer.parseInt(text)));
        } catch (Exception e) {
            edit.setText("1");
        }
    }

    public void setOnOrderEditCallBack(OnOrderEditCallBack callback) {
        this.callback = callback;
    }

    /**
     * 设置edit最大值
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 点击事件的回调
     */
    public interface OnOrderEditCallBack {
        void OnCallBack(int num);
    }
}
