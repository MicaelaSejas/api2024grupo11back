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
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(exclude = "favorito")
@Table(name = "favoritoproductos")
public class FavoritoProductos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFavoritoProducto")
	@JsonIgnore
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idProducto", referencedColumnName = "id")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "idFavorito", referencedColumnName = "idFavorito")
	@JsonIgnore
	private Favorito favorito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

}
