package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * 生成订单结果
 * Created by baishixin on 16/7/11.
 */
public class CreateOrderResponse extends ResponseResult implements Serializable {


    /**
     * outTradeNo : 支付单号
     * totalFee : 总价
     * body : 所有商品名字
     * subject : 安虫商城订单支付
     * notifyURLAlipay : http://pay.anchong.net/pay/webnotify(回调页面)
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        private String outTradeNo;
        private String totalFee;
        private String body;
        private String subject;
        private String notifyURLAlipay;

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

        public String getNotifyURLAlipay() {
            return notifyURLAlipay;
        }

        public void setNotifyURLAlipay(String notifyURLAlipay) {
            this.notifyURLAlipay = notifyURLAlipay;
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "outTradeNo='" + outTradeNo + '\'' +
                    ", totalFee='" + totalFee + '\'' +
                    ", body='" + body + '\'' +
                    ", subject='" + subject + '\'' +
                    ", notifyURLAlipay='" + notifyURLAlipay + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CreateOrderResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
