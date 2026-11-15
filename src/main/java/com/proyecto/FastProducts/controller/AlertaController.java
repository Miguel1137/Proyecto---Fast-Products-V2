package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class AlertaController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/alertas")
    public String mostrarAlertas(Model model) {
        // Llama al método del repositorio que busca los productos en alerta
        List<Producto> alertas = productoRepository.buscarProductosConBajoStock();
        
        // Envía la lista a la vista alertas.jsp
        model.addAttribute("alertas", alertas);
        
        return "alertas"; 
    }
}