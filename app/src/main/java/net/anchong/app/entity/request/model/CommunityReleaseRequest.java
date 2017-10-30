package net.anchong.app.entity.request.model;

import java.io.Serializable;
import java.util.List;

/**
 * 发布聊聊请求
 * Created by baishixin on 16/6/30.
 */
public class CommunityReleaseRequest extends ParamModel implements Serializable{


    /**
     * title : 聊聊标题
     * content : 聊聊内容
     * tags : 自定义标签，每个标签空格隔开
     * pic : ["图片","数组"]
     */

    private String title;
    private String content;
    private String tags;
    private List<String> pic;

    public CommunityReleaseRequest() {
    }

    public CommunityReleaseRequest(String title, String content, String tags, List<String> pic) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.pic = pic;
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
