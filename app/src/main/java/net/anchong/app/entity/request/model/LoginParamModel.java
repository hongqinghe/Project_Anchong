package net.anchong.app.entity.request.model;

import net.anchong.app.entity.request.model.ParamModel;

import java.io.Serializable;

/**
 * Created by baishixin on 16/3/25.
 */
public class LoginParamModel extends ParamModel implements Serializable {

    private String username;
    private String password;

    public LoginParamModel() {
    }

    public LoginParamModel(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginParamModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
