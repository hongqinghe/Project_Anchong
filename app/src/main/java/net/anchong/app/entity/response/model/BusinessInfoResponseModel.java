package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 商机查看结果处理Model
 * Created by baishixin on 16/4/18.
 */
public class BusinessInfoResponseModel extends ResponseResult implements Serializable {


    /**
     * ResultData : {"total":79,"showphone":1,"list":[{"bid":"156","phone":"18911607218","contact":"18911607218","title":"小白11","content":"还是睡觉额几点呢可么么","tag":"探测监控","tags":"","created_at":"2016-04-18 08:38:04","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162856_-917744109.jpg"]},{"bid":"157","phone":"18911607218","contact":"18911607218","title":"该睡觉阿克苏办法","content":"啥话日单拔牙回复你搜神记嘴巴吃奶是吃","tag":"探测监控","tags":"北京 北京","created_at":"2016-04-15 14:43:35","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160415_144301_-917744109.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162810_234648979.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162635_-1916352701.jpg"]},{"bid":"43","phone":"2147483647","contact":"范骏测试","title":"woshibiaoqian","content":"Wishi Bering ","tag":"上海市","tags":"","created_at":"2016-04-15 11:03:27","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460006502.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460006509.png"]},{"bid":"97","phone":"18600818638","contact":"范骏","title":"Cheshideshuju ","content":"Women's hi ceshishuju规划局个","tag":"上海市","tags":"Beijing Shanghai","created_at":"2016-04-14 22:40:38","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460358761.png"]},{"bid":"155","phone":"18911607218","contact":"18911607218","title":"小白10","content":"富贵哦金夫人瑞","tag":"探测监控","tags":"","created_at":"2016-04-14 16:28:34","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162831_-307616433.jpg"]},{"bid":"154","phone":"18911607218","contact":"18911607218","title":"小白9","content":"过会太热晚上下班姐姐","tag":"探测监控","tags":"","created_at":"2016-04-14 16:28:13","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223931_-2073264327.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162810_234648979.jpg"]},{"bid":"153","phone":"18911607218","contact":"18911607218","title":"小白8","content":"出一个b考几分你看看","tag":"探测监控","tags":"","created_at":"2016-04-14 16:26:45","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162635_-1916352701.jpg"]},{"bid":"152","phone":"18911607218","contact":"18911607218","title":"小白7","content":"放个假句衣服干活呢","tag":"探测监控","tags":"","created_at":"2016-04-14 16:26:09","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_212447_-307616433.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162606_-917744109.jpg"]},{"bid":"151","phone":"18911607218","contact":"18911607218","title":"小白6","content":"查干湖看不出听不","tag":"探测监控","tags":"","created_at":"2016-04-14 16:25:40","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223931_-2073264327.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162537_234648979.jpg"]},{"bid":"150","phone":"18911607218","contact":"18911607218","title":"小白5","content":"v几个斗牛特价机女性","tag":"探测监控","tags":"","created_at":"2016-04-14 16:25:14","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223830_-943356385.jpg"]},{"bid":"149","phone":"18911607218","contact":"18911607218","title":"小白4","content":"vv话费是u有他对象差不多推荐个电梯减肥","tag":"探测监控","tags":"方法","created_at":"2016-04-14 16:23:44","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223931_-2073264327.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162339_-917744109.jpg"]},{"bid":"148","phone":"18911607218","contact":"18911607218","title":"小白3","content":"广告费都上课哭犹太人东西吃","tag":"探测监控","tags":"","created_at":"2016-04-14 16:09:51","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_160946_-307616433.jpg"]},{"bid":"147","phone":"18911607218","contact":"18911607218","title":"小白2","content":"耍手机啊饥饿抗日无污染天涯","tag":"探测监控","tags":"到底","created_at":"2016-04-14 16:09:01","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_160835_234648979.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224035_350786490.jpg"]},{"bid":"146","phone":"18911607218","contact":"18911607218","title":"小白1","content":"实话实说实话实说倒计时就是说","tag":"探测监控","tags":"北京 上海","created_at":"2016-04-14 16:06:47","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224035_350786490.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_160635_-307616433.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223823_1132080622.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_212612_-492160413.jpg"]},{"bid":"138","phone":"18911607218","contact":"18911607218","title":"柔柔弱弱ddddewqe","content":"古古怪怪古古怪怪古古怪怪嘎嘎嘎嘎嘎嘎顾","tag":"探测监控","tags":"让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法","created_at":"2016-04-14 15:41:40","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224026_-1455113649.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224035_350786490.jpg"]},{"bid":"132","phone":"18911607218","contact":"18911607218","title":"额呃呃呃","content":"本就我发了咯醉咯","tag":"弱店工程","tags":"","created_at":"2016-04-14 15:39:51","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_155634_444269728.jpg"]},{"bid":"120","phone":"17777838278","contact":"范小二","title":"呼救器","content":"呼救器测试数据","tag":"呼救器","tags":"莆田 大庆 哈尔滨 天津 廊坊","created_at":"2016-04-14 15:03:45","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437265.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437280.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437295.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437314.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437347.png"]},{"bid":"145","phone":"17777838278","contact":"范小二","title":"是好多好多好多话","content":"都觉得大家都比较大的季节","tag":"探测监控\n","tags":"北京市","created_at":"2016-04-14 15:03:41","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460615278.png"]},{"bid":"139","phone":"18600818638","contact":"范骏","title":"发布交通设施","content":"我要发布交通设施测试工程信息","tag":"交通设施","tags":"河北 衡水 沈阳","created_at":"2016-04-14 11:45:58","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599768.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599780.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599795.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599808.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599821.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599833.png"]},{"bid":"123","phone":"18502702013","contact":"jack sir","title":"工程机械","content":"咩名议员刘俊刻薄的人是你自己都不知道是不是因为太阳一个人家都市全部的确有点小时了。我们的爱上你的时候你们还有没有理由的话。我的心","tag":"探测报警\n","tags":"北京市 呼和浩特 大连","created_at":"2016-04-14 10:26:48","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311460452371.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311460452384.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311460527551.png"]}]}
     */

    /**
     * total : 79
     * showphone : 1  (该字段判断是否显示手机号码，0(未登录或未通过审核)1(登录并通过审核))
     * list : [{"bid":"156","phone":"18911607218","contact":"18911607218","title":"小白11","content":"还是睡觉额几点呢可么么","tag":"探测监控","tags":"","created_at":"2016-04-18 08:38:04","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162856_-917744109.jpg"]},{"bid":"157","phone":"18911607218","contact":"18911607218","title":"该睡觉阿克苏办法","content":"啥话日单拔牙回复你搜神记嘴巴吃奶是吃","tag":"探测监控","tags":"北京 北京","created_at":"2016-04-15 14:43:35","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160415_144301_-917744109.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162810_234648979.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162635_-1916352701.jpg"]},{"bid":"43","phone":"2147483647","contact":"范骏测试","title":"woshibiaoqian","content":"Wishi Bering ","tag":"上海市","tags":"","created_at":"2016-04-15 11:03:27","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460006502.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460006509.png"]},{"bid":"97","phone":"18600818638","contact":"范骏","title":"Cheshideshuju ","content":"Women's hi ceshishuju规划局个","tag":"上海市","tags":"Beijing Shanghai","created_at":"2016-04-14 22:40:38","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460358761.png"]},{"bid":"155","phone":"18911607218","contact":"18911607218","title":"小白10","content":"富贵哦金夫人瑞","tag":"探测监控","tags":"","created_at":"2016-04-14 16:28:34","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162831_-307616433.jpg"]},{"bid":"154","phone":"18911607218","contact":"18911607218","title":"小白9","content":"过会太热晚上下班姐姐","tag":"探测监控","tags":"","created_at":"2016-04-14 16:28:13","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223931_-2073264327.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162810_234648979.jpg"]},{"bid":"153","phone":"18911607218","contact":"18911607218","title":"小白8","content":"出一个b考几分你看看","tag":"探测监控","tags":"","created_at":"2016-04-14 16:26:45","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162635_-1916352701.jpg"]},{"bid":"152","phone":"18911607218","contact":"18911607218","title":"小白7","content":"放个假句衣服干活呢","tag":"探测监控","tags":"","created_at":"2016-04-14 16:26:09","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_212447_-307616433.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162606_-917744109.jpg"]},{"bid":"151","phone":"18911607218","contact":"18911607218","title":"小白6","content":"查干湖看不出听不","tag":"探测监控","tags":"","created_at":"2016-04-14 16:25:40","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223931_-2073264327.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162537_234648979.jpg"]},{"bid":"150","phone":"18911607218","contact":"18911607218","title":"小白5","content":"v几个斗牛特价机女性","tag":"探测监控","tags":"","created_at":"2016-04-14 16:25:14","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223830_-943356385.jpg"]},{"bid":"149","phone":"18911607218","contact":"18911607218","title":"小白4","content":"vv话费是u有他对象差不多推荐个电梯减肥","tag":"探测监控","tags":"方法","created_at":"2016-04-14 16:23:44","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223931_-2073264327.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162339_-917744109.jpg"]},{"bid":"148","phone":"18911607218","contact":"18911607218","title":"小白3","content":"广告费都上课哭犹太人东西吃","tag":"探测监控","tags":"","created_at":"2016-04-14 16:09:51","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_160946_-307616433.jpg"]},{"bid":"147","phone":"18911607218","contact":"18911607218","title":"小白2","content":"耍手机啊饥饿抗日无污染天涯","tag":"探测监控","tags":"到底","created_at":"2016-04-14 16:09:01","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_160835_234648979.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224035_350786490.jpg"]},{"bid":"146","phone":"18911607218","contact":"18911607218","title":"小白1","content":"实话实说实话实说倒计时就是说","tag":"探测监控","tags":"北京 上海","created_at":"2016-04-14 16:06:47","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224035_350786490.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_160635_-307616433.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_223823_1132080622.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_212612_-492160413.jpg"]},{"bid":"138","phone":"18911607218","contact":"18911607218","title":"柔柔弱弱ddddewqe","content":"古古怪怪古古怪怪古古怪怪嘎嘎嘎嘎嘎嘎顾","tag":"探测监控","tags":"让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法 让人 方法","created_at":"2016-04-14 15:41:40","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224026_-1455113649.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_224035_350786490.jpg"]},{"bid":"132","phone":"18911607218","contact":"18911607218","title":"额呃呃呃","content":"本就我发了咯醉咯","tag":"弱店工程","tags":"","created_at":"2016-04-14 15:39:51","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160413_155634_444269728.jpg"]},{"bid":"120","phone":"17777838278","contact":"范小二","title":"呼救器","content":"呼救器测试数据","tag":"呼救器","tags":"莆田 大庆 哈尔滨 天津 廊坊","created_at":"2016-04-14 15:03:45","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437265.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437280.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437295.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437314.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460437347.png"]},{"bid":"145","phone":"17777838278","contact":"范小二","title":"是好多好多好多话","content":"都觉得大家都比较大的季节","tag":"探测监控\n","tags":"北京市","created_at":"2016-04-14 15:03:41","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/421460615278.png"]},{"bid":"139","phone":"18600818638","contact":"范骏","title":"发布交通设施","content":"我要发布交通设施测试工程信息","tag":"交通设施","tags":"河北 衡水 沈阳","created_at":"2016-04-14 11:45:58","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599768.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599780.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599795.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599808.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599821.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351460599833.png"]},{"bid":"123","phone":"18502702013","contact":"jack sir","title":"工程机械","content":"咩名议员刘俊刻薄的人是你自己都不知道是不是因为太阳一个人家都市全部的确有点小时了。我们的爱上你的时候你们还有没有理由的话。我的心","tag":"探测报警\n","tags":"北京市 呼和浩特 大连","created_at":"2016-04-14 10:26:48","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311460452371.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311460452384.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311460527551.png"]}]
     */

    private ResultDataBean ResultData;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable {
        private int total;
        private int showphone;
        /**
         * bid : 156
         * phone : 18911607218
         * contact : 18911607218
         * title : 小白11
         * content : 还是睡觉额几点呢可么么
         * tag : 探测监控
         * tags :
         * created_at : 2016-04-18 08:38:04
         * pic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/JPEG_20160414_162856_-917744109.jpg"]
         */

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getShowphone() {
            return showphone;
        }

        public void setShowphone(int showphone) {
            this.showphone = showphone;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable {
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
                return "ListBean{" +
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
            return "ResultDataBean{" +
                    "total=" + total +
                    ", showphone=" + showphone +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BusinessInfoResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
