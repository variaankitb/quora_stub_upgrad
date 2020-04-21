package org.upgrad.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "notification")
public class Notification implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6089712522373822870L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private Date date;

    @Column(name = "read")
    private boolean read = false;

    public Notification() {};

    public Notification(User user, String message, Date date, boolean read) {
        this.user = user;
        this.message = message;
        this.date = date;
        this.read = read;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
