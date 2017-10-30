package net.anchong.app.entity.request.model;

/**
 * 社区聊聊直接发表评论请求
 * Created by baishixin on 16/6/16.
 */
public class CommuinityCommentRequest extends ParamModel {


    /**
     * chat_id : 聊聊ID
     * content : 评论内容
     */

    private String chat_id;
    private String content;

    public CommuinityCommentRequest() {
    }

    public CommuinityCommentRequest(String chat_id, String content) {
        this.chat_id = chat_id;
        this.content = content;
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
