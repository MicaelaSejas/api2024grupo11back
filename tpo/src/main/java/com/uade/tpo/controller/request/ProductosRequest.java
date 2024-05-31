package com.uade.tpo.controller.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductosRequest {

    private Long idProductos;
    private String titulo;
    private MultipartFile  imagen_1;
    private MultipartFile  imagen_2;
    private String descripcion;
    private float precio;
    private int cantidad;
    private int idCategoria;
    private int idDescuento;
}