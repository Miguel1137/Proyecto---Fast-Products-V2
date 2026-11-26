package com.proyecto.FastProducts.controller;

import com.proyecto.FastProducts.entity.Producto;
import com.proyecto.FastProducts.repository.ProductoRepository;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

// Controlador de API REST para consumir desde el frontend React
@RestController 
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductoApiController {

    @Autowired
    private ProductoRepository productoRepository;

    // GET: Listar todos los productos
    @GetMapping("/productos")
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }
    
    // GET: Alertas (bajo stock)
    @GetMapping("/alertas")
    public List<Producto> listarAlertas() {
        return productoRepository.buscarProductosConBajoStock();
    }
    
    // POST: Crear producto con imagen
    @PostMapping(value = "/productos", consumes = {"multipart/form-data"})
    public ResponseEntity<?> crearProductoReact(
            @RequestParam("nombreProducto") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidadDisp") Integer cantidadDisp,
            @RequestParam(value = "cantidadMin", required = false) Integer cantidadMin,
            @RequestParam("talla") String talla,
            @RequestParam("precio") Double precio,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {

        try {
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
            return ResponseEntity.ok(producto);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al guardar producto: " + e.getMessage());
        }
    }
    
    // GET: Obtener un producto por ID
    @GetMapping("/productos/{id}")
    public ResponseEntity<Producto> obtenerProducto(@PathVariable Integer id) {
        Optional<Producto> producto = productoRepository.findById(id);
        return producto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // PUT: Actualizar producto
    @PutMapping(value = "/productos/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> actualizarProductoReact(
            @PathVariable Integer id,
            @RequestParam("nombreProducto") String nombre,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("cantidadDisp") Integer cantidadDisp,
            @RequestParam(value = "cantidadMin", required = false) Integer cantidadMin,
            @RequestParam("talla") String talla,
            @RequestParam("precio") Double precio,
            @RequestParam(value = "foto", required = false) MultipartFile foto) {

        try {
            Optional<Producto> optional = productoRepository.findById(id);
            if (optional.isPresent()) {
                Producto p = optional.get();
                p.setNombre(nombre);
                p.setDescripcion(descripcion);
                p.setCantidadDisp(cantidadDisp);
                p.setCantidadMin(cantidadMin != null ? cantidadMin : 0);
                p.setTalla(talla);
                p.setPrecio(precio);

                if (foto != null && !foto.isEmpty()) {
                    String nombreArchivo = System.currentTimeMillis() + "_" + foto.getOriginalFilename().replace(" ", "_");
                    Path rutaDestino = Paths.get("C:\\FotosFastProducts", nombreArchivo);
                    Files.createDirectories(rutaDestino.getParent());
                    Files.write(rutaDestino, foto.getBytes());
                    p.setFoto(nombreArchivo);
                }

                productoRepository.save(p);
                return ResponseEntity.ok(p);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al actualizar producto: " + e.getMessage());
        }
    }

    // DELETE: Eliminar un producto
    @DeleteMapping("/productos/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
        try {
            if (productoRepository.existsById(id)) {
                productoRepository.deleteById(id);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Si el producto tiene reportes asociados, MySQL lanzará un error de llave foránea
            return ResponseEntity.internalServerError().body("No se puede eliminar: el producto tiene historial asociado. " + e.getMessage());
        }
    }
}