package co.mushu.blogging.entities;

import co.mushu.blogging.entities.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Blog {
    @Id
    private String blogId;
    @NotNull
    @ManyToOne
    @JsonManagedReference()
    private UserProfile createdBy;
    @NotNull
    private String content;
    @NotNull
    private String subject;
    @NotNull
    private Date createdDate;
    @NotNull
    private Long likes;


    public Blog(){

    }

    public Blog(String blogId, UserProfile createdBy, String content, String subject, Date createdDate, Long likes) {
        this.blogId = blogId;
        this.createdBy = createdBy;
        this.content = content;
        this.subject = subject;
        this.createdDate = createdDate;
        this.likes = likes;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public UserProfile getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserProfile createdBy) {
        this.createdBy = createdBy;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

}
