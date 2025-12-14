package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.configuration.TestcontainerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(TestcontainerConfig.class)
public class YouthRepositoryTest {

    @Autowired
    YouthRepository repository;

    private Youth activeYouth1;
    private Youth activeYouth2;
    private Youth inactiveYouth1;
    private Youth inactiveYouth2;

    @Test
    public void whenFindByActive_shouldReturnOnlyActiveYouths() throws Exception{

        List<Youth> activeYouths = repository.findByActive(true);

        assertThat(activeYouth1.getId()).isNotNull();
        assertThat(activeYouth2.getId()).isNotNull();
        assertThat(inactiveYouth1.getId()).isNotNull();
        assertThat(inactiveYouth2.getId()).isNotNull();

        assertThat(activeYouths.size()).isEqualTo(2);
        assertThat(activeYouths).containsExactlyInAnyOrder(activeYouth1, activeYouth2);
        assertThat(activeYouths).doesNotContain(inactiveYouth1);
        assertThat(activeYouths).doesNotContain(inactiveYouth2);

    }

    @Test
    public void whenFindByActive_shouldReturnOnlyInactiveYouths(){

        List<Youth> inactiveYouths = repository.findByActive(false);

        assertThat(activeYouth1.getId()).isNotNull();
        assertThat(activeYouth2.getId()).isNotNull();
        assertThat(inactiveYouth1.getId()).isNotNull();
        assertThat(inactiveYouth2.getId()).isNotNull();

        assertThat(inactiveYouths.size()).isEqualTo(2);
        assertThat(inactiveYouths).containsExactlyInAnyOrder(inactiveYouth1, inactiveYouth2);
        assertThat(inactiveYouths).doesNotContain(activeYouth1);
        assertThat(inactiveYouths).doesNotContain(activeYouth2);

    }

    @BeforeEach
    public void setUp(){

        this.activeYouth1 = aYouth()
                .withId(null)
                .build();

        this.activeYouth2 = aYouth()
            .withId(null)
            .withFirstName("Jane")
            .withCpf("11111111111")
            .active()
            .build();

        this.inactiveYouth1 = aYouth()
            .withId(null)
            .withFirstName("Bob")
            .withCpf("22222222222")
            .inactive()
            .build();

        this.inactiveYouth2 = aYouth()
            .withId(null)
            .withFirstName("Ana")
            .withCpf("33333333333")
            .inactive()
            .build();

        List<Youth> allYouths = List.of(activeYouth1, activeYouth2, inactiveYouth1, inactiveYouth2);

        repository.saveAll(allYouths);

    }

}
