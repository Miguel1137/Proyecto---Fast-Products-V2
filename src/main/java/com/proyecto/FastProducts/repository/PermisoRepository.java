package com.proyecto.FastProducts.repository;

import com.proyecto.FastProducts.entity.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermisoRepository extends JpaRepository<Permiso, Integer> {
    // Aquí podríamos agregar búsquedas por nombre de permiso si es necesario
}