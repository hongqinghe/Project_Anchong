package net.anchong.app.entity;

/**
 * 安虫用户信息表 实体对象
 * 根据用户信息表定义
 * Created by baishixin on 16/3/18.
 */
public class Anchong_UserMessages {

    private int id;
    private String contact;
    private String account;
    private String password;
    private String qq;
    private String email;

    public Anchong_UserMessages() {
    }

    public Anchong_UserMessages(String contact, String account, String password, String qq, String email) {
        this.contact = contact;
        this.account = account;
        this.password = password;
        this.qq = qq;
        this.email = email;
    }

    public Anchong_UserMessages(int id, String contact, String account, String password, String qq, String email) {
        this.id = id;
        this.contact = contact;
        this.account = account;
        this.password = password;
        this.qq = qq;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Anchong_UserMessages{" +
                "id=" + id +
                ", contact='" + contact + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
