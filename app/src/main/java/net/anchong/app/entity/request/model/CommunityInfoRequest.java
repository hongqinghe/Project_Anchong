package net.anchong.app.entity.request.model;

/**
 * 聊聊详情
 * Created by baishixin on 16/6/14.
 */
public class CommunityInfoRequest extends ParamModel {


    /**
     * chat_id : 聊聊ID
     */

    private String chat_id;

    public CommunityInfoRequest() {
    }

    public CommunityInfoRequest(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }
}
