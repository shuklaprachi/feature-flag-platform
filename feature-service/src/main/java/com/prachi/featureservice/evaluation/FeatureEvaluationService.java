package com.prachi.featureservice.evaluation;


import com.prachi.featureservice.dto.evaluation.FeatureEvaluationResponse;
import com.prachi.featureservice.exception.ResourceNotFoundException;
import com.prachi.featureservice.featureflag.FeatureFlag;
import com.prachi.featureservice.featureflag.FeatureFlagRepository;


import org.springframework.stereotype.Service;


import java.util.UUID;



@Service
public class FeatureEvaluationService {


    private final FeatureFlagRepository repository;
    private final RolloutEvaluator rolloutEvaluator;



    public FeatureEvaluationService(
            FeatureFlagRepository repository,
            RolloutEvaluator rolloutEvaluator
    ){
        this.repository = repository;
        this.rolloutEvaluator = rolloutEvaluator;
    }




    public FeatureEvaluationResponse evaluate(
            UUID projectId,
            String flagKey,
            EvaluationContext context
    ){

        FeatureFlag flag =
                repository
                .findByProjectIdAndKey(
                        projectId,
                        flagKey
                )
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Feature flag not found"
                        )
                );


        boolean enabled =
                flag.isEnabled()
                &&
                rolloutEvaluator.evaluate(
                        context.userId(),
                        flag.getKey(),
                        flag.getRolloutPercentage()
                );


        return new FeatureEvaluationResponse(
                flag.getKey(),
                enabled,
                flag.getType()
        );

    }

}