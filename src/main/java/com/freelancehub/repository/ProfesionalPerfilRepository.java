package com.freelancehub.repository;

import com.freelancehub.entity.ProfesionalPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfesionalPerfilRepository extends JpaRepository<ProfesionalPerfil, Long> {

    Optional<ProfesionalPerfil> findByUsuarioId(Long usuarioId);
}
