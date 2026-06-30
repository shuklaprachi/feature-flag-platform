package com.prachi.featureservice.featureflag;


import com.prachi.featureservice.project.Project;

import jakarta.persistence.*;

import lombok.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.time.Instant;
import java.util.UUID;


@Entity
@Table(
    name="feature_flags",
    uniqueConstraints = {
        @UniqueConstraint(
            name="uk_flag_project_key",
            columnNames={
                "project_id",
                "key"
            }
        )
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeatureFlag {


    @Id
    private UUID id;


    @Column(nullable=false)
    private String key;


    @Column(nullable=false)
    private String name;


    private String description;


    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private FeatureFlagType type;


    @Column(nullable=false)
    private boolean enabled;

    @Min(0)
    @Max(100)
    @Column(nullable=false)
    private Integer rolloutPercentage;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name="project_id",
        nullable=false
    )
    private Project project;


    @Column(nullable=false)
    private Instant createdAt;


    private Instant updatedAt;

}