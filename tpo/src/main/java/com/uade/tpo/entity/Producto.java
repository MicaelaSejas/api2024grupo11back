package com.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "imagen_1_url", nullable = true)
    private String imagen_1_url;

    @Column(name = "imagen_2_url", nullable = true)
    private String imagen_2_url;

    @Column(name = "precio", nullable = false)
    private float precio = 0.0f;

    @Column(name = "cantidad", nullable = false)
    private int cantidad = 0;

    @ManyToOne(fetch = FetchType.LAZY) // Muchos productos pueden estar asociados a una categoría
    @JoinColumn(name = "idCategoria", referencedColumnName = "id", nullable = false)
    private Categoria categoria; // Cambiado a tipo Categoria, y se elimina el prefijo "id" del nombre de la columna

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDescuento", referencedColumnName = "id", nullable = true)
    private Descuento descuento; // Se cambia a "descuento" en minúsculas


}
