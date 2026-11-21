package com.proyecto.FastProducts.entity;

import jakarta.persistence.*;
import java.util.List;

// Entidad que representa la tabla 'tabla_usuario'
@Entity
@Table(name = "tabla_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nombre_completo")
    private String nombreCompleto;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;

    // En la base de datos la columna tiene la letra 'ñ'
    @Column(name = "contraseña") 
    private String contrasena;
    
    @Column(name = "activo")
    private Boolean activo = true;

    // Relación Muchos a Muchos con la tabla de permisos (usando la tabla intermedia)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "administracion_permisos",
        joinColumns = @JoinColumn(name = "id_usuario"),
        inverseJoinColumns = @JoinColumn(name = "id_permiso")
    )
    private List<Permiso> permisos;

    // Constructor vacío
    public Usuario() {
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public List<Permiso> getPermisos() { return permisos; }
    public void setPermisos(List<Permiso> permisos) { this.permisos = permisos; }

    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}