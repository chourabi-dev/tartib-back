package com.solidwall.tartib.dto.project.plan;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDto {

  private Long project;

  private float amount;

  private Long economicNature;

  private Long currency;

  private float exchangeRate;

  private String itemTitle;

  private float itemLocalCost;

  private float itemEquivalentCost;

  private Long fundingSource;

  private Long fundingSourceType;

  private Long fundingCurrency;

  private float fundingAmount;

  private float fundingLocalAmount;

  private float fundingEquivalentAmount;

  private Date fundingStart;

  private Date fundingEnd;

  private String fundingAgreement;

  private Date effectiveDate;

  private Date sendingDate;

  private Date approvalDate;

  private Date arpDate;

  private Date ratificationDate;

  private Date plenaryDate;

  private String ortNumber;

  private Date ortDate;

}
