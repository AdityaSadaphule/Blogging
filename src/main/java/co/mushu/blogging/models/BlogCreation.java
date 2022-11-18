package co.mushu.blogging.models;


import co.mushu.blogging.entities.UserProfile;

public class BlogCreation {
    private String username;
    private String subject;
    private String content;

    public BlogCreation(String subject, String content, String username) {
        this.subject = subject;
        this.content = content;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
