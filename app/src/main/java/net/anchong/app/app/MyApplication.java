package net.anchong.app.app;

import android.app.Application;
import android.content.Context;

import com.cengalabs.flatui.FlatUI;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;

import net.anchong.app.okhttputils.OkHttpUtils;
import net.anchong.app.uitls.AppUtils;

import org.xutils.BuildConfig;
import org.xutils.x;

import java.io.File;
import java.util.concurrent.TimeUnit;

import in.srain.cube.Cube;

/**
 * Created by baishixin on 16/3/14.
 */
public class MyApplication extends Application {

    /**
     * 版本号
     */
    public static final float APP_VERSION = 1.0F;
    /**
     * RequestCode
     */
    //选择收货地址
    public static final int SELECT_ADDRESS = 1001;

    /**
     * 广告固定地址前缀
     */
    public static final String AD_URL_BEFORE = "http://www.anchong.net/information/";


    /**
     * 默认的ToKen
     */
    public static final String DEFAULT_TOKEN = "anchongnet";

    /**
     * SharedPreference 保存的用户信息的文件名
     */
    public static final String USERMESSAGE_FILE_NAME = "usermessage";


    public static final String ENDPOINT = "http://oss-cn-hangzhou.aliyuncs.com";
    public static final String IMG_URL_HEAD = "http://anchongres.oss-cn-hangzhou.aliyuncs.com/certificate/";
    /**
     * 商铺头像的OSS路径
     */
    public static final String SHOP_IMG_URL_HEAD = "http://anchongres.oss-cn-hangzhou.aliyuncs.com/shops/headerpic";
    /**
     * 商机图片的OSS路径
     */
    public static final String BUSINESS_IMG_URL_HEAD = "http://anchongres.oss-cn-hangzhou.aliyuncs.com/business/";
    /**
     * 商铺品牌认证书图片的OSS路径
     */
    public static final String SHOP_BRAND_URL_HEAD = "http://anchongres.oss-cn-hangzhou.aliyuncs.com/shops/sign";
    //    public static final String API = "http://anchongapi.fanxiao2.net/";
//    public static final String API = "http://api.anchong.net/";
//    public static final String API = "http://api.rqbin.com/";
    public static final String API = "http://api.moliying.com/";
    public static final String SMSAUTH = "user/smsauth";
    public static final String LOGIN = "user/login";
    /**
     * 商家资质认证上传图片的目录
     */
    public static final String IMG_DIR = "certificate/";
    /**
     * 店铺头像目录
     */
    public static final String IMG_SHOP_HEADERPIC = "shops/headerpic";

    /**
     * 个人发布商机上传图片的目录
     */
    public static final String BUSINESS_IMG_DIR = "business/";
    /**
     * 注册
     */
    public static final String REGISTER = "user/register";
    /**
     * 重置密码
     */
    public static final String FORGETPASSWORD = "user/forgetpassword";
    /**
     * 获取用户资料
     */
    public static final String GETUSERMESSAGE = "user/getmessage";
    /**
     * 设置用户资料
     */
    public static final String SETUSERMESSAGE = "user/setmessage";
    /**
     * 上传用户头像
     */
    public static final String SETHEAD = "user/sethead";
    /**
     * 上传资质认证的图片
     */
    public static final String STS = "user/sts";
    /**
     * 上传资质认证
     */
    public static final String INDIVI = "user/indivi";
    /**
     * 发布分类和类别查询
     */
    public static final String TYPETAG = "business/typetag";
    /**
     * 个人商机查看
     */
    public static final String MYBUSINESSINFO = "business/mybusinessinfo";
    /**
     * 个人商机发布
     */
    public static final String MYBUSINESSRELEASE = "business/release";
    /**
     * 个人商机修改
     */
    public static final String MYBUSINESSEDIT = "business/businessedit";
    /**
     * 个人商机操作，包括更新时间和删除  1：更新  2：删除
     */
    public static final String MYBUSINESSACTION = "business/businessaction";
    /**
     * 商机检索分类
     */
    public static final String BUSINESSSEARCH = "business/search";
    /**
     * 商机查看
     */
    public static final String BUSINESSINFO = "business/businessinfo";
    /**
     * 获取收货地址列表
     */
    public static final String USERADDRESS = "user/address";
    /**
     * 添加收货地址
     */
    public static final String USERSTOREADDRESS = "user/storeaddress";
    /**
     * 进入收货地址修改界面
     */
    public static final String USEREDITADDRESS = "user/editaddress";
    /**
     * 执行收货地址修改操作
     */
    public static final String USERUPDATEADDRESS = "user/updateaddress";
    /**
     * 删除收货地址修改操作
     */
    public static final String USERDELADDRESS = "user/deladdress";
    /**
     * 修改默认收货地址修改操作
     */
    public static final String USERSETDEFAULTADDRESS = "user/setdefaultaddress";
    /**
     * 获取用户默认收货地址
     */
    public static final String USERGETDEFAULTADDRESS = "user/getdefaultaddress";
    /**
     * 商品列表一级分类
     */
    public static final String GOODSCATONE = "goods/catone";
    /**
     * 商品列表二、三级分类
     */
    public static final String GOODSCATINFO = "goods/catinfo";
    /**
     * 根据三级分类ID获取商品列表
     */
    public static final String GOODSGOODSLIST = "goods/goodslist";
    /**
     * 商品详情
     */
    public static final String GOODSGOODSINFO = "goods/goodsinfo";
    /**
     * 商品规格
     */
    public static final String GOODSGOODSFORMAT = "goods/goodsformat";
    /**
     * 货品详情
     */
    public static final String GOODSSHOW = "goods/goodsshow";
    /**
     * 商品添加到购物车
     */
    public static final String CARTCARTADD = "cart/cartadd";
    /**
     * 购物车查看
     */
    public static final String CARTCARTINFO = "cart/cartinfo";
    /**
     * 获取购物车中货品数量
     */
    public static final String CARTCARTAMOUNT = "cart/cartamount";
    /**
     * 获取购物车中货品数量加减
     */
    public static final String CARTCARTNUM = "cart/cartnum";
    /**
     * 获取购物车中货品数量加减
     */
    public static final String CARTCARTDEL = "cart/cartdel";
    /**
     * 生成订单
     */
    public static final String ORDERORDERCREATE = "order/ordercreate";
    /**
     * 查看订单
     */
    public static final String ORDERORDERINFO = "order/orderinfo";
    /**
     * 获取商品分类和品牌
     */
    public static final String CATBRAND = "catbrand";
    /**
     * 店铺申请
     */
    public static final String SHOP = "shop";
    /**
     * 商铺货品查看
     */
    public static final String SHOPSGOODSSHOW = "shops/goodsshow";
    /**
     * 商铺首页信息
     */
    public static final String SHOPSSHOPSINDEX = "shops/shopsindex";

    /**
     * 商铺货品操作
     */
    public static final String SHOPSGOODSACTION = "shops/goodsaction";
    /**
     * 商铺订单信息
     */
    public static final String SHOPSSHOPSORDER = "shops/shopsorder";
    /**
     * 商铺发货快递公司
     */
    public static final String SHOPSLOGISTCOMPANY = "shops/logistcompany";
    /**
     * 商铺订单操作
     */
    public static final String SHOPSSHOPSOPERATION = "shops/shopsoperation";
    /**
     * 商铺商品列表
     */
    public static final String SHOPSSHOPSGOODS = "shops/shopsgoods";
    /**
     * 商铺新品信息
     */
    public static final String SHOPSNEWGOODS = "shops/newgoods";
    /**
     * 个人商铺信息
     */
    public static final String SHOPSMYSHOPS = "shops/myshops";
    /**
     * 社区首页聊聊展示
     */
    public static final String COMMUNITYCOMMUNITYSHOW = "community/communityshow";
    /**
     * 聊聊详情展示
     */
    public static final String COMMUNITYCOMMUNITYINFO = "community/communityinfo";
    /**
     * 聊聊评论
     */
    public static final String COMMUNITYCOMMUNITYCOM = "community/communitycom";
    /**
     * 聊聊发布评论
     */
    public static final String COMMUNITYCOMMENT = "community/comment";
    /**
     * 聊聊回复评论
     */
    public static final String COMMUNITYREPLY = "community/reply";
    /**
     * 收藏聊聊
     */
    public static final String COMMUNITYADDCOLLECT = "community/addcollect";
    /**
     * 取消收藏聊聊
     */
    public static final String COMMUNITYDELCOLLECT = "community/delcollect";
    /**
     * 删除聊聊
     */
    public static final String COMMUNITYCOMMUNITYDEL = "community/communitydel";
    /**
     * 个人聊聊显示
     */
    public static final String COMMUNITYMYCOMMUNITY = "community/mycommunity";
    /**
     * 收藏聊聊显示
     */
    public static final String COMMUNITYMYCOLLECT = "community/mycollect";
    /**
     * 商机首页广告
     */
    public static final String ADVERTBUSINESSADVERT = "advert/businessadvert";
    /**
     * 热门招标项目查看
     */
    public static final String BUSINESSBUSINESSHOT = "business/businesshot";
    /**
     * 最新招标项目查看
     */
    public static final String BUSINESSRECENT = "business/recent";
    /**
     * 最新招标项目查看
     */
    public static final String BUSINESSHOTPROJECT = "business/hotproject";
    /**
     * 商机内部轮播图
     */
    public static final String ADVERTPROJECTADVERT = "advert/projectadvert";
    /**
     * 商城首页广告
     */
    public static final String ADVERTGOODSADVERT = "advert/goodsadvert";
    /**
     * 商品商铺收藏
     */
    public static final String COLLECTADDCOLLECT = "collect/addcollect";
    /**
     * 取消商品商铺收藏
     */
    public static final String COLLECTDELCOLLECT = "collect/delcollect";
    /**
     * 货品筛选分类显示
     */
    public static final String SHOPSGOODSTYPE = "shops/goodstype";
    /**
     * 发布聊聊
     */
    public static final String COMMUNITYRELEASE = "community/release";
    /**
     * 商品分类所有商品请求
     */
    public static final String GOODSGOODSALL = "goods/goodsall";
    /**
     * 订单支付信息
     */
    public static final String ORDERORDERPAY = "order/orderpay";
    /**
     * 商机单个查看
     */
    public static final String BUSINESSBUSINESSINDEX = "business/businessindex";
    /**
     * 推荐商品
     */
    public static final String GOODSCORRELATION = "goods/correlation";
    /**
     * 配套商品
     */
    public static final String GOODSSUPPORTING = "goods/supporting";


    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {

        /// fresco
        Fresco.initialize(this);
//        initFresco(this);
        /// xutils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);
        // Converts the default values (radius, size, border) to dp to be compatible with different
        // screen sizes. If you skip this there may be problem with different screen densities
        FlatUI.initDefaultValues(this);
        Cube.onCreate(this);
        // Setting default theme to avoid to add the attribute "theme" to XML
        // and to be able to change the whole theme at once
        FlatUI.setDefaultTheme(FlatUI.DEEP);
//        FlatUI.setDefaultTheme(R.array.custom_theme);    // for using custom theme as default
        initOkhttp();
    }

    private void initOkhttp() {
        OkHttpUtils.getInstance().setConnectTimeout(30000, TimeUnit.MILLISECONDS);

    }

    public static void initFresco(Context context) {
        ProgressiveJpegConfig pjpegConfig = new ProgressiveJpegConfig() {
            @Override
            public int getNextScanNumberToDecode(int scanNumber) {
                return scanNumber + 2;
            }

            public QualityInfo getQualityInfo(int scanNumber) {
                boolean isGoodEnough = (scanNumber >= 5);
                return ImmutableQualityInfo.of(scanNumber, isGoodEnough, false);
            }
        };

        final ImagePipelineConfig config = ImagePipelineConfig
                .newBuilder(context)
                .setProgressiveJpegConfig(pjpegConfig)
                .setNetworkFetcher(new HttpUrlConnectionNetworkFetcher())
                .setMainDiskCacheConfig(
                        DiskCacheConfig.newBuilder(context).setBaseDirectoryName("image_cache")
                                .setBaseDirectoryPath(new File(AppUtils.getPicturePath(context)))
                                .setMaxCacheSize(100 * ByteConstants.MB)
                                .setMaxCacheSizeOnLowDiskSpace(100 * ByteConstants.MB)
                                .setMaxCacheSizeOnVeryLowDiskSpace(20 * ByteConstants.MB).build())
                .build();
        Fresco.initialize(context, config);
    }

}
