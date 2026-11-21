package com.proyecto.FastProducts.entity;

import jakarta.persistence.*;

// Entidad que mapea el catálogo de permisos disponibles en el sistema
@Entity
@Table(name = "permisos")
public class Permiso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer id;

    @Column(name = "nombre_permiso")
    private String nombrePermiso;

    // Constructor vacío requerido por Hibernate
    public Permiso() {
    }

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombrePermiso() { return nombrePermiso; }
    public void setNombrePermiso(String nombrePermiso) { this.nombrePermiso = nombrePermiso; }
}