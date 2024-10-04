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
@Table(name = "project_strategy")
@Entity
public class ProjectStrategyEntity extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = true)
    private ProjectEntity project;

    @ManyToOne
    @JoinColumn(name = "strategy_id", nullable = true)
    private StrategyEntity strategy;

    @ManyToOne
    @JoinColumn(name = "strategy_axis_id", nullable = true)
    private StrategyAxisEntity strategyAxis;

    @ManyToOne
    @JoinColumn(name = "pnd_id", nullable = true)
    private PndEntity pnd;

    @ManyToOne
    @JoinColumn(name = "pnd_axis_id", nullable = true)
    private PndAxisEntity pndAxis;

    @Column(name = "schema_name", length = 255, nullable = true)
    private String shemaName;

    @Column(name = "schema_project", length = 255, nullable = true)
    private String shemaProject;

    @Column(name = "blueprint_name", length = 255, nullable = true)
    private String blueprintName;

    @Column(name = "blueprint_project", length = 255, nullable = true)
    private String blueprintProject;

    @Column(name = "pnd_project", length = 255, nullable = true)
    private String pndProject;

    @Column(name = "objective", nullable = true, columnDefinition = "TEXT")
    private String objective;

    @Column(name = "result", nullable = true, columnDefinition = "TEXT")
    private String result;

    @Column(name = "components", nullable = true, columnDefinition = "TEXT")
    private String components;

    @Column(name = "document", nullable = true, columnDefinition = "TEXT")
    private String document;

    @Column(name = "realisation_start", length = 255, nullable = true)
    private Date realisationStart;

    @Column(name = "realisation_end", length = 255, nullable = true)
    private Date realisationEnd;

    @Column(name = "operation_start", length = 255, nullable = true)
    private Date operationStart;

    @Column(name = "Workplan", nullable = true, columnDefinition = "TEXT")
    private String Workplan;

}
