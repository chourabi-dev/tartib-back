package com.solidwall.tartib.dto.project.strategy;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDto {

    private Long project;

    private Long strategy;

    private Long strategyAxis;

    private Long pnd;

    private Long pndAxis;

    private String shemaName;

    private String shemaProject;

    private String blueprintName;

    private String blueprintProject;

    private String pndProject;

    private String objective;

    private String result;

    private String components;

    private String document;

    private Date realisationStart;

    private Date realisationEnd;

    private Date operationStart;

    private String Workplan;
  
}
