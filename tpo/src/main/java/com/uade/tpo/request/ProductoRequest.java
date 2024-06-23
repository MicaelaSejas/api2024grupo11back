package com.uade.tpo.request;


import lombok.Data;

@Data
public class ProductoRequest {

    private int id;
    private String titulo;
    private String descripcion;
    private double precio;
    private int cantidad;
    private byte[] imagen_1;
    private byte[] imagen_2;
    private int idCategoria;
    private Integer idDescuento;

}
