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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ListarAlertas", urlPatterns = {"/alertas"})
public class ListarAlertas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Map<String, Object>> listaAlertas = new ArrayList<>();
        
        String sql = "SELECT id_producto, nombre_producto, cantidad_disponible, cantidad_minima, foto_url FROM productos "
                   + "WHERE cantidad_disponible <= cantidad_minima";

        try (Connection conexionDB = conexion.obtenerConexion();
             PreparedStatement sentencia = conexionDB.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {

            while (resultado.next()) {
                Map<String, Object> alerta = new HashMap<>();
                alerta.put("id", resultado.getInt("id_producto"));
                alerta.put("nombre", resultado.getString("nombre_producto"));
                alerta.put("disp", resultado.getInt("cantidad_disponible"));
                alerta.put("min", resultado.getInt("cantidad_minima"));
                alerta.put("foto", resultado.getString("foto_url"));
                listaAlertas.add(alerta);
            }

        } catch (SQLException e) {
            getServletContext().log("Error al listar alertas", e);
        }

        request.setAttribute("alertas", listaAlertas);
        request.getRequestDispatcher("/alertas.jsp").forward(request, response);
    }
}