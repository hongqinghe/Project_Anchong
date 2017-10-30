package net.anchong.app.entity.response.model;

import com.orhanobut.logger.Logger;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/4/28.
 */
public class ShopCarResponseModel extends ResponseResult implements Serializable {


    /**
     * ResultData : [{"sid":"4","free_price":"0","freight":"0","sname":"安虫自营","goods":[{"cart_id":37,"goods_name":"小白测试3","goods_num":"5","goods_price":"79","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是小白测试3","gid":"300","sid":"4","sname":"安虫自营"}]},{"sid":"1","free_price":"300","freight":"15","sname":"安虫测试","goods":[{"cart_id":39,"goods_name":"111","goods_num":"1","goods_price":"150","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1461809365.jpg","goods_type":"6s","gid":"3","sid":"1","sname":"安虫自营"},{"cart_id":35,"goods_name":"小白测试1","goods_num":"2","goods_price":"99","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是小白测试1","gid":"500","sid":"1","sname":"安虫测试"}]},{"sid":"3","free_price":"0","freight":"0","sname":"安虫自营测试","goods":[{"cart_id":36,"goods_name":"小白测试2","goods_num":"9","goods_price":"89","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是小白测试2","gid":"400","sid":"3","sname":"安虫自营测试"},{"cart_id":5,"goods_name":"我是安虫自营测试11","goods_num":"39","goods_price":"60","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是安虫自营测试9","gid":"1","sid":"3","sname":"安虫自营测试"},{"cart_id":19,"goods_name":"我是安虫自营测试11","goods_num":"5","goods_price":"60","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是安虫自营测试11","gid":"6","sid":"3","sname":"安虫自营测试"}]},{"sid":"5","free_price":"0","freight":"0","sname":"安虫自营\n","goods":[{"cart_id":38,"goods_name":"小白测试4","goods_num":"15","goods_price":"69","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是小白测试4","gid":"200","sid":"5","sname":"安虫自营\n"}]},{"sid":"6","free_price":"100","freight":"10","sname":"小白自营","goods":[{"cart_id":33,"goods_name":"小白测试","goods_num":"5","goods_price":"99","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是小白","gid":"1","sid":"6","sname":"小白自营"},{"cart_id":18,"goods_name":"红绿灯 普通","goods_num":"9","goods_price":"15","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1461653080.png","goods_type":"红绿灯 普通","gid":"13","sid":"6","sname":"小白自营"}]}]
     */

    /**
     * sid : 4
     * free_price : 0
     * freight : 0
     * sname : 安虫自营
     * goods : [{"cart_id":37,"goods_name":"小白测试3","goods_num":"5","goods_price":"79","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg","goods_type":"我是小白测试3","gid":"300","sid":"4","sname":"安虫自营"}]
     */

    private List<ResultDataBean> ResultData;

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable {
        private String sid;
        private String free_price;
        private String freight;
        private String sname;
        private boolean isSelect;

        public boolean isSelect() {
            return isSelect;
        }

        public void setIsSelect(boolean isSelect) {
            this.isSelect = isSelect;
        }

        /**
         * cart_id : 37
         * goods_name : 小白测试3
         * goods_num : 5
         * goods_price : 79
         * img : http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/1460510864869.jpg
         * goods_type : 我是小白测试3
         * gid : 300
         * sid : 4
         * sname : 安虫自营
         */


        private List<GoodsBean> goods;

        public String getSid() {
            return sid;
        }

        public void setSid(String sid) {
            this.sid = sid;
        }

        public String getFree_price() {
            return free_price;
        }

        public void setFree_price(String free_price) {
            this.free_price = free_price;
        }

        public String getFreight() {
            return freight;
        }

        public void setFreight(String freight) {
            this.freight = freight;
        }

        public String getSname() {
            return sname;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean implements Serializable {
            private int cart_id;
            private String goods_name;
            private String goods_num;
            private String goods_price;
            private String img;
            private String goods_type;
            private String gid;
            private String sid;
            private String sname;
            private boolean isSelect;

            public boolean isSelect() {
                return isSelect;
            }

            public void setIsSelect(boolean isSelect) {
                if (isSelect) {
                    Logger.i("设置为选中状态");
                } else {
                    Logger.i("设置为未选中状态");
                }
                this.isSelect = isSelect;
            }

            public int getCart_id() {
                return cart_id;
            }

            public void setCart_id(int cart_id) {
                this.cart_id = cart_id;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getGoods_type() {
                return goods_type;
            }

            public void setGoods_type(String goods_type) {
                this.goods_type = goods_type;
            }

            public String getGid() {
                return gid;
            }

            public void setGid(String gid) {
                this.gid = gid;
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

            @Override
            public String toString() {
                return "GoodsBean{" +
                        "cart_id=" + cart_id +
                        ", goods_name='" + goods_name + '\'' +
                        ", goods_num='" + goods_num + '\'' +
                        ", goods_price='" + goods_price + '\'' +
                        ", img='" + img + '\'' +
                        ", goods_type='" + goods_type + '\'' +
                        ", gid='" + gid + '\'' +
                        ", sid='" + sid + '\'' +
                        ", sname='" + sname + '\'' +
                        ", isSelect=" + isSelect +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataBean{" +
                    "sid='" + sid + '\'' +
                    ", free_price='" + free_price + '\'' +
                    ", freight='" + freight + '\'' +
                    ", sname='" + sname + '\'' +
                    ", isSelect=" + isSelect +
                    ", goods=" + goods +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopCarResponseModel{" +
                "ResultData=" + ResultData +
                '}';
    }
}
