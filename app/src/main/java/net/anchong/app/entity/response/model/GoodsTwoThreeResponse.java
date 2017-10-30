package net.anchong.app.entity.response.model;

import java.io.Serializable;
import java.util.List;

/**
 * 商品二三级分类
 * Created by baishixin on 16/4/21.
 */
public class GoodsTwoThreeResponse implements Serializable {


    /**
     * serverTime : 1462519045
     * ServerNo : 0
     * ResultData : [{"cat_id":"39","cat_name":"门禁控制器"},{"cat_id":"40","cat_name":"门禁一体机"},{"cat_id":"41","cat_name":"门禁读卡器"},{"cat_id":"42","cat_name":"生物识别"},{"cat_id":"43","cat_name":"门禁锁"},{"cat_id":"44","cat_name":"电梯控制"},{"cat_id":"45","cat_name":"快速通道"},{"cat_id":"46","cat_name":"门禁配套"},{"cat_id":"49","cat_name":"智能门锁"},{"cat_id":"50","cat_name":"储物柜锁"}]
     */

    private String serverTime;
    private String ServerNo;
    /**
     * cat_id : 39
     * cat_name : 门禁控制器
     */

    private List<ResultDataEntity> ResultData;

    public String getServerTime() {
        return serverTime;
    }

    public void setServerTime(String serverTime) {
        this.serverTime = serverTime;
    }

    public String getServerNo() {
        return ServerNo;
    }

    public void setServerNo(String ServerNo) {
        this.ServerNo = ServerNo;
    }

    public List<ResultDataEntity> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataEntity> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable {
        private String cat_id;
        private String cat_name;

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }
    }
}
