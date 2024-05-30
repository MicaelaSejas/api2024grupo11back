package com.uade.tpo.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarritoRequest {
	
	@JsonProperty
	private Long userId;

    @JsonProperty("productoId")
    private Long productoId;

    @JsonProperty("cantidad")
    private int cantidad;

	public Long getProductoId() {
		return productoId;
	}

	public int getCantidad() {
		return cantidad;
	}

    
    
    
}
