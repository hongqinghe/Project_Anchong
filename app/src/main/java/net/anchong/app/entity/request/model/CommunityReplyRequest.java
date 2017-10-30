package net.anchong.app.entity.request.model;

/**
 * 社区聊聊评论回复请求
 * Created by baishixin on 16/6/16.
 */
public class CommunityReplyRequest extends ParamModel {


    /**
     * comid : 评论ID
     * name : 被回复的那条评论人的姓名
     * chat_id : 聊聊id
     * content : 评论内容
     */

    private String comid;
    private String name;
    private String chat_id;
    private String content;

    public CommunityReplyRequest() {
    }

    public CommunityReplyRequest(String comid, String name, String chat_id, String content) {
        this.comid = comid;
        this.name = name;
        this.chat_id = chat_id;
        this.content = content;
    }

    public String getComid() {
        return comid;
    }

    public void setComid(String comid) {
        this.comid = comid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
