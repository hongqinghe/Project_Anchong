package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.util.List;

/**
 * Created by baishixin on 16/6/12.
 */
public class CommunityShowResponse extends ResponseResult{


    /**
     * ResultData : {"total":21,"list":[{"chat_id":41,"title":"出租周末","name":"zz","content":"添加正文：出租周末，陪吃陪喝陪睡觉。","created_at":"2016-06-12 10:26:23","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451465698378.png"]},{"chat_id":40,"title":"兄弟们估个价吧","name":"范jun","content":"添加正文：媳妇刚给买的，兄弟们帮忙估估价。","created_at":"2016-06-12 10:19:29","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"4","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697853.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697869.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697881.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697895.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697908.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697920.png"]},{"chat_id":39,"title":"Junely","name":"叶子","content":"打卡，第二天","created_at":"2016-06-08 09:51:52","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/441465350660.png"]},{"chat_id":32,"title":"七月活动","name":"范jun","content":"我是七月活动栏...","created_at":"2016-06-07 11:47:38","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"1"},{"chat_id":31,"title":"七月活动","name":"范jun","content":"啊啊啊啊啊啊啊啊啊啊啊啊","created_at":"2016-06-07 11:47:10","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"},{"chat_id":30,"title":"发布闲聊...","name":"范jun","content":"我是测试...","created_at":"2016-06-07 11:45:37","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465271129.png"]},{"chat_id":29,"title":"发布闲聊...","name":"范jun","content":"今天天气很好，但是需要做的事情太多。搞起...","created_at":"2016-06-07 11:39:49","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270744.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270756.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270765.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270772.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270783.png"]},{"chat_id":28,"title":"发布活动","name":"范jun","content":"庆祝安虫app测试版即将上线...关注七月安虫官方动态！","created_at":"2016-06-06 15:42:34","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"4","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465198917.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465198938.png"]},{"chat_id":27,"title":"333333","name":"sdave","content":"添加正文:3333333333333","created_at":"2016-06-06 14:52:55","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460351910.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311465195968.png"]},{"chat_id":26,"title":"22222222","name":"sdave","content":"添加正文:222222222222","created_at":"2016-06-06 14:52:31","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460351910.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311465195947.png"]},{"chat_id":25,"title":"11111111","name":"sdave","content":"添加正文:111111111111","created_at":"2016-06-06 14:52:10","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460351910.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311465195927.png"]},{"chat_id":18,"title":"美院学生活动星期天活动","name":"叶子","content":"一年一度的毕业季，美院学生的毕业展怎能不去看？想去的小伙伴们到这里报名～一起噻","created_at":"2016-06-06 09:23:17","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/441465176076.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/441465176193.png"]},{"chat_id":17,"title":"今天天气挺好","name":"范jun","content":"去哪玩啊\u2026\u2026小活动...","created_at":"2016-06-05 14:47:41","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465109204.png"]},{"chat_id":16,"title":"今天晚上吃啥","name":"范jun","content":"添加正文:韩老师，今天晚上你又去旁边的夜店吃了？","created_at":"2016-06-04 17:38:24","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465033098.png"]},{"chat_id":14,"title":"今天晚上吃什么","name":"范jun","content":"伙计们，今天晚上吃点什么？","created_at":"2016-06-03 17:37:07","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351464946618.png"]},{"chat_id":33,"title":"晚上能吃西瓜吗？","name":"admin","content":"<p style=\"text-align: center;\">我很想吃西瓜啊<\/p><p style=\"text-align: center;\"><img src=\"/uploads/ueditor/php/upload/image/20160607/1465282781177048.png\" title=\"1465282781177048.png\" alt=\"6355684091506214044070489.png\"/><\/p>","created_at":null,"tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460554017.jpg","comnum":"1"},{"chat_id":34,"title":"国民指纹手机再升级 中兴Blade A2评测","name":"admin","content":"<p><img src=\"/uploads/ueditor/php/upload/image/20160607/1465283313477350.png\" title=\"1465283313477350.png\" alt=\"laravel5.1.png\"/><\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460554017.jpg","comnum":"1"},{"chat_id":35,"title":"后台测试闲聊发布","name":"18600818638","content":"<p>今天发布闲聊后台测试！<\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"},{"chat_id":36,"title":"后台测试闲聊发布","name":"18600818638","content":"<p>今天发布闲聊后台测试！<\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"},{"chat_id":37,"title":"后台测试闲聊发布","name":"18600818638","content":"<p>今天发布闲聊后台测试！<\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"}]}
     */

    /**
     * total : 21
     * list : [{"chat_id":41,"title":"出租周末","name":"zz","content":"添加正文：出租周末，陪吃陪喝陪睡觉。","created_at":"2016-06-12 10:26:23","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451465698378.png"]},{"chat_id":40,"title":"兄弟们估个价吧","name":"范jun","content":"添加正文：媳妇刚给买的，兄弟们帮忙估估价。","created_at":"2016-06-12 10:19:29","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"4","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697853.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697869.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697881.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697895.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697908.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465697920.png"]},{"chat_id":39,"title":"Junely","name":"叶子","content":"打卡，第二天","created_at":"2016-06-08 09:51:52","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/441465350660.png"]},{"chat_id":32,"title":"七月活动","name":"范jun","content":"我是七月活动栏...","created_at":"2016-06-07 11:47:38","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"1"},{"chat_id":31,"title":"七月活动","name":"范jun","content":"啊啊啊啊啊啊啊啊啊啊啊啊","created_at":"2016-06-07 11:47:10","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"},{"chat_id":30,"title":"发布闲聊...","name":"范jun","content":"我是测试...","created_at":"2016-06-07 11:45:37","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465271129.png"]},{"chat_id":29,"title":"发布闲聊...","name":"范jun","content":"今天天气很好，但是需要做的事情太多。搞起...","created_at":"2016-06-07 11:39:49","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270744.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270756.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270765.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270772.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465270783.png"]},{"chat_id":28,"title":"发布活动","name":"范jun","content":"庆祝安虫app测试版即将上线...关注七月安虫官方动态！","created_at":"2016-06-06 15:42:34","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"4","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465198917.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465198938.png"]},{"chat_id":27,"title":"333333","name":"sdave","content":"添加正文:3333333333333","created_at":"2016-06-06 14:52:55","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460351910.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311465195968.png"]},{"chat_id":26,"title":"22222222","name":"sdave","content":"添加正文:222222222222","created_at":"2016-06-06 14:52:31","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460351910.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311465195947.png"]},{"chat_id":25,"title":"11111111","name":"sdave","content":"添加正文:111111111111","created_at":"2016-06-06 14:52:10","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460351910.jpg","comnum":"0","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311465195927.png"]},{"chat_id":18,"title":"美院学生活动星期天活动","name":"叶子","content":"一年一度的毕业季，美院学生的毕业展怎能不去看？想去的小伙伴们到这里报名～一起噻","created_at":"2016-06-06 09:23:17","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465176020.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/441465176076.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/441465176193.png"]},{"chat_id":17,"title":"今天天气挺好","name":"范jun","content":"去哪玩啊\u2026\u2026小活动...","created_at":"2016-06-05 14:47:41","tags":"活动","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465109204.png"]},{"chat_id":16,"title":"今天晚上吃啥","name":"范jun","content":"添加正文:韩老师，今天晚上你又去旁边的夜店吃了？","created_at":"2016-06-04 17:38:24","tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"1","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465033098.png"]},{"chat_id":14,"title":"今天晚上吃什么","name":"范jun","content":"伙计们，今天晚上吃点什么？","created_at":"2016-06-03 17:37:07","tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1464234450.jpg","comnum":"2","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351464946618.png"]},{"chat_id":33,"title":"晚上能吃西瓜吗？","name":"admin","content":"<p style=\"text-align: center;\">我很想吃西瓜啊<\/p><p style=\"text-align: center;\"><img src=\"/uploads/ueditor/php/upload/image/20160607/1465282781177048.png\" title=\"1465282781177048.png\" alt=\"6355684091506214044070489.png\"/><\/p>","created_at":null,"tags":"问问","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460554017.jpg","comnum":"1"},{"chat_id":34,"title":"国民指纹手机再升级 中兴Blade A2评测","name":"admin","content":"<p><img src=\"/uploads/ueditor/php/upload/image/20160607/1465283313477350.png\" title=\"1465283313477350.png\" alt=\"laravel5.1.png\"/><\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1460554017.jpg","comnum":"1"},{"chat_id":35,"title":"后台测试闲聊发布","name":"18600818638","content":"<p>今天发布闲聊后台测试！<\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"},{"chat_id":36,"title":"后台测试闲聊发布","name":"18600818638","content":"<p>今天发布闲聊后台测试！<\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"},{"chat_id":37,"title":"后台测试闲聊发布","name":"18600818638","content":"<p>今天发布闲聊后台测试！<\/p>","created_at":null,"tags":"闲聊","headpic":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1465200925.jpg","comnum":"0"}]
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
         * chat_id : 41
         * title : 出租周末
         * name : zz
         * content : 添加正文：出租周末，陪吃陪喝陪睡觉。
         * created_at : 2016-06-12 10:26:23
         * tags : 活动
         * headpic : http://anchongres.oss-cn-hangzhou.aliyuncs.com/headpic/1461210864.jpg
         * comnum : 1
         * pic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/451465698378.png"]
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
            private String chat_id;
            private String title;
            private String name;
            private String content;
            private String created_at;
            private String tags;
            private String headpic;
            private String comnum;
            private List<String> pic;

            public String getChat_id() {
                return chat_id;
            }

            public void setChat_id(String chat_id) {
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
                        "chat_id='" + chat_id + '\'' +
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
        return "CommunityShowResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
