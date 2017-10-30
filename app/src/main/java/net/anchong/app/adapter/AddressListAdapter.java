package net.anchong.app.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.EditAddressParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.AddressListResponse;
import net.anchong.app.entity.response.model.GoodsListResponseModel;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.http.domain.ResponseResult;
import net.anchong.app.ui.AddressManagerActivity;
import net.anchong.app.ui.EditAddressActivity;
import net.anchong.app.uitls.ACRequestUtils;

import java.util.List;

import okhttp3.Call;

/**
 * Created by baishixin on 16/4/20.
 */
public class AddressListAdapter extends BaseAdapter {

    private AddressManagerActivity activity;
    private LayoutInflater mInflater;
    private List<AddressListResponse.ResultDataBean> addressList;

    public AddressListAdapter(AddressManagerActivity activity, LayoutInflater mInflater, List<AddressListResponse.ResultDataBean> addressList) {
        this.activity = activity;
        this.mInflater = mInflater;
        this.addressList = addressList;
    }

    @Override
    public int getCount() {
        if (addressList == null || addressList.size() <= 0) {
            return 0;
        }
        return addressList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_list_address, null);
            vh.select = (LinearLayout) convertView.findViewById(R.id.ll_item_select);
            vh.name = (TextView) convertView.findViewById(R.id.tv_name);
            vh.phone = (TextView) convertView.findViewById(R.id.tv_phone);
            vh.address = (TextView) convertView.findViewById(R.id.tv_address);
            vh.edit = (TextView) convertView.findViewById(R.id.tv_edit);
            vh.del = (TextView) convertView.findViewById(R.id.tv_del);
            vh.isdefault = (CheckBox) convertView.findViewById(R.id.cb_isdefault);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        vh.name.setText(addressList.get(position).getAdd_name().toString().trim());
        vh.phone.setText(addressList.get(position).getPhone().toString().trim());
        vh.address.setText(addressList.get(position).getRegion().toString().trim() + addressList.get(position).getAddress().toString().trim());
        String flag = addressList.get(position).getIsdefault();
        if ("1".equals(flag)) {
            vh.isdefault.setChecked(true);
            vh.isdefault.setText("默认地址");
            vh.isdefault.setTextColor(Color.RED);
        }
        /**
         * 修改默认收货地址
         */
        vh.isdefault.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    //如果没有被选中，请求设置为默认收货地址
                    EditAddressParamModel editAddressParamModel = new EditAddressParamModel(addressList.get(position).getId() + "");
                    RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), editAddressParamModel);
                    String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                    String json = new Gson().toJson(editAddressParamModel);
                    String signature = ACRequestUtils.getMD5(MyApplication.USERSETDEFAULTADDRESS + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                    requestModel.setSignature(signature);
                    HttpManager.getInstance()
                            .post(MyApplication.API + MyApplication.USERSETDEFAULTADDRESS)
                            .addParams("time", requestModel.getTime())
                            .addParams("version", requestModel.getVersion())
                            .addParams("guid", requestModel.getGuid())
                            .addParams("param", json)
                            .addParams("signature", signature)
                            .build()
                            .execute(GoodsListResponseModel.class, new CommonCallback<GoodsListResponseModel>() {
                                @Override
                                public void onSuccess(GoodsListResponseModel response, Object... obj) {
                                    Log.e("MainActivity", response.toString());
                                    //返回的状态码为0 代表请求正常
                                    if (response.getServerNo().equals("0")) {
                                        ((AddressManagerActivity) activity).updateDefaultAddress();
                                    } else {
                                        Toast.makeText(activity, activity.getString(R.string.request_error_msg), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onError(Call call, Exception e) {
                                    e.printStackTrace();
                                }
                            });
                }
            }
        });
        /**
         * 修改收货地址
         */
        vh.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressListAdapter.this.activity, EditAddressActivity.class);
                intent.putExtra("aid", addressList.get(position).getId() + "");
                activity.startActivity(intent);
            }
        });
        /**
         * 删除收货地址
         */
        vh.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAddressParamModel editAddressParamModel = new EditAddressParamModel(addressList.get(position).getId() + "");
                RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid(), editAddressParamModel);
                String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                String json = new Gson().toJson(editAddressParamModel);
                String signature = ACRequestUtils.getMD5(MyApplication.USERDELADDRESS + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                requestModel.setSignature(signature);
                HttpManager.getInstance()
                        .post(MyApplication.API + MyApplication.USERDELADDRESS)
                        .addParams("time", requestModel.getTime())
                        .addParams("version", requestModel.getVersion())
                        .addParams("guid", requestModel.getGuid())
                        .addParams("param", json)
                        .addParams("signature", signature)
                        .build()
                        .execute(ResponseResult.class, new CommonCallback<ResponseResult>() {
                            @Override
                            public void onSuccess(ResponseResult response, Object... obj) {
                                Log.e("MainActivity", response.toString());
                                //返回的状态码为0 代表请求正常
                                if (response.getServerNo().equals("0")) {
                                    ((AddressManagerActivity) activity).updateDefaultAddress();
                                } else {
                                    Toast.makeText(activity, activity.getString(R.string.request_error_msg), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Call call, Exception e) {
                                Toast.makeText(activity, "网络不稳定，请重试", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
            }
        });
        vh.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AddressManagerActivity) activity).selectByPosition(position);
            }
        });
        return convertView;
    }

    class ViewHolder {
        LinearLayout select;
        TextView name;
        TextView phone;
        TextView address;
        TextView edit;
        TextView del;
        CheckBox isdefault;

    }
}







