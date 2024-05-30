package com.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "descuentos")

public class Descuento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDescuentos",nullable = false)
    private Long idDescuentos;

    @Column(name = "Porcentaje",nullable = false)
    private int Porcentaje;

    @JsonIgnore
    @OneToOne(mappedBy = "idDescuento")
   private Producto productos;

    // Aca los getters and Setters
    public Long getIdDescuentos() {
        return idDescuentos;
    }

    public void setIdDescuentos(Long idDescuentos) {
        this.idDescuentos = idDescuentos;
    }

    public int getPorcentaje() {
        return Porcentaje;
    }

    public void setPorcentaje(int Porcentaje) {
        this.Porcentaje = Porcentaje;
    }

    public Producto getProductos() {
        return productos;
    }

    public void setProductos(Producto productos) {
        this.productos = productos;
    }   
}
