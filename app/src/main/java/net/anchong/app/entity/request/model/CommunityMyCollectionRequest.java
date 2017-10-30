package net.anchong.app.entity.request.model;

/**
 * 收藏聊聊请求
 * Created by baishixin on 16/6/21.
 */
public class CommunityMyCollectionRequest extends ParamModel {


    /**
     * page : 分页(首页是1)
     */

    private String page;

    public CommunityMyCollectionRequest() {
    }

    public CommunityMyCollectionRequest(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }
}
