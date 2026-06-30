package com.prachi.featureservice.dto.featureflag;


import com.prachi.featureservice.featureflag.FeatureFlagType;

import java.time.Instant;
import java.util.UUID;


public record FeatureFlagResponse(

        UUID id,

        String key,

        String name,

        String description,

        FeatureFlagType type,

        boolean enabled,
        
        Integer rolloutPercentage,

        UUID projectId,

        Instant createdAt

) {}