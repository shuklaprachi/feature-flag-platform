package com.prachi.featureservice.evaluation;


import org.springframework.stereotype.Component;


@Component
public class RolloutEvaluator {


    public boolean evaluate(
            String userId,
            String flagKey,
            int percentage
    ){

        if(percentage >= 100){
            return true;
        }


        if(percentage <= 0){
            return false;
        }


        String input =
                userId + ":" + flagKey;


        int bucket =
                Math.floorMod(
                        input.hashCode(),
                        100
                );


        return bucket < percentage;
    }

}