package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * Created by baishixin on 16/3/28.
 */
public class RegisterParamModel extends ParamModel implements Serializable {


    /**
     * phone : 注册的手机号码
     * password : 登录密码
     * phonecode : 短信获取验证码
     */

    private String phone;
    private String password;
    private String phonecode;

    public RegisterParamModel() {
    }

    public RegisterParamModel(String phone, String password, String phonecode) {
        this.phone = phone;
        this.password = password;
        this.phonecode = phonecode;
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
