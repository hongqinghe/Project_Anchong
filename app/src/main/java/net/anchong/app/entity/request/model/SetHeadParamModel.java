package net.anchong.app.entity.request.model;

import net.anchong.app.entity.request.model.ParamModel;

/**
 * Created by baishixin on 16/3/30.
 */
public class SetHeadParamModel extends ParamModel {

    /**
     * headpic : 用户上传的头像
     */

    private String headpic;

    public SetHeadParamModel(String headpic) {
        this.headpic = headpic;
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }
}
