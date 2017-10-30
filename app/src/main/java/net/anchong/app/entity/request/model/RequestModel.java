package net.anchong.app.entity.request.model;


/**
 * Created by baishixin on 16/2/24.
 */
public class RequestModel {

    private String signature;//签名验证
    private String time;//当前系统毫秒值
    private String version;//当前版本现在是1.0
    private String guid;//用户id没有时为0
    private ParamModel param;

    @Override
    public String toString() {
        return "IndexRequestModel{" +
                "time='" + time + '\'' +
                ", version='" + version + '\'' +
                ", guid='" + guid + '\'' +
                ", param=" + param +
                '}';
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public ParamModel getParam() {
        return param;
    }

    public void setParam(ParamModel param) {
        this.param = param;
    }

    public RequestModel(String time, String version, String guid, ParamModel param) {

        this.time = time;
        this.version = version;
        this.guid = guid;
        this.param = param;
    }

    public RequestModel() {

    }
}
