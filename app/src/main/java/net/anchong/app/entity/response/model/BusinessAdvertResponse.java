package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/6/22.
 */
public class BusinessAdvertResponse extends ResponseResult implements Serializable {


    /**
     * pic : [{"ad_code":"http://pic67.nipic.com/file/20150515/12973503_100930685000_2.jpg","ad_name":"business","ad_link":"17"},{"ad_code":"http://pic67.nipic.com/file/20150515/12973503_100930685000_2.jpg","ad_name":"http//www.anchong.net/information/","ad_link":"1"},{"ad_code":"http://pic67.nipic.com/file/20150515/12973503_100930685000_2.jpg","ad_name":"business","ad_link":"18"},{"ad_code":"http://pic67.nipic.com/file/20150515/12973503_100930685000_2.jpg","ad_name":"http//www.anchong.net/information/","ad_link":"1"}]
     * information : {"infor_id":7,"title":"yhjigfds","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1465266102448028.png"}
     * informationurl : http//www.anchong.net/information/
     * recommend : [{"bid":7,"phone":"13718779260","contact":"公茂通","title":"聚朋友垂钓园监控系统招标","content":"输入详细内容本垂钓园为长条型鱼塘，长56米宽12米。办公室3间，餐厅一间，停车场长26*8米。\n看现场上午8点到下午5点","tag":"探测监控","tags":"","created_at":"2016-06-16 14:52:42","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406066.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406144.png"]},{"bid":13,"phone":"13718257801","contact":"小慧","title":"山东省招远市人民法院远程执行视频指挥调度系统招标","content":"一、采购项目名称：招远市人民法院远程执行视频指挥调度系统采购\n二、采购项目编号：SDXY2016货物128\n三、采购项目分包情况：\n包\t货物名称\t供应商资格要求\t预算金额\n1\t招远市人民法院远程执行视频指挥调度系统采购\t（1）在中国境内注册，具有独立法人资格，持有合法的营业执照，营业执照须涵盖本次采购内容；（2）具有建设行政主管部门颁发的建筑智能化工程设计与施工一体化二级及以上资质，或同时具有电子与智能化工程专业承包二级及以上资质和建筑智能化设计专项乙级及以上资质并取得安全生产许可证；（3）具有计算机信息系统集成与服务三级及以上资质；（4）所投液晶拼接大屏和车载智能调度终端需提供设备厂家针对本项目的授权书原件；（5）本项目不接受联合体投标。\t52.51万元\n四、获取招标文件\n1、时间：自公告发布之日至2016年6月13日17时30分(北京时间，法定节假日除外)\n2、地点：山东信一项目管理有限公司(烟台市莱山区山海路117号内7号烟台咨询大厦12楼)\n3、方式：购买或邮寄\n4、售价：人民币300元(如需邮购，另加特快专递费50元)。招标文件售后不退。\n五、递交投标文件时间及地点\n1、时间：2016年6月23日08时30分至2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n六、开标时间及地点\n1、2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n七、联系方式\n1、采购人：招远市人民法院\n地    址：招远市温泉路291号\n联 系 人：王洪江\n电    话：0535-8233543\n2、采购代理机构：山东信一项目管理有限公司\n地    址：烟台市莱山区山海路117号内7号烟台咨询大厦12楼\n联 系 人：王刚\n联系方式：0535-2111955\n注：供应商须携带营业执照及资质证书副本复印件至山东信一项目管理有限公司报名。\n招远市人民法院远程执行视频指挥调度系统招标文件定稿\n发 布 人：山东信一项目管理有限公司","tag":"监控配套","tags":"","created_at":"2016-06-03 17:06:24","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/-1fb8f55d8b0adca7.jpg","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/-1fb8f55d8b0adca7.jpg"]}]
     * recent : [{"ad_code":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311462265978.png","ad_name":"河北市"},{"ad_code":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311462265978.png","ad_name":"南京市"},{"ad_code":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311462265978.png","ad_name":"上海市"},{"ad_code":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311462265978.png","ad_name":"北京市"}]
     * hotproject : [{"bid":13,"phone":"13718257801","contact":"小慧","title":"山东省招远市人民法院远程执行视频指挥调度系统招标","content":"一、采购项目名称：招远市人民法院远程执行视频指挥调度系统采购\n二、采购项目编号：SDXY2016货物128\n三、采购项目分包情况：\n包\t货物名称\t供应商资格要求\t预算金额\n1\t招远市人民法院远程执行视频指挥调度系统采购\t（1）在中国境内注册，具有独立法人资格，持有合法的营业执照，营业执照须涵盖本次采购内容；（2）具有建设行政主管部门颁发的建筑智能化工程设计与施工一体化二级及以上资质，或同时具有电子与智能化工程专业承包二级及以上资质和建筑智能化设计专项乙级及以上资质并取得安全生产许可证；（3）具有计算机信息系统集成与服务三级及以上资质；（4）所投液晶拼接大屏和车载智能调度终端需提供设备厂家针对本项目的授权书原件；（5）本项目不接受联合体投标。\t52.51万元\n四、获取招标文件\n1、时间：自公告发布之日至2016年6月13日17时30分(北京时间，法定节假日除外)\n2、地点：山东信一项目管理有限公司(烟台市莱山区山海路117号内7号烟台咨询大厦12楼)\n3、方式：购买或邮寄\n4、售价：人民币300元(如需邮购，另加特快专递费50元)。招标文件售后不退。\n五、递交投标文件时间及地点\n1、时间：2016年6月23日08时30分至2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n六、开标时间及地点\n1、2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n七、联系方式\n1、采购人：招远市人民法院\n地    址：招远市温泉路291号\n联 系 人：王洪江\n电    话：0535-8233543\n2、采购代理机构：山东信一项目管理有限公司\n地    址：烟台市莱山区山海路117号内7号烟台咨询大厦12楼\n联 系 人：王刚\n联系方式：0535-2111955\n注：供应商须携带营业执照及资质证书副本复印件至山东信一项目管理有限公司报名。\n招远市人民法院远程执行视频指挥调度系统招标文件定稿\n发 布 人：山东信一项目管理有限公司","tag":"监控配套","tags":"","created_at":"2016-06-03 17:06:24","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406066.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406144.png"]},{"bid":13,"phone":"13718257801","contact":"小慧","title":"山东省招远市人民法院远程执行视频指挥调度系统招标","content":"一、采购项目名称：招远市人民法院远程执行视频指挥调度系统采购\n二、采购项目编号：SDXY2016货物128\n三、采购项目分包情况：\n包\t货物名称\t供应商资格要求\t预算金额\n1\t招远市人民法院远程执行视频指挥调度系统采购\t（1）在中国境内注册，具有独立法人资格，持有合法的营业执照，营业执照须涵盖本次采购内容；（2）具有建设行政主管部门颁发的建筑智能化工程设计与施工一体化二级及以上资质，或同时具有电子与智能化工程专业承包二级及以上资质和建筑智能化设计专项乙级及以上资质并取得安全生产许可证；（3）具有计算机信息系统集成与服务三级及以上资质；（4）所投液晶拼接大屏和车载智能调度终端需提供设备厂家针对本项目的授权书原件；（5）本项目不接受联合体投标。\t52.51万元\n四、获取招标文件\n1、时间：自公告发布之日至2016年6月13日17时30分(北京时间，法定节假日除外)\n2、地点：山东信一项目管理有限公司(烟台市莱山区山海路117号内7号烟台咨询大厦12楼)\n3、方式：购买或邮寄\n4、售价：人民币300元(如需邮购，另加特快专递费50元)。招标文件售后不退。\n五、递交投标文件时间及地点\n1、时间：2016年6月23日08时30分至2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n六、开标时间及地点\n1、2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n七、联系方式\n1、采购人：招远市人民法院\n地    址：招远市温泉路291号\n联 系 人：王洪江\n电    话：0535-8233543\n2、采购代理机构：山东信一项目管理有限公司\n地    址：烟台市莱山区山海路117号内7号烟台咨询大厦12楼\n联 系 人：王刚\n联系方式：0535-2111955\n注：供应商须携带营业执照及资质证书副本复印件至山东信一项目管理有限公司报名。\n招远市人民法院远程执行视频指挥调度系统招标文件定稿\n发 布 人：山东信一项目管理有限公司","tag":"监控配套","tags":"","created_at":"2016-06-03 17:06:24","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1465867054958062.png"]},{"bid":13,"phone":"13718257801","contact":"小慧","title":"山东省招远市人民法院远程执行视频指挥调度系统招标","content":"一、采购项目名称：招远市人民法院远程执行视频指挥调度系统采购\n二、采购项目编号：SDXY2016货物128\n三、采购项目分包情况：\n包\t货物名称\t供应商资格要求\t预算金额\n1\t招远市人民法院远程执行视频指挥调度系统采购\t（1）在中国境内注册，具有独立法人资格，持有合法的营业执照，营业执照须涵盖本次采购内容；（2）具有建设行政主管部门颁发的建筑智能化工程设计与施工一体化二级及以上资质，或同时具有电子与智能化工程专业承包二级及以上资质和建筑智能化设计专项乙级及以上资质并取得安全生产许可证；（3）具有计算机信息系统集成与服务三级及以上资质；（4）所投液晶拼接大屏和车载智能调度终端需提供设备厂家针对本项目的授权书原件；（5）本项目不接受联合体投标。\t52.51万元\n四、获取招标文件\n1、时间：自公告发布之日至2016年6月13日17时30分(北京时间，法定节假日除外)\n2、地点：山东信一项目管理有限公司(烟台市莱山区山海路117号内7号烟台咨询大厦12楼)\n3、方式：购买或邮寄\n4、售价：人民币300元(如需邮购，另加特快专递费50元)。招标文件售后不退。\n五、递交投标文件时间及地点\n1、时间：2016年6月23日08时30分至2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n六、开标时间及地点\n1、2016年6月23日09时30分(北京时间);\n2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。\n七、联系方式\n1、采购人：招远市人民法院\n地    址：招远市温泉路291号\n联 系 人：王洪江\n电    话：0535-8233543\n2、采购代理机构：山东信一项目管理有限公司\n地    址：烟台市莱山区山海路117号内7号烟台咨询大厦12楼\n联 系 人：王刚\n联系方式：0535-2111955\n注：供应商须携带营业执照及资质证书副本复印件至山东信一项目管理有限公司报名。\n招远市人民法院远程执行视频指挥调度系统招标文件定稿\n发 布 人：山东信一项目管理有限公司","tag":"监控配套","tags":"","created_at":"2016-06-03 17:06:24","pic":["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/351465350563.png"]}]
     * showphone : 1
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        /**
         * infor_id : 7
         * title : yhjigfds
         * img : http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1465266102448028.png
         */

        private InformationEntity information;
        private String informationurl;
        private int showphone;
        /**
         * ad_code : http://pic67.nipic.com/file/20150515/12973503_100930685000_2.jpg
         * ad_name : business
         * ad_link : 17
         */

        private List<PicEntity> pic;
        /**
         * bid : 7
         * phone : 13718779260
         * contact : 公茂通
         * title : 聚朋友垂钓园监控系统招标
         * content : 输入详细内容本垂钓园为长条型鱼塘，长56米宽12米。办公室3间，餐厅一间，停车场长26*8米。
         看现场上午8点到下午5点
         * tag : 探测监控
         * tags :
         * created_at : 2016-06-16 14:52:42
         * pic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406066.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406144.png"]
         */

        private List<RecommendEntity> recommend;
        /**
         * ad_code : http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/311462265978.png
         * ad_name : 河北市
         */

        private List<RecentEntity> recent;
        /**
         * bid : 13
         * phone : 13718257801
         * contact : 小慧
         * title : 山东省招远市人民法院远程执行视频指挥调度系统招标
         * content : 一、采购项目名称：招远市人民法院远程执行视频指挥调度系统采购
         二、采购项目编号：SDXY2016货物128
         三、采购项目分包情况：
         包	货物名称	供应商资格要求	预算金额
         1	招远市人民法院远程执行视频指挥调度系统采购	（1）在中国境内注册，具有独立法人资格，持有合法的营业执照，营业执照须涵盖本次采购内容；（2）具有建设行政主管部门颁发的建筑智能化工程设计与施工一体化二级及以上资质，或同时具有电子与智能化工程专业承包二级及以上资质和建筑智能化设计专项乙级及以上资质并取得安全生产许可证；（3）具有计算机信息系统集成与服务三级及以上资质；（4）所投液晶拼接大屏和车载智能调度终端需提供设备厂家针对本项目的授权书原件；（5）本项目不接受联合体投标。	52.51万元
         四、获取招标文件
         1、时间：自公告发布之日至2016年6月13日17时30分(北京时间，法定节假日除外)
         2、地点：山东信一项目管理有限公司(烟台市莱山区山海路117号内7号烟台咨询大厦12楼)
         3、方式：购买或邮寄
         4、售价：人民币300元(如需邮购，另加特快专递费50元)。招标文件售后不退。
         五、递交投标文件时间及地点
         1、时间：2016年6月23日08时30分至2016年6月23日09时30分(北京时间);
         2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。
         六、开标时间及地点
         1、2016年6月23日09时30分(北京时间);
         2、地点：招远市政府采购大厅(初山路1号，招远市住建局院内1楼)。
         七、联系方式
         1、采购人：招远市人民法院
         地    址：招远市温泉路291号
         联 系 人：王洪江
         电    话：0535-8233543
         2、采购代理机构：山东信一项目管理有限公司
         地    址：烟台市莱山区山海路117号内7号烟台咨询大厦12楼
         联 系 人：王刚
         联系方式：0535-2111955
         注：供应商须携带营业执照及资质证书副本复印件至山东信一项目管理有限公司报名。
         招远市人民法院远程执行视频指挥调度系统招标文件定稿
         发 布 人：山东信一项目管理有限公司
         * tag : 监控配套
         * tags :
         * created_at : 2016-06-03 17:06:24
         * pic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406066.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/331463406144.png"]
         */

        private List<HotprojectEntity> hotproject;

        public InformationEntity getInformation() {
            return information;
        }

        public void setInformation(InformationEntity information) {
            this.information = information;
        }

        public String getInformationurl() {
            return informationurl;
        }

        public void setInformationurl(String informationurl) {
            this.informationurl = informationurl;
        }

        public int getShowphone() {
            return showphone;
        }

        public void setShowphone(int showphone) {
            this.showphone = showphone;
        }

        public List<PicEntity> getPic() {
            return pic;
        }

        public void setPic(List<PicEntity> pic) {
            this.pic = pic;
        }

        public List<RecommendEntity> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendEntity> recommend) {
            this.recommend = recommend;
        }

        public List<RecentEntity> getRecent() {
            return recent;
        }

        public void setRecent(List<RecentEntity> recent) {
            this.recent = recent;
        }

        public List<HotprojectEntity> getHotproject() {
            return hotproject;
        }

        public void setHotproject(List<HotprojectEntity> hotproject) {
            this.hotproject = hotproject;
        }

        public static class InformationEntity {
            private int infor_id;
            private String title;
            private String img;

            public int getInfor_id() {
                return infor_id;
            }

            public void setInfor_id(int infor_id) {
                this.infor_id = infor_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            @Override
            public String toString() {
                return "InformationEntity{" +
                        "infor_id=" + infor_id +
                        ", title='" + title + '\'' +
                        ", img='" + img + '\'' +
                        '}';
            }
        }

        public static class PicEntity {
            private String ad_code;
            private String ad_name;
            private String ad_link;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }

            public String getAd_name() {
                return ad_name;
            }

            public void setAd_name(String ad_name) {
                this.ad_name = ad_name;
            }

            public String getAd_link() {
                return ad_link;
            }

            public void setAd_link(String ad_link) {
                this.ad_link = ad_link;
            }

            @Override
            public String toString() {
                return "PicEntity{" +
                        "ad_code='" + ad_code + '\'' +
                        ", ad_name='" + ad_name + '\'' +
                        ", ad_link='" + ad_link + '\'' +
                        '}';
            }
        }

        public static class RecommendEntity {
            private int bid;
            private String phone;
            private String contact;
            private String title;
            private String content;
            private String tag;
            private String tags;
            private String created_at;
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

            public List<String> getPic() {
                return pic;
            }

            public void setPic(List<String> pic) {
                this.pic = pic;
            }

            @Override
            public String toString() {
                return "RecommendEntity{" +
                        "bid=" + bid +
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

        public static class RecentEntity {
            private String ad_code;
            private String ad_name;

            public String getAd_code() {
                return ad_code;
            }

            public void setAd_code(String ad_code) {
                this.ad_code = ad_code;
            }

            public String getAd_name() {
                return ad_name;
            }

            public void setAd_name(String ad_name) {
                this.ad_name = ad_name;
            }

            @Override
            public String toString() {
                return "RecentEntity{" +
                        "ad_code='" + ad_code + '\'' +
                        ", ad_name='" + ad_name + '\'' +
                        '}';
            }
        }

        public static class HotprojectEntity {
            private int bid;
            private String phone;
            private String contact;
            private String title;
            private String content;
            private String tag;
            private String tags;
            private String created_at;
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

            public List<String> getPic() {
                return pic;
            }

            public void setPic(List<String> pic) {
                this.pic = pic;
            }

            @Override
            public String toString() {
                return "HotprojectEntity{" +
                        "bid=" + bid +
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
                    "information=" + information +
                    ", informationurl='" + informationurl + '\'' +
                    ", showphone=" + showphone +
                    ", pic=" + pic +
                    ", recommend=" + recommend +
                    ", recent=" + recent +
                    ", hotproject=" + hotproject +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BusinessAdvertResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
