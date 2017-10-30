package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * Created by baishixin on 16/7/1.
 */
public class SMSAuthResponse extends ResponseResult implements Serializable {


    /**
     * ResultData : 1
     */

    private int ResultData;

    public int getResultData() {
        return ResultData;
    }

    public void setResultData(int ResultData) {
        this.ResultData = ResultData;
    }
}
