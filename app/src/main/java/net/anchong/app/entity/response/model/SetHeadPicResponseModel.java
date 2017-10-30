package net.anchong.app.entity.response.model;

/**
 * Created by baishixin on 16/3/30.
 */
public class SetHeadPicResponseModel {

    /**
     * serverTime : 1459325239
     * ServerNo : 0
     * ResultData : {"isSuccesss":0,"Message":"上传成功","headPicUrl":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1459325239.jpg"}
     */

    private String serverTime;
    private String ServerNo;
    /**
     * isSuccesss : 0
     * Message : 上传成功
     * headPicUrl : http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1459325239.jpg
     */

    private ResultDataBean ResultData;

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

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean {
        private String isSuccesss;
        private String Message;
        private String headPicUrl;

        public String getIsSuccesss() {
            return isSuccesss;
        }

        public void setIsSuccesss(String isSuccesss) {
            this.isSuccesss = isSuccesss;
        }

        public String getMessage() {
            return Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }

        public String getHeadPicUrl() {
            return headPicUrl;
        }

        public void setHeadPicUrl(String headPicUrl) {
            this.headPicUrl = headPicUrl;
        }
    }
}
