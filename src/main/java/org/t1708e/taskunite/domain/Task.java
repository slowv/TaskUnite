package org.t1708e.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
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

    @Column(name = "description")
    private String description;

    @Column(name = "plan_date")
    private Instant planDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "task")
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "task")
    private Set<Review> reviews = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "task_task_categories",
               joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "task_categories_id", referencedColumnName = "id"))
    private Set<TaskCategory> taskCategories = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("tasks")
    private Tasker tasker;

    @ManyToOne
    @JsonIgnoreProperties("tasks")
    private Master master;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Instant getPlanDate() {
        return planDate;
    }

    public Task planDate(Instant planDate) {
        this.planDate = planDate;
        return this;
    }

    public void setPlanDate(Instant planDate) {
        this.planDate = planDate;
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

    public Set<Message> getMessages() {
        return messages;
    }

    public Task messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Task addMessages(Message message) {
        this.messages.add(message);
        message.setTask(this);
        return this;
    }

    public Task removeMessages(Message message) {
        this.messages.remove(message);
        message.setTask(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
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

    public Set<TaskCategory> getTaskCategories() {
        return taskCategories;
    }

    public Task taskCategories(Set<TaskCategory> taskCategories) {
        this.taskCategories = taskCategories;
        return this;
    }

    public Task addTaskCategories(TaskCategory taskCategory) {
        this.taskCategories.add(taskCategory);
        taskCategory.getTasks().add(this);
        return this;
    }

    public Task removeTaskCategories(TaskCategory taskCategory) {
        this.taskCategories.remove(taskCategory);
        taskCategory.getTasks().remove(this);
        return this;
    }

    public void setTaskCategories(Set<TaskCategory> taskCategories) {
        this.taskCategories = taskCategories;
    }

    public Tasker getTasker() {
        return tasker;
    }

    public Task tasker(Tasker tasker) {
        this.tasker = tasker;
        return this;
    }

    public void setTasker(Tasker tasker) {
        this.tasker = tasker;
    }

    public Master getMaster() {
        return master;
    }

    public Task master(Master master) {
        this.master = master;
        return this;
    }

    public void setMaster(Master master) {
        this.master = master;
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
            ", description='" + getDescription() + "'" +
            ", planDate='" + getPlanDate() + "'" +
            ", totalPrice=" + getTotalPrice() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
