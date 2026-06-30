package com.prachi.featureservice.controller;


import com.prachi.featureservice.dto.project.*;

import com.prachi.featureservice.project.ProjectService;


import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/v1")
public class ProjectController {


    private final ProjectService service;


    public ProjectController(
            ProjectService service
    ){
        this.service = service;
    }



    @PostMapping(
        "/organizations/{organizationId}/projects"
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ProjectResponse create(
            @PathVariable UUID organizationId,
            @Valid @RequestBody CreateProjectRequest request
    ){

        return service.create(
                organizationId,
                request
        );

    }



    @GetMapping(
        "/organizations/{organizationId}/projects"
    )
    public List<ProjectResponse> findAll(
            @PathVariable UUID organizationId
    ){

        return service.findAll(
                organizationId
        );

    }



    @GetMapping(
        "/projects/{id}"
    )
    public ProjectResponse findById(
            @PathVariable UUID id
    ){

        return service.findById(id);

    }



    @PutMapping(
        "/projects/{id}"
    )
    public ProjectResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateProjectRequest request
    ){

        return service.update(
                id,
                request
        );

    }



    @DeleteMapping(
        "/projects/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable UUID id
    ){

        service.delete(id);

    }

}