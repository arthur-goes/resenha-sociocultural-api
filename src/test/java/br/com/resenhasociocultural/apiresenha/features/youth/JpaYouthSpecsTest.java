package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.configuration.TestcontainerConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({TestcontainerConfig.class, YouthSpecs.class})
public class JpaYouthSpecsTest {

    @Autowired
    YouthRepository repository;

    @Autowired
    YouthSpecs specs;

    private Youth johnDoe;
    private Youth janeDoe;
    private Youth bobSmith;
    private Youth anaJohnson;

    @Test
    public void whenFindWithNameOrSurnameLikeSpecs_shouldReturnMatchingFirstNameYouths(){

    }

    @Test
    public void whenFindWithNameOrSurnameLikeSpecs_shouldReturnMatchingSurnameYouths(){
        Specification<Youth> searchedSubstring = specs.nameOrSurnameLike("Doe");
        List<Youth> foundYouths = repository.findAll(searchedSubstring);

        assertThat(foundYouths.size()).isEqualTo(2);
        assertThat(foundYouths).containsExactlyInAnyOrder(johnDoe, janeDoe);
    }

    @Test
    public void whenFindWithNameOrSurnameLikeSpecs_shouldReturnMatchingFirstNameAndSurnameYouths(){
        Specification<Youth> searchedSubstring = specs.nameOrSurnameLike("Joh");
        List<Youth> foundYouths = repository.findAll(searchedSubstring);

        assertThat(foundYouths.size()).isEqualTo(2);
        assertThat(foundYouths).containsExactlyInAnyOrder(johnDoe, anaJohnson);
    }

    @BeforeEach
    public void setUp(){

        this.johnDoe = aYouth()
            .withId(null)
            .build();

        this.janeDoe = aYouth()
            .withId(null)
            .withFirstName("Jane")
            .withSurname("Doe")
            .withCpf("11111111111")
            .active()
            .build();

        this.bobSmith = aYouth()
            .withId(null)
            .withFirstName("Bob")
            .withSurname("Smith")
            .withCpf("22222222222")
            .inactive()
            .build();

        this.anaJohnson = aYouth()
            .withId(null)
            .withFirstName("Ana")
            .withSurname("Johnson")
            .withCpf("33333333333")
            .inactive()
            .build();

        List<Youth> allYouths = List.of(johnDoe, janeDoe, bobSmith, anaJohnson);

        repository.saveAll(allYouths);

    }
}
