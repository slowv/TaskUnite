package org.t1708e.taskunite.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.t1708e.taskunite.domain.UserExtend} entity.
 */
public class UserExtendDTO implements Serializable {

    private Long id;

    private String address;

    private String phone;

    private Integer status;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private LocalDate deletedAt;


    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDate getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDate deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserExtendDTO userExtendDTO = (UserExtendDTO) o;
        if (userExtendDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userExtendDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserExtendDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", user=" + getUserId() +
            "}";
    }
}
