package com.uade.tpo.request;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductoForm {
    
    
    private int id;
    private String titulo;
    private String descripcion;
    private double precio;
    private int cantidad;
    private MultipartFile imagen_1;
    private MultipartFile imagen_2;
    private int idCategoria;
    private Integer idDescuento;

}