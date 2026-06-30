package com.prachi.featureservice.project;


import com.prachi.featureservice.dto.project.CreateProjectRequest;
import com.prachi.featureservice.dto.project.ProjectResponse;
import com.prachi.featureservice.exception.ResourceAlreadyExistsException;
import com.prachi.featureservice.exception.ResourceNotFoundException;
import com.prachi.featureservice.organization.Organization;
import com.prachi.featureservice.organization.OrganizationRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prachi.featureservice.dto.project.UpdateProjectRequest;
import java.util.List;
import java.time.Instant;
import java.util.UUID;


@Service
public class ProjectService {


    private final ProjectRepository projectRepository;

    private final OrganizationRepository organizationRepository;


    public ProjectService(
            ProjectRepository projectRepository,
            OrganizationRepository organizationRepository
    ){
        this.projectRepository = projectRepository;
        this.organizationRepository = organizationRepository;
    }



    @Transactional
    public ProjectResponse create(
            UUID organizationId,
            CreateProjectRequest request
    ){


        Organization organization =
                organizationRepository
                        .findById(organizationId)
                        .orElseThrow(
                                () -> new ResourceNotFoundException(
                                    "Organization not found"
                                )
                        );


        if(projectRepository
                .existsByOrganizationIdAndName(
                        organizationId,
                        request.name()
                )){


            throw new ResourceAlreadyExistsException(
                    "Project already exists"
            );
        }



        Project project =
                Project.builder()
                        .id(UUID.randomUUID())
                        .name(request.name())
                        .organization(organization)
                        .createdAt(Instant.now())
                        .build();


        Project saved =
                projectRepository.save(project);



        return map(saved);

    }



    public List<ProjectResponse> findAll(
            UUID organizationId
    ){

        return projectRepository
                .findByOrganizationId(organizationId)
                .stream()
                .map(this::map)
                .toList();

    }

    public ProjectResponse findById(UUID id){

        Project project =
                projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Project not found"
                        )
                );


        return map(project);

    }

    @Transactional
    public ProjectResponse update(
            UUID id,
            UpdateProjectRequest request
    ){

        Project project =
                projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Project not found"
                        )
                );


        project.setName(request.name());


        Project updated =
                projectRepository.save(project);


        return map(updated);

    }

    @Transactional
    public void delete(UUID id){

        Project project =
                projectRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Project not found"
                        )
                );


        projectRepository.delete(project);

    }

    private ProjectResponse map(Project project){

        return new ProjectResponse(
                project.getId(),
                project.getName(),
                project.getOrganization().getId(),
                project.getCreatedAt()
        );

    }

}