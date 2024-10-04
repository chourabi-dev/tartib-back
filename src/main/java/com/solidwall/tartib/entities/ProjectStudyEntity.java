package com.solidwall.tartib.entities;

import com.solidwall.tartib.entities.base.AbstractBaseEntity;

import java.sql.Date;
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
@Table(name = "project_study")
@Entity
public class ProjectStudyEntity extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = true)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "study_state_id", nullable = true)
    private StudyStateEntity studyState;

    @Column(name = "title", length = 255, nullable = true)
    private String title;

    @Column(name = "observation", length = 255, nullable = true, columnDefinition = "TEXT")
    private String observation;

    @Column(name = "realisation_date", length = 255, nullable = true)
    private Date realisationDate;

    @Column(name = "actualisation_date", length = 255, nullable = true)
    private Date actualisationDate;

    @Column(name = "office_name", length = 255, nullable = true)
    private String officeName;

    @Column(name = "office_email", length = 255, nullable = true)
    private String officeEmail;

    @Column(name = "report", length = 255, nullable = true)
    private String report;

    @Column(name = "authorisation_title", length = 255, nullable = true)
    private String autorisationTitle;

    @Column(name = "authorisation_office", length = 255, nullable = true)
    private String autorisationOffice;

    @Column(name = "authorisation_document", length = 255, nullable = true)
    private String autorisationDocument;

    @Column(name = "authorisation_observation", length = 255, nullable = true)
    private String autorisationObservation;
}
