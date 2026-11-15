package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.repository.ProductoRepository;
import com.proyecto.FastProducts.service.ReporteService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.util.Optional;

// Controlador que gestiona la eliminación de productos (el Delete del CRUD)
@Controller
public class EliminarProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ReporteService reporteService;

    @PostMapping("/eliminarProducto")
    public String eliminarProducto(@RequestParam("id") Integer id, HttpSession session) {
        
        Optional<Producto> producto = productoRepository.findById(id);
        
        if (producto.isPresent()) {
            Producto prod = producto.get();
            
            // 1. Extraemos los datos del producto antes de borrarlo (para el historial)
            String datosViejos = prod.getNombre() + ", " + prod.getDescripcion() + ", " + prod.getCantidadDisp() + ", " + prod.getTalla() + ", " + prod.getPrecio();
            
            // 2. Obtenemos el usuario logueado
            Integer idUsuario = (Integer) session.getAttribute("idUsuarioLogueado");
            if (idUsuario == null) idUsuario = 1;

            // 3. IMPORTANTE: Registramos el reporte de eliminación ANTES de borrar el producto
            reporteService.registrarMovimiento(idUsuario, id, "ELIMINACION", datosViejos, "N/A");

            // 4. Borramos la imagen asociada del disco duro
            if (prod.getFoto() != null && !prod.getFoto().isEmpty()) {
                File archivoFoto = new File("C:\\FotosFastProducts\\" + prod.getFoto());
                if (archivoFoto.exists()) {
                    archivoFoto.delete();
                }
            }
            
            // 5. Finalmente, borramos el producto de la base de datos
            productoRepository.deleteById(id);
        }
        
        return "redirect:/inventario";
    }
}