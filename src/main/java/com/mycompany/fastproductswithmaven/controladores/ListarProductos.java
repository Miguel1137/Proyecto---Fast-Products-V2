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
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ListarProductos", urlPatterns = {"/inventario"})
public class ListarProductos extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Map<String, Object>> listaProductos = new ArrayList<>();
        
        String sql = "SELECT id_producto, nombre_producto, talla, precio, cantidad_disponible, foto_url FROM productos";

        try (Connection conexionDB = conexion.obtenerConexion()) {
            
            if (conexionDB == null) {
                System.out.println("¡ERROR CRÍTICO EN INVENTARIO! La conexión a MySQL devolvió null.");
                request.setAttribute("errorDB", "No hay conexión a la base de datos.");
            } else {
                try (PreparedStatement sentencia = conexionDB.prepareStatement(sql);
                     ResultSet resultado = sentencia.executeQuery()) {

                    while (resultado.next()) {
                        Map<String, Object> producto = new HashMap<>();
                        producto.put("id", resultado.getInt("id_producto"));
                        producto.put("nombre", resultado.getString("nombre_producto"));
                        producto.put("talla", resultado.getString("talla"));
                        producto.put("precio", resultado.getDouble("precio"));
                        producto.put("cantidad", resultado.getInt("cantidad_disponible"));
                        
                        producto.put("foto", resultado.getString("foto_url"));
                        
                        listaProductos.add(producto);
                    }
                    System.out.println("¡ÉXITO! Se encontraron " + listaProductos.size() + " productos en la BD.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error de SQL en Inventario: " + e.getMessage());
            getServletContext().log("Error al listar productos", e);
            request.setAttribute("errorDB", "Error al consultar la BD: " + e.getMessage());
        }

        request.setAttribute("productos", listaProductos);
        request.getRequestDispatcher("/inventario.jsp").forward(request, response);
    }
}