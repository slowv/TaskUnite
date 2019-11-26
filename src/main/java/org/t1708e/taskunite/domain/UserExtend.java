package org.t1708e.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
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

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "deleted_at")
    private LocalDate deletedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "user")
    private Set<Address> workingAddresses = new HashSet<>();

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
    private Statistic statistic;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public UserExtend address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public UserExtend phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public UserExtend status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public UserExtend createdAt(LocalDate createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public UserExtend updatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public UserExtend deletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User getUser() {
        return user;
    }

    public UserExtend user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Address> getWorkingAddresses() {
        return workingAddresses;
    }

    public UserExtend workingAddresses(Set<Address> addresses) {
        this.workingAddresses = addresses;
        return this;
    }

    public UserExtend addWorkingAddresses(Address address) {
        this.workingAddresses.add(address);
        address.setUser(this);
        return this;
    }

    public UserExtend removeWorkingAddresses(Address address) {
        this.workingAddresses.remove(address);
        address.setUser(null);
        return this;
    }

    public void setWorkingAddresses(Set<Address> addresses) {
        this.workingAddresses = addresses;
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

    public Statistic getStatistic() {
        return statistic;
    }

    public UserExtend statistic(Statistic statistic) {
        this.statistic = statistic;
        return this;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
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
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
