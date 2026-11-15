package com.proyecto.FastProducts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controlador que maneja la vista de Configuración del sistema
@Controller
public class ConfiguracionController {

    // Atiende la ruta /configuracion. Solo se encarga de mostrar la interfaz,
    // la lógica de los botones (gestión de usuarios y limpieza) se hará más adelante.
    @GetMapping("/configuracion")
    public String mostrarConfiguracion() {
        return "configuracion"; // Renderiza el archivo configuracion.jsp
    }
}