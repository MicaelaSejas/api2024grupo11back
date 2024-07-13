package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FavoritoRequest {

	@JsonProperty("productoId")
	private Long productoId;

	public Long getProductoId() {
		return productoId;
	}

	public void setProductoId(long l) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setProductoId'");
	}

}
