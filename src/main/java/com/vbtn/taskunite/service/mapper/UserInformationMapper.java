package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.UserInformationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserInformation} and its DTO {@link UserInformationDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, DistrictMapper.class})
public interface UserInformationMapper extends EntityMapper<UserInformationDTO, UserInformation> {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "district.id", target = "districtId")
    UserInformationDTO toDto(UserInformation userInformation);

    @Mapping(source = "userId", target = "user")
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "removeTasks", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "removeReviews", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "removeNotifications", ignore = true)
    @Mapping(target = "taskerCategories", ignore = true)
    @Mapping(target = "removeTaskerCategories", ignore = true)
    @Mapping(target = "statistic", ignore = true)
    @Mapping(target = "payment", ignore = true)
    @Mapping(source = "districtId", target = "district")
    UserInformation toEntity(UserInformationDTO userInformationDTO);

    default UserInformation fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserInformation userInformation = new UserInformation();
        userInformation.setId(id);
        return userInformation;
    }
}
