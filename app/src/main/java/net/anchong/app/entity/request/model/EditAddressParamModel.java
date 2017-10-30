package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * Created by baishixin on 16/4/21.
 */
public class EditAddressParamModel extends ParamModel implements Serializable {


    /**
     * aid : 要修改的收货地址的主键id
     */

    private String aid;

    public EditAddressParamModel() {
    }

    public EditAddressParamModel(String aid) {
        this.aid = aid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }
}
