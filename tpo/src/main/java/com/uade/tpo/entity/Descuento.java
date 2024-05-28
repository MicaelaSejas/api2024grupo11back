package com.uade.tpo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Descuento {

    public Descuento(){
    }

    public Descuento(int descuento){
        this.descuento=descuento;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idDescuentos")
    private Long id;

    @Column
    private int descuento;

    @OneToOne(mappedBy = "descuento")
    private Product product;
}
