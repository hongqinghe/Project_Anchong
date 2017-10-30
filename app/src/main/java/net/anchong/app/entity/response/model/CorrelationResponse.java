package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/7/21.
 */
public class CorrelationResponse extends ResponseResult implements Serializable {


    /**
     * total : 17
     * list : [{"gid":"货品ID","title":"货品名称","price":"货品价格","pic":"货品图片","goods_id":"商品ID"},{"gid":"货品ID","title":"货品名称","price":"货品价格","pic":"货品图片","goods_id":"商品ID"}]
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable {
        private int total;
        /**
         * gid : 货品ID
         * title : 货品名称
         * price : 货品价格
         * pic : 货品图片
         * goods_id : 商品ID
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

        public static class ListEntity implements Serializable {
            private String gid;
            private String title;
            private String price;
            private String pic;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
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
                        ", pic='" + pic + '\'' +
                        ", goods_id='" + goods_id + '\'' +
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
        return "CorrelationResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}

