package br.com.resenhasociocultural.apiresenha.config;

import io.swagger.v3.core.util.Yaml;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${openapi-config.server-url}")
    private String serverUrl;

    @Value("${openapi-config.auth-url}")
    private String authUrl;

    @Value("${openapi-config.token-url}")
    private String tokenUrl;

    @Value("${openapi-config.server-description}")
    private String serverDescription;

    @Value("classpath:static/openapi.yaml")
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

        return openApi;
    }
}