package com.mycompany.fastproductswithmaven.controladores;

import com.mycompany.fastproductswithmaven.config.conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "VerProducto", urlPatterns = {"/verProducto"})
public class VerProducto extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idParam = request.getParameter("id");
        Map<String, Object> producto = new HashMap<>();

        if (idParam != null && !idParam.isEmpty()) {
            String sql = "SELECT * FROM productos WHERE id_producto = ?";
            
            try (Connection conexionDB = conexion.obtenerConexion();
                 PreparedStatement sentencia = conexionDB.prepareStatement(sql)) {
                
                sentencia.setInt(1, Integer.parseInt(idParam));
                
                try (ResultSet resultado = sentencia.executeQuery()) {
                    if (resultado.next()) {
                        producto.put("id", resultado.getInt("id_producto"));
                        producto.put("nombre", resultado.getString("nombre_producto"));
                        producto.put("descripcion", resultado.getString("descripcion"));
                        producto.put("precio", resultado.getDouble("precio"));
                        producto.put("talla", resultado.getString("talla"));
                        producto.put("cantidad", resultado.getInt("cantidad_disponible"));
                        producto.put("foto", resultado.getString("foto_url"));
                    }
                }
            } catch (SQLException | NumberFormatException e) {
                getServletContext().log("Error al buscar producto", e);
            }
        }

        request.setAttribute("producto", producto);
        request.getRequestDispatcher("/productos.jsp").forward(request, response);
    }
}