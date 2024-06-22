package com.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Lob
    @Column(name = "imagen_1", nullable = true)
    private byte[] imagen_1;

    @Lob
    @Column(name = "imagen_2", nullable = true)
    private byte[] imagen_2;


    @Column(name = "precio", nullable = false)
    private float precio = 0.0f;

    @Column(name = "cantidad", nullable = false)
    private int cantidad = 0;

    @Column(name = "idCategoria", nullable = false)
    private Long idCategoria; 

    @Column(name = "idDescuento", nullable = true)
    private Long idDescuento; 


}
