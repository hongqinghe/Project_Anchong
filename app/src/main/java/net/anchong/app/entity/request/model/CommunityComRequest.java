package net.anchong.app.entity.request.model;

/**
 * 聊聊评论详情
 * Created by baishixin on 16/6/14.
 */
public class CommunityComRequest extends ParamModel {


    /**
     * chat_id : 聊聊ID
     * page : 1,分页（一页10个）
     */

    private String chat_id;
    private String page;

    public CommunityComRequest() {
    }

    public CommunityComRequest(String chat_id, String page) {
        this.chat_id = chat_id;
        this.page = page;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
