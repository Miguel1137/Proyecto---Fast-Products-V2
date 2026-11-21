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

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String mostrarLogin() {
        return "index";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam("user") String usuario, 
                                @RequestParam("password") String contrasena, 
                                HttpSession session,
                                Model model) {
        
        // Buscamos al usuario y verificamos que esté activo en la misma consulta
        Optional<Usuario> user = usuarioRepository.findByNombreUsuarioAndContrasenaAndActivoTrue(usuario, contrasena);
        
        if (user.isPresent()) {
            session.setAttribute("idUsuarioLogueado", user.get().getId());
            // También podemos guardar el nombre del usuario en sesión si lo necesitas
            session.setAttribute("nombreUsuarioLogueado", user.get().getNombreUsuario());
            return "redirect:/inventario"; 
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos, o cuenta inactiva.");
            return "forward:/"; 
        }
    }
}