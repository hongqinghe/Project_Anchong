package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/6/30.
 */
public class CatBrandResponse extends ResponseResult implements Serializable {


    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        /**
         * cat_id : 分类id
         * cat_name : 分类名称
         */

        private List<CatEntity> cat;
        /**
         * brand_id : 品牌id
         * brand_name : 品牌名称
         * brand_logo : 品牌logo
         */

        private List<BrandEntity> brand;

        public List<CatEntity> getCat() {
            return cat;
        }

        public void setCat(List<CatEntity> cat) {
            this.cat = cat;
        }

        public List<BrandEntity> getBrand() {
            return brand;
        }

        public void setBrand(List<BrandEntity> brand) {
            this.brand = brand;
        }

        public static class CatEntity {
            private String cat_id;
            private String cat_name;

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

            @Override
            public String toString() {
                return "CatEntity{" +
                        "cat_id='" + cat_id + '\'' +
                        ", cat_name='" + cat_name + '\'' +
                        '}';
            }
        }

        public static class BrandEntity {
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
                return "BrandEntity{" +
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
        return "CatBrandResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
