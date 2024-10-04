package com.solidwall.tartib.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.solidwall.tartib.core.exceptions.FoundException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.entities.PndEntity;
import com.solidwall.tartib.implementations.PndImplementation;
import com.solidwall.tartib.repositories.PndRepository;

@Service
public class PndService implements PndImplementation {

    @Autowired
    PndRepository pndRepository;

    @Override
    public PndEntity getOne(Long id) {
        Optional<PndEntity> data = pndRepository.findById(id);
        if (data.isPresent()) {
            return data.get();
        } else {
            throw new NotFoundException("PND not exist");
        }
    }

    @Override
    public PndEntity findOne() {
        throw new UnsupportedOperationException("Unimplemented method 'findOne'");
    }

    @Override
    public List<PndEntity> findAll() {
        if (!pndRepository.findAll().isEmpty()) {
            return pndRepository.findAll();
        } else {
            throw new NotFoundException("not exist any PND ");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<PndEntity> data = pndRepository.findById(id);
        if (data.isPresent()) {
            pndRepository.deleteById(id);
        } else {
            throw new NotFoundException("PND not exist");
        }
    }

    @Override
    public PndEntity create(PndEntity data) {
        Optional<PndEntity> pnd = pndRepository.findByName(data.getName());
        if (!pnd.isPresent()) {
            return pndRepository.save(data);
        } else {
            throw new FoundException("PND already exist");
        }
    }

    @Override
    public PndEntity update(Long id, PndEntity data) {
        Optional<PndEntity> pnd = pndRepository.findById(id);
        if (pnd.isPresent()) {
            PndEntity updatedPnd = pnd.get();
            updatedPnd.setName(data.getName());
            updatedPnd.setCode(data.getCode());
            updatedPnd.setDescription(data.getDescription());
            updatedPnd.setActive(data.isActive());
            updatedPnd.setUpdatedAt(data.getUpdatedAt());
            return pndRepository.save(updatedPnd);
        } else {
            throw new NotFoundException("PND not found");
        }
    }

}
