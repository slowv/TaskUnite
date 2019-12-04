package com.vbtn.taskunite.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A Master.
 */
@Entity
@Table(name = "master")
public class Master implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private UserInformation user;

    @OneToMany(mappedBy = "master")
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "master")
    private Set<Address> workingAddresses = new HashSet<>();

    @OneToMany(mappedBy = "master")
    private Set<Task> tasks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public Master status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Master createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Master updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public Master deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public UserInformation getUser() {
        return user;
    }

    public Master user(UserInformation userInformation) {
        this.user = userInformation;
        return this;
    }

    public void setUser(UserInformation userInformation) {
        this.user = userInformation;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public Master rooms(Set<Room> rooms) {
        this.rooms = rooms;
        return this;
    }

    public Master addRooms(Room room) {
        this.rooms.add(room);
        room.setMaster(this);
        return this;
    }

    public Master removeRooms(Room room) {
        this.rooms.remove(room);
        room.setMaster(null);
        return this;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<Address> getWorkingAddresses() {
        return workingAddresses;
    }

    public Master workingAddresses(Set<Address> addresses) {
        this.workingAddresses = addresses;
        return this;
    }

    public Master addWorkingAddresses(Address address) {
        this.workingAddresses.add(address);
        address.setMaster(this);
        return this;
    }

    public Master removeWorkingAddresses(Address address) {
        this.workingAddresses.remove(address);
        address.setMaster(null);
        return this;
    }

    public void setWorkingAddresses(Set<Address> addresses) {
        this.workingAddresses = addresses;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public Master tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    public Master addTasks(Task task) {
        this.tasks.add(task);
        task.setMaster(this);
        return this;
    }

    public Master removeTasks(Task task) {
        this.tasks.remove(task);
        task.setMaster(null);
        return this;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Master)) {
            return false;
        }
        return id != null && id.equals(((Master) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Master{" +
            "id=" + getId() +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
