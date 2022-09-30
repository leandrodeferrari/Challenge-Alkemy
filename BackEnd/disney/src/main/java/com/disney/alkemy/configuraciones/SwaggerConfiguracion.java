package com.disney.alkemy.configuraciones;

import java.util.Collections;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Contact;

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
                .apiInfo(getApiInfo());

    }

    private ApiInfo getApiInfo() {

        return new ApiInfo(
                "Challenge Alkemy",
                "Api Rest de Disney",
                "0.0.1",
                "Terminos de servicio",
                new Contact("Leandro Deferrari",
                        "https://www.linkedin.com/in/javadeveloper-leandrodeferrariarevalo/",
                        "leandro_deferrari@hotmail.com"),
                "Lincencia de API",
                "Licencia de API URL",
                Collections.emptyList());

    }

}
