package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DescuentosRequest {
	
	@JsonProperty("idDescuentos")
    private Long idDescuentos;
	
	@JsonProperty("porcentaje")
    private int porcentaje;

	public Long getIdDescuentos() {
		return idDescuentos;
	}

	public int getPorcentaje() {
		return porcentaje;
	}

}
