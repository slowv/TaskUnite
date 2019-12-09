package com.vbtn.taskunite.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
/**
 * A TaskCategory.
 */
@Entity
@Table(name = "task_category")
public class TaskCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "min_price")
    private Double minPrice;

    @Column(name = "created_at")
@CreationTimestamp
private Instant createdAt;

    @Column(name = "updated_at")
@UpdateTimestamp
private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToMany(mappedBy = "taskCategory")
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "taskCategory", fetch = FetchType.EAGER)
    private Set<TaskerCategory> taskerCategories = new HashSet<>();

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

    public TaskCategory name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public TaskCategory description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public TaskCategory image(String image) {
        this.image = image;
        return this;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public TaskCategory minPrice(Double minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public TaskCategory createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public TaskCategory updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public TaskCategory deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public TaskCategory tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public TaskCategory addTasks(Task task) {
        this.tasks.add(task);
        task.setTaskCategory(this);
        return this;
    }

    public TaskCategory removeTasks(Task task) {
        this.tasks.remove(task);
        task.setTaskCategory(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<TaskerCategory> getTaskerCategories() {
        return taskerCategories;
    }

    public TaskCategory taskerCategories(Set<TaskerCategory> taskerCategories) {
        this.taskerCategories = taskerCategories;
        return this;
    }

    public TaskCategory addTaskerCategories(TaskerCategory taskerCategory) {
        this.taskerCategories.add(taskerCategory);
        taskerCategory.setTaskCategory(this);
        return this;
    }

    public TaskCategory removeTaskerCategories(TaskerCategory taskerCategory) {
        this.taskerCategories.remove(taskerCategory);
        taskerCategory.setTaskCategory(null);
        return this;
    }

    public void setTaskerCategories(Set<TaskerCategory> taskerCategories) {
        this.taskerCategories = taskerCategories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TaskCategory)) {
            return false;
        }
        return id != null && id.equals(((TaskCategory) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TaskCategory{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", image='" + getImage() + "'" +
            ", minPrice=" + getMinPrice() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
