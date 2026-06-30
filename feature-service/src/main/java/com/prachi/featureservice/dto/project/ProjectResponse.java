package com.prachi.featureservice.dto.project;


import java.time.Instant;
import java.util.UUID;


public record ProjectResponse(

        UUID id,

        String name,

        UUID organizationId,

        Instant createdAt

){}