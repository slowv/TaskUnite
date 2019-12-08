package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.AdminProfitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdminProfit} and its DTO {@link AdminProfitDTO}.
 */
@Mapper(componentModel = "spring", uses = {TaskMapper.class})
public interface AdminProfitMapper extends EntityMapper<AdminProfitDTO, AdminProfit> {

    @Mapping(source = "task.id", target = "taskId")
    AdminProfitDTO toDto(AdminProfit adminProfit);

    @Mapping(source = "taskId", target = "task")
    AdminProfit toEntity(AdminProfitDTO adminProfitDTO);

    default AdminProfit fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdminProfit adminProfit = new AdminProfit();
        adminProfit.setId(id);
        return adminProfit;
    }
}
