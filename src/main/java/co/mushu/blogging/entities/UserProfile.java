package co.mushu.blogging.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class UserProfile {
    @Id
    private String username;
    @OneToMany(mappedBy="createdBy", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Blog> blogs;
    @NotNull
    private Date createDate;
    @NotNull
    private Date dateOfBirth;
    private String email;
    private String phoneNumber;
    private Date lastBlogCreationTime;

    public UserProfile() {
    }

    public UserProfile(String username, Date time, Date dateOfBirth, String email, String phone) {
        this.username = username;
        this.createDate = time;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(Set<Blog> blogs) {
        this.blogs = blogs;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getLastBlogCreationTime() {
        return lastBlogCreationTime;
    }

    public void setLastBlogCreationTime(Date lastBlogCreationTime) {
        this.lastBlogCreationTime = lastBlogCreationTime;
    }
}
