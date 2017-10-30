package net.anchong.app.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by baishixin on 16/6/27.
 */
public class BusinessModel implements Serializable {

    private int bid;
    private String phone;
    private String contact;
    private String title;
    private String content;
    private String tag;
    private String tags;
    private String created_at;
    private List<String> pic;

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public List<String> getPic() {
        return pic;
    }

    public void setPic(List<String> pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "ListEntity{" +
                "bid=" + bid +
                ", phone='" + phone + '\'' +
                ", contact='" + contact + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", tag='" + tag + '\'' +
                ", tags='" + tags + '\'' +
                ", created_at='" + created_at + '\'' +
                ", pic=" + pic +
                '}';
    }
}
