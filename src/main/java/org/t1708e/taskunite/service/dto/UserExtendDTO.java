package org.t1708e.taskunite.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link org.t1708e.taskunite.domain.UserExtend} entity.
 */
public class UserExtendDTO implements Serializable {

    private Long id;

    private String name;


    private Long userLoginId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(Long userId) {
        this.userLoginId = userId;
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
            ", name='" + getName() + "'" +
            ", userLogin=" + getUserLoginId() +
            "}";
    }
}
