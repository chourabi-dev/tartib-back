package com.solidwall.tartib.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectRiskEntity;

@Repository
public interface ProjectRiskRepository extends JpaRepository<ProjectRiskEntity, Long> {

    Optional<ProjectRiskEntity> findByProject(ProjectEntity project);
}
