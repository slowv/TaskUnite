package org.t1708e.taskunite.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Tasker.
 */
@Entity
@Table(name = "tasker")
public class Tasker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "level")
    private Integer level;

    @Column(name = "price_per_hour")
    private Double pricePerHour;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @OneToOne
    @JoinColumn(unique = true)
    private UserExtend user;

    @OneToMany(mappedBy = "tasker")
    private Set<Address> workingAddresses = new HashSet<>();

    @OneToMany(mappedBy = "tasker")
    private Set<Task> tasks = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tasker_task_categories",
               joinColumns = @JoinColumn(name = "tasker_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "task_categories_id", referencedColumnName = "id"))
    private Set<TaskCategory> taskCategories = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public Tasker level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Double getPricePerHour() {
        return pricePerHour;
    }

    public Tasker pricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
        return this;
    }

    public void setPricePerHour(Double pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public Integer getStatus() {
        return status;
    }

    public Tasker status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Tasker createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Tasker updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public Tasker deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public UserExtend getUser() {
        return user;
    }

    public Tasker user(UserExtend userExtend) {
        this.user = userExtend;
        return this;
    }

    public void setUser(UserExtend userExtend) {
        this.user = userExtend;
    }

    public Set<Address> getWorkingAddresses() {
        return workingAddresses;
    }

    public Tasker workingAddresses(Set<Address> addresses) {
        this.workingAddresses = addresses;
        return this;
    }

    public Tasker addWorkingAddresses(Address address) {
        this.workingAddresses.add(address);
        address.setTasker(this);
        return this;
    }

    public Tasker removeWorkingAddresses(Address address) {
        this.workingAddresses.remove(address);
        address.setTasker(null);
        return this;
    }

    public void setWorkingAddresses(Set<Address> addresses) {
        this.workingAddresses = addresses;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Tasker tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Tasker addTasks(Task task) {
        this.tasks.add(task);
        task.setTasker(this);
        return this;
    }

    public Tasker removeTasks(Task task) {
        this.tasks.remove(task);
        task.setTasker(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Set<TaskCategory> getTaskCategories() {
        return taskCategories;
    }

    public Tasker taskCategories(Set<TaskCategory> taskCategories) {
        this.taskCategories = taskCategories;
        return this;
    }

    public Tasker addTaskCategories(TaskCategory taskCategory) {
        this.taskCategories.add(taskCategory);
        taskCategory.getTaskers().add(this);
        return this;
    }

    public Tasker removeTaskCategories(TaskCategory taskCategory) {
        this.taskCategories.remove(taskCategory);
        taskCategory.getTaskers().remove(this);
        return this;
    }

    public void setTaskCategories(Set<TaskCategory> taskCategories) {
        this.taskCategories = taskCategories;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tasker)) {
            return false;
        }
        return id != null && id.equals(((Tasker) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tasker{" +
            "id=" + getId() +
            ", level=" + getLevel() +
            ", pricePerHour=" + getPricePerHour() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
