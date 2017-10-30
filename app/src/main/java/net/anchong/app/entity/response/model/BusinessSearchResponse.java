package net.anchong.app.entity.response.model;

import net.anchong.app.http.domain.ResponseResult;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/4/19.
 */
public class BusinessSearchResponse extends ResponseResult implements Serializable {


    /**
     * ResultData : {"tag":["探测监控\n","防护保障\n","探测报警\n","弱店工程\n","呼救器","楼宇对讲","快速通道","智能消费","智能卡","相关线缆","智能家居","入侵探测","隔离护栏","道路施工","交通警示灯","交通设施","通讯设备","救护器材","防护器材","制服器材","消防器材","建筑防火","消防配套","现场设备","控制设备","监控配套","语音视频","停车场设施"],"area":["北京市","上海市","武汉市","天津市","保定市","衡水市","石家庄","邢台市"]}
     */

    private ResultDataBean ResultData;

    public ResultDataBean getResultData() {
        return ResultData;
    }

    public void setResultData(ResultDataBean ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable {
        private List<String> tag;
        private List<String> area;

        public List<String> getTag() {
            return tag;
        }

        public void setTag(List<String> tag) {
            this.tag = tag;
        }

        public List<String> getArea() {
            return area;
        }

        public void setArea(List<String> area) {
            this.area = area;
        }

        @Override
        public String toString() {
            return "ResultDataBean{" +
                    "tag=" + tag +
                    ", area=" + area +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "BusinessSearchResponse{" +
                "ResultData=" + ResultData +
                '}';
    }
}
