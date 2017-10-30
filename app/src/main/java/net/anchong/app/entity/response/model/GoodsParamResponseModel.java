package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/4/25.
 */
public class GoodsParamResponseModel extends ResponseResult implements Serializable {


    /**
     * ResultData : [{"name":"颜色","value":["红色","黄色","绿色"]},{"name":"尺寸","value":["32","33"]}]
     */

    /**
     * name : 颜色
     * value : ["红色","黄色","绿色"]
     */

    private List<ResultDataEntity> ResultData;

    public List<ResultDataEntity> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataEntity> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable{
        private String name;
        private List<String> value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getValue() {
            return value;
        }

        public void setValue(List<String> value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodsParamResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
