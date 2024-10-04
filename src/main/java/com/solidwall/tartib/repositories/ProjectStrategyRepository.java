package com.solidwall.tartib.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectStrategyEntity;

@Repository
public interface ProjectStrategyRepository extends JpaRepository<ProjectStrategyEntity, Long> {

  Optional<ProjectStrategyEntity> findByProject(ProjectEntity project);

}
