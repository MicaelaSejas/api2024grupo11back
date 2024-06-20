package com.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "imagen_1", nullable = true)
    private byte[] imagen_1;

    @Column(name = "imagen_2", nullable = true)
    private byte[] imagen_2;

    @Column(name = "imagen_3_url", nullable = true)
    private String imagen_3_url;

    @Column(name = "imagen_4_url", nullable = true)
    private String imagen_4_url;

    @Column(name = "precio", nullable = false)
    private float precio = 0.0f;

    @Column(name = "cantidad", nullable = false)
    private int cantidad = 0;

    @ManyToOne
    @JoinColumn(name = "idCategoria", nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Categoria categoria;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "idDescuento", nullable = true, referencedColumnName = "id")
    @JsonIgnore
    private Descuento descuento;
}
