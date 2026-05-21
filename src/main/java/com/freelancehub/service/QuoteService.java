package com.freelancehub.service;

import com.freelancehub.dto.quote.CreateQuoteRequest;
import com.freelancehub.dto.quote.QuoteResponse;
import com.freelancehub.entity.Cotizacion;
import com.freelancehub.entity.Proyecto;
import com.freelancehub.entity.Usuario;
import com.freelancehub.enums.EstadoCotizacion;
import com.freelancehub.enums.EstadoProyecto;
import com.freelancehub.enums.RolUsuario;
import com.freelancehub.exception.BusinessException;
import com.freelancehub.exception.ForbiddenException;
import com.freelancehub.exception.ResourceNotFoundException;
import com.freelancehub.mapper.CotizacionMapper;
import com.freelancehub.repository.CotizacionRepository;
import com.freelancehub.repository.ProyectoRepository;
import com.freelancehub.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuoteService {

    private final CotizacionRepository cotizacionRepository;
    private final ProyectoRepository proyectoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CotizacionMapper cotizacionMapper;

    public QuoteService(CotizacionRepository cotizacionRepository,
                        ProyectoRepository proyectoRepository,
                        UsuarioRepository usuarioRepository,
                        CotizacionMapper cotizacionMapper) {
        this.cotizacionRepository = cotizacionRepository;
        this.proyectoRepository = proyectoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cotizacionMapper = cotizacionMapper;
    }

    @Transactional
    public QuoteResponse createQuote(Long projectId, Long profesionalId, CreateQuoteRequest request) {
        Usuario profesional = usuarioRepository.findById(profesionalId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Profesional no encontrado con id: " + profesionalId));

        if (profesional.getRol() != RolUsuario.PROFESIONAL) {
            throw new ForbiddenException("Solo los profesionales pueden enviar cotizaciones");
        }

        Proyecto proyecto = proyectoRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proyecto no encontrado con id: " + projectId));

        if (proyecto.getEstado() != EstadoProyecto.ABIERTO) {
            throw new BusinessException(
                    "Solo se pueden enviar cotizaciones a proyectos en estado ABIERTO");
        }

        if (cotizacionRepository.existsByProyectoIdAndProfesionalId(projectId, profesionalId)) {
            throw new BusinessException(
                    "Ya envió una cotización para este proyecto");
        }

        Cotizacion c = new Cotizacion();
        c.setProyecto(proyecto);
        c.setProfesional(profesional);
        c.setPrecio(request.getPrecio());
        c.setPlazo(request.getPlazo());
        c.setDescripcion(request.getDescripcion());
        c.setEstado(EstadoCotizacion.PENDIENTE);
        c = cotizacionRepository.save(c);

        return cotizacionMapper.toResponse(c);
    }

    @Transactional(readOnly = true)
    public List<QuoteResponse> listForProject(Long projectId, Long currentClienteId) {
        Proyecto proyecto = proyectoRepository.findById(projectId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Proyecto no encontrado con id: " + projectId));

        if (!proyecto.getCliente().getId().equals(currentClienteId)) {
            throw new ForbiddenException(
                    "Solo el cliente dueño del proyecto puede ver sus cotizaciones");
        }

        return cotizacionRepository.findByProyectoId(projectId).stream()
                .map(cotizacionMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<QuoteResponse> listMyQuotes(Long profesionalId) {
        return cotizacionRepository.findByProfesionalId(profesionalId).stream()
                .map(cotizacionMapper::toResponse)
                .toList();
    }
}