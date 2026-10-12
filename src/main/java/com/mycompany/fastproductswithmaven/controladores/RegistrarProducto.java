/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.fastproductswithmaven.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author miguel
 */
@WebServlet(name = "registrarProducto", urlPatterns = {"/registrarProducto"})
public class RegistrarProducto extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        
        String nombreProducto = request.getParameter("nombreProducto");
        String descripcion = request.getParameter("descripcion");
        String cantidadDisp = request.getParameter("cantidadDisp");
        String cantidadMin = request.getParameter("cantidadMin");
        String talla = request.getParameter("talla");
        String precioStr = request.getParameter("precio");
        
        if (nombreProducto == null || nombreProducto.isBlank()
                || precioStr == null || precioStr.isBlank()) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Nombre y precio son obligatorios."
            );
            return;
        }
        
        Double precio;
                
        try {
            precio= Double.parseDouble(precioStr);
            if (precio < 0) {
                response.sendError(
                        HttpServletResponse.SC_BAD_REQUEST,
                        "El precio no puede ser negativo."
                );
                return;
            }
        } catch (NumberFormatException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "El precio debe ser un número válido."
            );
            return;
        }

        String sql =
                "INSERT INTO tabla_productos"+"(nombre_producto, descripcion, cantidad_disponible, cantidad_minima, talla)"
                +"VALUES (?, ?, ?, ?, ?)";

        try (Connection Conexion = conexion.obtenerConexion();
            PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nombreProducto);
            sentencia.setString(2, descripcion);
            sentencia.setInt(3, cantidadDisp);
            sentencia.setInt(4, cantidadMin);
            sentencia.setInt(5, talla);

            int filasInsertadas = sentencia.executeUpdate();

            if (filasInsertadas == 1) {
                response.sendRedirect(
                        request.getContextPath() + "/alertas.html"
                );
            } else {
                response.sendError(
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                        "No se pudo guardar el producto."
                );
            }

        } catch (SQLException e) {
            // Registrar el detalle en los logs del servidor.
            getServletContext().log("Error guardando el producto", e);

            response.sendError(
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Ocurrió un error al guardar el producto."
            );
        }
        
    } 
}
