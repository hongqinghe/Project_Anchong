package net.anchong.app.http.domain;

import java.io.Serializable;

/**
 * Created by Starry Jerry on 2016/6/19.
 * 模型基类
 */
public class ResponseResult implements Serializable{

    /**
     * serverTime : 1466281966
     * ServerNo : 5
     */

    private String serverTime;
    private String ServerNo;

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

    @Override
    public String toString() {
        return "ResponseResult{" +
                "serverTime=" + serverTime +
                ", ServerNo=" + ServerNo +
                '}';
    }
}
