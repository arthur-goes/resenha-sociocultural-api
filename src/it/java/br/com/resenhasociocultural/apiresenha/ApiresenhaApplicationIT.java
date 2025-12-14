package br.com.resenhasociocultural.apiresenha;

import br.com.resenhasociocultural.apiresenha.configuration.TestcontainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = {Application.class, TestcontainerConfig.class})
class ApiresenhaApplicationIT {

    @Test
    void contextLoads() {
    }
}
