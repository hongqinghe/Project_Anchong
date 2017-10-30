package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/5/18.
 */
public class OrderInfoResponse extends ResponseResult implements Serializable {


    /**
     * total : 总数
     * list : [{"order_id":"订单ID","order_num":"订单编号","sid":"商铺ID","sname":"商铺名称","state":"订单状态","created_at":"时间","total_price":"商品总价","name":"收货人姓名","phone":"收货人电话","address":"收货地址","freight":"运费","invoice":"发票","body":"订单描述（付款时使用）","goods":[{"goods_name":"商品名","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"},{"goods_name":"商品名","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"}]},{"order_id":"订单ID","order_num":"订单编号","sid":"商铺ID","sname":"商铺名称","state":"订单状态","created_at":"时间","total_price":"商品总价","name":"收货人姓名","phone":"收货人电话","address":"收货地址","freight":"运费","invoice":"发票","body":"订单描述（付款时使用）","goods":[{"goods_name":"商品名","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"},{"goods_name":"商品名","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"}]}]
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
        /**
         * order_id : 订单ID
         * order_num : 订单编号
         * sid : 商铺ID
         * sname : 商铺名称
         * state : 订单状态
         * created_at : 时间
         * total_price : 商品总价
         * name : 收货人姓名
         * phone : 收货人电话
         * address : 收货地址
         * freight : 运费
         * invoice : 发票
         * body : 订单描述（付款时使用）
         * goods : [{"goods_name":"商品名","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"},{"goods_name":"商品名","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"}]
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

        public static class ListEntity implements Serializable{
            private String order_id;
            private String order_num;
            private String sid;
            private String sname;
            private String state;
            private String created_at;
            private String total_price;
            private String name;
            private String phone;
            private String address;
            private String freight;
            private String invoice;
            private String body;
            /**
             * goods_name : 商品名
             * goods_num : 商品数量
             * goods_price : 商品价格
             * goods_type : 商品规格
             * img : 商品图片
             */

            private List<GoodsEntity> goods;

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
            }

            public String getSid() {
                return sid;
            }

            public void setSid(String sid) {
                this.sid = sid;
            }

            public String getSname() {
                return sname;
            }

            public void setSname(String sname) {
                this.sname = sname;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getTotal_price() {
                return total_price;
            }

            public void setTotal_price(String total_price) {
                this.total_price = total_price;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getFreight() {
                return freight;
            }

            public void setFreight(String freight) {
                this.freight = freight;
            }

            public String getInvoice() {
                return invoice;
            }

            public void setInvoice(String invoice) {
                this.invoice = invoice;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public List<GoodsEntity> getGoods() {
                return goods;
            }

            public void setGoods(List<GoodsEntity> goods) {
                this.goods = goods;
            }

            public static class GoodsEntity {
                private String goods_name;
                private String goods_num;
                private String goods_price;
                private String goods_type;
                private String img;

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_num() {
                    return goods_num;
                }

                public void setGoods_num(String goods_num) {
                    this.goods_num = goods_num;
                }

                public String getGoods_price() {
                    return goods_price;
                }

                public void setGoods_price(String goods_price) {
                    this.goods_price = goods_price;
                }

                public String getGoods_type() {
                    return goods_type;
                }

                public void setGoods_type(String goods_type) {
                    this.goods_type = goods_type;
                }

                public String getImg() {
                    return img;
                }

                public void setImg(String img) {
                    this.img = img;
                }

                @Override
                public String toString() {
                    return "GoodsEntity{" +
                            "goods_name='" + goods_name + '\'' +
                            ", goods_num='" + goods_num + '\'' +
                            ", goods_price='" + goods_price + '\'' +
                            ", goods_type='" + goods_type + '\'' +
                            ", img='" + img + '\'' +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "ListEntity{" +
                        "order_id='" + order_id + '\'' +
                        ", order_num='" + order_num + '\'' +
                        ", sid='" + sid + '\'' +
                        ", sname='" + sname + '\'' +
                        ", state='" + state + '\'' +
                        ", created_at='" + created_at + '\'' +
                        ", total_price='" + total_price + '\'' +
                        ", name='" + name + '\'' +
                        ", phone='" + phone + '\'' +
                        ", address='" + address + '\'' +
                        ", freight='" + freight + '\'' +
                        ", invoice='" + invoice + '\'' +
                        ", body='" + body + '\'' +
                        ", goods=" + goods +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "total='" + total + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderInfoResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
