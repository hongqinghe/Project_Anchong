package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * 我的商铺结果
 * Created by baishixin on 16/6/29.
 */
public class MyShopsResponse extends ResponseResult implements Serializable {


    /**
     * shops : {"name":"商铺名","img":"商铺图片","banner":"商铺招牌图片","introduction":"店铺简介","customer":"客服电话"}
     * collect : 关注数
     * collresult : 0(未收藏)1(已收藏)
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
         * name : 商铺名
         * img : 商铺图片
         * banner : 商铺招牌图片
         * introduction : 店铺简介
         * customer : 客服电话
         */

        private ShopsEntity shops;
        private String collect;
        private String collresult;

        public ShopsEntity getShops() {
            return shops;
        }

        public void setShops(ShopsEntity shops) {
            this.shops = shops;
        }

        public String getCollect() {
            return collect;
        }

        public void setCollect(String collect) {
            this.collect = collect;
        }

        public String getCollresult() {
            return collresult;
        }

        public void setCollresult(String collresult) {
            this.collresult = collresult;
        }

        public static class ShopsEntity {
            private String name;
            private String img;
            private String banner;
            private String introduction;
            private String customer;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getBanner() {
                return banner;
            }

            public void setBanner(String banner) {
                this.banner = banner;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getCustomer() {
                return customer;
            }

            public void setCustomer(String customer) {
                this.customer = customer;
            }

            @Override
            public String toString() {
                return "ShopsEntity{" +
                        "name='" + name + '\'' +
                        ", img='" + img + '\'' +
                        ", banner='" + banner + '\'' +
                        ", introduction='" + introduction + '\'' +
                        ", customer='" + customer + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "shops=" + shops +
                    ", collect='" + collect + '\'' +
                    ", collresult='" + collresult + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MyShopsResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
