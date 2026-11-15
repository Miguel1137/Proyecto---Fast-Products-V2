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
import java.util.Optional;

// Controlador que se encarga de la edición de productos (el Update del CRUD)
@Controller
public class EditarProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ReporteService reporteService;

    // Muestra el formulario de edición con los datos actuales del producto
    @GetMapping("/editarProducto")
    public String mostrarFormularioEdicion(@RequestParam("id") Integer id, Model model) {
        Optional<Producto> producto = productoRepository.findById(id);
        
        if (producto.isPresent()) {
            model.addAttribute("producto", producto.get());
            return "editar-producto";
        }
        return "redirect:/inventario";
    }

    // Procesa el formulario cuando el usuario confirma la modificación
    @PostMapping("/modificarProducto")
    public String modificarProducto(
            @RequestParam("id") Integer id,
            @RequestParam("nombreProducto") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam(value = "cantidadDisp", required = false) Integer cantidadDisp,
            @RequestParam(value = "cantidadMin", required = false) Integer cantidadMin,
            @RequestParam("talla") String talla,
            @RequestParam(value = "precio", required = false) Double precio,
            @RequestParam(value = "foto", required = false) MultipartFile foto,
            HttpSession session,
            Model model) throws IOException { // Agregamos Model para los mensajes de error

        Optional<Producto> optionalProducto = productoRepository.findById(id);
        
        if (optionalProducto.isPresent()) {
            Producto producto = optionalProducto.get();
            
            // --- INICIO DE VALIDACIONES DE LONGITUD ---
            if (nombre == null || nombre.trim().isEmpty() || precio == null || cantidadDisp == null) {
                model.addAttribute("error", "Los campos obligatorios no pueden estar vacíos.");
                model.addAttribute("producto", producto); 
                return "editar-producto";
            }
            if (nombre.length() > 100) {
                model.addAttribute("error", "El nombre del producto no puede exceder los 100 caracteres.");
                model.addAttribute("producto", producto); 
                return "editar-producto";
            }
            if (descripcion != null && descripcion.length() > 255) {
                model.addAttribute("error", "La descripción no puede exceder los 255 caracteres.");
                model.addAttribute("producto", producto);
                return "editar-producto";
            }
            if (talla != null && talla.length() > 20) {
                model.addAttribute("error", "La talla no puede exceder los 20 caracteres.");
                model.addAttribute("producto", producto);
                return "editar-producto";
            }
            // --- FIN DE VALIDACIONES ---

            // Guardamos el estado anterior del producto en texto (para el reporte)
            String datosViejos = producto.getNombre() + ", " + producto.getDescripcion() + ", " + producto.getCantidadDisp() + ", " + producto.getTalla() + ", " + producto.getPrecio();
            
            // Actualizamos los campos con la nueva información
            producto.setNombre(nombre);
            producto.setDescripcion(descripcion);
            producto.setCantidadDisp(cantidadDisp);
            producto.setCantidadMin(cantidadMin != null ? cantidadMin : 0);
            producto.setTalla(talla);
            producto.setPrecio(precio);

            // Si el usuario subió una nueva imagen, la guardamos
            if (foto != null && !foto.isEmpty()) {
                String nombreArchivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename().replace(" ", "_");
                Path rutaDestino = Paths.get("C:\\FotosFastProducts", nombreArchivo);
                Files.createDirectories(rutaDestino.getParent());
                Files.write(rutaDestino, foto.getBytes());
                producto.setFoto(nombreArchivo);
            }

            // Guardamos los cambios en la base de datos
            productoRepository.save(producto);

            // Obtenemos el ID del usuario que está logueado (para el reporte)
            Integer idUsuario = (Integer) session.getAttribute("idUsuarioLogueado");
            if (idUsuario == null) idUsuario = 1;

            // Armamos el texto con los datos nuevos y registramos el movimiento
            String datosNuevos = nombre + ", " + descripcion + ", " + cantidadDisp + ", " + talla + ", " + precio;
            reporteService.registrarMovimiento(idUsuario, id, "MODIFICACION", datosViejos, datosNuevos);
        }
        
        return "redirect:/inventario";
    }
}