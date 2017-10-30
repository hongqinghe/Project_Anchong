package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/4/11.
 */
public class FaBaoResponseModel extends ResponseResult implements Serializable {


    /**
     * ResultData : {"total":2,"list":[{"bid":"116","phone":"18911607218","contact":"18911607218","title":"测试数据","content":"东北三省测试数据","tag":"北京市","tags":"黑龙江 吉林 沈阳","created_at":"2016-04-11 16:16:28","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460362573.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460362585.png"]},{"bid":"66","phone":"18911607218","contact":"18911607218","title":"水表维修","content":"家里水表坏了。请人修理。","tag":"北京市","tags":"北京 上海 深圳","created_at":"2016-04-11 14:07:52","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460354769.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460354781.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460354801.png"]}]}
     */

    /**
     * total : 2
     * list : [{"bid":"116","phone":"18911607218","contact":"18911607218","title":"测试数据","content":"东北三省测试数据","tag":"北京市","tags":"黑龙江 吉林 沈阳","created_at":"2016-04-11 16:16:28","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460362573.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460362585.png"]},{"bid":"66","phone":"18911607218","contact":"18911607218","title":"水表维修","content":"家里水表坏了。请人修理。","tag":"北京市","tags":"北京 上海 深圳","created_at":"2016-04-11 14:07:52","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460354769.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460354781.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460354801.png"]}]
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable {
        private int total;
        /**
         * bid : 116
         * phone : 18911607218
         * contact : 18911607218
         * title : 测试数据
         * content : 东北三省测试数据
         * tag : 北京市
         * tags : 黑龙江 吉林 沈阳
         * created_at : 2016-04-11 16:16:28
         * pic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460362573.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/291460362585.png"]
         */

        private List<ListEntity> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity implements Serializable {
            private String bid;
            private String phone;
            private String contact;
            private String title;
            private String content;
            private String tag;
            private String tags;
            private String created_at;
            private List<String> pic;

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
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

            public List<String> getPic() {
                return pic;
            }

            public void setPic(List<String> pic) {
                this.pic = pic;
            }

            @Override
            public String toString() {
                return "ListEntity{" +
                        "bid='" + bid + '\'' +
                        ", phone='" + phone + '\'' +
                        ", contact='" + contact + '\'' +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", tag='" + tag + '\'' +
                        ", tags='" + tags + '\'' +
                        ", created_at='" + created_at + '\'' +
                        ", pic=" + pic +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "total=" + total +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "FaBaoResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
