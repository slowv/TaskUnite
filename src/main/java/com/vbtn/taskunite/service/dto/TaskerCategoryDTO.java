package com.vbtn.taskunite.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vbtn.taskunite.domain.TaskerCategory} entity.
 */
public class TaskerCategoryDTO implements Serializable {

    private Long id;

    private String description;

    private Double price;

    private Integer type;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;


    private Long taskCategoryId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Long getTaskCategoryId() {
        return taskCategoryId;
    }

    public void setTaskCategoryId(Long taskCategoryId) {
        this.taskCategoryId = taskCategoryId;
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

        TaskerCategoryDTO taskerCategoryDTO = (TaskerCategoryDTO) o;
        if (taskerCategoryDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskerCategoryDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskerCategoryDTO{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            ", price=" + getPrice() +
            ", type=" + getType() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", taskCategory=" + getTaskCategoryId() +
            ", user=" + getUserId() +
            "}";
    }
}
