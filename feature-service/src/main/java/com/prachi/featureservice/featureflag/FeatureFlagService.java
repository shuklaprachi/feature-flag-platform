package com.prachi.featureservice.featureflag;


import com.prachi.featureservice.dto.featureflag.*;
import com.prachi.featureservice.exception.*;
import com.prachi.featureservice.project.Project;
import com.prachi.featureservice.project.ProjectRepository;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Service
public class FeatureFlagService {


    private final FeatureFlagRepository repository;

    private final ProjectRepository projectRepository;



    public FeatureFlagService(
            FeatureFlagRepository repository,
            ProjectRepository projectRepository
    ){
        this.repository = repository;
        this.projectRepository = projectRepository;
    }



    @Transactional
    public FeatureFlagResponse create(
            UUID projectId,
            CreateFeatureFlagRequest request
    ){

        Project project =
            projectRepository.findById(projectId)
            .orElseThrow(
                () -> new ResourceNotFoundException(
                    "Project not found"
                )
            );


        if(repository.existsByProjectIdAndKey(
                projectId,
                request.key()
        )){

            throw new ResourceAlreadyExistsException(
                    "Feature flag already exists"
            );

        }



        FeatureFlag flag =
            FeatureFlag.builder()
                .id(UUID.randomUUID())
                .key(request.key())
                .name(request.name())
                .description(request.description())
                .type(request.type())
                .enabled(request.enabled())
                .rolloutPercentage(
                request.rolloutPercentage() == null
                ? 0
                : request.rolloutPercentage()
                )
                .project(project)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();


        FeatureFlag saved =
                repository.save(flag);


        return map(saved);

    }


    public List<FeatureFlagResponse> findAll(
        UUID projectId
    ){

        return repository.findByProjectId(projectId)
                .stream()
                .map(this::map)
                .toList();

    }

    public FeatureFlagResponse findById(UUID id){

        FeatureFlag flag =
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Feature flag not found"
                    )
                );


        return map(flag);

    }

    @Transactional
    public FeatureFlagResponse update(
            UUID id,
            UpdateFeatureFlagRequest request
    ){

        FeatureFlag flag =
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException(
                        "Feature flag not found"
                    )
                );


        flag.setName(request.name());
        flag.setDescription(request.description());
        flag.setEnabled(request.enabled());

        if(request.rolloutPercentage()!=null){
            flag.setRolloutPercentage(
                    request.rolloutPercentage()
            );
        }


        return map(
            repository.save(flag)
        );
    }

    @Transactional
public void delete(UUID id){

    FeatureFlag flag =
            repository.findById(id)
            .orElseThrow(
                () -> new ResourceNotFoundException(
                    "Feature flag not found"
                )
            );


    repository.delete(flag);

}

    private FeatureFlagResponse map(
            FeatureFlag flag
    ){

        return new FeatureFlagResponse(
                flag.getId(),
                flag.getKey(),
                flag.getName(),
                flag.getDescription(),
                flag.getType(),
                flag.isEnabled(),
                flag.getRolloutPercentage(),
                flag.getProject().getId(),
                flag.getCreatedAt()
        );

    }

}