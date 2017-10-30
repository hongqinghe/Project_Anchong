package net.anchong.app.entity.model;

import java.util.List;

/**
 * Created by lihaiyi on 15/12/3.
 */
public class ShoppingCarModel {
    //复选框选中状态
    public boolean isSelect;
    //是否可以编辑
    public boolean enableEdit;
    public int viewType;
    public String item_code;
    public String product_code;
    public String img_url;
    public String title;
    //商品的tag标签
    public List<String> property;
    public String sale_price;
    public String list_price;
    public String label;
    public int stock;
    public String warehouse_id;
    public ShoppingcarStatusModel status;
    public String warehouse;
    public int item_count;
    public String id;
    public int position;
    //是否修改过数量
    public boolean isChange;
    //起始购物车数量
    public int source_item_count;
    public String total_price;
    public String total_count;
    public String tips;
    public String tips_address;
    public boolean is_split;
    public boolean is_login;


    public int edit_item_count;
    public String receipt_type;
    public AddressModel address;
    public String pay_price;
    public String logistics_cost;
    public String logistics_compay;
    public boolean is_haitao;
    public boolean weixin_pay;




}
