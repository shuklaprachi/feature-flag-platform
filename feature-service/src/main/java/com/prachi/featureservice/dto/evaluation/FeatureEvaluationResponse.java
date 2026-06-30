package com.prachi.featureservice.dto.evaluation;

import com.prachi.featureservice.featureflag.FeatureFlagType;

public record FeatureEvaluationResponse(

        String flagkey,

        boolean enabled,

        FeatureFlagType type

) {
}