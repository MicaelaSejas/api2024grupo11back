package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarritoRequest {
	
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
