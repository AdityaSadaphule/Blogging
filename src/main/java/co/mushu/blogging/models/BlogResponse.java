package co.mushu.blogging.models;

public class BlogResponse {
    private String username;
    private String blogId;
    private String subject;
    private String content;
    private Long likes;

    public BlogResponse(String username, String blogId, String subject, String content,Long likes) {
        this.username = username;
        this.blogId = blogId;
        this.subject = subject;
        this.content = content;
        this.likes = likes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
