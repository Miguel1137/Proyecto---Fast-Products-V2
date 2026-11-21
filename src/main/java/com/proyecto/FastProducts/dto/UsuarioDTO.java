package com.proyecto.FastProducts.dto;

import java.util.List;

// DTO para transferir datos de usuario al frontend sin exponer la contraseña
public class UsuarioDTO {
    private Integer id;
    private String nombreCompleto;
    private String nombreUsuario;
    private List<Integer> permisosIds; // Solo enviamos los IDs de los permisos

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public List<Integer> getPermisosIds() { return permisosIds; }
    public void setPermisosIds(List<Integer> permisosIds) { this.permisosIds = permisosIds; }
}