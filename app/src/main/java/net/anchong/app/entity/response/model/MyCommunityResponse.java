package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 个人聊聊查看结果
 * Created by baishixin on 16/6/17.
 */
public class MyCommunityResponse extends ResponseResult implements Serializable{


    /**
     * ResultData : {"total":3,"list":[{"chat_id":48,"title":"爱orz破傻X","name":"zz","content":"sex1破哦亲XP颜色坡起XP","created_at":"2016-06-16 16:21:04","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451466065260.png"]},{"chat_id":47,"title":"名字","name":"zz","content":"goes心上自习","created_at":"2016-06-16 16:20:31","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451466065217.png"]},{"chat_id":46,"title":"给几分？","name":"zz","content":"添加正文:如题如题！！","created_at":"2016-06-16 15:31:52","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451466062283.png"]}]}
     */

    /**
     * total : 3
     * list : [{"chat_id":48,"title":"爱orz破傻X","name":"zz","content":"sex1破哦亲XP颜色坡起XP","created_at":"2016-06-16 16:21:04","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451466065260.png"]},{"chat_id":47,"title":"名字","name":"zz","content":"goes心上自习","created_at":"2016-06-16 16:20:31","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451466065217.png"]},{"chat_id":46,"title":"给几分？","name":"zz","content":"添加正文:如题如题！！","created_at":"2016-06-16 15:31:52","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451466062283.png"]}]
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        private int total;
        /**
         * chat_id : 48
         * title : 爱orz破傻X
         * name : zz
         * content : sex1破哦亲XP颜色坡起XP
         * created_at : 2016-06-16 16:21:04
         * tags : 活动
         * headpic : http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg
         * comnum : 1
         * pic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451466065260.png"]
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

        public static class ListEntity {
            private int chat_id;
            private String title;
            private String name;
            private String content;
            private String created_at;
            private String tags;
            private String headpic;
            private String comnum;
            private List<String> pic;

            public int getChat_id() {
                return chat_id;
            }

            public void setChat_id(int chat_id) {
                this.chat_id = chat_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getTags() {
                return tags;
            }

            public void setTags(String tags) {
                this.tags = tags;
            }

            public String getHeadpic() {
                return headpic;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
            }

            public String getComnum() {
                return comnum;
            }

            public void setComnum(String comnum) {
                this.comnum = comnum;
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
                        "chat_id=" + chat_id +
                        ", title='" + title + '\'' +
                        ", name='" + name + '\'' +
                        ", content='" + content + '\'' +
                        ", created_at='" + created_at + '\'' +
                        ", tags='" + tags + '\'' +
                        ", headpic='" + headpic + '\'' +
                        ", comnum='" + comnum + '\'' +
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
        return "MyCommunityResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
