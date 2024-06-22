package com.uade.tpo.controller.request;

import lombok.Data;

@Data
public class ProductoRequest {

    private Long id;
    private String titulo;
    private String descripcion;
    private byte[] imagen_1;
    private byte[] imagen_2;
    private float precio;
    private int cantidad;
    private Long idCategoria;
    private Long idDescuento;

    
    public ProductoRequest() {
    }

    
    public ProductoRequest(Long id, String titulo, String descripcion,  byte[] imagen_1, byte[] imagen_2, float precio, int cantidad, Long idCategoria, Long idDescuento) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen_1 = imagen_1;
        this.imagen_2 = imagen_2;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.idDescuento = idDescuento;
    }
}
