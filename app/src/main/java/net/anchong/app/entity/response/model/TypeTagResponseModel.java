package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 连接服务器获取到发布分类和类别的实体对象
 * Created by baishixin on 16/4/11.
 */
public class TypeTagResponseModel extends ResponseResult implements Serializable {


    /**
     * ResultData : [{"type":"发布工程","tag":["探测监控\n","防护保障\n","探测报警\n","弱店工程\n","北京市"]},{"type":"承接工程","tag":["安保设备\n","北京市"]},{"type":"发布人才","tag":["巡更巡检","北京市"]},{"type":"招聘人才","tag":["智能门禁","北京市"]}]
     */

    /**
     * type : 发布工程
     * tag : ["探测监控\n","防护保障\n","探测报警\n","弱店工程\n","北京市"]
     */

    private List<ResultDataEntity> ResultData;

    public List<ResultDataEntity> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataEntity> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        private String type;
        private List<String> tag;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "type='" + type + '\'' +
                    ", tag=" + tag +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "TypeTagResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
