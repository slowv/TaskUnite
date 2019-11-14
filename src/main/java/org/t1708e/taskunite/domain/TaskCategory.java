package org.t1708e.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @ManyToMany(mappedBy = "taskCategories")
    @JsonIgnore
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany(mappedBy = "taskCategories")
    @JsonIgnore
    private Set<Tasker> taskers = new HashSet<>();

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

    public Integer getStatus() {
        return status;
    }

    public TaskCategory status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        task.getTaskCategories().add(this);
        return this;
    }

    public TaskCategory removeTasks(Task task) {
        this.tasks.remove(task);
        task.getTaskCategories().remove(this);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<Tasker> getTaskers() {
        return taskers;
    }

    public TaskCategory taskers(Set<Tasker> taskers) {
        this.taskers = taskers;
        return this;
    }

    public TaskCategory addTaskers(Tasker tasker) {
        this.taskers.add(tasker);
        tasker.getTaskCategories().add(this);
        return this;
    }

    public TaskCategory removeTaskers(Tasker tasker) {
        this.taskers.remove(tasker);
        tasker.getTaskCategories().remove(this);
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
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
