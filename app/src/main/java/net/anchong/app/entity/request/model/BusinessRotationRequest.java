package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * Created by baishixin on 16/6/28.
 */
public class BusinessRotationRequest extends ParamModel implements Serializable {


    /**
     * type : 哪个地方的轮播图(1工程2人才3找货)
     */

    private String type;

    public BusinessRotationRequest() {
    }

    public BusinessRotationRequest(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
