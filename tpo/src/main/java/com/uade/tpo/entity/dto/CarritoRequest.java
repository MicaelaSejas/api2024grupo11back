package com.uade.tpo.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CarritoRequest {

    @JsonProperty("productoId")
    private Long productoId;

    @JsonProperty("cantidad")
    private int cantidad;

}
