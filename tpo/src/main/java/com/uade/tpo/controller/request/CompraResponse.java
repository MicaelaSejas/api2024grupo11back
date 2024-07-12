package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.tpo.controller.auth.AuthenticationResponse;
import com.uade.tpo.entity.Compra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompraResponse {

	public CompraResponse(String message) {
		this.message = message;
	}
	
	@JsonProperty("message")
	private String message;
	
	@JsonProperty
	private Compra compra;
}
