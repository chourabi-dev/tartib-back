package com.solidwall.tartib.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.exceptions.BadRequestException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.entities.PndAxisEntity;
import com.solidwall.tartib.entities.PndEntity;
import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectStrategyEntity;
import com.solidwall.tartib.entities.StrategyAxisEntity;
import com.solidwall.tartib.entities.StrategyEntity;
import com.solidwall.tartib.implementations.ProjectStrategyImplementation;
import com.solidwall.tartib.repositories.PndAxisRepository;
import com.solidwall.tartib.repositories.PndRepository;
import com.solidwall.tartib.repositories.ProjectRepository;
import com.solidwall.tartib.repositories.ProjectStrategyRepository;
import com.solidwall.tartib.repositories.StrategyAxisRepository;
import com.solidwall.tartib.repositories.StrategyRepository;
import com.solidwall.tartib.dto.project.strategy.CreateDto;
import com.solidwall.tartib.dto.project.strategy.UpdateDto;

@Service
public class ProjectStrategyService implements ProjectStrategyImplementation {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ProjectStrategyRepository projectStrategyRepository;

  @Autowired
  PndRepository pndRepository;

  @Autowired
  PndAxisRepository pndAxisRepository;

  @Autowired
  StrategyRepository strategyRepository;

  @Autowired
  StrategyAxisRepository strategyAxisRepository;

  @Override
  public List<ProjectStrategyEntity> findAll() {
    if (!projectStrategyRepository.findAll().isEmpty()) {
      return projectStrategyRepository.findAll();
    } else {
      throw new NotFoundException("not exist any project strategy");
    }
  }

  @Override
  public ProjectStrategyEntity getOne(Long id) {
    Optional<ProjectStrategyEntity> projectStrategy = projectStrategyRepository.findById(id);
    if (projectStrategy.isPresent()) {
      return projectStrategy.get();
    } else {
      throw new NotFoundException("project strategy not exist");
    }
  }

  @Override
  public ProjectStrategyEntity findOne(Map<String, String> data) {
    if (data.get("project") != null) {
      Long projectId = Long.parseLong(data.get("project"));
      Optional<ProjectEntity> project = projectRepository.findById(projectId);
      if (!project.isPresent())
        throw new NotFoundException("project not found");
      Optional<ProjectStrategyEntity> projectStrategy = projectStrategyRepository.findByProject(project.get());
      if (!projectStrategy.isPresent())
        throw new NotFoundException("project identity not found");
      return projectStrategy.get();
    }
    throw new BadRequestException("param not exist");
  }

  @Override
  public ProjectStrategyEntity create(CreateDto data) {

    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    PndEntity pnd = data.getPnd() != null ? pndRepository.findById(data.getPnd()).orElse(null) : null;
    PndAxisEntity pndAxis = data.getPnd() != null ? pndAxisRepository.findById(data.getPndAxis()).orElse(null) : null;
    StrategyEntity strategy = data.getStrategy() != null ? strategyRepository.findById(data.getStrategy()).orElse(null) : null;
    StrategyAxisEntity strategyAxis = data.getStrategyAxis() != null ? strategyAxisRepository.findById(data.getStrategyAxis()).orElse(null) : null;

    if(project.isPresent()) {
      ProjectStrategyEntity newProjectStrategy = new ProjectStrategyEntity();
      newProjectStrategy.setProject(project.get());
      newProjectStrategy.setPnd(pnd);
      newProjectStrategy.setPndAxis(pndAxis);
      newProjectStrategy.setStrategy(strategy);
      newProjectStrategy.setStrategyAxis(strategyAxis);
      newProjectStrategy.setShemaName(data.getShemaName());
      newProjectStrategy.setShemaProject(data.getShemaProject());
      newProjectStrategy.setBlueprintName(data.getBlueprintName());
      newProjectStrategy.setBlueprintProject(data.getBlueprintProject());
      newProjectStrategy.setPndProject(data.getPndProject());
      newProjectStrategy.setObjective(data.getObjective());
      newProjectStrategy.setResult(data.getResult());
      newProjectStrategy.setComponents(data.getComponents());
      newProjectStrategy.setDocument(data.getDocument());
      newProjectStrategy.setRealisationStart(data.getRealisationStart());
      newProjectStrategy.setRealisationEnd(data.getRealisationEnd());
      newProjectStrategy.setOperationStart(data.getOperationStart());
      newProjectStrategy.setWorkplan(data.getWorkplan());
      return projectStrategyRepository.save(newProjectStrategy);
    } else {
      throw new NotFoundException("project not found");
    }

  }

  @Override
  public ProjectStrategyEntity update(Long id, UpdateDto data) {
    Optional<ProjectStrategyEntity> projectStrategy = projectStrategyRepository.findById(id);
    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    PndEntity pnd = data.getPnd() != null ? pndRepository.findById(data.getPnd()).orElse(null) : null;
    PndAxisEntity pndAxis = data.getPnd() != null ? pndAxisRepository.findById(data.getPndAxis()).orElse(null) : null;
    StrategyEntity strategy = data.getStrategy() != null ? strategyRepository.findById(data.getStrategy()).orElse(null) : null;
    StrategyAxisEntity strategyAxis = data.getStrategyAxis() != null ? strategyAxisRepository.findById(data.getStrategyAxis()).orElse(null) : null;

    if (projectStrategy.isPresent()) {
      ProjectStrategyEntity updateProjectStrategy = projectStrategy.get();
      updateProjectStrategy.setProject(project.get());
      updateProjectStrategy.setPnd(pnd);
      updateProjectStrategy.setPndAxis(pndAxis);
      updateProjectStrategy.setStrategy(strategy);
      updateProjectStrategy.setStrategyAxis(strategyAxis);
      updateProjectStrategy.setShemaName(data.getShemaName());
      updateProjectStrategy.setShemaProject(data.getShemaProject());
      updateProjectStrategy.setBlueprintName(data.getBlueprintName());
      updateProjectStrategy.setBlueprintProject(data.getBlueprintProject());
      updateProjectStrategy.setPndProject(data.getPndProject());
      updateProjectStrategy.setObjective(data.getObjective());
      updateProjectStrategy.setResult(data.getResult());
      updateProjectStrategy.setComponents(data.getComponents());
      updateProjectStrategy.setDocument(data.getDocument());
      updateProjectStrategy.setRealisationStart(data.getRealisationStart());
      updateProjectStrategy.setRealisationEnd(data.getRealisationEnd());
      updateProjectStrategy.setOperationStart(data.getOperationStart());
      updateProjectStrategy.setWorkplan(data.getWorkplan());
      return projectStrategyRepository.save(updateProjectStrategy);
    } else {
      throw new NotFoundException("project strategy not found");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<ProjectStrategyEntity> strategic = projectStrategyRepository.findById(id);
    if (strategic.isPresent()) {
      projectStrategyRepository.deleteById(id);
    } else {
      throw new NotFoundException("project strategy not exist");
    }
  }

}
