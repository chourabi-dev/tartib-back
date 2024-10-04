package com.solidwall.tartib.implementations;

import java.util.List;
import java.util.Map;

import com.solidwall.tartib.dto.project.identity.CreateDto;
import com.solidwall.tartib.dto.project.identity.UpdateDto;
import com.solidwall.tartib.entities.ProjectIdentityEntity;

public interface ProjectIdentityImplementation {

    List<ProjectIdentityEntity> findAll();

    ProjectIdentityEntity findOne(Map<String, String> data);

    ProjectIdentityEntity getOne(Long id);

    ProjectIdentityEntity create(CreateDto data);

    ProjectIdentityEntity update(Long id, UpdateDto data);

    void delete(Long id);
}
