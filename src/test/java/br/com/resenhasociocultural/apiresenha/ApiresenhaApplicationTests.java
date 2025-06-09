package br.com.resenhasociocultural.apiresenha;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

import java.sql.Connection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ApiresenhaApplicationTests {

	@Test
	void contextLoads() {
	}
}
