package com.solidwall.tartib.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.exceptions.BadRequestException;
import com.solidwall.tartib.core.exceptions.FoundException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.dto.project.risk.CreateDto;
import com.solidwall.tartib.dto.project.risk.UpdateDto;
import com.solidwall.tartib.entities.CategoryEntity;
import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectRiskEntity;
import com.solidwall.tartib.implementations.ProjectRiskImplementation;
import com.solidwall.tartib.repositories.CategoryRepository;
import com.solidwall.tartib.repositories.ProjectRepository;
import com.solidwall.tartib.repositories.ProjectRiskRepository;

@Service
public class ProjectRiskService implements ProjectRiskImplementation {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ProjectRiskRepository projectRiskRepository;

  @Autowired
  CategoryRepository categoryRepository;

  @Override
  public List<ProjectRiskEntity> findAll() {
    if (!projectRiskRepository.findAll().isEmpty()) {
      return projectRiskRepository.findAll();
    } else {
      throw new NotFoundException("not exist any project risk ");
    }
  }

  @Override
  public ProjectRiskEntity getOne(Long id) {
    Optional<ProjectRiskEntity> projectRisk = projectRiskRepository.findById(id);
    if (projectRisk.isPresent()) {
      return projectRisk.get();
    } else {
      throw new NotFoundException("project risk not exist");
    }
  }

  @Override
  public ProjectRiskEntity findOne(Map<String, String> data) {
    if (data.get("project") != null) {
      Long projectId = Long.parseLong(data.get("project"));
      Optional<ProjectEntity> project = projectRepository.findById(projectId);
      if (!project.isPresent())
        throw new NotFoundException("project not found");
      Optional<ProjectRiskEntity> projectRisk = projectRiskRepository.findByProject(project.get());
      if (!projectRisk.isPresent())
        throw new NotFoundException("project risk not found");
      return projectRisk.get();
    }
    throw new BadRequestException("param not exist");
  }

  @Override
  public ProjectRiskEntity create(CreateDto data) {

    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    CategoryEntity category = data.getCategory() != null
        ? categoryRepository.findById(data.getCategory()).orElse(null)
        : null;

    if (project.isPresent()) {
      ProjectRiskEntity newProjectRisk = new ProjectRiskEntity();
      newProjectRisk.setProject(project.get());
      newProjectRisk.setCategory(category);
      newProjectRisk.setName(data.getName());
      newProjectRisk.setDescription(data.getDescription());
      newProjectRisk.setProbability(data.getProbability());
      newProjectRisk.setImpact(data.getImpact());
      newProjectRisk.setGravity(data.getGravity());
      newProjectRisk.setMitigation(data.getMitigation());
      newProjectRisk.setComments(data.getComments());
      return projectRiskRepository.save(newProjectRisk);
    } else {
      throw new FoundException("project not exist");
    }
  }

  @Override
  public ProjectRiskEntity update(Long id, UpdateDto data) {
    Optional<ProjectRiskEntity> projectRisk = projectRiskRepository.findById(id);
    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    CategoryEntity category = data.getCategory() != null
        ? categoryRepository.findById(data.getCategory()).orElse(null)
        : null;
    if (projectRisk.isPresent()) {
      ProjectRiskEntity updateProjectRisk = projectRisk.get();
      updateProjectRisk.setCategory(category);
      updateProjectRisk.setProject(project.get());
      updateProjectRisk.setName(data.getName());
      updateProjectRisk.setDescription(data.getDescription());
      updateProjectRisk.setProbability(data.getProbability());
      updateProjectRisk.setImpact(data.getImpact());
      updateProjectRisk.setGravity(data.getGravity());
      updateProjectRisk.setMitigation(data.getMitigation());
      updateProjectRisk.setComments(data.getComments());
      return projectRiskRepository.save(updateProjectRisk);
    } else {
      throw new NotFoundException("project risk not found");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<ProjectRiskEntity> projectRisk = projectRiskRepository.findById(id);
    if (projectRisk.isPresent()) {
      projectRiskRepository.deleteById(id);
    } else {
      throw new NotFoundException("project risk not exist");
    }
  }

}
