package com.uade.tpo.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.tpo.entity.Carrito;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarritoResponse {

	@JsonProperty("message")
	public String message;

	@JsonProperty("error")
	public String error;
	
	@JsonProperty("carrito")
	public Carrito carrito;

	public CarritoResponse(String message, String error) {
		this.message = message;
        this.error = error != null ? error : "";
	}
	
	public CarritoResponse(String message, Carrito carrito) {
		this.message = message;
		this.carrito = carrito;
	}
	
}
