package br.com.resenhasociocultural.apiresenha;

import br.com.resenhasociocultural.apiresenha.repository.YouthRepository;
import br.com.resenhasociocultural.apiresenha.testdata.TestDataInitializer;
import br.com.resenhasociocultural.apiresenha.testdata.YouthTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner init(TestDataInitializer testDataInitializer){
		return args -> testDataInitializer.initialize();
	}

}
