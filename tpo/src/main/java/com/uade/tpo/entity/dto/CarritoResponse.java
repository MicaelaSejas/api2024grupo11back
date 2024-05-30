package com.uade.tpo.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.tpo.entity.Carrito;
import com.uade.tpo.entity.CarritoProductos;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarritoResponse {

    @JsonProperty("message")
    public String message;

    @JsonProperty("error")
    public String error;

    @JsonProperty("carrito")
    public CarritoWithProductos carrito;

    public CarritoResponse(String message, String error) {
        this.message = message;
        this.error = error != null ? error : "";
    }

    public CarritoResponse(String message, Carrito carrito) {
        this.message = message;
        this.carrito = new CarritoWithProductos(carrito);
    }

    public static class CarritoWithProductos {
        @JsonProperty("id")
        public Long id;

        @JsonProperty("total")
        public float total;

        @JsonProperty("productos")
        public Set<CarritoProductos> productos;

        public CarritoWithProductos(Carrito carrito) {
            this.id = carrito.getId();
            this.total = carrito.getTotal();
            this.productos = carrito.getCarritoProductos();
        }
    }
}
