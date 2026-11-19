package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Reporte;
import com.proyecto.FastProducts.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// Controlador de API REST para consumir los reportes desde el frontend React
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class ReporteApiController {

    @Autowired
    private ReporteRepository reporteRepository;

    // Endpoint para obtener todos los reportes ordenados por fecha
    @GetMapping("/reportes")
    public List<Reporte> listarReportes() {
        return reporteRepository.findAllByOrderByFechaHoraDesc();
    }

    // Endpoint para obtener un solo reporte por su ID (para la vista de detalle)
    @GetMapping("/reportes/{id}")
    public Reporte obtenerReporte(@PathVariable("id") Integer id) {
        Optional<Reporte> reporte = reporteRepository.findById(id);
        return reporte.orElse(null);
    }
}