package com.solidwall.tartib.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.exceptions.BadRequestException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.dto.project.identity.CreateDto;
import com.solidwall.tartib.dto.project.identity.UpdateDto;
import com.solidwall.tartib.entities.CategoryEntity;
import com.solidwall.tartib.entities.MinisterEntity;
import com.solidwall.tartib.entities.OrganisationEntity;
import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectIdentityEntity;
import com.solidwall.tartib.entities.SectorEntity;
import com.solidwall.tartib.entities.TypologyEntity;
import com.solidwall.tartib.implementations.ProjectIdentityImplementation;
import com.solidwall.tartib.repositories.CategoryRepository;
import com.solidwall.tartib.repositories.MinisterRepository;
import com.solidwall.tartib.repositories.OrganisationRepository;
import com.solidwall.tartib.repositories.ProjectIdentityRepository;
import com.solidwall.tartib.repositories.ProjectRepository;
import com.solidwall.tartib.repositories.SectorRepository;
import com.solidwall.tartib.repositories.TypologyRepository;

@Service
public class ProjectIdentityService implements ProjectIdentityImplementation {

  @Autowired
  ProjectIdentityRepository projectIdentityRepository;

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  TypologyRepository typologyRepository;

  @Autowired
  MinisterRepository ministerRepository;

  @Autowired
  SectorRepository sectorRepository;
  @Autowired
  CategoryRepository categoryRepository;

  @Autowired
  OrganisationRepository organisationRepository;

  @Override
  public List<ProjectIdentityEntity> findAll() {
    if (!projectIdentityRepository.findAll().isEmpty()) {
      return projectIdentityRepository.findAll();
    } else {
      throw new NotFoundException("not exist any project identity");
    }
  }

  @Override
  public ProjectIdentityEntity getOne(Long id) {
    Optional<ProjectIdentityEntity> projectIdentity = projectIdentityRepository.findById(id);
    if (projectIdentity.isPresent()) {
      return projectIdentity.get();
    } else {
      throw new NotFoundException("project identity not exist");
    }
  }

  @Override
  public ProjectIdentityEntity findOne(Map<String, String> data) {

    if (data.get("id") != null) {
      Long id = Long.parseLong(data.get("id"));
      Optional<ProjectEntity> project = projectRepository.findById(id);
      if (!project.isPresent())
        throw new NotFoundException("project not found");
      Optional<ProjectIdentityEntity> projectIdentity = projectIdentityRepository.findById(id);
      if (!projectIdentity.isPresent())
        throw new NotFoundException("project identity not found");
      return projectIdentity.get();
    }
    throw new BadRequestException("param not exist");

  }

  @Override
  public ProjectIdentityEntity create(CreateDto data) {

    // Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    TypologyEntity typology = data.getTypology() != null ? typologyRepository.findById(data.getTypology()).orElse(null)
        : null;
    MinisterEntity minister = data.getMinister() != null ? ministerRepository.findById(data.getMinister()).orElse(null)
        : null;
    OrganisationEntity organisation = data.getOrganisation() != null
        ? organisationRepository.findById(data.getOrganisation()).orElse(null)
        : null;
    SectorEntity sector = data.getSector() != null ? sectorRepository.findById(data.getSector()).orElse(null)
        : null;
        CategoryEntity category = data.getCategory() != null ? categoryRepository.findById(data.getCategory()).orElse(null)
        : null;
        String generatedCode = generateProjectCode(data,minister);
 
      ProjectIdentityEntity newProjectIdentityEntity = new ProjectIdentityEntity();
      // newProjectIdentityEntity.setProject(project.get());

      newProjectIdentityEntity.setCode(generatedCode);
      newProjectIdentityEntity.setName(data.getName());
      newProjectIdentityEntity.setDescription(data.getDescription());
      newProjectIdentityEntity.setTypology(typology);
      newProjectIdentityEntity.setCategory(category);
      newProjectIdentityEntity.setSector(sector);
      newProjectIdentityEntity.setMinister(minister);
      newProjectIdentityEntity.setMinisterName(data.getMinisterName());
      newProjectIdentityEntity.setOrganisation(organisation);
      newProjectIdentityEntity.setResponsibleName(data.getResponsibleName());
      newProjectIdentityEntity.setResponsibleEmail(data.getResponsibleEmail());
      newProjectIdentityEntity.setResponsiblePhone(data.getResponsiblePhone());
      newProjectIdentityEntity.setManagementUnitName(data.getManagementUnitName());
      newProjectIdentityEntity.setProjectManagerName(data.getProjectManagerName());
      newProjectIdentityEntity.setProjectManagerEmail(data.getProjectManagerEmail());
      newProjectIdentityEntity.setProjectManagerPhone(data.getProjectManagerPhone());
      newProjectIdentityEntity.setProjectOwnerName(data.getProjectOwnerName());
      newProjectIdentityEntity.setProjectOwnerEmail(data.getProjectOwnerEmail());
      newProjectIdentityEntity.setProjectOwnerPhone(data.getProjectOwnerPhone());
      return projectIdentityRepository.save(newProjectIdentityEntity);
     

  }

  @Override
  public ProjectIdentityEntity update(Long id, UpdateDto data) {

    Optional<ProjectIdentityEntity> projectIdentity = projectIdentityRepository.findById(id);
    // Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    TypologyEntity typology = data.getTypology() != null ? typologyRepository.findById(data.getTypology()).orElse(null)
        : null;
    MinisterEntity minister = data.getMinister() != null ? ministerRepository.findById(data.getMinister()).orElse(null)
        : null;
    OrganisationEntity organisation = data.getOrganisation() != null
        ? organisationRepository.findById(data.getOrganisation()).orElse(null)
        : null;
    SectorEntity sector = data.getSector() != null ? sectorRepository.findById(data.getSector()).orElse(null)
        : null;
        CategoryEntity category = data.getCategory() != null ? categoryRepository.findById(data.getCategory()).orElse(null)
        : null;

    if (projectIdentity.isPresent()) {
      String generatedCode = generateProjectCode(data,minister);
      ProjectIdentityEntity newProjectIdentity = projectIdentity.get();
      newProjectIdentity.setCode(generatedCode);
      // newProjectIdentity.setProject(project.get());
      newProjectIdentity.setName(data.getName());
      newProjectIdentity.setDescription(data.getDescription());
      newProjectIdentity.setTypology(typology);
      newProjectIdentity.setCategory(category);
      newProjectIdentity.setSector(sector);
      newProjectIdentity.setMinister(minister);
      newProjectIdentity.setMinisterName(data.getMinisterName());
      newProjectIdentity.setOrganisation(organisation);
      newProjectIdentity.setResponsibleName(data.getResponsibleName());
      newProjectIdentity.setResponsibleEmail(data.getResponsibleEmail());
      newProjectIdentity.setResponsiblePhone(data.getResponsiblePhone());
      newProjectIdentity.setManagementUnitName(data.getManagementUnitName());
      newProjectIdentity.setProjectManagerName(data.getProjectManagerName());
      newProjectIdentity.setProjectManagerEmail(data.getProjectManagerEmail());
      newProjectIdentity.setProjectManagerPhone(data.getProjectManagerPhone());
      newProjectIdentity.setProjectOwnerName(data.getProjectOwnerName());
      newProjectIdentity.setProjectOwnerEmail(data.getProjectOwnerEmail());
      newProjectIdentity.setProjectOwnerPhone(data.getProjectOwnerPhone());
      return projectIdentityRepository.save(newProjectIdentity);
    } else {
      throw new NotFoundException("project identity not found");
    }

  }

  @Override
  public void delete(Long id) {
    Optional<ProjectIdentityEntity> identity = projectIdentityRepository.findById(id);
    if (identity.isPresent()) {
      projectIdentityRepository.deleteById(id);
    } else {
      throw new NotFoundException("project identity not exist");
    }
  }


  private String generateProjectCode(CreateDto project,MinisterEntity ministre) {
    // Example: First two letters of the project name + minister code + sector code + year + sequential number
    String projectPrefix = "TR";
    String ministerCode = ministre.getName();
    String year = String.valueOf(2024);
    
    // Generate sequential number (this could be managed based on existing records)
    long projectCount = projectIdentityRepository.count();
    String sequenceNumber = String.format("%04d", projectCount + 1);

    // Final code format: Prefix + MinisterCode + Year + Sequence
    return String.format("%s-%s-%s-%s", projectPrefix, ministerCode, year, sequenceNumber);
}

private String generateProjectCode(UpdateDto project,MinisterEntity ministre) {
  // Example: First two letters of the project name + minister code + sector code + year + sequential number
  String projectPrefix = "TR";
  String ministerCode = ministre.getName();
  String year = String.valueOf(2024);
  
  // Generate sequential number (this could be managed based on existing records)
  long projectCount = projectIdentityRepository.count();
  String sequenceNumber = String.format("%04d", projectCount + 1);

  // Final code format: Prefix + MinisterCode + Year + Sequence
  return String.format("%s-%s-%s-%s", projectPrefix, ministerCode, year, sequenceNumber);
}
}
