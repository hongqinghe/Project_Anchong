package net.anchong.app.entity.request.model;

/**
 * 社区首页聊聊展示信息请求
 * Created by baishixin on 16/6/12.
 */
public class CommunityShowRequest extends ParamModel {


    /**
     * tags : 标签搜索(没有搜索时传空)
     * page : 分页(首页是1)
     */

    private String tags;
    private String page;

    public CommunityShowRequest() {
    }

    public CommunityShowRequest(String tags, String page) {
        this.tags = tags;
        this.page = page;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
