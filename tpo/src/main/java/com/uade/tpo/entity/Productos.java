package com.uade.tpo.entity;

import org.hsqldb.lib.RCData;

import com.mysql.cj.jdbc.Blob;
import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Productos {

    private Productos() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String categoria;

    @Column
    private Blob imagen_1;

    @Column
    private Blob imagen_2;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private float precio = 0.0f;

    @Column(nullable = false)
    private int cantidad = 0;

    @Column(nullable = false)
    private int idCategoria;

    @Column(nullable = false)
    private int idDescuento;

    @OneToOne
    @JoinColumn(name = "idDescuento", referencedColumnName = "idDescuentos")
    private Descuentos descuentos;

    @OneToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategorias")
    private Categorias categorias;
}
