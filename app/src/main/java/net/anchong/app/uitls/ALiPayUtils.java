package net.anchong.app.uitls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Created by baishixin on 16/7/11.
 */
public class ALiPayUtils {


    // 商户PID
    public static final String PARTNER = "2088911913159962";
    // 商户收款账号
    public static final String SELLER = "zhifu@anchong.net";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALwLCGHrIeOxIsPOMF9Xi1+oDEeOFuVyXRQywXRRERFBrPGQ0JKnjlOU299FhORbvfsmAmAqNoRuVI9R2Gk960fARt92G8t0NOWL/jv5xhGuiPYi7h9WwD1OiANVpF0ghXar50qLTGMDsU9b1Ncu42z+YUZyBQs35R/l/Olmy71VAgMBAAECgYBd1t+jBdUOrHod3sqS+bwqy1D9cHIKpKzAhKe35TCwPOcSUeNUHFvRaArle+rajtHbAFfVJ5u3MNvv/bcSpfzAIOI5sWEL21cXzvj3QwZu67Oc969ZOy7mbi5JuCJzMMnrTikvut24iBaOyFKnzKwkoDpfW3PaMGLItIsJ2C+8rQJBAO/DJtvmfUTc9ibuBOefDIhM1p7TtA3QtPimzGOVrpvfQs4iSh18/wDHtyH5NRV7pQpICXYqg/EA3wZgiYHbu2sCQQDIxzSqD6ErahoJ686uyrnU5QVOm3T2osNSwDzqJnORKCOhnmmnfuGXGlp7tSrsF5fjsyuTotCSfYQgDptXv1o/AkB5JwePvpw2G+FWMpfP4pKWnR0mDJvDD18i9kX5XD1rDhnXx4Pj+rFwV3q2dBLa1CvFUjlVuI4LTgFkW4ugTsQZAkB2KAVuL1SB4CcCv6saYmOKTl0ZLZNfsdmQ0WEHyyknu598dWVoFNtduTyWoL9pwVi5v69dauujygn/c5knwoyxAkEAgo9D0z9dUk5zmlQmJHJ/6XBH/NnVJXJB3JfOvIk3x2fLJay6eurBQysO2OviEul5Y8JnDIcdHKW2wp/W6xzz/w==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;


//    public static void payOrder(Context context){
//
//
//
//        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE) || TextUtils.isEmpty(SELLER)) {
//            new AlertDialog.Builder(context).setTitle("警告").setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
//                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialoginterface, int i) {
//                            //
////                            finish();
//                        }
//                    }).show();
//            return;
//        }
//        String orderInfo = getOrderInfo("测试的商品", "该测试商品的详细描述", "0.01","");
//
//        /**
//         * 特别注意，这里的签名逻辑需要放在服务端，切勿将私钥泄露在代码中！
//         */
//        String sign = sign(orderInfo);
//        try {
//            /**
//             * 仅需对sign 做URL编码
//             */
//            sign = URLEncoder.encode(sign, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        /**
//         * 完整的符合支付宝参数规范的订单信息
//         */
//        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&" + getSignType();
//
//        Logger.i("支付信息：" + payInfo);
//
//
////        Runnable payRunnable = new Runnable() {
////
////            @Override
////            public void run() {
////                // 构造PayTask 对象
////                PayTask alipay = new PayTask(PayDemoActivity.this);
////                // 调用支付接口，获取支付结果
////                String result = alipay.pay(payInfo, true);
////
////                Message msg = new Message();
////                msg.what = SDK_PAY_FLAG;
////                msg.obj = result;
////                mHandler.sendMessage(msg);
////            }
////        };
////
////        // 必须异步调用
////        Thread payThread = new Thread(payRunnable);
////        payThread.start();
//
//
//    }



    /**
     * create the order info. 创建订单信息
     *
     */
    public static String getOrderInfo(String subject, String body, String price,String out_trade_no) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
//        orderInfo += "&out_trade_no=" + "\"" + getOutTradeNo() + "\"";
        orderInfo += "&out_trade_no=" + "\"" + out_trade_no + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://pay.anchong.net/pay/mobilenotify" + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }


    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    public static String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss", Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public static String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    public static String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
