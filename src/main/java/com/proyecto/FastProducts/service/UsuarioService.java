package com.proyecto.FastProducts.service;

import com.proyecto.FastProducts.dto.UsuarioDTO;
import com.proyecto.FastProducts.entity.Permiso;
import com.proyecto.FastProducts.entity.Usuario;
import com.proyecto.FastProducts.repository.PermisoRepository;
import com.proyecto.FastProducts.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PermisoRepository permisoRepository;

    // Listar TODOS los usuarios (activos e inactivos)
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
            .map(this::convertirADTO)
            .collect(Collectors.toList());
    }

    // Obtener un usuario por ID
    public UsuarioDTO obtenerUsuario(Integer id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(this::convertirADTO).orElse(null);
    }

    // Crear un nuevo usuario
        public UsuarioDTO crearUsuario(UsuarioDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombreCompleto(dto.getNombreCompleto());
        usuario.setNombreUsuario(dto.getNombreUsuario());
        // Usar la contraseña del formulario, o 1234 si está vacía
        usuario.setContrasena(dto.getContrasena() != null && !dto.getContrasena().isEmpty() 
            ? dto.getContrasena() : "1234");
        usuario.setActivo(true);

        if (dto.getPermisosIds() != null) {
            List<Permiso> permisos = permisoRepository.findAllById(dto.getPermisosIds());
            usuario.setPermisos(permisos);
        }

        usuarioRepository.save(usuario);
        return convertirADTO(usuario);
    }

    public UsuarioDTO actualizarUsuario(Integer id, UsuarioDTO dto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombreCompleto(dto.getNombreCompleto());
            usuario.setNombreUsuario(dto.getNombreUsuario());
            
            // Si el administrador escribió una nueva contraseña, la actualizamos
            if (dto.getContrasena() != null && !dto.getContrasena().isEmpty()) {
                usuario.setContrasena(dto.getContrasena());
            }

            if (dto.getPermisosIds() != null) {
                List<Permiso> permisos = permisoRepository.findAllById(dto.getPermisosIds());
                usuario.setPermisos(permisos);
            }

            usuarioRepository.save(usuario);
            return convertirADTO(usuario);
        }
        return null;
    }

    // Inactivar un usuario (Soft Delete)
    public boolean inactivarUsuario(Integer id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setActivo(false);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    // Reactivar un usuario
    public boolean reactivarUsuario(Integer id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setActivo(true);
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    // Método auxiliar para convertir Entidad a DTO
    private UsuarioDTO convertirADTO(Usuario usuario) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(usuario.getId());
        dto.setNombreCompleto(usuario.getNombreCompleto());
        dto.setNombreUsuario(usuario.getNombreUsuario());
        // dto.setContrasena(usuario.getContrasena()); // <-- LO DEJAMOS COMENTADO PARA NO ENVIARLA
        dto.setActivo(usuario.getActivo());
        
        if (usuario.getPermisos() != null) {
            dto.setPermisosIds(usuario.getPermisos().stream().map(Permiso::getId).collect(Collectors.toList()));
        } else {
            dto.setPermisosIds(new ArrayList<>());
        }
        return dto;
    }
}