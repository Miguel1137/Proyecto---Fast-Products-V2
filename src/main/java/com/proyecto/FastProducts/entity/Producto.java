package com.proyecto.FastProducts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private Integer id;

    @Column(name = "nombre_producto")
    private String nombre;

    private String descripcion;

    @Column(name = "cantidad_disponible")
    private Integer cantidadDisp;

    @Column(name = "cantidad_minima")
    private Integer cantidadMin;

    private String talla;

    private Double precio;

    @Column(name = "foto_url")
    private String foto;

    public Producto() {
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Integer getCantidadDisp() { return cantidadDisp; }
    public void setCantidadDisp(Integer cantidadDisp) { this.cantidadDisp = cantidadDisp; }

    public Integer getCantidadMin() { return cantidadMin; }
    public void setCantidadMin(Integer cantidadMin) { this.cantidadMin = cantidadMin; }

    public String getTalla() { return talla; }
    public void setTalla(String talla) { this.talla = talla; }

    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}
