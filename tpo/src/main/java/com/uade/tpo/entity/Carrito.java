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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CarritoProductos getCarritoProducto() {
		return carritoProducto;
	}

	public void setCarritoProducto(CarritoProductos carritoProducto) {
		this.carritoProducto = carritoProducto;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
    
    
    
}
