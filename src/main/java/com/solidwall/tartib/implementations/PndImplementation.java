package com.solidwall.tartib.implementations;

import java.util.List;
import com.solidwall.tartib.entities.PndEntity;

public interface PndImplementation {

    List<PndEntity> findAll();

    PndEntity findOne();

    PndEntity getOne(Long id);

    PndEntity create(PndEntity data);

    PndEntity update(Long id, PndEntity data);

    void delete(Long id);
}
