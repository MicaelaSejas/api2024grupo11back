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

    public Descuento(int Porcentaje){
        this.Porcentaje=Porcentaje;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "idDescuentos")
    private Long id;

    @Column
    private int Porcentaje;

    @OneToOne(mappedBy = "idDescuento")
    private Product product;
}
