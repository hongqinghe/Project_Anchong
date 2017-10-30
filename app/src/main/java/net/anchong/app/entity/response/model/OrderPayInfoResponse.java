package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * 支付前获取订单信息结果
 * Created by baishixin on 16/7/13.
 */
public class OrderPayInfoResponse extends ResponseResult implements Serializable {


    /**
     * outTradeNo : 支付单号
     * totalFee : 总价
     * body : 支付描述
     * subject : 支付名称
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable {
        private String outTradeNo;
        private String totalFee;
        private String body;
        private String subject;

        public String getOutTradeNo() {
            return outTradeNo;
        }

        public void setOutTradeNo(String outTradeNo) {
            this.outTradeNo = outTradeNo;
        }

        public String getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(String totalFee) {
            this.totalFee = totalFee;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "outTradeNo='" + outTradeNo + '\'' +
                    ", totalFee='" + totalFee + '\'' +
                    ", body='" + body + '\'' +
                    ", subject='" + subject + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderPayInfoResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
