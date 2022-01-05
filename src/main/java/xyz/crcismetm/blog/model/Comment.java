package xyz.crcismetm.blog.model;

public class Comment {
    private Integer id;
    private String userID;
    private Integer articleID;
    private String content;
    private Integer edited;
    private String lastAccessedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getArticleID() {
        return articleID;
    }

    public void setArticleID(Integer articleID) {
        this.articleID = articleID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getEdited() {
        return edited;
    }

    public void setEdited(Integer edited) {
        this.edited = edited;
    }

    public String getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(String lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userID='" + userID + '\'' +
                ", articleID=" + articleID +
                ", content='" + content + '\'' +
                ", edited=" + edited +
                ", lastAccessedTime='" + lastAccessedTime + '\'' +
                '}';
    }
}
