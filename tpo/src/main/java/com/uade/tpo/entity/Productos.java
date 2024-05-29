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
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    @Column(name = "imagen_1", nullable = true)
    private byte[] imagen_1;

    @Column(name = "imagen_2", nullable = true)
    private byte[] imagen_2;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private float precio = 0.0f;

    @Column(name = "cantidad", nullable = false)
    private int cantidad = 0;

    @ManyToOne
    @JoinColumn(name = "idCategorias", nullable = false, referencedColumnName = "idCategorias")
    private Categorias idCategorias;

    @OneToOne
    @JoinColumn(name = "idDescuentos", nullable = true, referencedColumnName = "idDescuentos")
    private Descuentos idDescuentos;

    // Aca los getter  y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public byte[] getImagen_1() {
        return imagen_1;
    }

    public void setImagen_1(byte[] imagen_1) {
        this.imagen_1 = imagen_1;
    }

    public byte[] getImagen_2() {
        return imagen_2;
    }

    public void setImagen_2(byte[] imagen_2) {
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

    public Categorias getidCategorias() {
        return idCategorias;
    }

    public void setidCategorias(Categorias idCategorias) {
        this.idCategorias = idCategorias;
    }

    public Descuentos getidDescuentos() {
        return idDescuentos;
    }

    public void setidDescuentos(Descuentos idDescuentos) {
        this.idDescuentos = idDescuentos;
    }
}

