package com.proyecto.FastProducts.repository;

import com.proyecto.FastProducts.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    // Método para el Login: Valida credenciales y que el usuario esté activo
    Optional<Usuario> findByNombreUsuarioAndContrasenaAndActivoTrue(String nombreUsuario, String contrasena);

    // Método para validar si un nombre de usuario ya existe (útil para crear usuarios)
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}