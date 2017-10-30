package net.anchong.app.entity.request.model;

import net.anchong.app.entity.request.model.ParamModel;

import java.io.Serializable;

/**
 * Created by baishixin on 16/4/15.
 */
public class ResetPassParamModel extends ParamModel implements Serializable {

    /**
     * phone : 手机号
     * password : 用户密码
     * phonecode : 手机收到的验证码
     */

    private String phone;
    private String password;
    private String phonecode;

    public ResetPassParamModel(String phone, String password, String phonecode) {
        this.phone = phone;
        this.password = password;
        this.phonecode = phonecode;
    }

    public ResetPassParamModel() {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(String phonecode) {
        this.phonecode = phonecode;
    }
}
