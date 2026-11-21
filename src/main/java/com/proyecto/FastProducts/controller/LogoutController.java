package com.proyecto.FastProducts.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // <-- Usamos RestController en lugar de Controller
public class LogoutController {

    @GetMapping("/logout") 
    public ResponseEntity<?> cerrarSesion(HttpSession session) {
        session.invalidate(); // Destruye la sesión
        return ResponseEntity.ok().build(); // Devuelve un 200 OK limpio a React
    }
}