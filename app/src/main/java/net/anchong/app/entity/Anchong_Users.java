package net.anchong.app.entity;

/**
 * 安虫用户表 实体对象
 * 根据用户表定义
 * Created by baishixin on 16/3/18.
 */
public class Anchong_Users {

    private int id;
    private String password;
    private String mobile;
    private String email;
    private String ctime;
    private String certification;
    private String open;

    public Anchong_Users() {
    }

    public Anchong_Users(String password, String mobile, String email, String ctime, String certification, String open) {
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.ctime = ctime;
        this.certification = certification;
        this.open = open;
    }

    public Anchong_Users(int id, String password, String mobile, String email, String ctime, String certification, String open) {
        this.id = id;
        this.password = password;
        this.mobile = mobile;
        this.email = email;
        this.ctime = ctime;
        this.certification = certification;
        this.open = open;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    @Override
    public String toString() {
        return "Anchong_Users{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", ctime='" + ctime + '\'' +
                ", certification='" + certification + '\'' +
                ", open='" + open + '\'' +
                '}';
    }
}
