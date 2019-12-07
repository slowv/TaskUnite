package com.vbtn.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * A Task.
 */
@Entity
@Table(name = "task")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "jhi_from")
    private Instant from;

    @Column(name = "jhi_to")
    private Instant to;

    @Column(name = "duration")
    private Duration duration;

    @Column(name = "type")
    private Integer type;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "task")
    private Set<Review> reviews = new HashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<AdminProfit> adminProfits = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("tasks")
    private UserInformation user;

    @ManyToOne
    @JsonIgnoreProperties("tasks")
    private TaskCategory taskCategory;

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

    public Task address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Task name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public Task description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public Task totalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Instant getFrom() {
        return from;
    }

    public Task from(Instant from) {
        this.from = from;
        return this;
    }

    public void setFrom(Instant from) {
        this.from = from;
    }

    public Instant getTo() {
        return to;
    }

    public Task to(Instant to) {
        this.to = to;
        return this;
    }

    public void setTo(Instant to) {
        this.to = to;
    }

    public Duration getDuration() {
        return duration;
    }

    public Task duration(Duration duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Integer getType() {
        return type;
    }

    public Task type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public Task status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Task createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Task updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public Task deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public Task reviews(Set<Review> reviews) {
        this.reviews = reviews;
        return this;
    }

    public Task addReviews(Review review) {
        this.reviews.add(review);
        review.setTask(this);
        return this;
    }

    public Task removeReviews(Review review) {
        this.reviews.remove(review);
        review.setTask(null);
        return this;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<AdminProfit> getAdminProfits() {
        return adminProfits;
    }

    public Task adminProfits(Set<AdminProfit> adminProfits) {
        this.adminProfits = adminProfits;
        return this;
    }

    public Task addAdminProfits(AdminProfit adminProfit) {
        this.adminProfits.add(adminProfit);
        adminProfit.setTask(this);
        return this;
    }

    public Task removeAdminProfits(AdminProfit adminProfit) {
        this.adminProfits.remove(adminProfit);
        adminProfit.setTask(null);
        return this;
    }

    public void setAdminProfits(Set<AdminProfit> adminProfits) {
        this.adminProfits = adminProfits;
    }

    public UserInformation getUser() {
        return user;
    }

    public Task user(UserInformation userInformation) {
        this.user = userInformation;
        return this;
    }

    public void setUser(UserInformation userInformation) {
        this.user = userInformation;
    }

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public Task taskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
        return this;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        return id != null && id.equals(((Task) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Task{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", from='" + getFrom() + "'" +
            ", to='" + getTo() + "'" +
            ", duration='" + getDuration() + "'" +
            ", type=" + getType() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
