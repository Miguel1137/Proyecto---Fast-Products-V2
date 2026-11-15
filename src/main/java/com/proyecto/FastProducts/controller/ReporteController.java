package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Reporte;
import com.proyecto.FastProducts.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

// Controlador que maneja el historial de auditoría (los reportes de movimientos)
@Controller
public class ReporteController {

    @Autowired
    private ReporteRepository reporteRepository;

    // Muestra la lista de todos los reportes ordenados del más reciente al más antiguo
    @GetMapping("/reportes")
    public String mostrarReportes(Model model) {
        model.addAttribute("reportes", reporteRepository.findAllByOrderByFechaHoraDesc());
        return "reportes"; 
    }

    // Muestra el detalle de un reporte específico (la vista con el Antes y Después)
    @GetMapping("/verReporte")
    public String verDetalleReporte(@RequestParam("id") Integer id, Model model) {
        Optional<Reporte> reporte = reporteRepository.findById(id);
        if (reporte.isPresent()) {
            // Si el reporte existe, lo enviamos a la vista detalle
            model.addAttribute("reporte", reporte.get());
            return "detalle-reporte"; 
        }
        // Si no lo encuentra, vuelve a la lista principal
        return "redirect:/reportes";
    }
}