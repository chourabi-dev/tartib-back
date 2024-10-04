package com.solidwall.tartib.dto.project.risk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDto {

  private Long project;

  private Long category;

  private String name;

  private String description;

  private String probability;

  private String impact;

  private String gravity;

  private String mitigation;

  private String comments;
  
}
