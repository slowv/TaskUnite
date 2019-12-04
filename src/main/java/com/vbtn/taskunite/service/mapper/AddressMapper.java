package com.vbtn.taskunite.service.mapper;

import com.vbtn.taskunite.domain.*;
import com.vbtn.taskunite.service.dto.AddressDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Address} and its DTO {@link AddressDTO}.
 */
@Mapper(componentModel = "spring", uses = {DistrictMapper.class, UserInformationMapper.class})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {

    @Mapping(source = "district.id", target = "districtId")
    @Mapping(source = "user.id", target = "userId")
    AddressDTO toDto(Address address);

    @Mapping(source = "districtId", target = "district")
    @Mapping(source = "userId", target = "user")
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
