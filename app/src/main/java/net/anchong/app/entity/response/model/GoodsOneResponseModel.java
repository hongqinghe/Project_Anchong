package net.anchong.app.entity.response.model;

import java.io.Serializable;
import java.util.List;

/**
 * 商品列表一级分类
 * Created by baishixin on 16/4/21.
 */
public class GoodsOneResponseModel implements Serializable {

    /**
     * serverTime : 1461218076
     * ServerNo : 0
     * ResultData : [{"cat_id":"1","cat_name":"智能一卡通"},{"cat_id":"2","cat_name":"探测警报"},{"cat_id":"3","cat_name":"消防设备"},{"cat_id":"4","cat_name":"交通设施"},{"cat_id":"5","cat_name":"安保设备"},{"cat_id":"6","cat_name":"防护保障"}]
     */

    private String serverTime;
    private String ServerNo;
    /**
     * cat_id : 1
     * cat_name : 智能一卡通
     */

    private List<ResultDataBean> ResultData;

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

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable {
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
