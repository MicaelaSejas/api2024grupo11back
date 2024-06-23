package com.uade.tpo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.repository.DescuentoRepository;

@Service
public class DescuentoService {

    @Autowired
	private DescuentoRepository descuentoRepository;


    public List<Descuento> listarDescuentos() {
        return descuentoRepository.findAll();
    }

    public Optional<Descuento> obtenerDescuentoPorId(Integer id) {
        return descuentoRepository.findById(id);
    }

    public Descuento guardarDescuento(Descuento descuento) {
        return descuentoRepository.save(descuento);
    }

    public void eliminarDescuento(Integer id) {
        descuentoRepository.deleteById(id);
    }

    public Descuento actualizarDescuento(Integer id, Descuento descuentoActualizado) {
        Optional<Descuento> optionalDescuento = descuentoRepository.findById(id);
        if (optionalDescuento.isPresent()) {
            Descuento descuentoExistente = optionalDescuento.get();
            descuentoExistente.setPorcentaje(descuentoActualizado.getPorcentaje());
            return descuentoRepository.save(descuentoExistente);
        } else {
            throw new IllegalArgumentException("No se encontró el descuento con ID: " + id);
        }
    }
}
