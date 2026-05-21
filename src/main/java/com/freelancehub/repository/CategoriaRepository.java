package com.freelancehub.repository;

import com.freelancehub.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByActivoTrue();

    Optional<Categoria> findByNombre(String nombre);
}
