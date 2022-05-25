package com.svalero.gestitaller2.service;

import com.svalero.gestitaller2.domain.Spare;
import com.svalero.gestitaller2.exception.SpareNotFoundException;
import com.svalero.gestitaller2.repository.SpareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpareServiceImpl implements SpareService {

    @Autowired
    private SpareRepository spareRepository;

    @Override
    public List<Spare> findAll() {
        return spareRepository.findAll();
    }

    @Override
    public Spare findById(long id) throws SpareNotFoundException {
        return spareRepository.findById(id).orElseThrow(SpareNotFoundException::new);
    }

    @Override
    public List<Spare> findByStockAmount(int amountStock) {
        return spareRepository.findByStockAmount(amountStock);
    }

    @Override
    public Spare deleteSpare(long id) throws SpareNotFoundException {
        Spare spare = spareRepository.findById(id).orElseThrow(SpareNotFoundException::new);
        spareRepository.delete(spare);
        return spare;
    }

    @Override
    public Spare addSpare(Spare spare) {
        return spareRepository.save(spare);
    }

    @Override
    public Spare modifySpare(long id, Spare newSpare) throws SpareNotFoundException {
        Spare spare = spareRepository.findById(id).orElseThrow(SpareNotFoundException::new);
        spare.setDescription(newSpare.getDescription());
        spare.setPrice(newSpare.getPrice());
        spare.setStockAmount(newSpare.getStockAmount());
        spare.setMinAmount(newSpare.getMinAmount());
        spare.setEntryDate(newSpare.getEntryDate());

        return spareRepository.save(spare);
    }

    @Override
    public Spare modifySpareDescription(long id, String description) throws SpareNotFoundException {
        Spare spare = spareRepository.findById(id).orElseThrow(SpareNotFoundException::new);
        spare.setDescription(description);
        return spareRepository.save(spare);
    }

}
