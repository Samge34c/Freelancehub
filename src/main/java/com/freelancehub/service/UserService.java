package com.freelancehub.service;

import com.freelancehub.dto.user.UpdateUserRequest;
import com.freelancehub.dto.user.UserResponse;
import com.freelancehub.entity.Usuario;
import com.freelancehub.exception.ResourceNotFoundException;
import com.freelancehub.mapper.UsuarioMapper;
import com.freelancehub.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UserService(UsuarioRepository usuarioRepository,
                       UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public UserResponse getById(Long id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con id: " + id));
        return usuarioMapper.toResponse(u);
    }

    @Transactional
    public UserResponse updateOwn(Long currentUserId, UpdateUserRequest request) {
        Usuario u = usuarioRepository.findById(currentUserId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Usuario no encontrado con id: " + currentUserId));
        u.setNombre(request.getNombre());
        u.setTelefono(request.getTelefono());
        u = usuarioRepository.save(u);
        return usuarioMapper.toResponse(u);
    }
}
