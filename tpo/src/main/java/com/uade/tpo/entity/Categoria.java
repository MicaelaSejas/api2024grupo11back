package com.uade.tpo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "categorias")
public class Categoria {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategorias", nullable = false)
    private Long idCategorias;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @JsonIgnore
    @OneToMany(mappedBy = "idCategoria")
    private List<Producto> productos;

    // Aca los getter  y setters
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

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }    

}
