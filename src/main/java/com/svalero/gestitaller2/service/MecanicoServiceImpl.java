package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Mecanico;
import com.svalero.gestitaller2.exception.MecanicoNotFoundException;
import com.svalero.gestitaller2.repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MecanicoServiceImpl implements MecanicoService {

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Override
    public List<Mecanico> findAllMecanicos() {
        return mecanicoRepository.findAll();
    }

    @Override
    public Mecanico findMecanico(long id) throws MecanicoNotFoundException {
        return mecanicoRepository.findById(id).orElseThrow(MecanicoNotFoundException::new);
    }

    @Override
    public List<Mecanico> findByNombre(String nombre) {
        return mecanicoRepository.findByNombre(nombre);
    }

    @Override
    public Mecanico deleteMecanico(long id) throws MecanicoNotFoundException {
        Mecanico mecanico = mecanicoRepository.findById(id).orElseThrow(MecanicoNotFoundException::new);
        mecanicoRepository.delete(mecanico);
        return mecanico;
    }

    @Override
    public Mecanico addMecanico(Mecanico mecanico) {
        return mecanicoRepository.save(mecanico);
    }

    @Override
    public Mecanico modifyMecanico(long id, Mecanico newMecanico) throws MecanicoNotFoundException {
        Mecanico mecanico = mecanicoRepository.findById(id).orElseThrow(MecanicoNotFoundException::new);
        mecanico.setNombre(newMecanico.getNombre());
        mecanico.setApellido(newMecanico.getApellido());
        mecanico.setEdad(newMecanico.getEdad());
        mecanico.setDni(newMecanico.getDni());
        mecanico.setDisponible(newMecanico.isDisponible());
        mecanico.setFechaContratacion(newMecanico.getFechaContratacion());

        return mecanicoRepository.save(mecanico);
    }

    @Override
    public Mecanico modifyFacturaPagada(long id, boolean disponible) throws MecanicoNotFoundException {
        Mecanico mecanico = mecanicoRepository.findById(id).orElseThrow(MecanicoNotFoundException::new);
        mecanico.setDisponible(disponible);
        return mecanicoRepository.save(mecanico);
    }
}
