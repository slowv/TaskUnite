package com.vbtn.taskunite.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vbtn.taskunite.domain.AdminProfit} entity.
 */
public class AdminProfitDTO implements Serializable {

    private Long id;

    private Double taskerProfit;

    private Double masterProfit;

    private Double totalProfit;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;


    private Long taskId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTaskerProfit() {
        return taskerProfit;
    }

    public void setTaskerProfit(Double taskerProfit) {
        this.taskerProfit = taskerProfit;
    }

    public Double getMasterProfit() {
        return masterProfit;
    }

    public void setMasterProfit(Double masterProfit) {
        this.masterProfit = masterProfit;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AdminProfitDTO adminProfitDTO = (AdminProfitDTO) o;
        if (adminProfitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), adminProfitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AdminProfitDTO{" +
            "id=" + getId() +
            ", taskerProfit=" + getTaskerProfit() +
            ", masterProfit=" + getMasterProfit() +
            ", totalProfit=" + getTotalProfit() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", task=" + getTaskId() +
            "}";
    }
}
