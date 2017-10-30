package net.anchong.app.entity;

/**
 * 商家资质认证的实体类
 * Created by baishixin on 16/4/8.
 */
public class CertificationModel {

    private String name;
    private String desc;
    private String img_url;

    public CertificationModel() {
    }

    public CertificationModel(String name, String desc, String img_url) {
        this.name = name;
        this.desc = desc;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "CertificationModel{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }
}
