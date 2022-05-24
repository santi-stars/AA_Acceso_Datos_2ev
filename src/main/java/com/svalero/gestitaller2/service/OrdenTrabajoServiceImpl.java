package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Mecanico;
import com.svalero.gestitaller2.domain.Moto;
import com.svalero.gestitaller2.domain.OrdenTrabajo;
import com.svalero.gestitaller2.domain.dto.OrdenTrabajoDTO;
import com.svalero.gestitaller2.exception.MecanicoNotFoundException;
import com.svalero.gestitaller2.exception.MotoNotFoundException;
import com.svalero.gestitaller2.exception.OrdenNotFoundException;
import com.svalero.gestitaller2.repository.MecanicoRepository;
import com.svalero.gestitaller2.repository.MotoRepository;
import com.svalero.gestitaller2.repository.OrdenTrabajoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdenTrabajoServiceImpl implements OrdenTrabajoService {

    @Autowired
    private OrdenTrabajoRepository ordenTrabajoRepository;
    @Autowired
    private MecanicoRepository mecanicoRepository;
    @Autowired
    private MotoRepository motoRepository;

    @Override
    public List<OrdenTrabajo> findAll() {
        return ordenTrabajoRepository.findAll();
    }

    @Override
    public OrdenTrabajo findById(long id) throws OrdenNotFoundException {
        return ordenTrabajoRepository.findById(id).orElseThrow(OrdenNotFoundException::new);
    }

    @Override
    public List<OrdenTrabajo> findByEjecutada(boolean ejecutada) {
        return ordenTrabajoRepository.findByEjecutada(ejecutada);
    }

    @Override
    public OrdenTrabajo deleteOrden(long id) throws OrdenNotFoundException {
        OrdenTrabajo mecanico = ordenTrabajoRepository.findById(id).orElseThrow(OrdenNotFoundException::new);
        ordenTrabajoRepository.delete(mecanico);
        return mecanico;
    }

    @Override
    public OrdenTrabajo addOrden(OrdenTrabajoDTO newOrdenTrabajoDTO) throws
            MecanicoNotFoundException, MotoNotFoundException {

        Moto moto = motoRepository.findById(newOrdenTrabajoDTO.getMoto())
                .orElseThrow(MotoNotFoundException::new);

        Mecanico mecanico = mecanicoRepository.findById(newOrdenTrabajoDTO.getMecanico())
                .orElseThrow(MecanicoNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        OrdenTrabajo ordenTrabajo = mapper.map(newOrdenTrabajoDTO, OrdenTrabajo.class);
        ordenTrabajo.setMecanico(mecanico);
        ordenTrabajo.setMoto(moto);

        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    @Override
    public OrdenTrabajo modifyOrden(long id, OrdenTrabajoDTO newOrdenTrabajoDTO) throws
            MecanicoNotFoundException, MotoNotFoundException {

        Moto moto = motoRepository.findById(newOrdenTrabajoDTO.getMoto())
                .orElseThrow(MotoNotFoundException::new);

        Mecanico mecanico = mecanicoRepository.findById(newOrdenTrabajoDTO.getMecanico())
                .orElseThrow(MecanicoNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        OrdenTrabajo ordenTrabajo = mapper.map(newOrdenTrabajoDTO, OrdenTrabajo.class);
        ordenTrabajo.setId(id);
        ordenTrabajo.setMecanico(mecanico);
        ordenTrabajo.setMoto(moto);

        return ordenTrabajoRepository.save(ordenTrabajo);
    }

    @Override
    public OrdenTrabajo modifyOrdenEjecutada(long id, boolean ejecutada) throws OrdenNotFoundException {
        OrdenTrabajo ordenTrabajo = ordenTrabajoRepository.findById(id).orElseThrow(OrdenNotFoundException::new);
        ordenTrabajo.setEjecutada(ejecutada);
        return ordenTrabajoRepository.save(ordenTrabajo);
    }
}
