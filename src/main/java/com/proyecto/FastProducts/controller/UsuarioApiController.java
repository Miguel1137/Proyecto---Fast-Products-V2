package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.dto.UsuarioDTO;
import com.proyecto.FastProducts.entity.Permiso;
import com.proyecto.FastProducts.entity.Usuario;
import com.proyecto.FastProducts.repository.PermisoRepository;
import com.proyecto.FastProducts.repository.UsuarioRepository; // <-- IMPORTANTE
import com.proyecto.FastProducts.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class UsuarioApiController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermisoRepository permisoRepository;

    // NUEVO: Inyectamos el repositorio para buscar por credenciales
    @Autowired
    private UsuarioRepository usuarioRepository;

    // GET: Listar todos los usuarios
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

    // GET: Obtener los datos del usuario actual (Sesión)
    @GetMapping("/actual")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioActual(HttpSession session) {
        Integer idUsuario = (Integer) session.getAttribute("idUsuarioLogueado");
        if (idUsuario == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioDTO dto = usuarioService.obtenerUsuario(idUsuario);
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

    // DELETE: Inactivar un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inactivarUsuario(@PathVariable Integer id) {
        boolean inactivado = usuarioService.inactivarUsuario(id);
        return inactivado ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // PUT: Reactivar un usuario
    @PutMapping("/{id}/reactivar")
    public ResponseEntity<Void> reactivarUsuario(@PathVariable Integer id) {
        boolean reactivado = usuarioService.reactivarUsuario(id);
        return reactivado ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    // GET: Obtener el catálogo de permisos
    @GetMapping("/permisos")
    public List<Permiso> listarPermisos() {
        return permisoRepository.findAll();
    }

    // POST: Login desde React
    @PostMapping("/login")
    public ResponseEntity<?> loginReact(@RequestBody UsuarioDTO loginRequest, HttpSession session) {
        // Buscamos al usuario con las credenciales exactas (usando el repositorio)
        Optional<Usuario> user = usuarioRepository.findByNombreUsuarioAndContrasena(
            loginRequest.getNombreUsuario(), 
            loginRequest.getContrasena()
        );

        if (user.isPresent()) {
            // Validamos si está activo
            if (user.get().getActivo() != null && user.get().getActivo()) {
                session.setAttribute("idUsuarioLogueado", user.get().getId());
                return ResponseEntity.ok(user.get()); // Devuelve 200 OK
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("inactivo");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("credenciales");
    }
}