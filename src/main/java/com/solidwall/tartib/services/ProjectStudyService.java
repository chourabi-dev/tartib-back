package com.solidwall.tartib.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.exceptions.BadRequestException;
import com.solidwall.tartib.core.exceptions.FoundException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.dto.project.study.CreateDto;
import com.solidwall.tartib.dto.project.study.UpdateDto;
import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectStudyEntity;
import com.solidwall.tartib.entities.StudyStateEntity;
import com.solidwall.tartib.implementations.ProjectStudyImplementation;
import com.solidwall.tartib.repositories.ProjectRepository;
import com.solidwall.tartib.repositories.ProjectStudyRepository;
import com.solidwall.tartib.repositories.StudyStateRepository;

@Service
public class ProjectStudyService implements ProjectStudyImplementation {

  @Autowired
  ProjectStudyRepository projectStudyRepository;

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  StudyStateRepository studyStateRepository;

  @Override
  public List<ProjectStudyEntity> findAll() {
    if (!projectStudyRepository.findAll().isEmpty()) {
      return projectStudyRepository.findAll();
    } else {
      throw new NotFoundException("not exist any project study");
    }
  }

  @Override
  public ProjectStudyEntity getOne(Long id) {
    Optional<ProjectStudyEntity> projectStudy = projectStudyRepository.findById(id);
    if (projectStudy.isPresent()) {
      return projectStudy.get();
    } else {
      throw new NotFoundException("project study not exist");
    }
  }

  @Override
  public ProjectStudyEntity findOne(Map<String, String> data) {
    if (data.get("project") != null) {
      Long projectId = Long.parseLong(data.get("project"));
      Optional<ProjectEntity> project = projectRepository.findById(projectId);
      if (!project.isPresent())
        throw new NotFoundException("project not found");
      Optional<ProjectStudyEntity> projectStudy = projectStudyRepository.findByProject(project.get());
      if (!projectStudy.isPresent())
        throw new NotFoundException("project study not found");
      return projectStudy.get();
    }
    throw new BadRequestException("param not exist");
  }

  @Override
  public ProjectStudyEntity create(CreateDto data) {

    System.out.println(data.getProject());

    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    StudyStateEntity studyState = data.getStudyState() != null
        ? studyStateRepository.findById(data.getStudyState()).orElse(null)
        : null;

    if (project.isPresent()) {
      ProjectStudyEntity newProjectStudy = new ProjectStudyEntity();
      newProjectStudy.setProject(project.get());
      newProjectStudy.setStudyState(studyState);
      newProjectStudy.setTitle(data.getTitle());
      newProjectStudy.setObservation(data.getObservation());
      newProjectStudy.setRealisationDate(data.getRealisationDate());
      newProjectStudy.setActualisationDate(data.getActualisationDate());
      newProjectStudy.setOfficeName(data.getOfficeName());
      newProjectStudy.setOfficeEmail(data.getOfficeEmail());
      newProjectStudy.setReport(data.getReport());
      newProjectStudy.setAutorisationTitle(data.getAutorisationTitle());
      newProjectStudy.setAutorisationOffice(data.getAutorisationOffice());
      newProjectStudy.setAutorisationDocument(data.getAutorisationDocument());
      newProjectStudy.setAutorisationObservation(data.getAutorisationObservation());
      return projectStudyRepository.save(newProjectStudy);
    } else {
      throw new NotFoundException("project not exist");
    }
  }

  @Override
  public ProjectStudyEntity update(Long id, UpdateDto data) {

    Optional<ProjectStudyEntity> projectStudy = projectStudyRepository.findById(id);
    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    StudyStateEntity studyState = data.getStudyState() != null
        ? studyStateRepository.findById(data.getStudyState()).orElse(null)
        : null;

    if (projectStudy.isPresent()) {
      ProjectStudyEntity updateProjectStudy = projectStudy.get();
      updateProjectStudy.setProject(project.get());
      updateProjectStudy.setStudyState(studyState);
      updateProjectStudy.setTitle(data.getTitle());
      updateProjectStudy.setObservation(data.getObservation());
      updateProjectStudy.setRealisationDate(data.getRealisationDate());
      updateProjectStudy.setActualisationDate(data.getActualisationDate());
      updateProjectStudy.setOfficeName(data.getOfficeName());
      updateProjectStudy.setOfficeEmail(data.getOfficeEmail());
      updateProjectStudy.setReport(data.getReport());
      updateProjectStudy.setAutorisationTitle(data.getAutorisationTitle());
      updateProjectStudy.setAutorisationOffice(data.getAutorisationOffice());
      updateProjectStudy.setAutorisationDocument(data.getAutorisationDocument());
      updateProjectStudy.setAutorisationObservation(data.getAutorisationObservation());
      return projectStudyRepository.save(updateProjectStudy);
    } else {
      throw new NotFoundException("project study not found");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<ProjectStudyEntity> projectStudy = projectStudyRepository.findById(id);
    if (projectStudy.isPresent()) {
      projectStudyRepository.deleteById(id);
    } else {
      throw new NotFoundException("project study not exist");
    }
  }
}
