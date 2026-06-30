package com.prachi.featureservice.controller;


import com.prachi.featureservice.dto.evaluation.FeatureEvaluationResponse;
import com.prachi.featureservice.evaluation.EvaluationContext;
import com.prachi.featureservice.evaluation.FeatureEvaluationService;


import org.springframework.web.bind.annotation.*;


import java.util.UUID;



@RestController
@RequestMapping("/api/v1/evaluate")
public class FeatureEvaluationController {


    private final FeatureEvaluationService service;


    public FeatureEvaluationController(
            FeatureEvaluationService service
    ){
        this.service = service;
    }



    @GetMapping("/{projectId}/{flagKey}")
    public FeatureEvaluationResponse evaluate(
            @PathVariable UUID projectId,
            @PathVariable String flagKey,
            @RequestParam String userId
    ){

        EvaluationContext context =
                new EvaluationContext(
                        userId,
                        null
                );


        return service.evaluate(
                projectId,
                flagKey,
                context
        );

    }

}