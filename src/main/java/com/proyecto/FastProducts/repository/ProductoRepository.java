package com.proyecto.FastProducts.repository;

import com.proyecto.FastProducts.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    // 1. ALERTAS (Lo que ya teníamos)
    // Compara dos columnas de la misma tabla.
    @Query("SELECT p FROM Producto p WHERE p.cantidadDisp <= p.cantidadMin")
    List<Producto> buscarProductosConBajoStock();

    // 2. BARRA DE BÚSQUEDA (Para el futuro)
    // Busca productos cuyo nombre contenga el texto ingresado, sin importar mayúsculas/minúsculas.
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // 3. FILTRO POR TALLA (Para el futuro)
    // Trae todos los productos de una talla específica (ej. "M", "L").
    List<Producto> findByTalla(String talla);

    // 4. BÚSQUEDA COMBINADA (Opcional, para filtros avanzados)
    // Busca por nombre Y talla al mismo tiempo usando @Query.
    @Query("SELECT p FROM Producto p WHERE p.nombre LIKE %:nombre% AND p.talla = :talla")
    List<Producto> buscarPorNombreYTalla(@Param("nombre") String nombre, @Param("talla") String talla);
}
