package com.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private double precio;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    
    @Column(name = "imagen_1_URL", nullable = true)
    private String  imagen_1_URL;

    @Column(name = "imagen_2_URL", nullable = true)
    private String  imagen_2_URL;
    
    @ManyToOne()
    @JoinColumn(name = "idCategoria", nullable = false)
    private Categoria categoria;

    @ManyToOne()
    @JoinColumn(name = "idDescuento")
    private Descuento descuento;

    public double getPrecioConDescuento() {
        if (this.descuento != null && this.descuento.getPorcentaje() != 0) {
        	double porcentaje = (float)this.descuento.getPorcentaje() / 100f;
            double precioFinal = (this.precio - (this.precio * porcentaje));
            return precioFinal;
        } else {
            return this.precio;
        }
    }
    

}
