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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/")
    public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        // Si viene un error en la URL, lo mostramos en la vista
        if (error != null) {
            if (error.equals("inactivo")) {
                model.addAttribute("error", "Tu cuenta está inactiva. Contacta al administrador.");
            } else if (error.equals("credenciales")) {
                model.addAttribute("error", "Usuario o contraseña incorrectos.");
            } else if (error.equals("vacio")) {
                model.addAttribute("error", "Por favor, llena todos los campos.");
            }
        }
        return "index";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam(value = "user", required = false) String usuario, 
                                @RequestParam(value = "password", required = false) String contrasena, 
                                HttpSession session,
                                RedirectAttributes redirectAttrs) {
        
        // 1. Validar campos vacíos
        if (usuario == null || usuario.trim().isEmpty() || contrasena == null || contrasena.trim().isEmpty()) {
            redirectAttrs.addAttribute("error", "vacio");
            return "redirect:/";
        }
        
        // 2. Buscar usuario
        Optional<Usuario> user = usuarioRepository.findByNombreUsuarioAndContrasena(usuario, contrasena);
        
        if (user.isPresent()) {
            // 3. Validar si está activo
            if (user.get().getActivo() != null && user.get().getActivo()) {
                session.setAttribute("idUsuarioLogueado", user.get().getId());
                return "redirect:/inventario"; 
            } else {
                redirectAttrs.addAttribute("error", "inactivo");
                return "redirect:/"; 
            }
        } else {
            redirectAttrs.addAttribute("error", "credenciales");
            return "redirect:/"; 
        }
    }
}