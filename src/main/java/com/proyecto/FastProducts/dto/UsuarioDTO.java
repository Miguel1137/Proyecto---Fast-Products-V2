package com.proyecto.FastProducts.dto;

import java.util.List;

// DTO para transferir datos de usuario
public class UsuarioDTO {
    private Integer id;
    private String nombreCompleto;
    private String nombreUsuario;
    private String contrasena; // <--- AGREGAR ESTE CAMPO
    private List<Integer> permisosIds; 
    private Boolean activo; 

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    // NUEVOS GETTER Y SETTER PARA LA CONTRASEÑA
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public List<Integer> getPermisosIds() { return permisosIds; }
    public void setPermisosIds(List<Integer> permisosIds) { this.permisosIds = permisosIds; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}