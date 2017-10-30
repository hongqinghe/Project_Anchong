package net.anchong.app.entity.response.model;

/**
 * Created by baishixin on 16/3/28.
 */
public class ResponseModel {

    /**
     * serverTime : 1459147442
     * ServerNo : 账号密码错误
     * ResultData :
     */

    private int serverTime;
    private String ServerNo;
    private String ResultData;

    public int getServerTime() {
        return serverTime;
    }

    public void setServerTime(int serverTime) {
        this.serverTime = serverTime;
    }

    public String getServerNo() {
        return ServerNo;
    }

    public void setServerNo(String ServerNo) {
        this.ServerNo = ServerNo;
    }

    public String getResultData() {
        return ResultData;
    }

    public void setResultData(String ResultData) {
        this.ResultData = ResultData;
    }
}
