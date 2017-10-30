package net.anchong.app.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import net.anchong.app.MainActivity;
import net.anchong.app.R;
import net.anchong.app.app.MyApplication;
import net.anchong.app.entity.request.model.MyBusinessActionParamModel;
import net.anchong.app.entity.request.model.RequestModel;
import net.anchong.app.entity.response.model.FaBaoResponseModel;
import net.anchong.app.fragment.ChengJieFragment;
import net.anchong.app.fragment.FaBaoFragment;
import net.anchong.app.fragment.FaBuFragment;
import net.anchong.app.fragment.RenCaiFragment;
import net.anchong.app.http.CommonCallback;
import net.anchong.app.http.HttpManager;
import net.anchong.app.http.domain.ResponseResult;
import net.anchong.app.ui.EditPublishActivity;
import net.anchong.app.uitls.ACRequestUtils;

import java.util.List;

import okhttp3.Call;

/**
 * 发包工程商机列表的适配器
 * Created by baishixin on 16/4/11.
 */
public class FaBaoAdapter extends BaseAdapter {

    private Fragment faBaoFragment;
    private Context context;
    private LayoutInflater mInflater;
    //    private FaBaoResponseModel responseModel;
    private String type;
    private List<FaBaoResponseModel.ResultDataEntity.ListEntity> listEntitys;

    public FaBaoAdapter(Context context, Fragment faBaoFragment, LayoutInflater inflater, List<FaBaoResponseModel.ResultDataEntity.ListEntity> listEntity, String type) {
        this.context = context;
        this.faBaoFragment = faBaoFragment;
        mInflater = inflater;
        this.type = type;
        this.listEntitys = listEntity;
//        this.responseModel = responseModel;
    }


    @Override
    public int getCount() {
        if (listEntitys == null) {
            return 0;
        } else {
            return listEntitys.size();
        }
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        if (convertView == null) {
            vh = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_list_fabaofragment, null);
            vh.title = (TextView) convertView.findViewById(R.id.tv_item_title);
            vh.content = (ExpandableTextView) convertView.findViewById(R.id.expand_text_view);
            vh.created_at = (TextView) convertView.findViewById(R.id.tv_item_created_at);
            vh.refresh = (Button) convertView.findViewById(R.id.btn_refresh);
            vh.edit = (Button) convertView.findViewById(R.id.btn_edit);
            vh.delete = (Button) convertView.findViewById(R.id.btn_del);
            convertView.setTag(vh);
        }
        vh = (ViewHolder) convertView.getTag();
        final FaBaoResponseModel.ResultDataEntity.ListEntity listEntity = listEntitys.get(position);

        vh.title.setText(listEntity.getTitle());
        vh.content.setText(listEntity.getContent());
        vh.created_at.setText(listEntity.getCreated_at());
        vh.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBusinessActionParamModel myBusinessActionParamModel = new MyBusinessActionParamModel("1", listEntity.getBid());
                RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", myBusinessActionParamModel);
                String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                String json = new Gson().toJson(myBusinessActionParamModel);
                String signature = ACRequestUtils.getMD5(MyApplication.MYBUSINESSACTION + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                requestModel.setSignature(signature);
                HttpManager.getInstance()
                        .post(MyApplication.API + MyApplication.MYBUSINESSACTION)
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
                                    if (faBaoFragment instanceof FaBaoFragment) {
                                        ((FaBaoFragment) faBaoFragment).refreshData();
                                    }
                                    if (faBaoFragment instanceof ChengJieFragment) {
                                        ((ChengJieFragment) faBaoFragment).refreshData();
                                    }
                                    if (faBaoFragment instanceof FaBuFragment) {
                                        ((FaBuFragment) faBaoFragment).refreshData();
                                    }
                                    if (faBaoFragment instanceof RenCaiFragment) {
                                        ((RenCaiFragment) faBaoFragment).refreshData();
                                    }
                                } else {
                                    Toast.makeText(context, context.getString(R.string.request_error_msg), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Call call, Exception e) {
                                Toast.makeText(context, "网络不稳定，请重试", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
            }
        });
        vh.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditPublishActivity.type = type;
                EditPublishActivity.start(context, listEntity);
            }
        });
        vh.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBusinessActionParamModel myBusinessActionParamModel = new MyBusinessActionParamModel("2", listEntity.getBid());
                RequestModel requestModel = new RequestModel(System.currentTimeMillis() / 1000 + "", MyApplication.APP_VERSION + "", MainActivity.loginResponseModel.getResultData().getGuid() + "", myBusinessActionParamModel);
                String copyToken = ACRequestUtils.getCryptToken(MainActivity.loginResponseModel.getResultData().getToken());
                String json = new Gson().toJson(myBusinessActionParamModel);
                String signature = ACRequestUtils.getMD5(MyApplication.MYBUSINESSACTION + requestModel.getTime() + requestModel.getGuid() + json + copyToken);
                requestModel.setSignature(signature);
                HttpManager.getInstance()
                        .post(MyApplication.API + MyApplication.MYBUSINESSACTION)
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
                                    FaBaoAdapter.this.notifyDataSetChanged();
                                } else {
                                    Toast.makeText(context, context.getString(R.string.request_error_msg), Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(Call call, Exception e) {
                                Toast.makeText(context, "网络不稳定，请重试", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        });
            }
        });

        return convertView;
    }

    class ViewHolder {
        TextView title;
        ExpandableTextView content;
        //创建时间
        TextView created_at;
        Button refresh;
        Button edit;
        Button delete;

    }
}
