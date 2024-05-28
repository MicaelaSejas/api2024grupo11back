package com.uade.tpo.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Carrito {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCARRITO")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "idCarritoProductos", referencedColumnName = "idCarritoProducto")
    private CarritoProductos carritoProducto;

    @Column(name = "total")
    private float total;
    
}
