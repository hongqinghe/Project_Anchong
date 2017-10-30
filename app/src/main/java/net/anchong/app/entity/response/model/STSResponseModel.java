package net.anchong.app.entity.response.model;

import java.util.List;

/**
 * Created by baishixin on 16/4/6.
 */
public class STSResponseModel {

    /**
     * serverTime : 1459931458
     * ServerNo : 0
     * ResultData : ["{\"status\":200,\"AccessKeyId\":\"STS.KRSXfQyPXVad2oaHJzTe3TD6U\",\"AccessKeySecret\":\"Ewk3YH6wYqDNqB9uhK3FYotEXKKLSp4xnafxVww3TNJh\",\"Expiration\":1459932359,\"SecurityToken\":\"CAESlgMIARKAAQ9G25FdHDC1vz1q6v7ABkQa\\/TKkrflDLZDPqixIVjKqBF+KpaqIXtGhZBEn\\/tR83l\\/5eBRAxhwzxv0eubZ7iQqc74byc5VsTLmP5UyRmy+pHNQArPKhOJSww8rMPncghYF+kB2shj6zS\\/jGkDmv4pYLexkeP6\\/thPiQz65yQ1yLGh1TVFMuS1JTWGZReVBYVmFkMm9hSEp6VGUzVEQ2VSISMzIxNTg4NjU3OTE5ODY4NTI5KgtjbGllbnRfbmFtZTDo0v\\/Vvio6BlJzYU1ENUJaCgExGlUKBUFsbG93Eh8KDEFjdGlvbkVxdWFscxIGQWN0aW9uGgcKBW9zczoqEisKDlJlc291cmNlRXF1YWxzEghSZXNvdXJjZRoPCg1hY3M6b3NzOio6KjoqShAxNTg2NjM2NTg0MTIwNzI0UgUyNjg0MloPQXNzdW1lZFJvbGVVc2VyYABqEjMyMTU4ODY1NzkxOTg2ODUyOXIbYWxpeXVub3NzdG9rZW5nZW5lcmF0b3Jyb2xleJTj7++Z4egC\"}"]
     */

    private String serverTime;
    private String ServerNo;
    private List<String> ResultData;

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

    public List<String> getResultData() {
        return ResultData;
    }

    public void setResultData(List<String> ResultData) {
        this.ResultData = ResultData;
    }
}
