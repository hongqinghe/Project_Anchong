package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 商机单个查看结果
 * Created by baishixin on 16/7/15.
 */
public class BusinessIndexResponse extends ResponseResult implements Serializable {


    /**
     * data : {"bid":"该条信息的id","phone":"发布人电话","contact":"发布人姓名","title":"标题","content":"内容","tag":"标签","tags":"用户自定义的标签，返回一个空格隔开的字符串","created_at":"2016-02-24 08:02:50","endtime":"工程结束时间","pic":["图片地址1","图片地址2"]}
     * showphone : 0
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable{
        /**
         * bid : 该条信息的id
         * phone : 发布人电话
         * contact : 发布人姓名
         * title : 标题
         * content : 内容
         * tag : 标签
         * tags : 用户自定义的标签，返回一个空格隔开的字符串
         * created_at : 2016-02-24 08:02:50
         * endtime : 工程结束时间
         * pic : ["图片地址1","图片地址2"]
         */

        private DataEntity data;
        private int showphone;

        public DataEntity getData() {
            return data;
        }

        public void setData(DataEntity data) {
            this.data = data;
        }

        public int getShowphone() {
            return showphone;
        }

        public void setShowphone(int showphone) {
            this.showphone = showphone;
        }

        public static class DataEntity implements Serializable{
            private int bid;
            private String phone;
            private String contact;
            private String title;
            private String content;
            private String tag;
            private String tags;
            private String created_at;
            private String endtime;
            private List<String> pic;

            public int getBid() {
                return bid;
            }

            public void setBid(int bid) {
                this.bid = bid;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getContact() {
                return contact;
            }

            public void setContact(String contact) {
                this.contact = contact;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public List<String> getPic() {
                return pic;
            }

            public void setPic(List<String> pic) {
                this.pic = pic;
            }

            @Override
            public String toString() {
                return "DataEntity{" +
                        "bid='" + bid + '\'' +
                        ", phone='" + phone + '\'' +
                        ", contact='" + contact + '\'' +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", tag='" + tag + '\'' +
                        ", tags='" + tags + '\'' +
                        ", created_at='" + created_at + '\'' +
                        ", endtime='" + endtime + '\'' +
                        ", pic=" + pic +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "data=" + data +
                    ", showphone=" + showphone +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BusinessIndexResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
