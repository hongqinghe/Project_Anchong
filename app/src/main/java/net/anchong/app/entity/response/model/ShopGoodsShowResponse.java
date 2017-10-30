package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/5/24.
 */
public class ShopGoodsShowResponse extends ResponseResult implements Serializable {


    /**
     * ResultData : {"total":35,"list":[{"gid":119,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1464052919827865.jpg","title":"配套商品 蓝色 1号","market_price":"1222","vip_price":"111","sales":"0","goods_num":"100","goods_id":"44"},{"gid":118,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987660685629.jpg","title":"控制一体机 红色 大","market_price":"56","vip_price":"56","sales":"0","goods_num":"55","goods_id":"25"},{"gid":117,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987391670250.jpg","title":"测试多级分类 紫色 蓝色","market_price":"44","vip_price":"44","sales":"0","goods_num":"44","goods_id":"29"},{"gid":98,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463450790614277.jpg","title":"海豚 绿色 大","market_price":"50","vip_price":"45","sales":"0","goods_num":"5","goods_id":"33"},{"gid":116,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463733446477384.png","title":"测试试试 红色","market_price":"56","vip_price":"45","sales":"0","goods_num":"99","goods_id":"42"},{"gid":115,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463721593670173.png","title":"摩仕龙 MSSLOO 磁力锁 双门明装 大吸力180公斤（350磅）*2 电磁锁 180kg（350Lbs）X2  PT-180MAS 180公斤*2 双门明装无信号 12V","market_price":"160","vip_price":"133","sales":"0","goods_num":"100","goods_id":"41"},{"gid":114,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463636195824518.png","title":"门禁锁 白色 带信号","market_price":"33","vip_price":"11","sales":"0","goods_num":"100","goods_id":"39"},{"gid":113,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463635773103152.png","title":"摩仕龙 MSSLOO磁力锁 单门明装 大吸力180公斤（350磅） 单联电磁锁 180kg（350Lbs） MS-918MA 180公斤 单门明装无信号","market_price":"100","vip_price":"90","sales":"0","goods_num":"100","goods_id":"38"},{"gid":112,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png","title":"门禁 红色","market_price":"80","vip_price":"70","sales":"0","goods_num":"16","goods_id":"37"},{"gid":111,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463543909749647.png","title":"测试 ID 非防水","market_price":"66","vip_price":"55","sales":"0","goods_num":"99","goods_id":"36"},{"gid":110,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463472725613154.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 T35-ID ID 非防水 银色","market_price":"169","vip_price":"66","sales":"0","goods_num":"50","goods_id":"35"},{"gid":106,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458248767782.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 IC 非防水 银色","market_price":"169","vip_price":"100","sa les":"0","goods_num":"89","goods_id":"32"},{"gid":105,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458009950668.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 土豪金","market_price":"169","vip_price":"110","sales":"0","goods_num":"5","goods_id":"32"},{"gid":104,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457778875910.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 青古铜","market_price":"160","vip_price":"120","sales":"0","goods_num":"95","goods_id":"32"},{"gid":103,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457584517713.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 银色","market_price":"169","vip_price":"130","sales":"0","goods_num":"89","goods_id":"32"},{"gid":102,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463455668377551.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 IC 非防水 银色","market_price":"169","vip_price":"111","sales":"0","goods_num":"55","goods_id":"32"},{"gid":101,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453599278435.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 土豪金","market_price":"169","vip_price":"132","sales":"0","goods_num":"96","goods_id":"32"},{"gid":100,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453437774594.jpg","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","market_price":"23","vip_price":"32","sales":"0","goods_num":"23","goods_id":"32"},{"gid":99,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","market_price":"169","vip_price":"130","sales":"0","goods_num":"44","goods_id":"32"},{"gid":79,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463389100312485.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","market_price":"169","vip_price":"130","sales":"0","goods_num":"321307","goods_id":"32"},{"gid":78,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463386181840909.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T30-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 白古铜","market_price":"169","vip_price":"130","sales":"0","goods_num":"100321321","goods_id":"28"},{"gid":75,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463381676488462.jpg","title":"lucy 红色","market_price":"2","vip_price":"2","sales":"0","goods_num":"23213213","goods_id":"30"},{"gid":74,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463379323103029.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T30-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 白古铜","market_price":"169","vip_price":"130","sales":"0","goods_num":"99","goods_id":"28"},{"gid":72,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463370381230467.jpg","title":"王朝晖 红色","market_price":"3","vip_price":"3","sales":"0","goods_num":"3321312","goods_id":"27"},{"gid":71,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463370034617879.jpg","title":"李朝辉 红色","market_price":"2","vip_price":"2","sales":"0","goods_num":"2","goods_id":"26"},{"gid":70,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463369416674576.jpg","title":"李朝辉 红色","market_price":"1","vip_price":"1","sales":"0","goods_num":"1","goods_id":"26"},{"gid":69,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463120961285435.png","title":"门禁读卡器测试 红 白","market_price":"100","vip_price":"200","sales":"0","goods_num":"2","goods_id":"24"},{"gid":68,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463104315777507.png","title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 280公斤 单门明装带信号","market_price":"169","vip_price":"130","sales":"0","goods_num":"100","goods_id":"21"},{"gid":67,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463104204486762.png","title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 280公斤 单门明装无信号","market_price":"156","vip_price":"120","sales":"0","goods_num":"100","goods_id":"21"},{"gid":66,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463103054353441.png","title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 250公斤 单门明装带信号","market_price":"130","vip_price":"100","sales":"0","goods_num":"100","goods_id":"21"},{"gid":62,"goods_img":null,"title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 250公斤 单门明装无信号","market_price":"117","vip_price":"90","sales":"0","goods_num":"100","goods_id":"21"},{"gid":65,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463103865339644.png","title":"我是智能一体测试 灰黑 灰蓝","market_price":"111","vip_price":"101","sales":"0","goods_num":"20","goods_id":"23"},{"gid":61,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463033420597282.png","title":"我是智能一体测试 白灰 白色 黑色 白灰 灰黑 灰蓝","market_price":"200","vip_price":"180","sales":"0","goods_num":"100","goods_id":"23"},{"gid":60,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463024460587112.png","title":"李朝辉摄像机 红色 大","market_price":"120","vip_price":"110","sales":"0","goods_num":"100","goods_id":"19"},{"gid":36,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777260364961.png","title":"","market_price":"208","vip_price":"152","sales":"0","goods_num":"96","goods_id":"12"}]}
     */

    /**
     * total : 35
     * list : [{"gid":119,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1464052919827865.jpg","title":"配套商品 蓝色 1号","market_price":"1222","vip_price":"111","sales":"0","goods_num":"100","goods_id":"44"},{"gid":118,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987660685629.jpg","title":"控制一体机 红色 大","market_price":"56","vip_price":"56","sales":"0","goods_num":"55","goods_id":"25"},{"gid":117,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987391670250.jpg","title":"测试多级分类 紫色 蓝色","market_price":"44","vip_price":"44","sales":"0","goods_num":"44","goods_id":"29"},{"gid":98,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463450790614277.jpg","title":"海豚 绿色 大","market_price":"50","vip_price":"45","sales":"0","goods_num":"5","goods_id":"33"},{"gid":116,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463733446477384.png","title":"测试试试 红色","market_price":"56","vip_price":"45","sales":"0","goods_num":"99","goods_id":"42"},{"gid":115,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463721593670173.png","title":"摩仕龙 MSSLOO 磁力锁 双门明装 大吸力180公斤（350磅）*2 电磁锁 180kg（350Lbs）X2  PT-180MAS 180公斤*2 双门明装无信号 12V","market_price":"160","vip_price":"133","sales":"0","goods_num":"100","goods_id":"41"},{"gid":114,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463636195824518.png","title":"门禁锁 白色 带信号","market_price":"33","vip_price":"11","sales":"0","goods_num":"100","goods_id":"39"},{"gid":113,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463635773103152.png","title":"摩仕龙 MSSLOO磁力锁 单门明装 大吸力180公斤（350磅） 单联电磁锁 180kg（350Lbs） MS-918MA 180公斤 单门明装无信号","market_price":"100","vip_price":"90","sales":"0","goods_num":"100","goods_id":"38"},{"gid":112,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png","title":"门禁 红色","market_price":"80","vip_price":"70","sales":"0","goods_num":"16","goods_id":"37"},{"gid":111,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463543909749647.png","title":"测试 ID 非防水","market_price":"66","vip_price":"55","sales":"0","goods_num":"99","goods_id":"36"},{"gid":110,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463472725613154.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 T35-ID ID 非防水 银色","market_price":"169","vip_price":"66","sales":"0","goods_num":"50","goods_id":"35"},{"gid":106,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458248767782.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 IC 非防水 银色","market_price":"169","vip_price":"100","sa les":"0","goods_num":"89","goods_id":"32"},{"gid":105,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458009950668.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 土豪金","market_price":"169","vip_price":"110","sales":"0","goods_num":"5","goods_id":"32"},{"gid":104,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457778875910.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 青古铜","market_price":"160","vip_price":"120","sales":"0","goods_num":"95","goods_id":"32"},{"gid":103,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457584517713.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 银色","market_price":"169","vip_price":"130","sales":"0","goods_num":"89","goods_id":"32"},{"gid":102,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463455668377551.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 IC 非防水 银色","market_price":"169","vip_price":"111","sales":"0","goods_num":"55","goods_id":"32"},{"gid":101,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453599278435.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 土豪金","market_price":"169","vip_price":"132","sales":"0","goods_num":"96","goods_id":"32"},{"gid":100,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453437774594.jpg","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","market_price":"23","vip_price":"32","sales":"0","goods_num":"23","goods_id":"32"},{"gid":99,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","market_price":"169","vip_price":"130","sales":"0","goods_num":"44","goods_id":"32"},{"gid":79,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463389100312485.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","market_price":"169","vip_price":"130","sales":"0","goods_num":"321307","goods_id":"32"},{"gid":78,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463386181840909.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T30-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 白古铜","market_price":"169","vip_price":"130","sales":"0","goods_num":"100321321","goods_id":"28"},{"gid":75,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463381676488462.jpg","title":"lucy 红色","market_price":"2","vip_price":"2","sales":"0","goods_num":"23213213","goods_id":"30"},{"gid":74,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463379323103029.png","title":"摩仕龙MSSLOO 触摸型金属门禁机 T30-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 白古铜","market_price":"169","vip_price":"130","sales":"0","goods_num":"99","goods_id":"28"},{"gid":72,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463370381230467.jpg","title":"王朝晖 红色","market_price":"3","vip_price":"3","sales":"0","goods_num":"3321312","goods_id":"27"},{"gid":71,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463370034617879.jpg","title":"李朝辉 红色","market_price":"2","vip_price":"2","sales":"0","goods_num":"2","goods_id":"26"},{"gid":70,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463369416674576.jpg","title":"李朝辉 红色","market_price":"1","vip_price":"1","sales":"0","goods_num":"1","goods_id":"26"},{"gid":69,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463120961285435.png","title":"门禁读卡器测试 红 白","market_price":"100","vip_price":"200","sales":"0","goods_num":"2","goods_id":"24"},{"gid":68,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463104315777507.png","title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 280公斤 单门明装带信号","market_price":"169","vip_price":"130","sales":"0","goods_num":"100","goods_id":"21"},{"gid":67,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463104204486762.png","title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 280公斤 单门明装无信号","market_price":"156","vip_price":"120","sales":"0","goods_num":"100","goods_id":"21"},{"gid":66,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463103054353441.png","title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 250公斤 单门明装带信号","market_price":"130","vip_price":"100","sales":"0","goods_num":"100","goods_id":"21"},{"gid":62,"goods_img":null,"title":"摩仕龙 MSSLOO 单门明装 大吸力280公斤（600磅） 磁力锁 单联电磁锁 280kg（600Lbs） 250公斤 单门明装无信号","market_price":"117","vip_price":"90","sales":"0","goods_num":"100","goods_id":"21"},{"gid":65,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463103865339644.png","title":"我是智能一体测试 灰黑 灰蓝","market_price":"111","vip_price":"101","sales":"0","goods_num":"20","goods_id":"23"},{"gid":61,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463033420597282.png","title":"我是智能一体测试 白灰 白色 黑色 白灰 灰黑 灰蓝","market_price":"200","vip_price":"180","sales":"0","goods_num":"100","goods_id":"23"},{"gid":60,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463024460587112.png","title":"李朝辉摄像机 红色 大","market_price":"120","vip_price":"110","sales":"0","goods_num":"100","goods_id":"19"},{"gid":36,"goods_img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777260364961.png","title":"","market_price":"208","vip_price":"152","sales":"0","goods_num":"96","goods_id":"12"}]
     */

    private GoodsList ResultData;

    public GoodsList getResultData() {
        return ResultData;
    }

    public void setResultData(GoodsList ResultData) {
        this.ResultData = ResultData;
    }

    public static class GoodsList implements Serializable {
        private int total;
        /**
         * gid : 119
         * goods_img : http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1464052919827865.jpg
         * title : 配套商品 蓝色 1号
         * market_price : 1222
         * vip_price : 111
         * sales : 0
         * goods_num : 100
         * goods_id : 44
         */

        private List<Goods> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Goods> getList() {
            return list;
        }

        public void setList(List<Goods> list) {
            this.list = list;
        }

        public static class Goods implements Serializable {
            private int gid;
            private String goods_img;
            private String title;
            private String market_price;
            private String vip_price;
            private String sales;
            private String goods_num;
            private String goods_id;

            public int getGid() {
                return gid;
            }

            public void setGid(int gid) {
                this.gid = gid;
            }

            public String getGoods_img() {
                return goods_img;
            }

            public void setGoods_img(String goods_img) {
                this.goods_img = goods_img;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
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

            public String getSales() {
                return sales;
            }

            public void setSales(String sales) {
                this.sales = sales;
            }

            public String getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(String goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }

            @Override
            public String toString() {
                return "Goods{" +
                        "gid=" + gid +
                        ", goods_img='" + goods_img + '\'' +
                        ", title='" + title + '\'' +
                        ", market_price='" + market_price + '\'' +
                        ", vip_price='" + vip_price + '\'' +
                        ", sales='" + sales + '\'' +
                        ", goods_num='" + goods_num + '\'' +
                        ", goods_id='" + goods_id + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "GoodsList{" +
                    "total=" + total +
                    ", list=" + list +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ShopGoodsShowResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
