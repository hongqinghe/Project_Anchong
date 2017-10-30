package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * Created by baishixin on 16/3/24.
 */
public class PhoneParamModel extends ParamModel implements Serializable {

    /**
     * phone : 用户注册的手机号
     * action : 1(注册验证)2(修改密码)3(登录验证)4(身份验证)
     */

    private String phone;
    private String action;

    public PhoneParamModel() {
    }

    public PhoneParamModel(String action, String phone) {
        this.action = action;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
