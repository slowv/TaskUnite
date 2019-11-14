package org.t1708e.taskunite.service.mapper;

import org.t1708e.taskunite.domain.*;
import org.t1708e.taskunite.service.dto.CityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link City} and its DTO {@link CityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CityMapper extends EntityMapper<CityDTO, City> {


    @Mapping(target = "districts", ignore = true)
    @Mapping(target = "removeDistricts", ignore = true)
    City toEntity(CityDTO cityDTO);

    default City fromId(Long id) {
        if (id == null) {
            return null;
        }
        City city = new City();
        city.setId(id);
        return city;
    }
}
