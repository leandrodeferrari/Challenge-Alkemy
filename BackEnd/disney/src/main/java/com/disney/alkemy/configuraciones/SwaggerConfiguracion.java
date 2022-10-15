package com.disney.alkemy.configuraciones;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

/**
 *
 * @author Leandro Deferrari
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguracion {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.disney.alkemy.controladores"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));

    }

    private ApiKey apiKey(){
        
        return new ApiKey("JWT", "Authorization", "header");
        
    }
    
    private SecurityContext securityContext(){
        
        return SecurityContext.builder().securityReferences(defaultAuth()).build();
        
    }
    
    private List<SecurityReference> defaultAuth(){
        
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
        
    }
    
    private ApiInfo getApiInfo() {

        return new ApiInfo(
                "Challenge Alkemy",
                "Api Rest de Disney",
                "1.0.0",
                "Términos del servicio",
                new Contact("Leandro Deferrari",
                            "https://www.linkedin.com/in/javadeveloper-leandrodeferrariarevalo/",
                            "leandro_deferrari@hotmail.com"),
                "Lincencia de la API",
                "Licencia de la API (URL)",
                Collections.emptyList());

    }

}
