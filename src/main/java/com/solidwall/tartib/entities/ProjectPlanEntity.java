package com.solidwall.tartib.entities;

import java.sql.Date;
import com.solidwall.tartib.entities.base.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_plan")
@Entity
public class ProjectPlanEntity extends AbstractBaseEntity {

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = true)
  private ProjectEntity project;
  
  @Column(name = "amount", nullable = true)
  private float amount;

  @ManyToOne
  @JoinColumn(name = "economic_nature_id", nullable = true)
  private EconomicNatureEntity economicNature;

  @ManyToOne
  @JoinColumn(name = "currency_id", nullable = true)
  private CurrencyEntity currency;

  @Column(name = "exchange_rate", nullable = true)
  private float exchangeRate;

  @Column(name = "item_title", length = 255, nullable = true)
  private String itemTitle;

  @Column(name = "item_local_cost", nullable = true)
  private float itemLocalCost;

  @Column(name = "item_equivalent_cost", nullable = true)
  private float itemEquivalentCost;

  @ManyToOne
  @JoinColumn(name = "funding_source_id", nullable = true)
  private FundingSourceEntity fundingSource;

  @ManyToOne
  @JoinColumn(name = "funding_source_type_id", nullable = true)
  private FundingSourceTypeEntity fundingSourceType;

  @ManyToOne
  @JoinColumn(name = "funding_currency_id", nullable = true)
  private CurrencyEntity fundingCurrency;

  @Column(name = "funding_amount", nullable = true)
  private float fundingAmount;

  @Column(name = "funding_local_amount", nullable = true)
  private float fundingLocalAmount;

  @Column(name = "funding_equivalent_amount", nullable = true)
  private float fundingEquivalentAmount;

  @Column(name = "funding_start", nullable = true)
  private Date fundingStart;

  @Column(name = "funding_end", nullable = true)
  private Date fundingEnd;

  @Column(name = "funding_agreement", length = 255, nullable = true)
    private String fundingAgreement;

  @Column(name = "effective_date", nullable = true)
  private Date effectiveDate;

  @Column(name = "sending_date", nullable = true)
  private Date sendingDate;

  @Column(name = "approval_date", nullable = true)
  private Date approvalDate;

  @Column(name = "arp_date", nullable = true)
  private Date arpDate;

  @Column(name = "ratification_date", nullable = true)
  private Date ratificationDate;

  @Column(name = "plenary_date", nullable = true)
  private Date plenaryDate;

  @Column(name = "ort_number", length = 255, nullable = true)
  private String ortNumber;

  @Column(name = "ort_date", nullable = true)
  private Date ortDate;

}
