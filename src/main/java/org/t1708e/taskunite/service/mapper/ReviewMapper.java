package org.t1708e.taskunite.service.mapper;

import org.t1708e.taskunite.domain.*;
import org.t1708e.taskunite.service.dto.ReviewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Review} and its DTO {@link ReviewDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class, UserExtendMapper.class})
public interface ReviewMapper extends EntityMapper<ReviewDTO, Review> {

    @Mapping(source = "task.id", target = "taskId")
    @Mapping(source = "user.id", target = "userId")
    ReviewDTO toDto(Review review);

    @Mapping(source = "taskId", target = "task")
    @Mapping(source = "userId", target = "user")
    Review toEntity(ReviewDTO reviewDTO);

    default Review fromId(Long id) {
        if (id == null) {
            return null;
        }
        Review review = new Review();
        review.setId(id);
        return review;
    }
}
