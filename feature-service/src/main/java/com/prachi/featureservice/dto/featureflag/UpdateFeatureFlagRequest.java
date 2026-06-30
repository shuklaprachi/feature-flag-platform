package com.prachi.featureservice.dto.featureflag;


import jakarta.validation.constraints.NotBlank;


public record UpdateFeatureFlagRequest(

        @NotBlank
        String name,

        String description,

        boolean enabled,

        Integer rolloutPercentage

) {}