package com.prachi.featureservice.evaluation;


import java.util.Map;


public record EvaluationContext(

        String userId,

        Map<String,Object> attributes

) {}