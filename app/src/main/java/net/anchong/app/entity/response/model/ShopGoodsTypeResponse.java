package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * 店铺里面商品分类请求接口
 * Created by baishixin on 16/6/30.
 */
public class ShopGoodsTypeResponse extends ResponseResult implements Serializable {


    /**
     * parent_name : 一级分类名
     * list : [{"cat_id":"二级分类ID","cat_name":"二级分类名","parent_id":"这个没用"},{"cat_id":"二级分类ID","cat_name":"二级分类名","parent_id":"这个没用"},{"cat_id":"二级分类ID","cat_name":"二级分类名","parent_id":"这个没用"}]
     */

    private List<ResultDataEntity> ResultData;

    public List<ResultDataEntity> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataEntity> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity {
        private String parent_name;
        /**
         * cat_id : 二级分类ID
         * cat_name : 二级分类名
         * parent_id : 这个没用
         */

        private List<ListEntity> list;

        public String getParent_name() {
            return parent_name;
        }

        public void setParent_name(String parent_name) {
            this.parent_name = parent_name;
        }

        public List<ListEntity> getList() {
            return list;
        }

        public void setList(List<ListEntity> list) {
            this.list = list;
        }

        public static class ListEntity {
            private String cat_id;
            private String cat_name;
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

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            @Override
            public String toString() {
                return "ListEntity{" +
                        "cat_id='" + cat_id + '\'' +
                        ", cat_name='" + cat_name + '\'' +
                        ", parent_id='" + parent_id + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "parent_name='" + parent_name + '\'' +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopGoodsTypeResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
