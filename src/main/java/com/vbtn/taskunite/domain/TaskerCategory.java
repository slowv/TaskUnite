package com.vbtn.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToOne(mappedBy = "taskerCategory")
    @JsonIgnore
    private TaskCategory taskCategory;

    @ManyToMany(mappedBy = "taskerCategories")
    @JsonIgnore
    private Set<Tasker> taskers = new HashSet<>();

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

    public Integer getStatus() {
        return status;
    }

    public TaskerCategory status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Set<Tasker> getTaskers() {
        return taskers;
    }

    public TaskerCategory taskers(Set<Tasker> taskers) {
        this.taskers = taskers;
        return this;
    }

    public TaskerCategory addTaskers(Tasker tasker) {
        this.taskers.add(tasker);
        tasker.getTaskerCategories().add(this);
        return this;
    }

    public TaskerCategory removeTaskers(Tasker tasker) {
        this.taskers.remove(tasker);
        tasker.getTaskerCategories().remove(this);
        return this;
    }

    public void setTaskers(Set<Tasker> taskers) {
        this.taskers = taskers;
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
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}