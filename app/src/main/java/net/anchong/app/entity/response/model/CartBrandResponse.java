package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 个人商铺主营品牌和主营类别
 * Created by baishixin on 16/5/23.
 */
public class CartBrandResponse extends ResponseResult implements Serializable {


    /**
     * ResultData : {"cat":[{"cat_id":"1","cat_name":"智能门禁","keyword":"智能门禁","cat_desc":"智能门禁","sort_order":"0","is_show":"1","parent_id":"0"},{"cat_id":"2","cat_name":"视频监控","keyword":"视频监控","cat_desc":"视频监控","sort_order":"0","is_show":"1","parent_id":"0"},{"cat_id":"3","cat_name":"探测报警","keyword":"探测报警","cat_desc":"探测报警","sort_order":"0","is_show":"1","parent_id":"0"},{"cat_id":"4","cat_name":"巡更巡检","keyword":"巡更巡检","cat_desc":"巡更巡检","sort_order":"0","is_show":"1","parent_id":"0"},{"cat_id":"5","cat_name":"停车管理","keyword":"停车管理","cat_desc":"停车管理","sort_order":"0","is_show":"1","parent_id":"0"},{"cat_id":"6","cat_name":"楼宇对讲","keyword":"楼宇对讲","cat_desc":"楼宇对讲","sort_order":"0","is_show":"1","parent_id":"0"},{"cat_id":"7","cat_name":"智能消费","keyword":"智能消费","cat_desc":"智能消费","sort_order":"0","is_show":"1","parent_id":"0"},{"cat_id":"8","cat_name":"安防配套","keyword":"安防配套","cat_desc":"安防配套","sort_order":"0","is_show":"1","parent_id":"0"}],"brand":[{"brand_id":"1","brand_name":"中控科技","brand_logo":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/certificate%2Fbrand%2F%E4%B8%AD%E6%8E%A7%E7%A7%91%E6%8A%80.jpg"},{"brand_id":"2","brand_name":"兰德华","brand_logo":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/certificate%2Fbrand%2F%E5%85%B0%E5%BE%B7%E5%8D%8E.jpg"},{"brand_id":"3","brand_name":"思源","brand_logo":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/certificate%2Fbrand%2F%E6%80%9D%E6%BA%90%E7%A7%91%E5%AE%89.jpg"},{"brand_id":"4","brand_name":"魔仕龙","brand_logo":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/certificate%2Fbrand%2F%E6%91%A9%E4%BB%95%E9%BE%99.jpg"},{"brand_id":"5","brand_name":"斯玛特","brand_logo":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/certificate%2Fbrand%2F%E6%96%AF%E7%8E%9B%E7%89%B9.jpg"}]}
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable {
        /**
         * cat_id : 1
         * cat_name : 智能门禁
         * keyword : 智能门禁
         * cat_desc : 智能门禁
         * sort_order : 0
         * is_show : 1
         * parent_id : 0
         */

        private List<Cat> cat;
        /**
         * brand_id : 1
         * brand_name : 中控科技
         * brand_logo : http://anchongres.oss-cn-hangzhou.aliyuncs.com/certificate%2Fbrand%2F%E4%B8%AD%E6%8E%A7%E7%A7%91%E6%8A%80.jpg
         */

        private List<Brand> brand;

        public List<Cat> getCat() {
            return cat;
        }

        public void setCat(List<Cat> cat) {
            this.cat = cat;
        }

        public List<Brand> getBrand() {
            return brand;
        }

        public void setBrand(List<Brand> brand) {
            this.brand = brand;
        }

        public static class Cat implements Serializable {
            private String cat_id;
            private String cat_name;
            private String keyword;
            private String cat_desc;
            private String sort_order;
            private String is_show;
            private String parent_id;

            public String getCat_id() {
                return cat_id;
            }

            public void setCat_id(String cat_id) {
                this.cat_id = cat_id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public String getKeyword() {
                return keyword;
            }

            public void setKeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getCat_desc() {
                return cat_desc;
            }

            public void setCat_desc(String cat_desc) {
                this.cat_desc = cat_desc;
            }

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
            }

            public String getIs_show() {
                return is_show;
            }

            public void setIs_show(String is_show) {
                this.is_show = is_show;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            @Override
            public String toString() {
                return "Cat{" +
                        "cat_id='" + cat_id + '\'' +
                        ", cat_name='" + cat_name + '\'' +
                        ", keyword='" + keyword + '\'' +
                        ", cat_desc='" + cat_desc + '\'' +
                        ", sort_order='" + sort_order + '\'' +
                        ", is_show='" + is_show + '\'' +
                        ", parent_id='" + parent_id + '\'' +
                        '}';
            }
        }

        public static class Brand implements Serializable {
            private String brand_id;
            private String brand_name;
            private String brand_logo;

            public String getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(String brand_id) {
                this.brand_id = brand_id;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public String getBrand_logo() {
                return brand_logo;
            }

            public void setBrand_logo(String brand_logo) {
                this.brand_logo = brand_logo;
            }

            @Override
            public String toString() {
                return "Brand{" +
                        "brand_id='" + brand_id + '\'' +
                        ", brand_name='" + brand_name + '\'' +
                        ", brand_logo='" + brand_logo + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "cat=" + cat +
                    ", brand=" + brand +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CartBrandResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
