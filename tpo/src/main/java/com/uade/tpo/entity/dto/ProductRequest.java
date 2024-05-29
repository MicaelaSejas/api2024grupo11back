package com.uade.tpo.entity.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private int id;
    private String titulo;
    private int cantidad;
    private float precio;
    private String descripcion;
    private int idCategoria;
    private int idDescuento;
}
