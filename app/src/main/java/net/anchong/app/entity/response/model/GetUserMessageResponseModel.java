package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * 请求用户资料之后返回json的实体类
 * Created by baishixin on 16/3/29.
 */
public class GetUserMessageResponseModel extends ResponseResult implements Serializable {


    /**
     * contact : 18911607218
     * nickname : Admiral
     * account :
     * qq : 290887654
     * email : 290887654@qq.com
     * headpic : http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460554017.jpg
     * authStatus : 审核已通过
     * authNum : 3
     * waitforcash : 3 待付款
     * waitforsend : 0 待发货
     * waitforreceive : 2 待收货
     * aftermarket : 2 售后
     * shopstatus : 2
     * shopname : 小白自营
     * shoplogo : http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1459825334.jpg
     * shopid : 6
     */

    private ResultDataBean ResultData;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable {
        private String contact;
        private String nickname;
        private String account;
        private String qq;
        private String email;
        private String headpic;
        private String authStatus;
        private String authNum;
        private int waitforcash;
        private int waitforsend;
        private int waitforreceive;
        private int aftermarket;
        private String shopstatus;
        private String shopname;
        private String shoplogo;
        private String shopid;

        public String getContact() {
            return contact;
        }

        public void setContact(String contact) {
            this.contact = contact;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public String getAuthStatus() {
            return authStatus;
        }

        public void setAuthStatus(String authStatus) {
            this.authStatus = authStatus;
        }

        public String getAuthNum() {
            return authNum;
        }

        public void setAuthNum(String authNum) {
            this.authNum = authNum;
        }

        public int getWaitforcash() {
            return waitforcash;
        }

        public void setWaitforcash(int waitforcash) {
            this.waitforcash = waitforcash;
        }

        public int getWaitforsend() {
            return waitforsend;
        }

        public void setWaitforsend(int waitforsend) {
            this.waitforsend = waitforsend;
        }

        public int getWaitforreceive() {
            return waitforreceive;
        }

        public void setWaitforreceive(int waitforreceive) {
            this.waitforreceive = waitforreceive;
        }

        public int getAftermarket() {
            return aftermarket;
        }

        public void setAftermarket(int aftermarket) {
            this.aftermarket = aftermarket;
        }

        public String getShopstatus() {
            return shopstatus;
        }

        public void setShopstatus(String shopstatus) {
            this.shopstatus = shopstatus;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getShoplogo() {
            return shoplogo;
        }

        public void setShoplogo(String shoplogo) {
            this.shoplogo = shoplogo;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        @Override
        public String toString() {
            return "ResultDataBean{" +
                    "contact='" + contact + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", account='" + account + '\'' +
                    ", qq='" + qq + '\'' +
                    ", email='" + email + '\'' +
                    ", headpic='" + headpic + '\'' +
                    ", authStatus='" + authStatus + '\'' +
                    ", authNum='" + authNum + '\'' +
                    ", waitforcash=" + waitforcash +
                    ", waitforsend=" + waitforsend +
                    ", waitforreceive=" + waitforreceive +
                    ", aftermarket=" + aftermarket +
                    ", shopstatus='" + shopstatus + '\'' +
                    ", shopname='" + shopname + '\'' +
                    ", shoplogo='" + shoplogo + '\'' +
                    ", shopid='" + shopid + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetUserMessageResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
