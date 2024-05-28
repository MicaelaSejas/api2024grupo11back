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
    private Long id;

    @Column
    private String descripcion;

//    @OneToMany
//    @JoinColumn(name = "categoriaid", nullable = false)
//    private Product product;
}
