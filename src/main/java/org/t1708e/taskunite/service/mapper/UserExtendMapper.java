package org.t1708e.taskunite.service.mapper;

import org.t1708e.taskunite.domain.*;
import org.t1708e.taskunite.service.dto.UserExtendDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link UserExtend} and its DTO {@link UserExtendDTO}.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface UserExtendMapper extends EntityMapper<UserExtendDTO, UserExtend> {

    @Mapping(source = "userLogin.id", target = "userLoginId")
    UserExtendDTO toDto(UserExtend userExtend);

    @Mapping(source = "userLoginId", target = "userLogin")
    @Mapping(target = "sendingMessages", ignore = true)
    @Mapping(target = "removeSendingMessages", ignore = true)
    @Mapping(target = "receivingMessages", ignore = true)
    @Mapping(target = "removeReceivingMessages", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "removeReviews", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "removeNotifications", ignore = true)
    @Mapping(target = "tasker", ignore = true)
    @Mapping(target = "master", ignore = true)
    @Mapping(target = "address", ignore = true)
    UserExtend toEntity(UserExtendDTO userExtendDTO);

    default UserExtend fromId(Long id) {
        if (id == null) {
            return null;
        }
        UserExtend userExtend = new UserExtend();
        userExtend.setId(id);
        return userExtend;
    }
}
