package org.upgrad.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    // These  annotations creates a join table for many-to-many relationships
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="Question_Category",
            joinColumns = { @JoinColumn(name = "question_id")},
            inverseJoinColumns = { @JoinColumn(name = "category_id")})
    private List<Category> categoryList = new ArrayList<Category>();

    public Question() {
    }

    public Question(String content, Date date, List<Category> categoryList) {
        this.content = content;
        this.date = date;
        this.categoryList = categoryList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
