package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 三级分类ID下的商品列表
 * Created by baishixin on 16/4/22.
 */
public class GoodsListResponseModel extends ResponseResult implements Serializable {


    /**
     * ResultData : {"total":2,"showprice":"0(未登录或未通过审核)1(登录并通过审核)","list":[{"gid":"货品id","title":"货品名称","price":"价格","sname":"商铺名称","pic":"商品图片","vip_price":"商品VIP价格","goods_id":"商品id"},{"gid":"货品ID","title":"货品名称","price":"价格","sname":"商铺名称","pic":"商品图片","vip_price":"商品VIP价格","goods_id":"商品id"}]}
     */

    /**
     * total : 2
     * showprice : 0(未登录或未通过审核)1(登录并通过审核)
     * list : [{"gid":"货品id","title":"货品名称","price":"价格","sname":"商铺名称","pic":"商品图片","vip_price":"商品VIP价格","goods_id":"商品id"},{"gid":"货品ID","title":"货品名称","price":"价格","sname":"商铺名称","pic":"商品图片","vip_price":"商品VIP价格","goods_id":"商品id"}]
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable{
        private int total;
        private String showprice;
        /**
         * gid : 货品id
         * title : 货品名称
         * price : 价格
         * sname : 商铺名称
         * pic : 商品图片
         * vip_price : 商品VIP价格
         * goods_id : 商品id
         */

        private List<ListEntity> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getShowprice() {
            return showprice;
        }

        public void setShowprice(String showprice) {
            this.showprice = showprice;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity implements Serializable{
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
                return "ListEntity{" +
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
                    "total=" + total +
                    ", showprice='" + showprice + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GoodsListResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
