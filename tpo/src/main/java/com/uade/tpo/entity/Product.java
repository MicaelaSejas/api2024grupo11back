package com.uade.tpo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Product {

    public Product(){
    }

    public Product(String titulo, int cantidad, float precio, String descripcion){
        this.titulo=titulo;
        this.cantidad=cantidad;
        this.precio=precio;
        this.descripcion=descripcion;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private int cantidad;

    @Column
    private float precio;

    @Column
    private String descripcion;

    @OneToOne
    @JoinColumn(name = "idDescuento", referencedColumnName = "id")
    private Descuento descuento;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    private Categoria categoria;

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

    public Categoria getidCategoria() {
        return categoria;
    }

    public void setidCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Descuento getidDescuento() {
        return descuento;
    }

    public void setidDescuento(Descuento descuento) {
        this.descuento = descuento;
    }
}
