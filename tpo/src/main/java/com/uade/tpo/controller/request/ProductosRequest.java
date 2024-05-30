package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductosRequest {

	@JsonProperty("idProductos")
    private Long idProductos;
	
	@JsonProperty("titulo")
    private String titulo;
	
    private byte[] imagen_1;
    private byte[] imagen_2;
    
    @JsonProperty("descripcion")
    private String descripcion;
    
    @JsonProperty("precio")
    private float precio;
    
    @JsonProperty("cantidad")
    private int cantidad;
    
    @JsonProperty("idCategoria")
    private int idCategoria;
    
    @JsonProperty("idDescuento")
    private int idDescuento;

	public Long getIdProductos() {
		return idProductos;
	}

	public String getTitulo() {
		return titulo;
	}

	public byte[] getImagen_1() {
		return imagen_1;
	}

	public byte[] getImagen_2() {
		return imagen_2;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public int getIdDescuento() {
		return idDescuento;
	}
    
    
    
}