package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 快递公司请求响应对象
 * Created by baishixin on 16/5/25.
 */
public class SendCompanyResponse extends ResponseResult implements Serializable{


    /**
     * ResultData : ["德邦快递","顺丰快递","申通快递","圆通快递","百世汇通","中通快递","韵达快递","EMS","如风达","全峰速运","宅急送","优速快递","全一快递","万象物流","圣安物流","盛辉物流","中铁物流","新邦物流"]
     */

    private List<String> ResultData;


    public List<String> getResultData() {
        return ResultData;
    }

    public void setResultData(List<String> ResultData) {
        this.ResultData = ResultData;
    }

    @Override
    public String toString() {
        return "SendCompanyResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
