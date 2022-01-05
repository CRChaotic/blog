package xyz.crcismetm.blog.model;

public class Article {
    private Integer id;
    private String title;
    private String content;
    private String createdTime;
    private String lastAccessedTime;
    private String subject;
    private Integer status;
    private Integer commentable;
    private String userID;

    public Integer getCommentable() {
        return commentable;
    }

    public void setCommentable(Integer commentable) {
        this.commentable = commentable;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getLastAccessedTime() {
        return lastAccessedTime;
    }

    public void setLastAccessedTime(String lastAccessedTime) {
        this.lastAccessedTime = lastAccessedTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", lastAccessedTime='" + lastAccessedTime + '\'' +
                ", subject='" + subject + '\'' +
                ", status=" + status +
                ", commentable=" + commentable +
                ", userID='" + userID + '\'' +
                '}';
    }
}
