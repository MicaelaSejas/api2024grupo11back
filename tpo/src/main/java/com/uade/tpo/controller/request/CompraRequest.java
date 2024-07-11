package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CompraRequest {

    public CompraRequest() {}

    @JsonProperty("usuarioId")
    private Long usuarioId;

    @JsonProperty("total")
    private float total;

    @JsonProperty("productos")
    private List<ProductoCompraRequest> productos;

    public Long getUsuarioId() {
        return usuarioId;
    }

    public float getTotal() {
        return total;
    }

    public List<ProductoCompraRequest> getProductos() {
        return productos;
    }

    public static class ProductoCompraRequest {
        
        @JsonProperty("productoId")
        private Long productoId;

        @JsonProperty("cantidad")
        private int cantidad;

        @JsonProperty("precio")
        private int precio;

        public ProductoCompraRequest() {}

        public Long getProductoId() {
            return productoId;
        }

        public int getCantidad() {
            return cantidad;
        }

        public int getPrecio() {
            return precio;
        }
    }
}
