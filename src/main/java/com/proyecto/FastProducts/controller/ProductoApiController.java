package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador de API REST para consumir desde el frontend React
@RestController // @RestController devuelve datos (JSON) en lugar de vistas JSP
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173") // Permite que React (puerto 5173) consuma la API
public class ProductoApiController {

    @Autowired
    private ProductoRepository productoRepository;

    // Endpoint GET que devuelve la lista de productos en formato JSON
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
        // Endpoint para obtener solo los productos con bajo stock (Alertas)
    @GetMapping("/alertas")
    public List<Producto> listarAlertas() {
        // Usamos el método que ya habíamos creado en el ProductoRepository
        return productoRepository.buscarProductosConBajoStock();
    }
}