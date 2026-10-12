package com.mycompany.fastproductswithmaven.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    private static final String URL =
        "jdbc:mysql://localhost:3306/fast_products";
    private static final String USUARIO = "admin";
    private static final String CLAVE = "1234";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}