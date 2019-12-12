package com.vbtn.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A UserInformation.
 */
@Entity
@Table(name = "user_information")
public class UserInformation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "bday")
    private Integer bday;

    @Column(name = "bmonth")
    private Integer bmonth;

    @Column(name = "byear")
    private Integer byear;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "phone")
    private String phone;

    @Column(name = "image")
    private String image;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @OneToMany(mappedBy = "user")
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "tasker")
    private Set<Task> tasksAsTaskers = new HashSet<>();

    @OneToMany(mappedBy = "master")
    private Set<Task> tasksAsMasters = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<TaskerCategory> taskerCategories = new HashSet<>();

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Statistic statistic;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private PaymentInformation payment;

    @ManyToOne
    @JsonIgnoreProperties("users")
    private District district;

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

    public UserInformation address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getBday() {
        return bday;
    }

    public UserInformation bday(Integer bday) {
        this.bday = bday;
        return this;
    }

    public void setBday(Integer bday) {
        this.bday = bday;
    }

    public Integer getBmonth() {
        return bmonth;
    }

    public UserInformation bmonth(Integer bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public void setBmonth(Integer bmonth) {
        this.bmonth = bmonth;
    }

    public Integer getByear() {
        return byear;
    }

    public UserInformation byear(Integer byear) {
        this.byear = byear;
        return this;
    }

    public void setByear(Integer byear) {
        this.byear = byear;
    }

    public Integer getGender() {
        return gender;
    }

    public UserInformation gender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public UserInformation phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public UserInformation image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public UserInformation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public UserInformation status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public UserInformation createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public UserInformation updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public UserInformation deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User getUser() {
        return user;
    }

    public UserInformation user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public UserInformation messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public UserInformation addMessages(Message message) {
        this.messages.add(message);
        message.setUser(this);
        return this;
    }

    public UserInformation removeMessages(Message message) {
        this.messages.remove(message);
        message.setUser(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Task> getTasksAsTaskers() {
        return tasksAsTaskers;
    }

    public UserInformation tasksAsTaskers(Set<Task> tasks) {
        this.tasksAsTaskers = tasks;
        return this;
    }

    public UserInformation addTasksAsTasker(Task task) {
        this.tasksAsTaskers.add(task);
        task.setTasker(this);
        return this;
    }

    public UserInformation removeTasksAsTasker(Task task) {
        this.tasksAsTaskers.remove(task);
        task.setTasker(null);
        return this;
    }

    public void setTasksAsTaskers(Set<Task> tasks) {
        this.tasksAsTaskers = tasks;
    }

    public Set<Task> getTasksAsMasters() {
        return tasksAsMasters;
    }

    public UserInformation tasksAsMasters(Set<Task> tasks) {
        this.tasksAsMasters = tasks;
        return this;
    }

    public UserInformation addTasksAsMaster(Task task) {
        this.tasksAsMasters.add(task);
        task.setMaster(this);
        return this;
    }

    public UserInformation removeTasksAsMaster(Task task) {
        this.tasksAsMasters.remove(task);
        task.setMaster(null);
        return this;
    }

    public void setTasksAsMasters(Set<Task> tasks) {
        this.tasksAsMasters = tasks;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public UserInformation reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public UserInformation addReviews(Review review) {
        this.reviews.add(review);
        review.setUser(this);
        return this;
    }

    public UserInformation removeReviews(Review review) {
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

    public UserInformation notifications(Set<Notification> notifications) {
        this.notifications = notifications;
        return this;
    }

    public UserInformation addNotifications(Notification notification) {
        this.notifications.add(notification);
        notification.setUser(this);
        return this;
    }

    public UserInformation removeNotifications(Notification notification) {
        this.notifications.remove(notification);
        notification.setUser(null);
        return this;
    }

    public void setNotifications(Set<Notification> notifications) {
        this.notifications = notifications;
    }

    public Set<TaskerCategory> getTaskerCategories() {
        return taskerCategories;
    }

    public UserInformation taskerCategories(Set<TaskerCategory> taskerCategories) {
        this.taskerCategories = taskerCategories;
        return this;
    }

    public UserInformation addTaskerCategories(TaskerCategory taskerCategory) {
        this.taskerCategories.add(taskerCategory);
        taskerCategory.setUser(this);
        return this;
    }

    public UserInformation removeTaskerCategories(TaskerCategory taskerCategory) {
        this.taskerCategories.remove(taskerCategory);
        taskerCategory.setUser(null);
        return this;
    }

    public void setTaskerCategories(Set<TaskerCategory> taskerCategories) {
        this.taskerCategories = taskerCategories;
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public UserInformation statistic(Statistic statistic) {
        this.statistic = statistic;
        return this;
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
    }

    public PaymentInformation getPayment() {
        return payment;
    }

    public UserInformation payment(PaymentInformation paymentInformation) {
        this.payment = paymentInformation;
        return this;
    }

    public void setPayment(PaymentInformation paymentInformation) {
        this.payment = paymentInformation;
    }

    public District getDistrict() {
        return district;
    }

    public UserInformation district(District district) {
        this.district = district;
        return this;
    }

    public void setDistrict(District district) {
        this.district = district;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserInformation)) {
            return false;
        }
        return id != null && id.equals(((UserInformation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UserInformation{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", bday=" + getBday() +
            ", bmonth=" + getBmonth() +
            ", byear=" + getByear() +
            ", gender=" + getGender() +
            ", phone='" + getPhone() + "'" +
            ", image='" + getImage() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
