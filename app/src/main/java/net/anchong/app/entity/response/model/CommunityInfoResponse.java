package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 聊聊详情的请求结果
 * Created by baishixin on 16/6/14.
 */
public class CommunityInfoResponse extends ResponseResult implements Serializable{


    /**
     * ResultData : {"collresult":0,"chat_id":40,"headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","name":"范jun","created_at":"2016-06-12 10:19:29","title":"兄弟们估个价吧","tags":"问问","content":"添加正文：媳妇刚给买的，兄弟们帮忙估估价。","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697853.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697869.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697881.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697895.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697908.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697920.png"]}
     */

    /**
     * collresult : 0
     * chat_id : 40
     * headpic : http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg
     * name : 范jun
     * created_at : 2016-06-12 10:19:29
     * title : 兄弟们估个价吧
     * tags : 问问
     * content : 添加正文：媳妇刚给买的，兄弟们帮忙估估价。
     * pic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697853.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697869.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697881.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697895.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697908.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697920.png"]
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        private int collresult;
        private int chat_id;
        private String headpic;
        private String name;
        private String created_at;
        private String title;
        private String tags;
        private String content;
        private List<String> pic;

        public int getCollresult() {
            return collresult;
        }

        public void setCollresult(int collresult) {
            this.collresult = collresult;
        }

        public int getChat_id() {
            return chat_id;
        }

        public void setChat_id(int chat_id) {
            this.chat_id = chat_id;
        }

        public String getHeadpic() {
            return headpic;
        }

        public void setHeadpic(String headpic) {
            this.headpic = headpic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<String> getPic() {
            return pic;
        }

        public void setPic(List<String> pic) {
            this.pic = pic;
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "collresult=" + collresult +
                    ", chat_id=" + chat_id +
                    ", headpic='" + headpic + '\'' +
                    ", name='" + name + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", title='" + title + '\'' +
                    ", tags='" + tags + '\'' +
                    ", content='" + content + '\'' +
                    ", pic=" + pic +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CommunityInfoResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
