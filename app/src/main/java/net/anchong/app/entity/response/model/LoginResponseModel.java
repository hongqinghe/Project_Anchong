package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * 处理登录之后服务器返回json的response对象
 * Created by baishixin on 16/3/2.
 */
public class LoginResponseModel extends ResponseResult implements Serializable {

    /**
     * ResultData : {"certification":"0","users_rank":"1","token":"6948a1a7c56362873d8f44372f5880d9","guid":"14"}
     */

    /**
     * certification : 0
     * users_rank : 1
     * token : 6948a1a7c56362873d8f44372f5880d9
     * guid : 14
     */

    private ResultDataBean ResultData;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable {
        private String certification;
        private String users_rank;
        private String token;
        private String guid;

        public String getCertification() {
            return certification;
        }

        public void setCertification(String certification) {
            this.certification = certification;
        }

        public String getUsers_rank() {
            return users_rank;
        }

        public void setUsers_rank(String users_rank) {
            this.users_rank = users_rank;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        @Override
        public String toString() {
            return "ResultDataBean{" +
                    "certification='" + certification + '\'' +
                    ", users_rank='" + users_rank + '\'' +
                    ", token='" + token + '\'' +
                    ", guid='" + guid + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
