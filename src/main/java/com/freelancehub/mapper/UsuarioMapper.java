package com.freelancehub.mapper;

import com.freelancehub.dto.user.UserResponse;
import com.freelancehub.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UserResponse toResponse(Usuario usuario);
}
