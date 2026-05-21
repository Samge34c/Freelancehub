package com.freelancehub.repository;

import com.freelancehub.entity.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Long> {

    List<Cotizacion> findByProyectoId(Long proyectoId);

    List<Cotizacion> findByProfesionalId(Long profesionalId);

    boolean existsByProyectoIdAndProfesionalId(Long proyectoId, Long profesionalId);
}
