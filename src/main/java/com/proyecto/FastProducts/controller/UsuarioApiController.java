package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.dto.UsuarioDTO;
import com.proyecto.FastProducts.entity.Permiso;
import com.proyecto.FastProducts.repository.PermisoRepository;
import com.proyecto.FastProducts.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioApiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermisoRepository permisoRepository;

    // GET: Listar todos los usuarios activos
    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // GET: Obtener un usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Integer id) {
        UsuarioDTO dto = usuarioService.obtenerUsuario(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // POST: Crear un nuevo usuario
    @PostMapping
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO dto) {
        return usuarioService.crearUsuario(dto);
    }

    // PUT: Actualizar un usuario
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Integer id, @RequestBody UsuarioDTO dto) {
        UsuarioDTO actualizado = usuarioService.actualizarUsuario(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    // DELETE: Inactivar un usuario (Eliminación lógica)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inactivarUsuario(@PathVariable Integer id) {
        boolean inactivado = usuarioService.inactivarUsuario(id);
        return inactivado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // GET: Obtener el catálogo de permisos disponibles
    @GetMapping("/permisos")
    public List<Permiso> listarPermisos() {
        return permisoRepository.findAll();
    }
}