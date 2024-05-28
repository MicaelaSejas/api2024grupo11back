package com.uade.tpo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class CarritoProductos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarritoProducto")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    private Product producto;

    @ManyToOne
    @JoinColumn(name = "idCarrito", referencedColumnName = "idCARRITO")
    private Carrito carrito;

    @Column(name = "cantidad")
    private int cantidad;

}
