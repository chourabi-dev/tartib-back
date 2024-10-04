package com.solidwall.tartib.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solidwall.tartib.core.exceptions.BadRequestException;
import com.solidwall.tartib.core.exceptions.NotFoundException;
import com.solidwall.tartib.dto.project.plan.CreateDto;
import com.solidwall.tartib.dto.project.plan.UpdateDto;
import com.solidwall.tartib.entities.CurrencyEntity;
import com.solidwall.tartib.entities.EconomicNatureEntity;
import com.solidwall.tartib.entities.FundingSourceEntity;
import com.solidwall.tartib.entities.FundingSourceTypeEntity;
import com.solidwall.tartib.entities.ProjectEntity;
import com.solidwall.tartib.entities.ProjectPlanEntity;
import com.solidwall.tartib.implementations.ProjectPlanImplementation;
import com.solidwall.tartib.repositories.CurrencyRepository;
import com.solidwall.tartib.repositories.EconomicNatureRepository;
import com.solidwall.tartib.repositories.FundingSourceRepository;
import com.solidwall.tartib.repositories.FundingSourceTypeRepository;
import com.solidwall.tartib.repositories.ProjectPlanRepository;
import com.solidwall.tartib.repositories.ProjectRepository;

@Service
public class ProjectPlanService implements ProjectPlanImplementation {

  @Autowired
  ProjectRepository projectRepository;

  @Autowired
  ProjectPlanRepository projectPlanRepository;

  @Autowired
  FundingSourceRepository fundingSourceRepository;

  @Autowired
  FundingSourceTypeRepository fundingSourceTypeRepository;

  @Autowired
  CurrencyRepository currencyRepository;

  @Autowired
  EconomicNatureRepository economicNatureRepository;

  @Override
  public List<ProjectPlanEntity> findAll() {
    if (!projectPlanRepository.findAll().isEmpty()) {
      return projectPlanRepository.findAll();
    } else {
      throw new NotFoundException("not exist any project plan ");
    }
  }

  @Override
  public ProjectPlanEntity getOne(Long id) {
    Optional<ProjectPlanEntity> projectPlan = projectPlanRepository.findById(id);
    if (projectPlan.isPresent()) {
      return projectPlan.get();
    } else {
      throw new NotFoundException("project plan not exist");
    }
  }

  @Override
  public ProjectPlanEntity findOne(Map<String, String> data) {
    if (data.get("project") != null) {
      Long projectId = Long.parseLong(data.get("project"));
      Optional<ProjectEntity> project = projectRepository.findById(projectId);
      if (!project.isPresent())
        throw new NotFoundException("project plan not found");
      Optional<ProjectPlanEntity> projectPlan = projectPlanRepository.findByProject(project.get());
      if (!projectPlan.isPresent())
        throw new NotFoundException("project plan not found");
      return projectPlan.get();
    }
    throw new BadRequestException("param not exist");
  }

  @Override
  public ProjectPlanEntity create(CreateDto data) {

    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    CurrencyEntity currency = data.getCurrency() != null
        ? currencyRepository.findById(data.getCurrency()).orElse(null)
        : null;
    EconomicNatureEntity economicNature = data.getEconomicNature() != null
        ? economicNatureRepository.findById(data.getEconomicNature()).orElse(null)
        : null;
    FundingSourceEntity fundingSource = data.getFundingSource() != null
        ? fundingSourceRepository.findById(data.getFundingSource()).orElse(null)
        : null;
    FundingSourceTypeEntity fundingSourceTye = data.getFundingSourceType() != null
        ? fundingSourceTypeRepository.findById(data.getFundingSourceType()).orElse(null)
        : null;
    CurrencyEntity fundingCurrency = data.getFundingCurrency() != null
        ? currencyRepository.findById(data.getFundingCurrency()).orElse(null)
        : null;

    if(project.isPresent()) {
      ProjectPlanEntity newProjectPlan = new ProjectPlanEntity();
      newProjectPlan.setProject(project.get());
      newProjectPlan.setAmount(data.getAmount());
      newProjectPlan.setEconomicNature(economicNature);
      newProjectPlan.setCurrency(currency);
      newProjectPlan.setExchangeRate(data.getExchangeRate());
      newProjectPlan.setItemTitle(data.getItemTitle());
      newProjectPlan.setItemLocalCost(data.getItemLocalCost());
      newProjectPlan.setItemEquivalentCost(data.getItemEquivalentCost());
      newProjectPlan.setFundingSource(fundingSource);
      newProjectPlan.setFundingSourceType(fundingSourceTye);
      newProjectPlan.setFundingCurrency(fundingCurrency);
      newProjectPlan.setFundingLocalAmount(data.getFundingLocalAmount());
      newProjectPlan.setFundingEquivalentAmount(data.getFundingEquivalentAmount());
      newProjectPlan.setFundingStart(data.getFundingStart());
      newProjectPlan.setFundingEnd(data.getFundingEnd());
      newProjectPlan.setFundingAgreement(data.getFundingAgreement());
      newProjectPlan.setEffectiveDate(data.getEffectiveDate());
      newProjectPlan.setSendingDate(data.getSendingDate());
      newProjectPlan.setApprovalDate(data.getApprovalDate());
      newProjectPlan.setArpDate(data.getArpDate());
      newProjectPlan.setRatificationDate(data.getRatificationDate());
      newProjectPlan.setPlenaryDate(data.getPlenaryDate());
      newProjectPlan.setOrtDate(data.getOrtDate());
      newProjectPlan.setOrtNumber(data.getOrtNumber());
      return projectPlanRepository.save(newProjectPlan);
    } else {
      throw new NotFoundException("project not found");
    }

  }

  @Override
  public ProjectPlanEntity update(Long id, UpdateDto data) {
    Optional<ProjectPlanEntity> projectPlan = projectPlanRepository.findById(id);
    Optional<ProjectEntity> project = projectRepository.findById(data.getProject());
    CurrencyEntity currency = data.getCurrency() != null
        ? currencyRepository.findById(data.getCurrency()).orElse(null)
        : null;
    EconomicNatureEntity economicNature = data.getEconomicNature() != null
        ? economicNatureRepository.findById(data.getEconomicNature()).orElse(null)
        : null;
    FundingSourceEntity fundingSource = data.getFundingSource() != null
        ? fundingSourceRepository.findById(data.getFundingSource()).orElse(null)
        : null;
    FundingSourceTypeEntity fundingSourceTye = data.getFundingSourceType() != null
        ? fundingSourceTypeRepository.findById(data.getFundingSourceType()).orElse(null)
        : null;
    CurrencyEntity fundingCurrency = data.getFundingCurrency() != null
        ? currencyRepository.findById(data.getFundingCurrency()).orElse(null)
        : null;

    if (projectPlan.isPresent()) {
      ProjectPlanEntity updateProjectPlan = projectPlan.get();
      updateProjectPlan.setProject(project.get());
      updateProjectPlan.setAmount(data.getAmount());
      updateProjectPlan.setEconomicNature(economicNature);
      updateProjectPlan.setCurrency(currency);
      updateProjectPlan.setExchangeRate(data.getExchangeRate());
      updateProjectPlan.setItemTitle(data.getItemTitle());
      updateProjectPlan.setItemLocalCost(data.getItemLocalCost());
      updateProjectPlan.setItemEquivalentCost(data.getItemEquivalentCost());
      updateProjectPlan.setFundingSource(fundingSource);
      updateProjectPlan.setFundingSourceType(fundingSourceTye);
      updateProjectPlan.setFundingCurrency(fundingCurrency);
      updateProjectPlan.setFundingLocalAmount(data.getFundingLocalAmount());
      updateProjectPlan.setFundingEquivalentAmount(data.getFundingEquivalentAmount());
      updateProjectPlan.setFundingStart(data.getFundingStart());
      updateProjectPlan.setFundingEnd(data.getFundingEnd());
      updateProjectPlan.setFundingAgreement(data.getFundingAgreement());
      updateProjectPlan.setEffectiveDate(data.getEffectiveDate());
      updateProjectPlan.setSendingDate(data.getSendingDate());
      updateProjectPlan.setApprovalDate(data.getApprovalDate());
      updateProjectPlan.setArpDate(data.getArpDate());
      updateProjectPlan.setRatificationDate(data.getRatificationDate());
      updateProjectPlan.setPlenaryDate(data.getPlenaryDate());
      updateProjectPlan.setOrtDate(data.getOrtDate());
      updateProjectPlan.setOrtNumber(data.getOrtNumber());
      return projectPlanRepository.save(updateProjectPlan);
    } else {
      throw new NotFoundException("project plan not found");
    }
  }

  @Override
  public void delete(Long id) {
    Optional<ProjectPlanEntity> projectPlan = projectPlanRepository.findById(id);
    if (projectPlan.isPresent()) {
      projectPlanRepository.deleteById(id);
    } else {
      throw new NotFoundException("project plan not exist");
    }
  }
}
