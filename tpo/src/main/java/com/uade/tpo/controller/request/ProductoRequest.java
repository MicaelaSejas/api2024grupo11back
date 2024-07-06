package com.uade.tpo.controller.request;


import lombok.Data;

@Data
public class ProductoRequest {

    private int id;
    private String titulo;
    private String descripcion;
    private double precio;
    private int cantidad;
    private String imagen_1_URL;
    private String imagen_2_URL;
    private Integer idCategoria;
    private Integer idDescuento;

}
