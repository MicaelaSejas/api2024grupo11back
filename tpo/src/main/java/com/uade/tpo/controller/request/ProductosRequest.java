package com.uade.tpo.controller.request;

import lombok.Data;

@Data
public class ProductosRequest {

    private Long idProductos;
    private String titulo;
    private String categoria;
    private byte[] imagen_1;
    private byte[] imagen_2;
    private String descripcion;
    private float precio;
    private int cantidad;
    private int idCategoria;
    private int idDescuento;
}