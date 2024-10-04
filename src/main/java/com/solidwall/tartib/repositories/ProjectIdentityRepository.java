package com.solidwall.tartib.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectIdentityEntity;


@Repository
public interface ProjectIdentityRepository extends JpaRepository<ProjectIdentityEntity, Long> {

    Optional<ProjectIdentityEntity> findByCode(String code);    
    long count(); // To count existing projects for sequential numbering

    // Optional<ProjectIdentityEntity> findByProject(ProjectEntity project);
    
}
