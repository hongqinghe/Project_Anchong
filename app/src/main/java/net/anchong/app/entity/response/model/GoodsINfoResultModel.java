package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/4/25.
 */
public class GoodsINfoResultModel extends ResponseResult implements Serializable {


    /**
     * goods_id : 1213
     * market_price : 0.8
     * vip_price : 0.8
     * goods_name : ID卡
     * sid : 49
     * title : ID卡-晶睿智能   ID白卡 4100 / ID印刷卡 4100
     * name : 晶睿智能科技
     * img : http://anchongres.oss-cn-hangzhou.aliyuncs.com/shops/DA95533676936B649D43C9272153524E.png
     * customer :
     * goodspic : ["http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1467182431349766.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1467182446819171.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1467182449530352.png","http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1467182448946200.png"]
     * detailpic : http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/detail/1467182318706600.png
     * parameterpic : http://www.anchong.net/getparam?gid=1213
     * datapic : http://www.anchong.net/getpackage?gid=1213
     * collection : 0
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable {
        private String goods_id;
        private String market_price;
        private String vip_price;
        private String goods_name;
        private String sid;
        private String title;
        private String name;
        private String img;
        private String customer;
        private String detailpic;
        private String parameterpic;
        private String datapic;
        private int collection;
        private List<String> goodspic;

        public String getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(String goods_id) {
            this.goods_id = goods_id;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }

        public String getVip_price() {
            return vip_price;
        }

        public void setVip_price(String vip_price) {
            this.vip_price = vip_price;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getCustomer() {
            return customer;
        }

        public void setCustomer(String customer) {
            this.customer = customer;
        }

        public String getDetailpic() {
            return detailpic;
        }

        public void setDetailpic(String detailpic) {
            this.detailpic = detailpic;
        }

        public String getParameterpic() {
            return parameterpic;
        }

        public void setParameterpic(String parameterpic) {
            this.parameterpic = parameterpic;
        }

        public String getDatapic() {
            return datapic;
        }

        public void setDatapic(String datapic) {
            this.datapic = datapic;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public List<String> getGoodspic() {
            return goodspic;
        }

        public void setGoodspic(List<String> goodspic) {
            this.goodspic = goodspic;
        }
    }
}
