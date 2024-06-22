package com.uade.tpo.controller.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductoForm {
    private Long id;
    private String titulo;
    private String descripcion;
    private MultipartFile imagen_1;
    private MultipartFile imagen_2;
    private float precio;
    private int cantidad;
    private Long idCategoria;
    private Long idDescuento;

    
    public ProductoForm() {
    }

    
    public ProductoForm(String titulo, String descripcion, MultipartFile imagen_1, MultipartFile imagen_2,
                        float precio, int cantidad, Long idCategoria, Long idDescuento) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen_1 = imagen_1;
        this.imagen_2 = imagen_2;
        this.precio = precio;
        this.cantidad = cantidad;
        this.idCategoria = idCategoria;
        this.idDescuento = idDescuento;
    }

    
    public ProductoForm(Long id, String titulo, String descripcion, MultipartFile imagen_1, MultipartFile imagen_2,
                        float precio, int cantidad, Long idCategoria, Long idDescuento) {
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
