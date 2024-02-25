package com.stock.analysis.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@OpenAPIDefinition(info = @Info(title = "API 명세서", description = "BackEnd 명세서", version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
                .security(List.of(securityRequirement));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }

    /**
     * swagger-ui를 통해 보여주지 않을 API 를 숨길떄 사용한다.
     *  ex) 로그인, 회원가입 등의 사용자 인증 API
     */
//    @Bean
//    public GroupedOpenApi hiddenApi() {
//        return GroupedOpenApi.builder()
//                .group("hidden")
//                .packagesToScan("com.example.controllers.hidden")
//                .build();
//    }

//    @Hidden
//    @RestController
//    public static class HiddenController {
//    }
}
