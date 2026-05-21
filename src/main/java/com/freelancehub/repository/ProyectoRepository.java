package com.freelancehub.repository;

import com.freelancehub.entity.Proyecto;
import com.freelancehub.enums.EstadoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {

    List<Proyecto> findByEstado(EstadoProyecto estado);

    List<Proyecto> findByClienteId(Long clienteId);

    @Query("SELECT p FROM Proyecto p " +
            "WHERE p.estado = :estado " +
            "AND (:categoriaId IS NULL OR p.categoria.id = :categoriaId) " +
            "AND (:minBudget IS NULL OR p.presupuesto >= :minBudget) " +
            "AND (:maxBudget IS NULL OR p.presupuesto <= :maxBudget) " +
            "ORDER BY p.fechaCreacion DESC")
    List<Proyecto> findOpenProjectsWithFilters(
            @Param("estado") EstadoProyecto estado,
            @Param("categoriaId") Long categoriaId,
            @Param("minBudget") BigDecimal minBudget,
            @Param("maxBudget") BigDecimal maxBudget
    );
}
