package com.uade.tpo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Product {

    public Product(){
    }

    public Product(String nombre, int cantidad, int precio, String descripcion){
        this.nombre=nombre;
        this.cantidad=cantidad;
        this.precio=precio;
        this.descripcion=descripcion;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private int cantidad;

    @Column
    private int precio;

    @Column
    private String descripcion;

    @OneToOne(mappedBy ="product")
    private Descuento descuento;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;
}
