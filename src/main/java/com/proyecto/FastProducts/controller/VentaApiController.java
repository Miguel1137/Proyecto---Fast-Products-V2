package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.entity.Venta;
import com.proyecto.FastProducts.entity.DetalleVenta;
import com.proyecto.FastProducts.repository.ProductoRepository;
import com.proyecto.FastProducts.repository.VentaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class VentaApiController {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    // Endpoint para procesar la venta
    @PostMapping("/procesar")
    public ResponseEntity<?> procesarVenta(@RequestBody List<Map<String, Object>> items, HttpSession session) {
        try {
            // Validar que haya sesión
            Integer idUsuario = (Integer) session.getAttribute("idUsuarioLogueado");
            if (idUsuario == null) return ResponseEntity.status(401).body("Debes iniciar sesión para vender.");

            // Creamos el objeto Venta
            Venta venta = new Venta();
            venta.setIdUsuario(idUsuario);
            venta.setFechaHora(LocalDateTime.now());
            
            List<DetalleVenta> detalles = new ArrayList<>();
            double totalVenta = 0;

            // Recorremos los productos enviados desde React
            for (Map<String, Object> item : items) {
                Integer idProducto = (Integer) item.get("id");
                Integer cantidadVendida = (Integer) item.get("cantidad");

                Optional<Producto> prodOpt = productoRepository.findById(idProducto);
                if (prodOpt.isEmpty()) return ResponseEntity.badRequest().body("Producto no encontrado.");
                
                Producto producto = prodOpt.get();
                
                // Validar stock en el servidor
                if (cantidadVendida > producto.getCantidadDisp()) {
                    return ResponseEntity.badRequest().body("No hay suficiente stock para: " + producto.getNombre());
                }

                // Actualizar stock del producto
                producto.setCantidadDisp(producto.getCantidadDisp() - cantidadVendida);
                productoRepository.save(producto);

                // Crear el detalle de la venta
                DetalleVenta detalle = new DetalleVenta();
                detalle.setVenta(venta);
                detalle.setIdProducto(idProducto);
                detalle.setCantidad(cantidadVendida);
                detalle.setPrecioUnitario(producto.getPrecio());
                
                detalles.add(detalle);
                totalVenta += (producto.getPrecio() * cantidadVendida);
            }

            venta.setDetalles(detalles);
            venta.setTotal(totalVenta);
            
            // Guardar la venta y sus detalles en la BD
            ventaRepository.save(venta);
            
            return ResponseEntity.ok("Venta procesada con éxito. Total: $" + totalVenta);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al procesar venta: " + e.getMessage());
        }
    }
}