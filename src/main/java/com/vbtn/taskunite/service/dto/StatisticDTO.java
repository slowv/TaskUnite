package com.vbtn.taskunite.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vbtn.taskunite.domain.Statistic} entity.
 */
public class StatisticDTO implements Serializable {

    private Long id;

    private Integer level;

    private Integer experience;

    private Integer completedTask;

    private Integer rating;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getCompletedTask() {
        return completedTask;
    }

    public void setCompletedTask(Integer completedTask) {
        this.completedTask = completedTask;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userInformationId) {
        this.userId = userInformationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StatisticDTO statisticDTO = (StatisticDTO) o;
        if (statisticDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), statisticDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "StatisticDTO{" +
            "id=" + getId() +
            ", level=" + getLevel() +
            ", experience=" + getExperience() +
            ", completedTask=" + getCompletedTask() +
            ", rating=" + getRating() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
