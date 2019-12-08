package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.AdminTransactionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdminTransaction} and its DTO {@link AdminTransactionDTO}.
 */
@Mapper(componentModel = "spring", uses = {PaymentInformationMapper.class})
public interface AdminTransactionMapper extends EntityMapper<AdminTransactionDTO, AdminTransaction> {

    @Mapping(source = "payment.id", target = "paymentId")
    AdminTransactionDTO toDto(AdminTransaction adminTransaction);

    @Mapping(source = "paymentId", target = "payment")
    AdminTransaction toEntity(AdminTransactionDTO adminTransactionDTO);

    default AdminTransaction fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdminTransaction adminTransaction = new AdminTransaction();
        adminTransaction.setId(id);
        return adminTransaction;
    }
}
