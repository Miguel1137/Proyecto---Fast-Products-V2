package com.mycompany.fastproductswithmaven.controladores;

import com.mycompany.fastproductswithmaven.config.conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "EliminarProducto", urlPatterns = {"/eliminarProducto"})
public class EliminarProducto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String foto = request.getParameter("foto");

        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection conexionDB = conexion.obtenerConexion();
             PreparedStatement sentencia = conexionDB.prepareStatement(sql)) {

            sentencia.setInt(1, id);
            sentencia.executeUpdate();

            // Borrar la foto del disco duro para no dejar basura
            if (foto != null && !foto.isEmpty()) {
                File archivoFoto = new File("C:\\FotosFastProducts", foto);
                if (archivoFoto.exists()) {
                    archivoFoto.delete();
                }
            }

            response.sendRedirect(request.getContextPath() + "/inventario");

        } catch (SQLException e) {
            getServletContext().log("Error al eliminar producto", e);
            response.sendError(500, "Error al eliminar el producto.");
        }
    }
}