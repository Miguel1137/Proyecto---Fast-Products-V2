package com.mycompany.fastproductswithmaven.controladores;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mycompany.fastproductswithmaven.config.conexion;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;

@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
@WebServlet(name = "registrarProducto", urlPatterns = {"/registrarProducto"})
public class RegistrarProducto extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String nombreProducto = request.getParameter("nombreProducto");
        String descripcion = request.getParameter("descripcion");
        String cantidadDispStr = request.getParameter("cantidadDisp");
        String cantidadMinStr = request.getParameter("cantidadMin");
        String talla = request.getParameter("talla");
        String precioStr = request.getParameter("precio");
        
        Double precio;
        int cantidadDisp = 0;
        int cantidadMin = 0;
        
        // Validar campos obligatorios
        if (nombreProducto == null || nombreProducto.isBlank() || precioStr == null || precioStr.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Nombre y precio son obligatorios.");
            return;
        }
        
        // Validaciones numéricas
        try {
            precio = Double.parseDouble(precioStr);
            if (precio < 0) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El precio no puede ser negativo.");
                return;
            }
            cantidadDisp = cantidadDispStr != null && !cantidadDispStr.isBlank() ? Integer.parseInt(cantidadDispStr) : 0;
            cantidadMin = cantidadMinStr != null && !cantidadMinStr.isBlank() ? Integer.parseInt(cantidadMinStr) : 0;
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Los campos numéricos deben ser válidos.");
            return;
        }

        // --- GUARDADO DE IMAGEN (CORREGIDO Y OPTIMIZADO) ---
        Part filePart = request.getPart("foto");
        String nombreArchivoDB = null;

        if (filePart != null && filePart.getSize() > 0) {
            // 1. Obtener nombre y extensión
            String originalName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String extension = FilenameUtils.getExtension(originalName);
            String nombreBase = FilenameUtils.getBaseName(originalName);
            
            nombreBase = nombreBase.replace(" ", "_"); 

            // 2. Crear nombre único
            nombreArchivoDB = nombreBase + "_" + System.currentTimeMillis() + "." + extension;

            // 3. Ruta dinámica de Tomcat (donde está desplegada la app)
            String uploadPath = "C:\\FotosFastProducts";
            File uploadDir = new File(uploadPath);
            
            // 4. Crear carpeta si no existe
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            
            // 5. Guardar archivo (aquí estaba el error de la línea junta)
            filePart.write(uploadPath + File.separator + nombreArchivoDB);
        }
        // --- FIN GUARDADO DE IMAGEN ---

        String sql = "INSERT INTO productos (nombre_producto, descripcion, cantidad_disponible, cantidad_minima, talla, precio, foto_url) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conexionDB = conexion.obtenerConexion();
            PreparedStatement sentencia = conexionDB.prepareStatement(sql))  {

            sentencia.setString(1, nombreProducto);
            sentencia.setString(2, descripcion);
            sentencia.setInt(3, cantidadDisp);
            sentencia.setInt(4, cantidadMin);
            sentencia.setString(5, talla);
            sentencia.setDouble(6, precio);
            sentencia.setString(7, nombreArchivoDB); 

            int filasInsertadas = sentencia.executeUpdate();

            if (filasInsertadas == 1) {
                response.sendRedirect(request.getContextPath() + "/alertas.html");
            } else {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "No se pudo guardar el producto.");
            }

        } catch (SQLException e) {
            getServletContext().log("Error guardando el producto", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al guardar el producto.");
        }
    }
}