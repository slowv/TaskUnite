package org.t1708e.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A UserExtend.
 */
@Entity
@Table(name = "user_extend")
public class UserExtend implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(unique = true)
    private User userLogin;

    @OneToMany(mappedBy = "sender")
    private Set<Message> sendingMessages = new HashSet<>();

    @OneToMany(mappedBy = "receiver")
    private Set<Message> receivingMessages = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Tasker tasker;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Master master;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Address address;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public UserExtend name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUserLogin() {
        return userLogin;
    }

    public UserExtend userLogin(User user) {
        this.userLogin = user;
        return this;
    }

    public void setUserLogin(User user) {
        this.userLogin = user;
    }

    public Set<Message> getSendingMessages() {
        return sendingMessages;
    }

    public UserExtend sendingMessages(Set<Message> messages) {
        this.sendingMessages = messages;
        return this;
    }

    public UserExtend addSendingMessages(Message message) {
        this.sendingMessages.add(message);
        message.setSender(this);
        return this;
    }

    public UserExtend removeSendingMessages(Message message) {
        this.sendingMessages.remove(message);
        message.setSender(null);
        return this;
    }

    public void setSendingMessages(Set<Message> messages) {
        this.sendingMessages = messages;
    }

    public Set<Message> getReceivingMessages() {
        return receivingMessages;
    }

    public UserExtend receivingMessages(Set<Message> messages) {
        this.receivingMessages = messages;
        return this;
    }

    public UserExtend addReceivingMessages(Message message) {
        this.receivingMessages.add(message);
        message.setReceiver(this);
        return this;
    }

    public UserExtend removeReceivingMessages(Message message) {
        this.receivingMessages.remove(message);
        message.setReceiver(null);
        return this;
    }

    public void setReceivingMessages(Set<Message> messages) {
        this.receivingMessages = messages;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public UserExtend reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public UserExtend addReviews(Review review) {
        this.reviews.add(review);
        review.setUser(this);
        return this;
    }

    public UserExtend removeReviews(Review review) {
        this.reviews.remove(review);
        review.setUser(null);
        return this;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Notification> getNotifications() {
        return notifications;
    }

    public UserExtend notifications(Set<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public UserExtend addNotifications(Notification notification) {
        this.notifications.add(notification);
        notification.setUser(this);
        return this;
    }

    public UserExtend removeNotifications(Notification notification) {
        this.notifications.remove(notification);
        notification.setUser(null);
        return this;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Tasker getTasker() {
        return tasker;
    }

    public UserExtend tasker(Tasker tasker) {
        this.tasker = tasker;
        return this;
    }

    public void setTasker(Tasker tasker) {
        this.tasker = tasker;
    }

    public Master getMaster() {
        return master;
    }

    public UserExtend master(Master master) {
        this.master = master;
        return this;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Address getAddress() {
        return address;
    }

    public UserExtend address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserExtend)) {
            return false;
        }
        return id != null && id.equals(((UserExtend) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserExtend{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
