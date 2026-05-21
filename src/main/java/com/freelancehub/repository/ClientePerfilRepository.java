package com.freelancehub.repository;

import com.freelancehub.entity.ClientePerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientePerfilRepository extends JpaRepository<ClientePerfil, Long> {

    Optional<ClientePerfil> findByUsuarioId(Long usuarioId);
}
