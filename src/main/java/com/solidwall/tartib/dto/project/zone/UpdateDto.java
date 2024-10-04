package com.solidwall.tartib.dto.project.zone;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDto {

  private Long project;

  private String description;

  private String type;

  private boolean national;

  private Long district;

  private Long governorate;

  private Long delegation;

  private Long deanship;

  private boolean landDisponibility;

  private String landObservation;

  private String landRelease;

  private boolean projectRelated;

  private String projectCode;

  private String projectName;

  private String projectLinkType;

  private String stakeholderName;

  private String stakeholderEmail;

  private String stakeholderRole;

}
