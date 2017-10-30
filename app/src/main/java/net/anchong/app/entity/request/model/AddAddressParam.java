package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * Created by baishixin on 16/4/20.
 */
public class AddAddressParam extends ParamModel implements Serializable {


    /**
     * region : 所在区域
     * add_name : 收货人
     * phone : 联系电话
     * address : 详细地址
     */

    private String region;
    private String add_name;
    private String phone;
    private String address;

    public AddAddressParam() {
    }

    public AddAddressParam(String region, String add_name, String phone, String address) {
        this.region = region;
        this.add_name = add_name;
        this.phone = phone;
        this.address = address;
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
