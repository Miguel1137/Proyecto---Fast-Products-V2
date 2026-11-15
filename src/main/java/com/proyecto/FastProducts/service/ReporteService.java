package com.proyecto.FastProducts.service;

import com.proyecto.FastProducts.entity.Reporte;
import com.proyecto.FastProducts.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    // Ahora recibe el idUsuario como primer parámetro
    public void registrarMovimiento(Integer idUsuario, Integer idProducto, String tipoCambio, String valorAnterior, String valorNuevo) {
        Reporte reporte = new Reporte();
        reporte.setIdUsuario(idUsuario); // Usamos el ID que viene de la sesión
        reporte.setIdProducto(idProducto);
        reporte.setFechaHora(LocalDateTime.now());
        reporte.setTipoCambio(tipoCambio);
        reporte.setValorAnterior(valorAnterior);
        reporte.setValorNuevo(valorNuevo);
        
        reporteRepository.save(reporte);
    }
}