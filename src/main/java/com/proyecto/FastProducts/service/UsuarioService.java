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

    // Listar todos los usuarios (convirtiéndolos a DTO)
        // Listar solo usuarios activos
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream()
            .filter(Usuario::getActivo) // Filtra los inactivos
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
        usuario.setContrasena("1234"); // Contraseña por defecto (puedes cambiarla luego)

        // Asignar permisos
        if (dto.getPermisosIds() != null) {
            List<Permiso> permisos = permisoRepository.findAllById(dto.getPermisosIds());
            usuario.setPermisos(permisos);
        }

        usuarioRepository.save(usuario);
        return convertirADTO(usuario);
    }

    // Actualizar un usuario existente
    public UsuarioDTO actualizarUsuario(Integer id, UsuarioDTO dto) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombreCompleto(dto.getNombreCompleto());
            usuario.setNombreUsuario(dto.getNombreUsuario());

            if (dto.getPermisosIds() != null) {
                List<Permiso> permisos = permisoRepository.findAllById(dto.getPermisosIds());
                usuario.setPermisos(permisos);
            }

            usuarioRepository.save(usuario);
            return convertirADTO(usuario);
        }
        return null;
    }

    // Eliminar (o inactivar) un usuario
    public boolean inactivarUsuario(Integer id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setActivo(false); // Lo marcamos como inactivo
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
        if (usuario.getPermisos() != null) {
            dto.setPermisosIds(usuario.getPermisos().stream().map(Permiso::getId).collect(Collectors.toList()));
        } else {
            dto.setPermisosIds(new ArrayList<>());
        }
        return dto;
    }
}