package com.prachi.featureservice.organization;


import com.prachi.featureservice.dto.organization.CreateOrganizationRequest;
import com.prachi.featureservice.dto.organization.OrganizationResponse;
import com.prachi.featureservice.dto.organization.UpdateOrganizationRequest;
import com.prachi.featureservice.exception.ResourceAlreadyExistsException;
import com.prachi.featureservice.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
public class OrganizationService {


    private final OrganizationRepository repository;


    public OrganizationService(
            OrganizationRepository repository
    ) {
        this.repository = repository;
    }


    @Transactional
    public OrganizationResponse create(
            CreateOrganizationRequest request
    ) {


        if(repository.existsByName(request.name())) {

            throw new ResourceAlreadyExistsException(
                    "Organization already exists"
            );
        }


        Organization organization =
                Organization.builder()
                        .name(request.name())
                        .createdAt(Instant.now())
                        .build();


        Organization saved =
                repository.save(organization);


        return new OrganizationResponse(
                saved.getId(),
                saved.getName(),
                saved.getCreatedAt()
        );
    }

    public List<OrganizationResponse> findAll(){

        return repository.findAll()
                .stream()
                .map(this::map)
                .toList();

    }

    public OrganizationResponse findById(UUID id){

        Organization organization =
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Organization not found"
                    )
                );


        return map(organization);

    }
    @Transactional
    public OrganizationResponse update(
            UUID id,
            UpdateOrganizationRequest request
    ){

        Organization organization =
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Organization not found"
                    )
                );


        organization.setName(request.name());


        Organization saved =
                repository.save(organization);


        return map(saved);

    }

    @Transactional
    public void delete(UUID id){

        Organization organization =
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Organization not found"
                    )
                );


        repository.delete(organization);

    }

    private OrganizationResponse map(
            Organization organization
    ){

        return new OrganizationResponse(
                organization.getId(),
                organization.getName(),
                organization.getCreatedAt()
        );

    }
}