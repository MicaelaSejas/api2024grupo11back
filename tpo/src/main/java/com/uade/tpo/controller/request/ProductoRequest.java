package com.uade.tpo.controller.request;

import com.uade.tpo.entity.Categoria;
import com.uade.tpo.entity.Descuento;

import lombok.Data;

@Data
public class ProductoRequest {

    private Long id;
    private String titulo;
    private String imagen1Url;
    private String imagen2Url;
    private float precio;
    private int cantidad;
    private Long idCategoria;
    private Long idDescuento;

    // Constructor vacío
    public ProductoRequest() {
    }

    // Constructor con la entidad Producto
    public ProductoRequest(Long id, String titulo, String imagen1Url, String imagen2Url, float precio, int cantidad, Categoria categoria, Descuento descuento) {
        this.id = id;
        this.titulo = titulo;
        this.imagen1Url = imagen1Url;
        this.imagen2Url = imagen2Url;
        this.precio = precio;
        this.cantidad = cantidad;
        if (categoria != null) {
            this.idCategoria = categoria.getId();
        }
        if (descuento != null) {
            this.idDescuento = descuento.getId();
        }
    }
}
