package com.proyecto.FastProducts.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

// Controlador especializado en servir las imágenes guardadas en el disco duro
@Controller
public class ImagenController {

    // Este método recibe el nombre de la foto y devuelve el archivo real
    @GetMapping("/imagen")
    public ResponseEntity<Resource> mostrarImagen(@RequestParam("foto") String nombreFoto) {
        try {
            // Construimos la ruta exacta del archivo en la carpeta externa
            Path rutaFoto = Paths.get("C:\\FotosFastProducts").resolve(nombreFoto).normalize();
            Resource recurso = new UrlResource(rutaFoto.toUri());

            // Si la imagen existe y se puede leer, la enviamos al navegador
            if (recurso.exists() && recurso.isReadable()) {
                return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(recurso);
            }
        } catch (MalformedURLException e) {
            // Si hay un error en la ruta, se ignora y se envía un 404
        }
        return ResponseEntity.notFound().build();
    }
}