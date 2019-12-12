package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.MessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Message} and its DTO {@link MessageDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class, UserInformationMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    MessageDTO toDto(Message message);

    @Mapping(source = "taskId", target = "task")
    @Mapping(source = "userId", target = "user")
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
