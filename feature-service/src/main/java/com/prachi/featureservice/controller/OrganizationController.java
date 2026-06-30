package com.prachi.featureservice.controller;


import com.prachi.featureservice.dto.organization.*;

import com.prachi.featureservice.organization.OrganizationService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api/v1/organizations")
public class OrganizationController {


    private final OrganizationService service;


    public OrganizationController(
            OrganizationService service
    ){
        this.service = service;
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrganizationResponse create(
            @Valid @RequestBody CreateOrganizationRequest request
    ){

        return service.create(request);

    }



    @GetMapping
    public List<OrganizationResponse> findAll(){

        return service.findAll();

    }



    @GetMapping("/{id}")
    public OrganizationResponse findById(
            @PathVariable UUID id
    ){

        return service.findById(id);

    }



    @PutMapping("/{id}")
    public OrganizationResponse update(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateOrganizationRequest request
    ){

        return service.update(
                id,
                request
        );

    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable UUID id
    ){

        service.delete(id);

    }

}