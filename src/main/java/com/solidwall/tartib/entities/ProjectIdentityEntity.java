package com.solidwall.tartib.entities;

import java.util.ArrayList;
import java.util.List;

import com.solidwall.tartib.entities.base.AbstractBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "project_identity")
@Entity
public class ProjectIdentityEntity extends AbstractBaseEntity {

    // @ManyToOne
    // @JoinColumn(name = "project_id", nullable = true)
    // private ProjectEntity project;

    @Column(name = "code", length = 255, nullable = true)
    private String code;

    @Column(name = "name", length = 255, nullable = true)
    private String name;

    @Column(name = "description", length = 255, nullable = true, columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "typology_id", nullable = true)
    private TypologyEntity typology;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "sector_id", nullable = true)
    private SectorEntity sector;

    @ManyToOne
    @JoinColumn(name = "minister_id", nullable = true)
    private MinisterEntity minister;

    @Column(name = "minister_name", length = 255, nullable = true)
    private String ministerName;

    @ManyToOne
    @JoinColumn(name = "organisation_id", nullable = true)
    private OrganisationEntity organisation;

    @Column(name = "responsible_name", length = 255, nullable = true)
    private String responsibleName;

    @Column(name = "responsible_email", length = 255, nullable = true)
    private String responsibleEmail;

    @Column(name = "responsible_phone", length = 255, nullable = true)
    private String responsiblePhone;

    @Column(name = "management_unitname", length = 255, nullable = true)
    private String managementUnitName;

    @Column(name = "project_manager_name", length = 255, nullable = true)
    private String projectManagerName;

    @Column(name = "project_manager_email", length = 255, nullable = true)
    private String projectManagerEmail;

    @Column(name = "project_manager_phone", length = 255, nullable = true)
    private String projectManagerPhone;

    @Column(name = "po_name", length = 255, nullable = true)
    private String projectOwnerName;

    @Column(name = "po_email", length = 255, nullable = true)
    private String projectOwnerEmail;

    @Column(name = "po_phone", length = 255, nullable = true)
    private String projectOwnerPhone;
    @ManyToMany
    @JoinTable(
        name = "project_identity_district",
        joinColumns = @JoinColumn(name = "project_identity_id"),
        inverseJoinColumns = @JoinColumn(name = "district_id")
    )
    private List<DistrictEntity> districts = new ArrayList<>();

      @ManyToMany
    @JoinTable(
        name = "project_identity_governorate",
        joinColumns = @JoinColumn(name = "project_identity_id"),
        inverseJoinColumns = @JoinColumn(name = "governorate_id")
    )
    private List<GovernorateEntity> governorates = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "project_identity_delegation",
        joinColumns = @JoinColumn(name = "project_identity_id"),
        inverseJoinColumns = @JoinColumn(name = "delegation_id")
    )
    private List<DelegationEntity> delegations = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "project_identity_deanship",
        joinColumns = @JoinColumn(name = "project_identity_id"),
        inverseJoinColumns = @JoinColumn(name = "deanship_id")
    )
    private List<DeanshipEntity> deanships = new ArrayList<>();

}
