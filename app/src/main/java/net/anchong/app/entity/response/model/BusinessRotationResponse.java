package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/6/28.
 */
public class BusinessRotationResponse extends ResponseResult implements Serializable {


    /**
     * ad_code : 图片
     * ad_name : business
     * ad_link : 13
     */

    private List<ResultDataEntity> ResultData;

    public List<ResultDataEntity> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataEntity> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        private String ad_code;
        private String ad_name;
        private String ad_link;

        public String getAd_code() {
            return ad_code;
        }

        public void setAd_code(String ad_code) {
            this.ad_code = ad_code;
        }

        public String getAd_name() {
            return ad_name;
        }

        public void setAd_name(String ad_name) {
            this.ad_name = ad_name;
        }

        public String getAd_link() {
            return ad_link;
        }

        public void setAd_link(String ad_link) {
            this.ad_link = ad_link;
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "ad_code='" + ad_code + '\'' +
                    ", ad_name='" + ad_name + '\'' +
                    ", ad_link='" + ad_link + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BusinessRotationResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
