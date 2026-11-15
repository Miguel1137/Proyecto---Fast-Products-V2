package com.proyecto.FastProducts.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CuentaController {

    @GetMapping("/cuenta")
    public String mostrarCuenta() {
        return "cuenta"; // Busca cuenta.jsp en WEB-INF/jsp/
    }
}