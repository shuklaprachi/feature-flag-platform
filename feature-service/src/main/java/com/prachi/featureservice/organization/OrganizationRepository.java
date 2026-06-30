package com.prachi.featureservice.organization;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrganizationRepository 
        extends JpaRepository<Organization, UUID> {

    boolean existsByName(String name);

}