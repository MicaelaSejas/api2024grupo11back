package com.uade.tpo.entity;

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
@Table(name = "productos")
public class Product {

    public Product(){
    }

    public Product(String nombre, int cantidad, int precio, String descripcion){
        this.cantidad=cantidad;
        this.precio=precio;
        this.descripcion=descripcion;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private int cantidad;

    @Column
    private int precio;

    @Column
    private String descripcion;

//    @OneToOne(mappedBy ="product")
//    private Descuento descuento;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "idCategorias")
    private Categoria categoria;
    
    
    public int getCantidad() {
    	return this.cantidad;
    }
    
    public void setCantidad(int cantidad) {
    	this.cantidad = cantidad;
    }

	public int getPrecio() {
		return this.precio;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getId() {
		return id;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	
    
}
