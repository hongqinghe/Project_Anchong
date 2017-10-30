package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 商城首页结果
 * Created by baishixin on 16/6/28.
 */
public class GoodsAdvertResponse extends ResponseResult implements Serializable {


    /**
     * pic : [{"ad_code":"轮播图","ad_name":"类型(shop(商铺),goods(商品)如果是商铺的话这个字段是shop字符串，如果是商品的话这个字段是goods_id的值)","ad_link":"对应不同的类型传不同的值"},{"ad_code":"轮播图","ad_name":"类型(shop(商铺),goods(商品))","ad_link":"对应不同的类型传不同的值"},{"ad_code":"轮播图","ad_name":"类型(shop(商铺),goods(商品))","ad_link":"对应不同的类型传不同的值"},{"ad_code":"轮播图","ad_name":"类型(shop(商铺),goods(商品))","ad_link":"对应不同的类型传不同的值"}]
     * one : {"name":"模块名称","list":[{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]}
     * two : {"name":"模块名称","list":[{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"}]}
     * three : {"name":"模块名称","list":[{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]}
     * four : {"name":"APP广告","list":[{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"}]}
     * five : {"name":"模块名称","list":[{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]}
     * six : {"name":"模块名称","list":[{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]}
     * goods : [{"gid":"货品id","title":"货品名称","price":"价格","sname":"商铺名称","pic":"商品图片","vip_price":"商品VIP价格","goods_id":"商品id"}]
     * showprice : 0(未验证)1(已验证)
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
         * name : 模块名称
         * list : [{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]
         */

        private OneEntity one;
        /**
         * name : 模块名称
         * list : [{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"}]
         */

        private TwoEntity two;
        /**
         * name : 模块名称
         * list : [{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]
         */

        private ThreeEntity three;
        /**
         * name : APP广告
         * list : [{"position_id":"目前没什么用","ad_code":"图片","ad_name":"shop","ad_link":"商铺ID"}]
         */

        private FourEntity four;
        /**
         * name : 模块名称
         * list : [{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]
         */

        private FiveEntity five;
        /**
         * name : 模块名称
         * list : [{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"},{"position_id":"目前没什么用","ad_code":"图片","ad_name":"商品ID","ad_link":"货品ID"}]
         */

        private SixEntity six;
        private String showprice;
        /**
         * ad_code : 轮播图
         * ad_name : 类型(shop(商铺),goods(商品)如果是商铺的话这个字段是shop字符串，如果是商品的话这个字段是goods_id的值)
         * ad_link : 对应不同的类型传不同的值
         */

        private List<PicEntity> pic;
        /**
         * gid : 货品id
         * title : 货品名称
         * price : 价格
         * sname : 商铺名称
         * pic : 商品图片
         * vip_price : 商品VIP价格
         * goods_id : 商品id
         */

        private List<GoodsEntity> goods;

        public OneEntity getOne() {
            return one;
        }

        public void setOne(OneEntity one) {
            this.one = one;
        }

        public TwoEntity getTwo() {
            return two;
        }

        public void setTwo(TwoEntity two) {
            this.two = two;
        }

        public ThreeEntity getThree() {
            return three;
        }

        public void setThree(ThreeEntity three) {
            this.three = three;
        }

        public FourEntity getFour() {
            return four;
        }

        public void setFour(FourEntity four) {
            this.four = four;
        }

        public FiveEntity getFive() {
            return five;
        }

        public void setFive(FiveEntity five) {
            this.five = five;
        }

        public SixEntity getSix() {
            return six;
        }

        public void setSix(SixEntity six) {
            this.six = six;
        }

        public String getShowprice() {
            return showprice;
        }

        public void setShowprice(String showprice) {
            this.showprice = showprice;
        }

        public List<PicEntity> getPic() {
            return pic;
        }

        public void setPic(List<PicEntity> pic) {
            this.pic = pic;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public static class OneEntity implements Serializable {
            private String name;
            /**
             * position_id : 目前没什么用
             * ad_code : 图片
             * ad_name : 商品ID
             * ad_link : 货品ID
             */

            private List<ListEntity> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public static class ListEntity implements Serializable {
                private String position_id;
                private String ad_code;
                private String ad_name;
                private String ad_link;

                public String getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(String position_id) {
                    this.position_id = position_id;
                }

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
                    return "ListEntity{" +
                            "position_id='" + position_id + '\'' +
                            ", ad_code='" + ad_code + '\'' +
                            ", ad_name='" + ad_name + '\'' +
                            ", ad_link='" + ad_link + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "OneEntity{" +
                        "name='" + name + '\'' +
                        ", list=" + list +
                        '}';
            }
        }

        public static class TwoEntity implements Serializable {
            private String name;
            /**
             * position_id : 目前没什么用
             * ad_code : 图片
             * ad_name : shop
             * ad_link : 商铺ID
             */

            private List<ListEntity> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public static class ListEntity implements Serializable {
                private String position_id;
                private String ad_code;
                private String ad_name;
                private String ad_link;

                public String getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(String position_id) {
                    this.position_id = position_id;
                }

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
                    return "ListEntity{" +
                            "position_id='" + position_id + '\'' +
                            ", ad_code='" + ad_code + '\'' +
                            ", ad_name='" + ad_name + '\'' +
                            ", ad_link='" + ad_link + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "TwoEntity{" +
                        "name='" + name + '\'' +
                        ", list=" + list +
                        '}';
            }
        }

        public static class ThreeEntity implements Serializable {
            private String name;
            /**
             * position_id : 目前没什么用
             * ad_code : 图片
             * ad_name : 商品ID
             * ad_link : 货品ID
             */

            private List<ListEntity> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public static class ListEntity implements Serializable {
                private String position_id;
                private String ad_code;
                private String ad_name;
                private String ad_link;

                public String getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(String position_id) {
                    this.position_id = position_id;
                }

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
                    return "ListEntity{" +
                            "position_id='" + position_id + '\'' +
                            ", ad_code='" + ad_code + '\'' +
                            ", ad_name='" + ad_name + '\'' +
                            ", ad_link='" + ad_link + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ThreeEntity{" +
                        "name='" + name + '\'' +
                        ", list=" + list +
                        '}';
            }
        }

        public static class FourEntity implements Serializable {
            private String name;
            /**
             * position_id : 目前没什么用
             * ad_code : 图片
             * ad_name : shop
             * ad_link : 商铺ID
             */

            private List<ListEntity> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public static class ListEntity implements Serializable {
                private String position_id;
                private String ad_code;
                private String ad_name;
                private String ad_link;

                public String getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(String position_id) {
                    this.position_id = position_id;
                }

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
                    return "ListEntity{" +
                            "position_id='" + position_id + '\'' +
                            ", ad_code='" + ad_code + '\'' +
                            ", ad_name='" + ad_name + '\'' +
                            ", ad_link='" + ad_link + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "FourEntity{" +
                        "name='" + name + '\'' +
                        ", list=" + list +
                        '}';
            }
        }

        public static class FiveEntity implements Serializable {
            private String name;
            /**
             * position_id : 目前没什么用
             * ad_code : 图片
             * ad_name : 商品ID
             * ad_link : 货品ID
             */

            private List<ListEntity> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public static class ListEntity implements Serializable {
                private String position_id;
                private String ad_code;
                private String ad_name;
                private String ad_link;

                public String getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(String position_id) {
                    this.position_id = position_id;
                }

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
                    return "ListEntity{" +
                            "position_id='" + position_id + '\'' +
                            ", ad_code='" + ad_code + '\'' +
                            ", ad_name='" + ad_name + '\'' +
                            ", ad_link='" + ad_link + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "FiveEntity{" +
                        "name='" + name + '\'' +
                        ", list=" + list +
                        '}';
            }
        }

        public static class SixEntity implements Serializable {
            private String name;
            /**
             * position_id : 目前没什么用
             * ad_code : 图片
             * ad_name : 商品ID
             * ad_link : 货品ID
             */

            private List<ListEntity> list;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<ListEntity> getList() {
                return list;
            }

            public void setList(List<ListEntity> list) {
                this.list = list;
            }

            public static class ListEntity implements Serializable {
                private String position_id;
                private String ad_code;
                private String ad_name;
                private String ad_link;

                public String getPosition_id() {
                    return position_id;
                }

                public void setPosition_id(String position_id) {
                    this.position_id = position_id;
                }

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
                    return "ListEntity{" +
                            "position_id='" + position_id + '\'' +
                            ", ad_code='" + ad_code + '\'' +
                            ", ad_name='" + ad_name + '\'' +
                            ", ad_link='" + ad_link + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "SixEntity{" +
                        "name='" + name + '\'' +
                        ", list=" + list +
                        '}';
            }
        }

        public static class PicEntity implements Serializable {
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

        public static class GoodsEntity implements Serializable {
            private String gid;
            private String title;
            private String price;
            private String sname;
            private String pic;
            private String vip_price;
            private String goods_id;

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            @Override
            public String toString() {
                return "GoodsEntity{" +
                        "gid='" + gid + '\'' +
                        ", title='" + title + '\'' +
                        ", price='" + price + '\'' +
                        ", sname='" + sname + '\'' +
                        ", pic='" + pic + '\'' +
                        ", vip_price='" + vip_price + '\'' +
                        ", goods_id='" + goods_id + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "one=" + one +
                    ", two=" + two +
                    ", three=" + three +
                    ", four=" + four +
                    ", five=" + five +
                    ", six=" + six +
                    ", showprice='" + showprice + '\'' +
                    ", pic=" + pic +
                    ", goods=" + goods +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodsAdvertResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
