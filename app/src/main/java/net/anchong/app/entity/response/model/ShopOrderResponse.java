package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/5/24.
 */
public class ShopOrderResponse extends ResponseResult implements Serializable {


    /**
     * ResultData : {"total":30,"list":[{"order_id":113,"order_num":"9638131463991360","state":"1","created_at":"2016-05-23 16:16:01","total_price":"71","name":"范小二","phone":"15810037653","address":"北京北京市海淀区唐家岭1号院it兄弟连教育","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"控制一体机 红色 大","goods_num":"1","goods_price":"56","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987660685629.jpg"}]},{"order_id":105,"order_num":"4641463982870","state":"1","created_at":"2016-05-23 13:54:31","total_price":"184","name":"fanxiao2","phone":"15810037653","address":"上海上海市闵行区103hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"169","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"}]},{"order_id":104,"order_num":"9641463982866","state":"1","created_at":"2016-05-23 13:54:27","total_price":"71","name":"fanxiao2","phone":"15810037653","address":"上海上海市闵行区103hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"测试试试 红色","goods_num":"1","goods_price":"56","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463733446477384.png"}]},{"order_id":103,"order_num":"8341463730027","state":"1","created_at":"2016-05-20 15:40:27","total_price":"960","name":"把","phone":"13333333333","address":"北京北京市东城区可owl嗯哦哦咯哦哦OK啦","invoice":"啃老快乐哦","customer":"010-57456017 (24h)","tname":"13691027868","goods":[{"goods_name":"门禁 红色","goods_num":"12","goods_price":"80","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png"}]},{"order_id":101,"order_num":"2221463712766","state":"6","created_at":"2016-05-20 10:52:47","total_price":"70","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"呵呵过几年","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色 大","goods_num":"10","goods_price":"7","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463464818201766.jpg"}]},{"order_id":99,"order_num":"4421463665241","state":"1","created_at":"2016-05-19 21:40:41","total_price":"7","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色 大","goods_num":"1","goods_price":"7","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463464818201766.jpg"}]},{"order_id":98,"order_num":"5221463665204","state":"1","created_at":"2016-05-19 21:40:04","total_price":"70","name":"白世鑫","phone":"18211168676","address":"北京市北京市昌平区给大家开的就是就是卡就打","invoice":"继续继续奖学金","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色 大","goods_num":"10","goods_price":"7","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463464818201766.jpg"}]},{"order_id":92,"order_num":"6621463648711","state":"1","created_at":"2016-05-19 17:05:11","total_price":"6500","name":"小白","phone":"13333333333","address":"甘肃省白银市靖远县睡觉爱上就像你睡觉睡觉","invoice":"还是计算机阿克苏快上课","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"ID 非防水 青古铜","goods_num":"50","goods_price":"130","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"}]},{"order_id":91,"order_num":"6221463648497","state":"1","created_at":"2016-05-19 17:01:36","total_price":"1400","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"北京异地有教育咨询有限公司","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"20","goods_price":"70","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png"}]},{"order_id":90,"order_num":"1241463638422","state":"6","created_at":"2016-05-19 14:13:43","total_price":"1500","name":"fanxiao2","phone":"15810037653","address":"上海上海市闵行区103hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 银色","goods_num":"11","goods_price":"100","goods_type":"ID 防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457584517713.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 土豪金","goods_num":"4","goods_price":"100","goods_type":"ID 防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458009950668.png"}]},{"order_id":89,"order_num":"4541463638318","state":"1","created_at":"2016-05-19 14:11:59","total_price":"1300","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"woshifapiaotaitou","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 青古铜","goods_num":"4","goods_price":"100","goods_type":"ID 防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457778875910.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 土豪金","goods_num":"3","goods_price":"100","goods_type":"ID 非防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453599278435.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"2","goods_price":"100","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","goods_num":"4","goods_price":"100","goods_type":"ID 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463389100312485.png"}]},{"order_id":85,"order_num":"7321463624502","state":"1","created_at":"2016-05-19 10:21:42","total_price":"9900","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"ID 防水 土豪金","goods_num":"90","goods_price":"110","goods_type":"ID 防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458009950668.png"}]},{"order_id":83,"order_num":"7521463624436","state":"1","created_at":"2016-05-19 10:20:36","total_price":"2800","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"40","goods_price":"70","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png"}]},{"order_id":79,"order_num":"7721463624378","state":"1","created_at":"2016-05-19 10:19:38","total_price":"3300","name":"小白","phone":"13333333333","address":"甘肃省白银市靖远县睡觉爱上就像你睡觉睡觉","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"ID 非防水 银色","goods_num":"50","goods_price":"66","goods_type":"ID 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463472725613154.png"}]},{"order_id":78,"order_num":"7231463623624","state":"1","created_at":"2016-05-19 10:07:04","total_price":"429","name":"范小二","phone":"15810037653","address":"北京北京市海淀区唐家岭1号院it兄弟连教育","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 土豪金","goods_num":"1","goods_price":"100","goods_type":"ID 防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/ goods/img/goods/1463458009950668.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 青古铜","goods_num":"1","goods_price":"160","goods_type":"ID 防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457778875910.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"169","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"}]},{"order_id":77,"order_num":"3341463623069","state":"6","created_at":"2016-05-19 09:57:50","total_price":"215","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463389100312485.png"}]},{"order_id":75,"order_num":"8441463622798","state":"6","created_at":"2016-05-19 09:53:18","total_price":"390","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T31-ID 锌合金门禁一体机 刷卡机 密码机 外接读卡器  IC 非防水 银色","goods_num":"3","goods_price":"130","goods_type":"IC 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777260364961.png"}]},{"order_id":74,"order_num":"9841463622793","state":"6","created_at":"2016-05-19 09:53:13","total_price":"215","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 土豪金","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453599278435.png"}]},{"order_id":69,"order_num":"3221463390736","state":"6","created_at":"2016-05-16 17:25:40","total_price":"220","name":"上将","phone":"13888888888","address":"黑龙江省双鸭山市友谊县死啊就睡吧子阿加莎","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"绿色","goods_num":"55","goods_price":"4","goods_type":"绿色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382038141746.jpg"}]},{"order_id":67,"order_num":"1021463390631","state":"6","created_at":"2016-05-16 17:23:54","total_price":"5","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"1","goods_price":"5","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382173605726.jpg"}]},{"order_id":66,"order_num":"1621463390617","state":"6","created_at":"2016-05-16 17:23:41","total_price":"5","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"1","goods_price":"5","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382173605726.jpg"}]},{"order_id":63,"order_num":"3221463390588","state":"6","created_at":"2016-05-16 17:23:12","total_price":"5","name":"小白","phone":"13333333333","address":"甘肃省白银市靖远县睡觉爱上就像你睡觉睡觉","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"1","goods_price":"5","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382173605726.jpg"}]},{"order_id":62,"order_num":"2921463390538","state":"6","created_at":"2016-05-16 17:22:21","total_price":"16","name":"上将","phone":"13888888888","address":"黑龙江省双鸭山市友谊县死啊就睡吧子阿加莎","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"绿色","goods_num":"4","goods_price":"4","goods_type":"绿色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382038141746.jpg"}]},{"order_id":56,"order_num":"1921463382770","state":"6","created_at":"2016-05-16 15:12:53","total_price":"16","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"绿色","goods_num":"4","goods_price":"4","goods_type":"绿色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382038141746.jpg"}]},{"order_id":50,"order_num":"5441463023509","state":"6","created_at":"2016-05-12 11:25:09","total_price":"988","name":"把","phone":"13333333333","address":"北京北京市东城区可owl嗯哦哦咯哦哦OK啦","invoice":"","customer":"010-57456017 (24h)","tname":"13691027868","goods":[{"goods_name":"IC 防水 银色","goods_num":"3","goods_price":"247","goods_type":"IC 防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777555880622.png"},{"goods_name":"IC 防水 白古铜","goods_num":"1","goods_price":"247","goods_type":"IC 防水 白古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777628417868.png"}]},{"order_id":48,"order_num":"5931462953768","state":"6","created_at":"2016-05-11 16:02:48","total_price":"360","name":"范小二","phone":"15810037653","address":"北京北京市海淀区唐家岭1号院it兄弟连教育","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"3","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]},{"order_id":47,"order_num":"7141462939527","state":"3","created_at":"2016-05-11 12:05:28","total_price":"451","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"Beijingshi","customer":"010-57456017 (24h)","tname":"范小san","goods":[{"goods_name":"添加测试商品 白色 黑色","goods_num":"1","goods_price":"211","goods_type":"白色 黑色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462864772449929.png"},{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"2","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]},{"order_id":46,"order_num":"2541462937418","state":"6","created_at":"2016-05-11 11:30:18","total_price":"331","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"Beijingshi","customer":"010-57456017 (24h)","tname":"范小san","goods":[{"goods_name":"添加测试商品 白色 黑色","goods_num":"1","goods_price":"211","goods_type":"白色 黑色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462864772449929.png"},{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"1","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]},{"order_id":45,"order_num":"2831462892174","state":"4","created_at":"2016-05-10 22:56:15","total_price":"145","name":"小二","phone":"17777838278","address":"北京北京市大兴区还是好多好多好多好多11111111","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T31-ID 锌合金门禁一体机 刷卡机 密码机 外接读卡器  IC 非防水 银色","goods_num":"1","goods_price":"130","goods_type":"IC 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777260364961.png"}]},{"order_id":44,"order_num":"1031462872397","state":"6","created_at":"2016-05-10 17:26:38","total_price":"135","name":"小二","phone":"17777838278","address":"北京北京市大兴区还是好多好多好多好多11111111","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"1","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]}]}
     */

    /**
     * total : 30
     * list : [{"order_id":113,"order_num":"9638131463991360","state":"1","created_at":"2016-05-23 16:16:01","total_price":"71","name":"范小二","phone":"15810037653","address":"北京北京市海淀区唐家岭1号院it兄弟连教育","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"控制一体机 红色 大","goods_num":"1","goods_price":"56","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987660685629.jpg"}]},{"order_id":105,"order_num":"4641463982870","state":"1","created_at":"2016-05-23 13:54:31","total_price":"184","name":"fanxiao2","phone":"15810037653","address":"上海上海市闵行区103hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"169","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"}]},{"order_id":104,"order_num":"9641463982866","state":"1","created_at":"2016-05-23 13:54:27","total_price":"71","name":"fanxiao2","phone":"15810037653","address":"上海上海市闵行区103hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"测试试试 红色","goods_num":"1","goods_price":"56","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463733446477384.png"}]},{"order_id":103,"order_num":"8341463730027","state":"1","created_at":"2016-05-20 15:40:27","total_price":"960","name":"把","phone":"13333333333","address":"北京北京市东城区可owl嗯哦哦咯哦哦OK啦","invoice":"啃老快乐哦","customer":"010-57456017 (24h)","tname":"13691027868","goods":[{"goods_name":"门禁 红色","goods_num":"12","goods_price":"80","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png"}]},{"order_id":101,"order_num":"2221463712766","state":"6","created_at":"2016-05-20 10:52:47","total_price":"70","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"呵呵过几年","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色 大","goods_num":"10","goods_price":"7","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463464818201766.jpg"}]},{"order_id":99,"order_num":"4421463665241","state":"1","created_at":"2016-05-19 21:40:41","total_price":"7","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色 大","goods_num":"1","goods_price":"7","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463464818201766.jpg"}]},{"order_id":98,"order_num":"5221463665204","state":"1","created_at":"2016-05-19 21:40:04","total_price":"70","name":"白世鑫","phone":"18211168676","address":"北京市北京市昌平区给大家开的就是就是卡就打","invoice":"继续继续奖学金","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色 大","goods_num":"10","goods_price":"7","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463464818201766.jpg"}]},{"order_id":92,"order_num":"6621463648711","state":"1","created_at":"2016-05-19 17:05:11","total_price":"6500","name":"小白","phone":"13333333333","address":"甘肃省白银市靖远县睡觉爱上就像你睡觉睡觉","invoice":"还是计算机阿克苏快上课","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"ID 非防水 青古铜","goods_num":"50","goods_price":"130","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"}]},{"order_id":91,"order_num":"6221463648497","state":"1","created_at":"2016-05-19 17:01:36","total_price":"1400","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"北京异地有教育咨询有限公司","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"20","goods_price":"70","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png"}]},{"order_id":90,"order_num":"1241463638422","state":"6","created_at":"2016-05-19 14:13:43","total_price":"1500","name":"fanxiao2","phone":"15810037653","address":"上海上海市闵行区103hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 银色","goods_num":"11","goods_price":"100","goods_type":"ID 防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457584517713.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 土豪金","goods_num":"4","goods_price":"100","goods_type":"ID 防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458009950668.png"}]},{"order_id":89,"order_num":"4541463638318","state":"1","created_at":"2016-05-19 14:11:59","total_price":"1300","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"woshifapiaotaitou","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 青古铜","goods_num":"4","goods_price":"100","goods_type":"ID 防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457778875910.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 土豪金","goods_num":"3","goods_price":"100","goods_type":"ID 非防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453599278435.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"2","goods_price":"100","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","goods_num":"4","goods_price":"100","goods_type":"ID 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463389100312485.png"}]},{"order_id":85,"order_num":"7321463624502","state":"1","created_at":"2016-05-19 10:21:42","total_price":"9900","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"ID 防水 土豪金","goods_num":"90","goods_price":"110","goods_type":"ID 防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463458009950668.png"}]},{"order_id":83,"order_num":"7521463624436","state":"1","created_at":"2016-05-19 10:20:36","total_price":"2800","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"40","goods_price":"70","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463544209235817.png"}]},{"order_id":79,"order_num":"7721463624378","state":"1","created_at":"2016-05-19 10:19:38","total_price":"3300","name":"小白","phone":"13333333333","address":"甘肃省白银市靖远县睡觉爱上就像你睡觉睡觉","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"ID 非防水 银色","goods_num":"50","goods_price":"66","goods_type":"ID 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463472725613154.png"}]},{"order_id":78,"order_num":"7231463623624","state":"1","created_at":"2016-05-19 10:07:04","total_price":"429","name":"范小二","phone":"15810037653","address":"北京北京市海淀区唐家岭1号院it兄弟连教育","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 土豪金","goods_num":"1","goods_price":"100","goods_type":"ID 防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/ goods/img/goods/1463458009950668.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 防水 青古铜","goods_num":"1","goods_price":"160","goods_type":"ID 防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463457778875910.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"169","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"}]},{"order_id":77,"order_num":"3341463623069","state":"6","created_at":"2016-05-19 09:57:50","total_price":"215","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 银色","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463389100312485.png"}]},{"order_id":75,"order_num":"8441463622798","state":"6","created_at":"2016-05-19 09:53:18","total_price":"390","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T31-ID 锌合金门禁一体机 刷卡机 密码机 外接读卡器  IC 非防水 银色","goods_num":"3","goods_price":"130","goods_type":"IC 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777260364961.png"}]},{"order_id":74,"order_num":"9841463622793","state":"6","created_at":"2016-05-19 09:53:13","total_price":"215","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"","customer":"010-57456017 (24h)","tname":"范小er","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 青古铜","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 青古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463451961555647.png"},{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T33-ID 锌合金门禁一体机 刷卡机 密码机 可外接读卡器或独立当做读卡器使用 ID 非防水 土豪金","goods_num":"1","goods_price":"100","goods_type":"ID 非防水 土豪金","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463453599278435.png"}]},{"order_id":69,"order_num":"3221463390736","state":"6","created_at":"2016-05-16 17:25:40","total_price":"220","name":"上将","phone":"13888888888","address":"黑龙江省双鸭山市友谊县死啊就睡吧子阿加莎","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"绿色","goods_num":"55","goods_price":"4","goods_type":"绿色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382038141746.jpg"}]},{"order_id":67,"order_num":"1021463390631","state":"6","created_at":"2016-05-16 17:23:54","total_price":"5","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"1","goods_price":"5","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382173605726.jpg"}]},{"order_id":66,"order_num":"1621463390617","state":"6","created_at":"2016-05-16 17:23:41","total_price":"5","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"1","goods_price":"5","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382173605726.jpg"}]},{"order_id":63,"order_num":"3221463390588","state":"6","created_at":"2016-05-16 17:23:12","total_price":"5","name":"小白","phone":"13333333333","address":"甘肃省白银市靖远县睡觉爱上就像你睡觉睡觉","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"红色","goods_num":"1","goods_price":"5","goods_type":"红色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382173605726.jpg"}]},{"order_id":62,"order_num":"2921463390538","state":"6","created_at":"2016-05-16 17:22:21","total_price":"16","name":"上将","phone":"13888888888","address":"黑龙江省双鸭山市友谊县死啊就睡吧子阿加莎","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"绿色","goods_num":"4","goods_price":"4","goods_type":"绿色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382038141746.jpg"}]},{"order_id":56,"order_num":"1921463382770","state":"6","created_at":"2016-05-16 15:12:53","total_price":"16","name":"白世鑫","phone":"18211168676","address":"null北京市北京市昌平区给大家开的就是就是卡就打","invoice":"","customer":"010-57456017 (24h)","tname":"18911607218","goods":[{"goods_name":"绿色","goods_num":"4","goods_price":"4","goods_type":"绿色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463382038141746.jpg"}]},{"order_id":50,"order_num":"5441463023509","state":"6","created_at":"2016-05-12 11:25:09","total_price":"988","name":"把","phone":"13333333333","address":"北京北京市东城区可owl嗯哦哦咯哦哦OK啦","invoice":"","customer":"010-57456017 (24h)","tname":"13691027868","goods":[{"goods_name":"IC 防水 银色","goods_num":"3","goods_price":"247","goods_type":"IC 防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777555880622.png"},{"goods_name":"IC 防水 白古铜","goods_num":"1","goods_price":"247","goods_type":"IC 防水 白古铜","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777628417868.png"}]},{"order_id":48,"order_num":"5931462953768","state":"6","created_at":"2016-05-11 16:02:48","total_price":"360","name":"范小二","phone":"15810037653","address":"北京北京市海淀区唐家岭1号院it兄弟连教育","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"3","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]},{"order_id":47,"order_num":"7141462939527","state":"3","created_at":"2016-05-11 12:05:28","total_price":"451","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"Beijingshi","customer":"010-57456017 (24h)","tname":"范小san","goods":[{"goods_name":"添加测试商品 白色 黑色","goods_num":"1","goods_price":"211","goods_type":"白色 黑色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462864772449929.png"},{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"2","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]},{"order_id":46,"order_num":"2541462937418","state":"6","created_at":"2016-05-11 11:30:18","total_price":"331","name":"haha","phone":"18000000000","address":"北京北京市西城区101hao","invoice":"Beijingshi","customer":"010-57456017 (24h)","tname":"范小san","goods":[{"goods_name":"添加测试商品 白色 黑色","goods_num":"1","goods_price":"211","goods_type":"白色 黑色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462864772449929.png"},{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"1","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]},{"order_id":45,"order_num":"2831462892174","state":"4","created_at":"2016-05-10 22:56:15","total_price":"145","name":"小二","phone":"17777838278","address":"北京北京市大兴区还是好多好多好多好多11111111","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"摩仕龙MSSLOO 触摸型金属门禁机 T31-ID 锌合金门禁一体机 刷卡机 密码机 外接读卡器  IC 非防水 银色","goods_num":"1","goods_price":"130","goods_type":"IC 非防水 银色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462777260364961.png"}]},{"order_id":44,"order_num":"1031462872397","state":"6","created_at":"2016-05-10 17:26:38","total_price":"135","name":"小二","phone":"17777838278","address":"北京北京市大兴区还是好多好多好多好多11111111","invoice":"","customer":"010-57456017 (24h)","tname":"范骏","goods":[{"goods_name":"商品新图 粑粑色 屎黄色","goods_num":"1","goods_price":"120","goods_type":"粑粑色 屎黄色","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1462865623164917.png"}]}]
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
         * order_id : 113
         * order_num : 9638131463991360
         * state : 1
         * created_at : 2016-05-23 16:16:01
         * total_price : 71
         * name : 范小二
         * phone : 15810037653
         * address : 北京北京市海淀区唐家岭1号院it兄弟连教育
         * invoice :
         * customer : 010-57456017 (24h)
         * tname : 范骏
         * goods : [{"goods_name":"控制一体机 红色 大","goods_num":"1","goods_price":"56","goods_type":"红色 大","img":"http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987660685629.jpg"}]
         */

        private List<Order> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<Order> getList() {
            return list;
        }

        public void setList(List<Order> list) {
            this.list = list;
        }

        public static class Order implements Serializable {
            private int order_id;
            private String order_num;
            private String state;
            private String created_at;
            private String total_price;
            private String name;
            private String phone;
            private String address;
            private String invoice;
            private String customer;
            private String tname;
            /**
             * goods_name : 控制一体机 红色 大
             * goods_num : 1
             * goods_price : 56
             * goods_type : 红色 大
             * img : http://anchongres.oss-cn-hangzhou.aliyuncs.com/goods/img/goods/1463987660685629.jpg
             */

            private List<Goods> goods;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getOrder_num() {
                return order_num;
            }

            public void setOrder_num(String order_num) {
                this.order_num = order_num;
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

            public String getInvoice() {
                return invoice;
            }

            public void setInvoice(String invoice) {
                this.invoice = invoice;
            }

            public String getCustomer() {
                return customer;
            }

            public void setCustomer(String customer) {
                this.customer = customer;
            }

            public String getTname() {
                return tname;
            }

            public void setTname(String tname) {
                this.tname = tname;
            }

            public List<Goods> getGoods() {
                return goods;
            }

            public void setGoods(List<Goods> goods) {
                this.goods = goods;
            }

            public static class Goods implements Serializable {
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
                    return "Goods{" +
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
                return "Order{" +
                        "order_id=" + order_id +
                        ", order_num='" + order_num + '\'' +
                        ", state='" + state + '\'' +
                        ", created_at='" + created_at + '\'' +
                        ", total_price='" + total_price + '\'' +
                        ", name='" + name + '\'' +
                        ", phone='" + phone + '\'' +
                        ", address='" + address + '\'' +
                        ", invoice='" + invoice + '\'' +
                        ", customer='" + customer + '\'' +
                        ", tname='" + tname + '\'' +
                        ", goods=" + goods +
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
        return "ShopOrderResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
