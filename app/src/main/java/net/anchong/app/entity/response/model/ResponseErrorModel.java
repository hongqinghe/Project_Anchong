package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * 请求错误的处理类
 * Created by baishixin on 16/3/29.
 */
public class ResponseErrorModel extends ResponseResult implements Serializable{


    /**
     * ResultData : {"Message":"账号密码错误"}
     */

    /**
     * Message : 账号密码错误
     */

    private ResultDataBean ResultData;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean {
        private String Message;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        @Override
        public String toString() {
            return "ResultDataBean{" +
                    "Message='" + Message + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResponseErrorModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
