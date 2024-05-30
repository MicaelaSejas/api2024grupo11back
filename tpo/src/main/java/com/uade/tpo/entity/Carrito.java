package com.uade.tpo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "carrito")
public class Carrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCARRITO")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idUsuario", referencedColumnName = "id")
	private Usuario usuario;

	@Column(name = "total")
	private float total;

	@OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<CarritoProductos> carritoProductos;

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

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public Set<CarritoProductos> getCarritoProductos() {
		return carritoProductos;
	}

	public void setCarritoProductos(Set<CarritoProductos> carritoProductos) {
		this.carritoProductos = carritoProductos;
	}

}
