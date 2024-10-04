package com.solidwall.tartib.entities;

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
@Table(name = "project_risk")
@Entity
public class ProjectRiskEntity extends AbstractBaseEntity {

  @ManyToOne
  @JoinColumn(name = "project_id", nullable = true)
  private ProjectEntity project;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = true)
  private CategoryEntity category;

  @Column(name = "name", length = 50, nullable = true)
  private String name;

  @Column(name = "description", length = 50, nullable = true, columnDefinition = "TEXT")
  private String description;

  @Column(name = "probability", length = 255, nullable = true)
  private String probability;

  @Column(name = "impact", length = 255, nullable = true)
  private String impact;

  @Column(name = "gravity", length = 255, nullable = true)
  private String gravity;

  @Column(name = "mitigation", length = 255, nullable = true)
  private String mitigation;

  @Column(name = "comments", length = 255, nullable = true, columnDefinition = "TEXT")
  private String comments;

 

  

}
