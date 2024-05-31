package com.uade.tpo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "productos")

public class Productos {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long idProductos;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "imagen_1", nullable = true)
    private byte[] imagen_1;

    @Column(name = "imagen_2", nullable = true)
    private byte[]  imagen_2;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private float precio = 0.0f;

    @Column(name = "cantidad", nullable = false)
    private int cantidad = 0;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false, referencedColumnName = "idCategorias")
    private Categorias idCategoria;

    @OneToOne
    @JoinColumn(name = "idDescuento", nullable = true, referencedColumnName = "idDescuentos")
    private Descuentos idDescuento;

    // Aca los getter  y setters

    public Long getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(Long idProductos) {
        this.idProductos = idProductos;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte[]  getImagen_1() {
        return imagen_1;
    }

    public void setImagen_1(byte[] imagen_1) {
        this.imagen_1 = imagen_1;
    }

    public byte[]  getImagen_2() {
        return imagen_2;
    }

    public void setImagen_2(byte[]  imagen_2) {
        this.imagen_2 = imagen_2;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Categorias getidCategoria() {
        return idCategoria;
    }

    public void setidCategoria(Categorias idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Descuentos getidDescuento() {
        return idDescuento;
    }

    public void setidDescuento(Descuentos idDescuento) {
        this.idDescuento = idDescuento;
    }
}

