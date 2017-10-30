package net.anchong.app.entity.request.model;

import net.anchong.app.entity.request.model.ParamModel;

/**
 * Created by baishixin on 16/3/30.
 */
public class SetUserMessageModel extends ParamModel {


    /**
     * nickname : 昵称
     * qq : qq账号
     * email : 邮箱
     * contact : 联系人
     */

    private String nickname;
    private String qq;
    private String email;
    private String contact;

    public SetUserMessageModel() {
    }

    public SetUserMessageModel(String nickname, String qq, String email, String contact) {
        this.nickname = nickname;
        this.qq = qq;
        this.email = email;
        this.contact = contact;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
