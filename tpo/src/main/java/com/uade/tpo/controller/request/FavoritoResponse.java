package com.uade.tpo.controller.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uade.tpo.entity.Favorito;
import com.uade.tpo.entity.FavoritoProductos;

import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class FavoritoResponse {

    @JsonProperty("message")
    public String message;

    @JsonProperty("error")
    public String error;

    @JsonProperty("favorito")
    public ProductosFavoritos favorito;

    public FavoritoResponse(String message, String error) {
        this.message = message;
        this.error = error != null ? error : "";
    }

    public FavoritoResponse(String message, Favorito favorito) {
        this.message = message;
        this.favorito = new ProductosFavoritos(favorito);
    }

    public static class ProductosFavoritos {
        @JsonProperty("id")
        public Long id;

        @JsonProperty("productos")
        public Set<FavoritoProductos> productos;

        public ProductosFavoritos(Favorito favorito) {
            this.id = favorito.getId();
            this.productos = favorito.getFavoritoProductos();
        }
    }

    public Object getMessage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMessage'");
    }
}
