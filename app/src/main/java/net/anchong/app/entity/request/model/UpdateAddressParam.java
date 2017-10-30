package net.anchong.app.entity.request.model;

import net.anchong.app.entity.request.model.ParamModel;

import java.io.Serializable;

/**
 * 执行收货地址修改操作是的请求参数
 * Created by baishixin on 16/4/21.
 */
public class UpdateAddressParam extends ParamModel implements Serializable {


    /**
     * aid : 被修改的收货地址的主键id
     * region : 所在区域
     * add_name : 收货人
     * phone : 联系电话
     * address : 详细地址
     */

    private String aid;
    private String region;
    private String add_name;
    private String phone;
    private String address;

    public UpdateAddressParam() {
    }

    public UpdateAddressParam(String aid, String region, String add_name, String phone, String address) {
        this.aid = aid;
        this.region = region;
        this.add_name = add_name;
        this.phone = phone;
        this.address = address;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAdd_name() {
        return add_name;
    }

    public void setAdd_name(String add_name) {
        this.add_name = add_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
