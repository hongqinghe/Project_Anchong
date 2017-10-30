
package net.anchong.app.view;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.request.model.ShopCarNumberParam;
import net.anchong.app.entity.response.model.ResponseErrorModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.ui.LoginActivity;
import net.anchong.app.uitls.ACRequestUtils;

import okhttp3.Call;

/**
 * 购物车专用
 * 带±的输入框 Created by 白世鑫 on 15/12/3.
 */
public class ShopCarOrderEditTextView extends FrameLayout implements View.OnClickListener {

    private View add;

    private View del;

    private EditText edit;

    private OnOrderEditCallBack callback;

    //添加商品是默认的最大添加数量
    private int max = 255;

    private final int MIN = 1;

    public boolean canToast = true;

    private int cart_id;

    private Context context;

    private int num;

    /**
     * 申请退货提示
     */
    private boolean isReturnGoodsToast;

    public ShopCarOrderEditTextView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ShopCarOrderEditTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
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
        edit.setKeyListener(null);
        edit.setOnClickListener(this);
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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        num = Integer.parseInt(
                TextUtils.isEmpty(edit.getText().toString()) ? "1" : edit.getText().toString());
        switch (view.getId()) {
            case R.id.add:
                if (num < max) {
                    changeNum(++num);
                } else {
                    if (!isReturnGoodsToast) {
                        Toast.makeText(getContext(), "已达到商品可购买最大数量了哦", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "已达到可申请最大数量了哦", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.delete:
                if ((num - 1) >= MIN) {
                    changeNum(num - 1);
                }
                if ((num - 1) < MIN) {
                    Toast.makeText(getContext(), "不能再减少啦", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.edit:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View v = inflate(getContext(), R.layout.layout_shopcar_orderedittext, null);

                final EditText numUpdate = (EditText) v.findViewById(R.id.edit_update);
                numUpdate.setText(edit.getText());

                builder.setTitle("修改购买数量");
                builder.setView(v);
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        num = Integer.parseInt(numUpdate.getText().toString());
                        showMessage("修改后的数量：" + num);
                        showMessage("原来的数量：" + edit.getText());
//                        ShopCarOrderEditTextView.this.setText(nnnn+"",cart_id);
                        changeNum(num);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;
        }

        edit.setSelection(edit.getText().length());
    }

    private void changeNum(final int num) {
        if (num > max || num < MIN) {
            showMessage("商品数量有误！");
            return;
        }
        ShopCarNumberParam shopCarNumberParam = new ShopCarNumberParam(cart_id + "", num + "");
        String json = new Gson().toJson(shopCarNumberParam);
        RequestModel requestModel = null;
        String copyToken = null;
        //判断用户是否登录
        //登录状态
        if (MainActivity.isLogin == true) {
            requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "",
                    MainActivity.loginResponseModel.getResultData().getGuid() + "", shopCarNumberParam);
            copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
        } else {
            LoginActivity.start(getContext());
        }
        String signature = ACRequestUtils.getMD5(MyApplication.CARTCARTNUM + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
        requestModel.setSignature(signature);


        HttpManager.getInstance()
                .post(MyApplication.API + MyApplication.CARTCARTNUM)
                .addParams("time", requestModel.getTime())
                .addParams("version", requestModel.getVersion())
                .addParams("guid", requestModel.getGuid())
                .addParams("param", json)
                .addParams("signature", signature)
                .build()
                .execute(ResponseErrorModel.class, new CommonCallback<ResponseErrorModel>() {
                    @Override
                    public void onSuccess(ResponseErrorModel response, Object... obj) {
                        if ("0".equals(response.getServerNo())) {
                            //购物车添加成功，查询购物车获取数量并且更新UI
                            edit.setText(String.valueOf(num));
                            showMessage(response.getResultData().getMessage());
                        } else {
                            showMessage(response.getResultData().getMessage());
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e) {
                        showMessage(context.getString(R.string.request_error_msg));
                        e.printStackTrace();
                    }
                });
    }

    private void showMessage(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }


    public int getText() {
        try {
            return Integer.parseInt(edit.getText().toString());
        } catch (Exception e) {
            return 1;
        }
    }

    public void setText(String text, int cart_id) {
        this.cart_id = cart_id;
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
        boolean OnCallBack(int num);
    }
    /**
     *
     */
}
