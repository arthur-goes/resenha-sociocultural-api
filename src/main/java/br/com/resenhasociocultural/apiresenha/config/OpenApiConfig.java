package br.com.resenhasociocultural.apiresenha.config;

import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiOAuthProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@Configuration
@Profile("!test")
public class OpenApiConfig {

    @Value("${openapi-config.server-url:http://localhost:8080}")
    private String serverUrl;

    @Value("${openapi-config.auth-url:http://localhost:8080/oauth2/authorize}")
    private String authUrl;

    @Value("${openapi-config.token-url:http://localhost:8080/oauth2/token}")
    private String tokenUrl;

    @Value("${openapi-config.server-description:Development Server}")
    private String serverDescription;

    @Value("${openapi-config.doc-description:}")
    private String docDescription;

    @Value("classpath:openapi.yaml")
    private Resource openApiResource;

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        OpenAPI openApi = Yaml.mapper().readValue(openApiResource.getInputStream(), OpenAPI.class);

        openApi.setServers(List.of(
            new Server().url(serverUrl).description(serverDescription)
        ));

        if (openApi.getComponents() != null &&
            openApi.getComponents().getSecuritySchemes() != null) {

            SecurityScheme securityScheme = openApi.getComponents().getSecuritySchemes().get("bearerAuth");
            if (securityScheme != null && securityScheme.getFlows() != null) {
                OAuthFlows flows = securityScheme.getFlows();
                if (flows.getAuthorizationCode() != null) {
                    flows.getAuthorizationCode().setAuthorizationUrl(authUrl);
                    flows.getAuthorizationCode().setTokenUrl(tokenUrl);
                }
            }
        }

        if (!docDescription.isEmpty()){
            openApi.getInfo().setDescription(docDescription);
        }

        return openApi;
    }

    @Bean
    public SwaggerUiOAuthProperties swaggerUiOAuthProperties() {
        SwaggerUiOAuthProperties config = new SwaggerUiOAuthProperties();
        config.setUsePkceWithAuthorizationCodeGrant(true);
        config.setClientId("swagger-ui");
        config.setScopes(List.of("api"));
        return config;
    }
}