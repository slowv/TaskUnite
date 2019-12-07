package com.vbtn.taskunite.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.vbtn.taskunite.domain.UserInformation} entity.
 */
public class UserInformationDTO implements Serializable {

    private Long id;

    private String address;

    private Integer bday;

    private Integer bmonth;

    private Integer byear;

    private Integer gender;

    private String phone;

    private String image;

    private String description;

    private Integer status;

    private Instant createdAt;

    private Instant updatedAt;

    private Instant deletedAt;


    private Long userId;

    private Long districtId;

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

    public Integer getBday() {
        return bday;
    }

    public void setBday(Integer bday) {
        this.bday = bday;
    }

    public Integer getBmonth() {
        return bmonth;
    }

    public void setBmonth(Integer bmonth) {
        this.bmonth = bmonth;
    }

    public Integer getByear() {
        return byear;
    }

    public void setByear(Integer byear) {
        this.byear = byear;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UserInformationDTO userInformationDTO = (UserInformationDTO) o;
        if (userInformationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), userInformationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UserInformationDTO{" +
            "id=" + getId() +
            ", address='" + getAddress() + "'" +
            ", bday=" + getBday() +
            ", bmonth=" + getBmonth() +
            ", byear=" + getByear() +
            ", gender=" + getGender() +
            ", phone='" + getPhone() + "'" +
            ", image='" + getImage() + "'" +
            ", description='" + getDescription() + "'" +
            ", status=" + getStatus() +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", user=" + getUserId() +
            ", district=" + getDistrictId() +
            "}";
    }
}
