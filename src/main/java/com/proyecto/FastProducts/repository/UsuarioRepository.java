package com.proyecto.FastProducts.repository;

import com.proyecto.FastProducts.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // Usamos @Query para que no haya error al leer la 'ñ' de la columna en la BD
    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario = :usuario AND u.contrasena = :contrasena")
    Optional<Usuario> buscarPorCredenciales(@Param("usuario") String usuario, @Param("contrasena") String contrasena);
}