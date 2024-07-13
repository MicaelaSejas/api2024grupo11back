package com.uade.tpo.service;

import org.springframework.http.ResponseEntity;

import com.uade.tpo.controller.request.FavoritoRequest;
import com.uade.tpo.controller.request.FavoritoResponse;
import com.uade.tpo.entity.Favorito;
import com.uade.tpo.exception.FavNotFoundException;
import com.uade.tpo.exception.ProductInFavsException;

public interface FavoritoService {

	public Favorito getFavoritoByUserId(final Long userId) throws FavNotFoundException;

	public ResponseEntity<FavoritoResponse> removeFromFavorito(final Long favoritoId, final FavoritoRequest request)
			throws FavNotFoundException, ProductInFavsException;

	public ResponseEntity<FavoritoResponse> addToFavorito(final Long favoritoId, final FavoritoRequest request)
			throws FavNotFoundException, ProductInFavsException;
}
