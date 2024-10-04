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
@Table(name = "project_zone")
@Entity
public class ProjectZoneEntity extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", unique = true, nullable = true)
    private ProjectEntity project;

    @Column(name = "description", length = 255, nullable = true)
    private String description;

    @Column(name = "type", length = 255, nullable = true)
    private String type;

    @Column(name = "national", nullable = true, columnDefinition = "boolean")
    private boolean national;

    @ManyToOne
    @JoinColumn(name = "district_id", nullable = true)
    private DistrictEntity district;

    @ManyToOne
    @JoinColumn(name = "governorate_id", nullable = true)
    private GovernorateEntity governorate;

    @ManyToOne
    @JoinColumn(name = "delegation_id", nullable = true)
    private DelegationEntity delegation;

    @ManyToOne
    @JoinColumn(name = "deanship_id", nullable = true)
    private DeanshipEntity deanship;

    @Column(name = "land_disponibility", length = 255, nullable = true, columnDefinition = "boolean")
    private boolean landDisponibility;

    @Column(name = "land_observation", length = 255, nullable = true, columnDefinition = "TEXT")
    private String landObservation;

    @Column(name = "land_release", length = 255, nullable = true, columnDefinition = "TEXT")
    private String landRelease;

    @Column(name = "project_related", length = 255, nullable = true, columnDefinition = "boolean")
    private boolean projectRelated;

    @Column(name = "project_code", length = 255, nullable = true)
    private String projectCode;

    @Column(name = "project_name", length = 255, nullable = true)
    private String projectName;

    @Column(name = "project_link_type", length = 255, nullable = true)
    private String projectLinkType;

    @Column(name = "stakeholder_name", length = 255, nullable = true)
    private String stakeholderName;

    @Column(name = "stakeholder_email", length = 255, nullable = true)
    private String stakeholderEmail;

    @Column(name = "stakeholder_role", length = 255, nullable = true)
    private String stakeholderRole;

    

    

}
