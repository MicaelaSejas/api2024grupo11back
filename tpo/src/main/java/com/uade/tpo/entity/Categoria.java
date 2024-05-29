package com.uade.tpo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Categoria {

    public Categoria() {
    }

    public Categoria(String descripcion) {
        this.descripcion = descripcion;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idCategorias")
    private Long id;

    @Column
    private String descripcion;

    @OneToMany
    @JoinColumn(name = "idCategoria", nullable = false)
    private Product product;

    //getters setters
    public Long getIdCategorias() {
        return id;
    }

    public void setIdCategorias(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
