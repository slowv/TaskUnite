package com.vbtn.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A TaskerCategory.
 */
@Entity
@Table(name = "tasker_category")
public class TaskerCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    private Integer type;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "taskerCategory")
    private Set<Task> tasks = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("taskerCategories")
    private TaskCategory taskCategory;

    @ManyToOne
    @JsonIgnoreProperties("taskerCategories")
    private UserInformation user;

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

    public TaskerCategory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public TaskerCategory price(Double price) {
        this.price = price;
        return this;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public TaskerCategory type(Integer type) {
        this.type = type;
        return this;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public TaskerCategory createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public TaskerCategory updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public TaskerCategory deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public TaskerCategory tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public TaskerCategory addTasks(Task task) {
        this.tasks.add(task);
        task.setTaskerCategory(this);
        return this;
    }

    public TaskerCategory removeTasks(Task task) {
        this.tasks.remove(task);
        task.setTaskerCategory(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskCategory getTaskCategory() {
        return taskCategory;
    }

    public TaskerCategory taskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
        return this;
    }

    public void setTaskCategory(TaskCategory taskCategory) {
        this.taskCategory = taskCategory;
    }

    public UserInformation getUser() {
        return user;
    }

    public TaskerCategory user(UserInformation userInformation) {
        this.user = userInformation;
        return this;
    }

    public void setUser(UserInformation userInformation) {
        this.user = userInformation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskerCategory)) {
            return false;
        }
        return id != null && id.equals(((TaskerCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TaskerCategory{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", type=" + getType() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
