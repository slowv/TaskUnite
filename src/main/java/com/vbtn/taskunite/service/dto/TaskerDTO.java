package com.vbtn.taskunite.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the {@link com.vbtn.taskunite.domain.Tasker} entity.
 */
public class TaskerDTO implements Serializable {

    private Long id;

    private String image;

    private String description;

    private Integer status;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;


    private Long userId;

    private Set<TaskerCategoryDTO> taskerCategories = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Set<TaskerCategoryDTO> getTaskerCategories() {
        return taskerCategories;
    }

    public void setTaskerCategories(Set<TaskerCategoryDTO> taskerCategories) {
        this.taskerCategories = taskerCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TaskerDTO taskerDTO = (TaskerDTO) o;
        if (taskerDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), taskerDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TaskerDTO{" +
            "id=" + getId() +
            ", image='" + getImage() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
