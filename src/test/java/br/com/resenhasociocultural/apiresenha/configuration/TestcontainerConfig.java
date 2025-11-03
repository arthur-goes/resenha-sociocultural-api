package br.com.resenhasociocultural.apiresenha.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@TestConfiguration(proxyBeanMethods = false)
public class TestcontainerConfig {

    @Container
    private static PostgreSQLContainer postgresContainer = new PostgreSQLContainer<>("postgres:14.19-alpine3.21");

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer(){
        return postgresContainer;
    }
}
