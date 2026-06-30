package com.prachi.featureservice.project;

import com.prachi.featureservice.organization.Organization;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(
    name="projects",
    uniqueConstraints = {
        @UniqueConstraint(
            name="uk_project_org_name",
            columnNames={
                "organization_id",
                "name"
            }
        )
    }
)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {


    @Id
    private UUID id;


    @Column(nullable=false)
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name="organization_id",
        nullable=false
    )
    private Organization organization;


    @Column(nullable=false)
    private Instant createdAt;

}