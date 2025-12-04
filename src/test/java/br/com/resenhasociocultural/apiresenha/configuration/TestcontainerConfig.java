package br.com.resenhasociocultural.apiresenha.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration(proxyBeanMethods = false)
@Slf4j
public class TestcontainerConfig {

    private static final PostgreSQLContainer<?> postgresContainer;
    private static final String FALLBACK_IMG = "postgres:14.19-alpine3.21";

    static {
        String envImg = System.getenv("POSTGRESQL_DOCKER_IMAGE");
        String dockerImage;
        if (envImg != null && !envImg.trim().isEmpty()){
            dockerImage = envImg;
        } else {
            dockerImage = FALLBACK_IMG;
        }
        postgresContainer = new PostgreSQLContainer<>(dockerImage);
    }

    @Bean
    @ServiceConnection
    PostgreSQLContainer<?> postgresContainer(){
        String envImg = System.getenv("POSTGRESQL_DOCKER_IMAGE");
        if (envImg == null || envImg.trim().isEmpty()){
            log.warn("It was not possible to get Testcontainer docker image name from enviroment. Proceeding with fallback image: " + FALLBACK_IMG);
        }
        return postgresContainer;
    }
}
