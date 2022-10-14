package co.mushu.blogging.models;

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
    @JsonManagedReference
    private Users createdBy;
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

    public Blog(String blogId, Users createdBy, String content, String subject, Date createdDate, Long likes) {
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

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
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
