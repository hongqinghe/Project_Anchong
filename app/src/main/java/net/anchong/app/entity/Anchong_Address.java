package net.anchong.app.entity;

/**
 * Created by baishixin on 16/3/21.
 */
public class Anchong_Address {
    private int id;
    //省份
    private String province;
    //市
    private String city;
    //区
    private String area;
    //区域范围
    private String scope;
    //详细地址
    private String address;

    public Anchong_Address() {
    }

    public Anchong_Address(String province, String city, String area, String scope, String address) {
        this.province = province;
        this.city = city;
        this.area = area;
        this.scope = scope;
        this.address = address;
    }

    public Anchong_Address(int id, String province, String city, String area, String scope, String address) {
        this.id = id;
        this.province = province;
        this.city = city;
        this.area = area;
        this.scope = scope;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Anchong_Address{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", scope='" + scope + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
