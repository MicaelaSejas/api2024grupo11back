package com.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "compraProducto")
public class CompraProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCompra", referencedColumnName = "id")
    @JsonIgnore
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    @JsonIgnore
    private Producto producto;

    @Column(name = "cantidad", nullable = false)
    private int cantidad;

    
}
