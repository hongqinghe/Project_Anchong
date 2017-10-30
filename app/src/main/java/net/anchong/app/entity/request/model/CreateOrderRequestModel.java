package net.anchong.app.entity.request.model;

import java.io.Serializable;
import java.util.List;

/**
 * 生成订单时的请求
 * Created by baishixin on 16/4/29.
 */
public class CreateOrderRequestModel extends ParamModel implements Serializable {

    /**
     * name : 收货人姓名
     * phone : 收货人电话
     * address : 收货地址
     * invoice : 发票
     * list : [{"sid":"商铺ID","sname":"商铺名称","total_price":"商铺内商品总价","freight":"运费","goods":[{"cart_id":"购物车商品id（上面购物车返回的时候有）","gid":"商品id","goods_name":"商品名称","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"},{"cart_id":"购物车商品id","gid":"商品id","goods_name":"商品名称","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"}]},{"sid":"商铺ID","sname":"商铺名称","total_price":"商铺内商品总价","freight":"运费","goods":[{"cart_id":"购物车商品id","gid":"商品id","goods_name":"商品名称","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"},{"cart_id":"购物车商品id","gid":"商品id","goods_name":"商品名称","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"}]}]
     */

    private String name;
    private String phone;
    private String address;
    private String invoice;
    /**
     * sid : 商铺ID
     * sname : 商铺名称
     * total_price : 商铺内商品总价
     * freight : 运费
     * goods : [{"cart_id":"购物车商品id（上面购物车返回的时候有）","gid":"商品id","goods_name":"商品名称","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"},{"cart_id":"购物车商品id","gid":"商品id","goods_name":"商品名称","goods_num":"商品数量","goods_price":"商品价格","goods_type":"商品规格","img":"商品图片"}]
     */

    private List<ListEntity> list;

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

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public static class ListEntity implements Serializable{
        private String sid;
        private String sname;
        private String total_price;
        private String freight;
        /**
         * cart_id : 购物车商品id（上面购物车返回的时候有）
         * gid : 商品id
         * goods_name : 商品名称
         * goods_num : 商品数量
         * goods_price : 商品价格
         * goods_type : 商品规格
         * img : 商品图片
         */

        private List<GoodsEntity> goods;

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

        public String getTotal_price() {
            return total_price;
        }

        public void setTotal_price(String total_price) {
            this.total_price = total_price;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public static class GoodsEntity implements Serializable{
            private String cart_id;
            private String gid;
            private String goods_name;
            private String goods_num;
            private String goods_price;
            private String goods_type;
            private String img;

            public String getCart_id() {
                return cart_id;
            }

            public void setCart_id(String cart_id) {
                this.cart_id = cart_id;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
            }

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
        }
    }
}
