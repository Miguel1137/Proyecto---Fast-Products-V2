package com.mycompany.fastproductswithmaven.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    // URL de la base de datos: jdbc:mysql://[host]:[puerto]/[nombre_db]
    private static final String URL = "jdbc:mysql://localhost:3306/fastproducts?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "Miguel";
    private static final String PASSWORD = "28041703Tm#";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            // Registrar el driver (buena práctica en Tomcat)
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Abrir la conexión
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("¡Conexión exitosa a la base de datos!");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return conexion;
    }
}