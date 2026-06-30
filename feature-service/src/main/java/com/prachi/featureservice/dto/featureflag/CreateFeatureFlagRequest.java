package com.prachi.featureservice.dto.featureflag;


import com.prachi.featureservice.featureflag.FeatureFlagType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateFeatureFlagRequest(

        @NotBlank
        String key,


        @NotBlank
        String name,


        String description,


        @NotNull
        FeatureFlagType type,


        boolean enabled,

        Integer rolloutPercentage

) {}