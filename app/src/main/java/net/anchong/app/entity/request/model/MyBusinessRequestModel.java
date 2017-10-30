package net.anchong.app.entity.request.model;

import java.io.Serializable;
import java.util.List;

/**
 * 发布个人商机的请求模型
 * Created by baishixin on 16/4/12.
 */
public class MyBusinessRequestModel extends ParamModel implements Serializable {

    /**
     * type : 1
     * title : 商机标题
     * content : 工程简介
     * tag : 类别标签
     * tags : 自定义标签，每个标签空格隔开
     * pic : ["图片","数组"]
     */

    private int type;
    private String title;
    private String content;
    private String tag;
    private String tags;
    private List<String> pic;

    public MyBusinessRequestModel() {
    }

    public MyBusinessRequestModel(int type, String title, String content, String tag, String tags, List<String> pic) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.tag = tag;
        this.tags = tags;
        this.pic = pic;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
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
