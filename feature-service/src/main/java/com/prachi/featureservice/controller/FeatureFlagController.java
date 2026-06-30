package com.prachi.featureservice.controller;


import com.prachi.featureservice.dto.featureflag.*;
import com.prachi.featureservice.featureflag.FeatureFlagService;


import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping(
"/api/v1/projects/{projectId}/feature-flags"
)
public class FeatureFlagController {


    private final FeatureFlagService service;


    public FeatureFlagController(
            FeatureFlagService service
    ){
        this.service = service;
    }

    @GetMapping(
    "/projects/{projectId}/feature-flags"
    )
    public List<FeatureFlagResponse> findAll(
            @PathVariable UUID projectId
    ){

        return service.findAll(projectId);

    }

    @GetMapping(
    "/feature-flags/{id}"
    )
    public FeatureFlagResponse findById(
            @PathVariable UUID id
    ){

        return service.findById(id);

    }

    @PutMapping(
    "/feature-flags/{id}"
    )
    public FeatureFlagResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateFeatureFlagRequest request
    ){

        return service.update(
                id,
                request
        );

    }

    @DeleteMapping(
    "/feature-flags/{id}"
    )
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable UUID id
    ){

        service.delete(id);

    }

    @PostMapping
    public FeatureFlagResponse create(
            @PathVariable UUID projectId,
            @Valid @RequestBody CreateFeatureFlagRequest request
    ){

        return service.create(
                projectId,
                request
        );

    }

}