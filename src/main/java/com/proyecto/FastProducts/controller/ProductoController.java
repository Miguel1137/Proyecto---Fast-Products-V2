package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;

// Controlador principal para la visualización del inventario y los detalles de cada producto
@Controller
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Carga la interfaz del inventario con todos los productos registrados
    @GetMapping("/inventario")
    public String mostrarInventario(Model model) {
        // Traemos todos los productos de la base de datos y los enviamos al JSP
        model.addAttribute("productos", productoRepository.findAll());
        return "inventario"; 
    }

    // Muestra la vista detallada de un producto específico
    @GetMapping("/verProducto")
    public String verProducto(@RequestParam("id") Integer id, Model model) {
        // Buscamos el producto por su ID
        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isPresent()) {
            // Si existe, lo enviamos a la vista de detalles
            model.addAttribute("producto", producto.get());
            return "productos";
        }
        // Si no existe, volvemos al inventario
        return "redirect:/inventario";
    }
}