package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 聊聊回复结果
 * Created by baishixin on 16/6/14.
 */
public class CommunityComResponse extends ResponseResult implements Serializable {


    /**
     * ResultData : {"total":2,"list":[{"comid":47,"name":"叶子","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","content":"我也很想看呢","created_at":"2016-06-06 09:44:10","reply":[{"reid":56,"name":"zz","content":"好的朋友们一起分享吧！","comname":"叶子"},{"reid":43,"name":"sdave","content":"me too!","comname":"叶子"}]},{"comid":46,"name":"叶子","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","content":"约起来","created_at":"2016-06-06 09:44:04","reply":[{"reid":57,"name":"zz","content":"健健康康","comname":"叶子"},{"reid":42,"name":"sdave","content":"nice ","comname":"叶子"}]}]}
     */

    /**
     * total : 2
     * list : [{"comid":47,"name":"叶子","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","content":"我也很想看呢","created_at":"2016-06-06 09:44:10","reply":[{"reid":56,"name":"zz","content":"好的朋友们一起分享吧！","comname":"叶子"},{"reid":43,"name":"sdave","content":"me too!","comname":"叶子"}]},{"comid":46,"name":"叶子","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","content":"约起来","created_at":"2016-06-06 09:44:04","reply":[{"reid":57,"name":"zz","content":"健健康康","comname":"叶子"},{"reid":42,"name":"sdave","content":"nice ","comname":"叶子"}]}]
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
         * comid : 47
         * name : 叶子
         * headpic : http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg
         * content : 我也很想看呢
         * created_at : 2016-06-06 09:44:10
         * reply : [{"reid":56,"name":"zz","content":"好的朋友们一起分享吧！","comname":"叶子"},{"reid":43,"name":"sdave","content":"me too!","comname":"叶子"}]
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
            private int comid;
            private String name;
            private String headpic;
            private String content;
            private String created_at;
            /**
             * reid : 56
             * name : zz
             * content : 好的朋友们一起分享吧！
             * comname : 叶子
             */

            private List<ReplyEntity> reply;

            public int getComid() {
                return comid;
            }

            public void setComid(int comid) {
                this.comid = comid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getHeadpic() {
                return headpic;
            }

            public void setHeadpic(String headpic) {
                this.headpic = headpic;
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

            public List<ReplyEntity> getReply() {
                return reply;
            }

            public void setReply(List<ReplyEntity> reply) {
                this.reply = reply;
            }

            public static class ReplyEntity {
                private int reid;
                private String name;
                private String content;
                private String comname;

                public int getReid() {
                    return reid;
                }

                public void setReid(int reid) {
                    this.reid = reid;
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

                public String getComname() {
                    return comname;
                }

                public void setComname(String comname) {
                    this.comname = comname;
                }

                @Override
                public String toString() {
                    return "ReplyEntity{" +
                            "reid=" + reid +
                            ", name='" + name + '\'' +
                            ", content='" + content + '\'' +
                            ", comname='" + comname + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ListEntity{" +
                        "comid=" + comid +
                        ", name='" + name + '\'' +
                        ", headpic='" + headpic + '\'' +
                        ", content='" + content + '\'' +
                        ", created_at='" + created_at + '\'' +
                        ", reply=" + reply +
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
        return "CommunityComResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
