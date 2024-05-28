package com.uade.tpo.service;

import com.uade.tpo.entity.Descuentos;
import java.util.Optional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface DescuentosService {

    public Page<Descuentos> getDescuentos(PageRequest pageRequest);

    public Optional<Descuentos> getDescuentosById(Integer idDescuentos);

    public Descuentos crearDescuentos(Integer idDescuentos);

    public List<Descuentos> getAllDescuentos();

    public Descuentos eliminarDescuentos(Integer idDescuentos);

    public Descuentos modificarDescuentos(Integer idDescuentos);

}