package com.solidwall.tartib.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.exceptions.BadRequestException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.dto.project.zone.CreateDto;
import com.solidwall.tartib.dto.project.zone.UpdateDto;
import com.solidwall.tartib.entities.DeanshipEntity;
import com.solidwall.tartib.entities.DelegationEntity;
import com.solidwall.tartib.entities.DistrictEntity;
import com.solidwall.tartib.entities.GovernorateEntity;
import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectZoneEntity;
import com.solidwall.tartib.implementations.ProjectZoneImplementation;
import com.solidwall.tartib.repositories.DeanshipRepository;
import com.solidwall.tartib.repositories.DelegationRepository;
import com.solidwall.tartib.repositories.DistrictRepository;
import com.solidwall.tartib.repositories.GovernorateRepository;
import com.solidwall.tartib.repositories.ProjectRepository;
import com.solidwall.tartib.repositories.ProjectZoneRepository;

@Service
public class ProjectZoneService implements ProjectZoneImplementation {

  @Autowired
  ProjectZoneRepository projectZoneRepository;

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  DeanshipRepository deanshipRepository;

  @Autowired
  DelegationRepository delegationRepository;

  @Autowired
  DistrictRepository districtRepository;

  @Autowired
  GovernorateRepository governorateRepository;

  @Override
  public ProjectZoneEntity getOne(Long id) {
    Optional<ProjectZoneEntity> projectZone = projectZoneRepository.findById(id);
    if (projectZone.isPresent()) {
      return projectZone.get();
    } else {
      throw new NotFoundException("project zone not exist");
    }
  }

  @Override
  public ProjectZoneEntity findOne(Map<String, String> data) {

    if (data.get("project") != null) {
      Long projectId = Long.parseLong(data.get("project"));
      Optional<ProjectEntity> project = projectRepository.findById(projectId);
      if (!project.isPresent())
        throw new NotFoundException("project not found");
      Optional<ProjectZoneEntity> projectZone = projectZoneRepository.findByProject(project.get());
      if (!projectZone.isPresent())
        throw new NotFoundException("project zone not found");
      return projectZone.get();
    }
    throw new BadRequestException("param not exist");

  }

  @Override
  public List<ProjectZoneEntity> findAll() {
    if (!projectZoneRepository.findAll().isEmpty()) {
      return projectZoneRepository.findAll();
    } else {
      throw new NotFoundException("not exist any project zone ");
    }
  }

  @Override
  public ProjectZoneEntity create(CreateDto data) {

    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    DistrictEntity district = data.getDistrict() != null ? districtRepository.findById(data.getDistrict()).orElse(null)
        : null;
    GovernorateEntity governorate = data.getGovernorate() != null
        ? governorateRepository.findById(data.getGovernorate()).orElse(null)
        : null;
    DelegationEntity delegation = data.getDelegation() != null
        ? delegationRepository.findById(data.getDelegation()).orElse(null)
        : null;
    DeanshipEntity deanship = data.getDeanship() != null ? deanshipRepository.findById(data.getDeanship()).orElse(null)
        : null;

    if (project.isPresent()) {
      ProjectZoneEntity newProjectZone = new ProjectZoneEntity();
      newProjectZone.setProject(project.get());
      newProjectZone.setDescription(data.getDescription());
      newProjectZone.setType(data.getType());
      newProjectZone.setNational(data.isNational());
      newProjectZone.setDistrict(district);
      newProjectZone.setGovernorate(governorate);
      newProjectZone.setDelegation(delegation);
      newProjectZone.setDeanship(deanship);
      newProjectZone.setLandDisponibility(data.isLandDisponibility());
      newProjectZone.setLandObservation(data.getLandObservation());
      newProjectZone.setLandRelease(data.getLandRelease());
      newProjectZone.setProjectRelated(data.isProjectRelated());
      newProjectZone.setProjectCode(data.getProjectCode());
      newProjectZone.setProjectName(data.getProjectName());
      newProjectZone.setProjectLinkType(data.getProjectLinkType());
      newProjectZone.setStakeholderName(data.getStakeholderName());
      newProjectZone.setStakeholderEmail(data.getStakeholderEmail());
      newProjectZone.setStakeholderRole(data.getStakeholderRole());
      return projectZoneRepository.save(newProjectZone);
    } else {
      throw new NotFoundException("project not found");
    }

  }

  @Override
  public ProjectZoneEntity update(Long id, UpdateDto data) {

    Optional<ProjectZoneEntity> projectZone = projectZoneRepository.findById(id);
    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    DistrictEntity district = data.getDistrict() != null ? districtRepository.findById(data.getDistrict()).orElse(null)
        : null;
    GovernorateEntity governorate = data.getGovernorate() != null
        ? governorateRepository.findById(data.getGovernorate()).orElse(null)
        : null;
    DelegationEntity delegation = data.getDelegation() != null
        ? delegationRepository.findById(data.getDelegation()).orElse(null)
        : null;
    DeanshipEntity deanship = data.getDeanship() != null ? deanshipRepository.findById(data.getDeanship()).orElse(null)
        : null;

    if (projectZone.isPresent()) {
      ProjectZoneEntity updateProjectZone = projectZone.get();
      updateProjectZone.setProject(project.get());
      updateProjectZone.setDescription(data.getDescription());
      updateProjectZone.setType(data.getType());
      updateProjectZone.setNational(data.isNational());
      updateProjectZone.setDistrict(district);
      updateProjectZone.setGovernorate(governorate);
      updateProjectZone.setDelegation(delegation);
      updateProjectZone.setDeanship(deanship);
      updateProjectZone.setLandDisponibility(data.isLandDisponibility());
      updateProjectZone.setLandObservation(data.getLandObservation());
      updateProjectZone.setLandRelease(data.getLandRelease());
      updateProjectZone.setProjectRelated(data.isProjectRelated());
      updateProjectZone.setProjectCode(data.getProjectCode());
      updateProjectZone.setProjectName(data.getProjectName());
      updateProjectZone.setProjectLinkType(data.getProjectLinkType());
      updateProjectZone.setStakeholderName(data.getStakeholderName());
      updateProjectZone.setStakeholderEmail(data.getStakeholderEmail());
      updateProjectZone.setStakeholderRole(data.getStakeholderRole());
      return projectZoneRepository.save(updateProjectZone);
    } else {
      throw new NotFoundException("project zone not found");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<ProjectZoneEntity> projectZone = projectZoneRepository.findById(id);
    if (projectZone.isPresent()) {
      projectZoneRepository.deleteById(id);
    } else {
      throw new NotFoundException("project zone not exist");
    }
  }

}
