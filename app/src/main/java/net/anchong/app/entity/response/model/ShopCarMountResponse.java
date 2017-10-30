package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;

/**
 * Created by baishixin on 16/5/11.
 */
public class ShopCarMountResponse extends ResponseResult implements Serializable {


    /**
     * ResultData : {"cartamount":17}
     */

    /**
     * cartamount : 17
     */

    private ResultDataEntity ResultData;

    public ResultDataEntity getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataEntity ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataEntity implements Serializable {
        private int cartamount;

        public int getCartamount() {
            return cartamount;
        }

        public void setCartamount(int cartamount) {
            this.cartamount = cartamount;
        }

        @Override
        public String toString() {
            return "ResultDataEntity{" +
                    "cartamount=" + cartamount +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopCarMountResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
