package org.upgrad.models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ans")
    private String ans;

    @Column(name = "date")
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Question question;

    @Column(name = "modifiedon")
    private Date modifiedon;

    public Answer() {
    }

    public Answer(String ans, Date date, Date modifiedon, User user, Question question) {
        this.ans = ans;
        this.date = date;
        this.modifiedon = modifiedon;
        this.user = user;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
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

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Date getModifiedon() {
        return modifiedon;
    }

    public void setModifiedon(Date modifiedOn) {
        this.modifiedon = modifiedOn;
    }
}
