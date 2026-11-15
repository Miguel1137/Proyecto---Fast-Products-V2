package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Usuario;
import com.proyecto.FastProducts.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

// Controlador que gestiona el inicio y cierre de sesión de los usuarios
@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Muestra la página de inicio de sesión (la raíz del sitio)
    @GetMapping("/")
    public String mostrarLogin() {
        return "index";
    }

    // Procesa el formulario cuando el usuario intenta entrar
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("user") String usuario, 
                                @RequestParam("password") String contrasena, 
                                HttpSession session,
                                Model model) {
        
        // Validamos si existe un usuario con esas credenciales en la base de datos
        Optional<Usuario> user = usuarioRepository.buscarPorCredenciales(usuario, contrasena);
        
        if (user.isPresent()) {
            // Si las credenciales son correctas, guardamos su ID en la sesión
            // para que los reportes sepan quién está haciendo los cambios
            session.setAttribute("idUsuarioLogueado", user.get().getId());
            return "redirect:/inventario"; 
        } else {
            // Si falla, devolvemos el error a la vista sin perder la URL
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "index"; 
        }
    }
}