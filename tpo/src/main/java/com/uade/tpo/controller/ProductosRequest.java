package com.uade.tpo.controller;

import com.mysql.cj.jdbc.Blob;

import lombok.Data;

@Data
public class ProductosRequest {

    private int id;
    private String titulo;
    private String categoria;
    private Blob imagen_1;
    private Blob imagen_2;
    private String descripcion;
    private float precio;
    private int cantidad;
    private int idCategoria;
    private int idDescuento;
}