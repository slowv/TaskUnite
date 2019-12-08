package com.vbtn.taskunite.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

/**
 * A AdminProfit.
 */
@Entity
@Table(name = "admin_profit")
public class AdminProfit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tasker_profit")
    private Double taskerProfit;

    @Column(name = "master_profit")
    private Double masterProfit;

    @Column(name = "total_profit")
    private Double totalProfit;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @ManyToOne
    @JsonIgnoreProperties("adminProfits")
    private Task task;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTaskerProfit() {
        return taskerProfit;
    }

    public AdminProfit taskerProfit(Double taskerProfit) {
        this.taskerProfit = taskerProfit;
        return this;
    }

    public void setTaskerProfit(Double taskerProfit) {
        this.taskerProfit = taskerProfit;
    }

    public Double getMasterProfit() {
        return masterProfit;
    }

    public AdminProfit masterProfit(Double masterProfit) {
        this.masterProfit = masterProfit;
        return this;
    }

    public void setMasterProfit(Double masterProfit) {
        this.masterProfit = masterProfit;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public AdminProfit totalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
        return this;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public AdminProfit createdAt(Instant createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public AdminProfit updatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public AdminProfit deletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Task getTask() {
        return task;
    }

    public AdminProfit task(Task task) {
        this.task = task;
        return this;
    }

    public void setTask(Task task) {
        this.task = task;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AdminProfit)) {
            return false;
        }
        return id != null && id.equals(((AdminProfit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "AdminProfit{" +
            "id=" + getId() +
            ", taskerProfit=" + getTaskerProfit() +
            ", masterProfit=" + getMasterProfit() +
            ", totalProfit=" + getTotalProfit() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            "}";
    }
}
