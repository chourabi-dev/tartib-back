package com.solidwall.tartib.dto.project.study;

import java.sql.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDto {

    private Long project;

    private Long studyState;

    private String title;

    private String observation;

    private Date realisationDate;

    private Date actualisationDate;

    private String officeName;

    private String officeEmail;

    private String report;

    private String autorisationTitle;

    private String autorisationOffice;

    private String autorisationDocument;

    private String autorisationObservation;
}
