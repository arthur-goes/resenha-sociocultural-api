package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.configuration.TestcontainerConfig;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({TestcontainerConfig.class, YouthNameSpecs.class})
public class JpaYouthNameSpecsTest {

    @Autowired
    YouthRepository youthRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    YouthNameSpecs nameSpecs;

    private Youth johnDoe;
    private Youth janeDoe;
    private Youth bobSmith;
    private Youth anaJohnson;

    @Nested
    public class youthEntityNameSpecsTest() {

        @Test
        public void whenFindWithNameOrSurnameLikeSpecs_shouldReturnMatchingSurnameYouths() {
            Specification<Youth> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouth("Doe");
            List<Youth> foundYouths = youthRepository.findAll(searchedSubstring);

            assertThat(foundYouths.size()).isEqualTo(2);
            assertThat(foundYouths).containsExactlyInAnyOrder(johnDoe, janeDoe);
        }

        @Test
        public void whenFindWithNameOrSurnameLikeSpecs_shouldReturnMatchingFirstNameAndSurnameYouths() {
            Specification<Youth> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouth("Joh");
            List<Youth> foundYouths = youthRepository.findAll(searchedSubstring);

            assertThat(foundYouths.size()).isEqualTo(2);
            assertThat(foundYouths).containsExactlyInAnyOrder(johnDoe, anaJohnson);
        }

        @BeforeEach
        public void setUp() {

            johnDoe = aYouth()
              .withId(null)
              .build();

            janeDoe = aYouth()
              .withId(null)
              .withFirstName("Jane")
              .withSurname("Doe")
              .withCpf("11111111111")
              .active()
              .build();

            bobSmith = aYouth()
              .withId(null)
              .withFirstName("Bob")
              .withSurname("Smith")
              .withCpf("22222222222")
              .inactive()
              .build();

            anaJohnson = aYouth()
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
}
