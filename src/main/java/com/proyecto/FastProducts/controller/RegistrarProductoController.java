package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.repository.ProductoRepository;
import com.proyecto.FastProducts.service.ReporteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class RegistrarProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ReporteService reporteService;

    @GetMapping("/crear-producto")
    public String mostrarFormulario() {
        return "crear-producto";
    }

    @PostMapping("/registrarProducto")
    public String registrarProducto(
            @RequestParam("nombreProducto") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "cantidadDisp", required = false) Integer cantidadDisp,
            @RequestParam(value = "cantidadMin", required = false) Integer cantidadMin,
            @RequestParam("talla") String talla,
            @RequestParam(value = "precio", required = false) Double precio,
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            HttpSession session,
            Model model) throws IOException {

        // 1. VALIDACIÓN DE CAMPOS OBLIGATORIOS
        if (nombre == null || nombre.trim().isEmpty() || precio == null || cantidadDisp == null) {
            model.addAttribute("error", "Los campos marcados con (*) son obligatorios.");
            return "crear-producto";
        }

        // 2. VALIDACIÓN DE LONGITUD MÁXIMA (Para evitar el error 500)
        if (nombre.length() > 39) {
            model.addAttribute("error", "El nombre del producto no puede exceder los 100 caracteres.");
            return "crear-producto";
        }
        if (descripcion != null && descripcion.length() > 149) {
            model.addAttribute("error", "La descripción no puede exceder los 255 caracteres.");
            return "crear-producto";
        }
        if (talla != null && talla.length() > 20) {
            model.addAttribute("error", "La talla no puede exceder los 20 caracteres.");
            return "crear-producto";
        }

        // Si pasa todas las validaciones, guardamos en la base de datos
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setCantidadDisp(cantidadDisp);
        producto.setCantidadMin(cantidadMin != null ? cantidadMin : 0);
        producto.setTalla(talla);
        producto.setPrecio(precio);

        if (foto != null && !foto.isEmpty()) {
            String nombreArchivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename().replace(" ", "_");
            Path rutaDestino = Paths.get("C:\\FotosFastProducts", nombreArchivo);
            Files.createDirectories(rutaDestino.getParent());
            Files.write(rutaDestino, foto.getBytes());
            producto.setFoto(nombreArchivo);
        }

        productoRepository.save(producto); 

        Integer idUsuario = (Integer) session.getAttribute("idUsuarioLogueado");
        if (idUsuario == null) idUsuario = 1;

        String datosNuevos = nombre + ", " + descripcion + ", " + cantidadDisp + ", " + talla + ", " + precio;
        reporteService.registrarMovimiento(idUsuario, producto.getId(), "CREACION", "N/A", datosNuevos);

        return "redirect:/inventario";
    }
}