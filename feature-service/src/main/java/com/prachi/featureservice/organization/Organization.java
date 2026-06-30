package com.prachi.featureservice.organization;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.domain.Persistable;

import java.time.Instant;
import java.util.UUID;


@Entity
@Table(name="organizations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Organization implements Persistable<UUID> {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;


    @Column(nullable=false, unique=true)
    private String name;


    @Column(name="created_at")
    private Instant createdAt;

    @Transient
    private boolean isNew = true;

    @PostLoad
    void markNotNew() {
        this.isNew = false;
    }

    @PrePersist
    void markNotNewBeforePersist() {
        this.isNew = false;
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

}