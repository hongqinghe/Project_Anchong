package net.anchong.app.http.domain;

/**
 * Created by Starry Jerry on 2016/6/19.
 */
public class Product extends ResponseResult {

    private ProductItem ResultData;

    public ProductItem getResultData() {
        return ResultData;
    }

    public void setResultData(ProductItem resultData) {
        ResultData = resultData;
    }

    public class ProductItem {
        private String productName;
        private float productPrice;
        private String Message;

        public String getMessage() {
            return Message;
        }

        public void setMessage(String message) {
            Message = message;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public float getProductPrice() {
            return productPrice;
        }

        public void setProductPrice(float productPrice) {
            this.productPrice = productPrice;
        }

        @Override
        public String toString() {
            return "ProductItem{" +
                    "productName='" + productName + '\'' +
                    ", productPrice=" + productPrice +
                    ", Message='" + Message + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return super.toString() + "Product{" +
                "ResultData=" + ResultData +
                '}';
    }
}
