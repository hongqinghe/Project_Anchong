package net.anchong.app.entity.request.model;

import java.util.List;

/**
 * 个人商机修改时的请求参数
 * Created by baishixin on 16/4/14.
 */
public class MyBusinessEditParamModel extends ParamModel {


    /**
     * bid : 该商机信息的id
     * type : 类型(数字，数据表中的数据)
     * title : 商机标题
     * content : 工程简介
     * tag : 类别标签
     * tags : 自定义标签，每个标签空格隔开
     * pic : ["图片","数组"]
     */

    private String bid;
    private String type;
    private String title;
    private String content;
    private String tag;
    private String tags;
    private List<String> pic;

    public MyBusinessEditParamModel() {
    }

    public MyBusinessEditParamModel(String bid, String type, String title, String content, String tag, String tags, List<String> pic) {
        this.bid = bid;
        this.type = type;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.tags = tags;
        this.pic = pic;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }
}
