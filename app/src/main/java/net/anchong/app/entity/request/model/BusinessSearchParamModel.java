package net.anchong.app.entity.request.model;

import java.io.Serializable;

/**
 * 商机检索分类请求模型
 * Created by baishixin on 16/4/18.
 */
public class BusinessSearchParamModel extends ParamModel implements Serializable {

    /**
     * type : 类型
     */

    private String type;

    public BusinessSearchParamModel() {
    }

    public BusinessSearchParamModel(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
