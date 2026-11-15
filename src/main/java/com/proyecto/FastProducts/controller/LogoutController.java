package com.proyecto.FastProducts.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controlador que se encarga de cerrar la sesión del usuario
@Controller
public class LogoutController {

    // Atiende la ruta /logout cuando el usuario presiona el botón de "Cerrar sesión"
    @GetMapping("/logout") 
    public String cerrarSesion(HttpSession session) {
        // Destruye la sesión actual para que el usuario ya no tenga acceso
        session.invalidate();
        
        // Lo devolvemos a la pantalla de login
        return "redirect:/";
    }
}