package org.t1708e.taskunite.service.mapper;

import org.t1708e.taskunite.domain.*;
import org.t1708e.taskunite.service.dto.DistrictDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link District} and its DTO {@link DistrictDTO}.
 */
@Mapper(componentModel = "spring", uses = {CityMapper.class})
public interface DistrictMapper extends EntityMapper<DistrictDTO, District> {

    @Mapping(source = "city.id", target = "cityId")
    DistrictDTO toDto(District district);

    @Mapping(target = "addresses", ignore = true)
    @Mapping(target = "removeAddresses", ignore = true)
    @Mapping(source = "cityId", target = "city")
    District toEntity(DistrictDTO districtDTO);

    default District fromId(Long id) {
        if (id == null) {
            return null;
        }
        District district = new District();
        district.setId(id);
        return district;
    }
}
