package com.uade.tpo.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.uade.tpo.entity.Descuento;
import com.uade.tpo.repository.DescuentoRepository;

@Service
public class DescuentoServiceImp implements DescuentoService {
    @Autowired
    private DescuentoRepository descuentoRepository;


    public Page<Descuento> getDescuento(PageRequest pageable){
        return descuentoRepository.findAll(pageable);
    }


    public Optional<Descuento> getDescuentoById(Long id){
        return descuentoRepository.findById(id);
    }

    public Descuento createDescuento(Descuento descuento) {
        Optional<Descuento> listaDescuento = descuentoRepository.findById(descuento.getIdDescuentos());
        if (listaDescuento.isEmpty()){
            return descuentoRepository.save(descuento);
        }else{
            throw new IllegalStateException("El descuento ya existe");
        }
    }

    public List<Descuento> getAllDescuento() {
        return descuentoRepository.findAll();
    }

    public Descuento eliminarDescuento(Long id) {
        Optional<Descuento> descuentoOptional = descuentoRepository.findById(id);
        if(descuentoOptional.isPresent()){
            Descuento descuentoEliminado = descuentoOptional.get();
            descuentoRepository.deleteById(id);
            return descuentoEliminado;
        }else{
            throw new NoSuchElementException("El descuento no existe");
        } 
    }

    public Descuento actualizarDescuento(Long id, Descuento descuentoActualizado) {
        descuentoActualizado.setIdDescuentos(id);
        return descuentoRepository.save(descuentoActualizado);
    }

}
