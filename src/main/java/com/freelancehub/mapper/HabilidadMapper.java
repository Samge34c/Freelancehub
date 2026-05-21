package com.freelancehub.mapper;

import com.freelancehub.dto.habilidad.HabilidadRequest;
import com.freelancehub.dto.habilidad.HabilidadResponse;
import com.freelancehub.entity.Habilidad;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface HabilidadMapper {

    HabilidadResponse toResponse(Habilidad habilidad);

    Habilidad toEntity(HabilidadRequest request);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(HabilidadRequest request, @MappingTarget Habilidad habilidad);
}
