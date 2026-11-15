package com.proyecto.FastProducts.repository;

import com.proyecto.FastProducts.entity.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    // Para traer los reportes ordenados del más reciente al más antiguo
    List<Reporte> findAllByOrderByFechaHoraDesc();
}