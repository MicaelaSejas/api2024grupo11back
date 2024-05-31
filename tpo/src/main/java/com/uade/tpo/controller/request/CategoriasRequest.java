package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CategoriasRequest {
	
	@JsonProperty("idCategorias")
    private Long idCategorias;
	
	@JsonProperty("descripcion")
    private String descripcion;

	public Long getIdCategorias() {
		return idCategorias;
	}

	public void setIdCategorias(Long idCategorias) {
		this.idCategorias = idCategorias;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
