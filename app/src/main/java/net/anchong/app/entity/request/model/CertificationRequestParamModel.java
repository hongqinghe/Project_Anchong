package net.anchong.app.entity.request.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by baishixin on 16/4/11.
 */
public class CertificationRequestParamModel extends ParamModel implements Serializable {

    /**
     * 认证名称
     */
    private String auth_name;
    /**
     * 资质名称
     */
    private ArrayList<String> qua_name;
    /**
     * 资质说明
     */
    private ArrayList<String> explanation;
    /**
     * 上传图片的url地址
     */
    private ArrayList<String> credentials;

    public CertificationRequestParamModel() {
    }

    public CertificationRequestParamModel(String auth_name, ArrayList<String> qua_name, ArrayList<String> explanation, ArrayList<String> credentials) {
        this.auth_name = auth_name;
        this.qua_name = qua_name;
        this.explanation = explanation;
        this.credentials = credentials;
    }

    public String getAuth_name() {
        return auth_name;
    }

    public void setAuth_name(String auth_name) {
        this.auth_name = auth_name;
    }

    public ArrayList<String> getQua_name() {
        return qua_name;
    }

    public void setQua_name(ArrayList<String> qua_name) {
        this.qua_name = qua_name;
    }

    public ArrayList<String> getExplanation() {
        return explanation;
    }

    public void setExplanation(ArrayList<String> explanation) {
        this.explanation = explanation;
    }

    public ArrayList<String> getCredentials() {
        return credentials;
    }

    public void setCredentials(ArrayList<String> credentials) {
        this.credentials = credentials;
    }

    @Override
    public String toString() {
        return "CertificationRequestParamModel{" +
                "auth_name='" + auth_name + '\'' +
                ", qua_name=" + qua_name +
                ", explanation=" + explanation +
                ", credentials=" + credentials +
                '}';
    }
}
