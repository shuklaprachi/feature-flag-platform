package com.prachi.featureservice.dto.evaluation;


import java.util.UUID;


public record EvaluationRequest(

        UUID projectId,

        String flagKey

) {}