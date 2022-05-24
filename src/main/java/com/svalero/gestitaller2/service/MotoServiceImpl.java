package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Moto;
import com.svalero.gestitaller2.exception.MotoNotFoundException;
import com.svalero.gestitaller2.repository.MotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoServiceImpl implements MotoService {

    @Autowired
    private MotoRepository motoRepository;

    @Override
    public List<Moto> findAll() {
        return motoRepository.findAll();
    }

    @Override
    public Moto findById(long id) throws MotoNotFoundException {
        return motoRepository.findById(id).orElseThrow(MotoNotFoundException::new);
    }

    @Override
    public List<Moto> findByMarca(String marca) {
        return motoRepository.findByMarca(marca);
    }

    @Override
    public Moto deleteMoto(long id) throws MotoNotFoundException {
        Moto moto = motoRepository.findById(id).orElseThrow(MotoNotFoundException::new);
        motoRepository.delete(moto);
        return moto;
    }

    @Override
    public Moto addMoto(Moto moto) {
        return motoRepository.save(moto);
    }

    @Override
    public Moto modifyMoto(long id, Moto newMoto) throws MotoNotFoundException {
        Moto moto = motoRepository.findById(id).orElseThrow(MotoNotFoundException::new);
        moto.setMarca(newMoto.getMarca());
        moto.setModelo(newMoto.getModelo());
        moto.setCilindrada(newMoto.getCilindrada());
        moto.setFechaMatriculacion(newMoto.getFechaMatriculacion());
        moto.setCliente(newMoto.getCliente());

        return motoRepository.save(moto);
    }

    @Override
    public Moto modifyMarcaMoto(long id, String marca) throws MotoNotFoundException {
        Moto moto = motoRepository.findById(id).orElseThrow(MotoNotFoundException::new);
        moto.setMarca(marca);
        return motoRepository.save(moto);
    }
}
