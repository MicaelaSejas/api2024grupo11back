package com.uade.tpo.entity.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private int id;
    private String nombre;
    private int cantidad;
    private int precio;
    private String descripcion;
}
