package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {DistrictMapper.class, TaskerMapper.class, MasterMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "dictrict.id", target = "dictrictId")
    @Mapping(source = "tasker.id", target = "taskerId")
    @Mapping(source = "master.id", target = "masterId")
    AddressDTO toDto(Address address);

    @Mapping(target = "user", ignore = true)
    @Mapping(source = "dictrictId", target = "dictrict")
    @Mapping(source = "taskerId", target = "tasker")
    @Mapping(source = "masterId", target = "master")
    Address toEntity(AddressDTO addressDTO);

    default Address fromId(Long id) {
        if (id == null) {
            return null;
        }
        Address address = new Address();
        address.setId(id);
        return address;
    }
}
