package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.configuration.TestcontainerConfig;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceRepository;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.meeting.MeetingRepository;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPointRepository;
import br.com.resenhasociocultural.apiresenha.features.strike.StrikeRepository;
import br.com.resenhasociocultural.apiresenha.utils.CPFGenerator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceBuilder.anAttendance;
import static br.com.resenhasociocultural.apiresenha.features.meeting.builder.MeetingBuilder.aMeeting;
import static br.com.resenhasociocultural.apiresenha.features.participationpoint.builder.ParticipationPointBuilder.aParticipationPoint;
import static br.com.resenhasociocultural.apiresenha.features.strike.builder.StrikeBuilder.aStrike;
import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import({TestcontainerConfig.class, YouthNameSpecs.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JpaYouthNameSpecsTest {

    @Autowired
    YouthRepository youthRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    @Autowired
    StrikeRepository strikeRepository;

    @Autowired
    ParticipationPointRepository participationPointRepository;

    @Autowired
    MeetingRepository meetingRepository;

    @Autowired
    YouthNameSpecs nameSpecs;

    private Meeting meeting;

    private Youth johnDoe;
    private Youth janeDoe;
    private Youth bobSmith;
    private Youth anaJohnson;

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Transactional
    public class youthEntityNameSpecsTest {

        @Test
        public void shouldReturnMatchingSurnameYouths_whenSearchingBySurname() {
            Specification<Youth> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouth("Doe");
            List<Youth> foundYouths = youthRepository.findAll(searchedSubstring);

            assertThat(foundYouths.size()).isEqualTo(2);
            assertThat(foundYouths).containsExactlyInAnyOrder(johnDoe, janeDoe);
        }

        @Test
        public void shouldReturnMatchingYouths_whenSearchingByPartialName() {
            Specification<Youth> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouth("Joh");
            List<Youth> foundYouths = youthRepository.findAll(searchedSubstring);

            assertThat(foundYouths.size()).isEqualTo(2);
            assertThat(foundYouths).containsExactlyInAnyOrder(johnDoe, anaJohnson);
        }

        @Test
        public void shouldReturnEmptyList_whenSearchingForNonExistentName() {
            Specification<Youth> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouth("Alice");
            List<Youth> foundYouths = youthRepository.findAll(searchedSubstring);

            assertThat(foundYouths).isEmpty();
        }

        @BeforeEach
        public void setup(){
            createYouthsAndPersist();
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Transactional
    public class youthEntryNameSpecsTest {

        @ParameterizedTest(name = "Scenario: {0}")
        @MethodSource("youthEntryNameSpecsScenario")
        public <E extends YouthEntry> void shouldReturnMatchingSurnameYouthEntries_whenSearchingBySurname(
            String description,
            JpaSpecificationExecutor<E> repository
        ) {
            createMeetingAndPersist();
            Specification<E> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouthEntry("Doe");
            List<E> foundEntries = repository.findAll(searchedSubstring);
            List<Youth> entriesYouth = foundEntries.stream().map(E::getYouth).collect(Collectors.toList());

            assertThat(foundEntries.size()).isEqualTo(2);
            assertThat(entriesYouth).containsExactlyInAnyOrder(johnDoe, janeDoe);
        }

        @ParameterizedTest(name = "Scenario: {0}")
        @MethodSource("youthEntryNameSpecsScenario")
        public <E extends YouthEntry> void shouldReturnMatchingYouths_whenSearchingByPartialName(
            String description,
            JpaSpecificationExecutor<E> repository
        ) {
            createMeetingAndPersist();
            Specification<E> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouthEntry("Joh");
            List<E> foundEntries = repository.findAll(searchedSubstring);
            List<Youth> entriesYouth = foundEntries.stream().map(E::getYouth).collect(Collectors.toList());

            assertThat(foundEntries.size()).isEqualTo(2);
            assertThat(entriesYouth).containsExactlyInAnyOrder(johnDoe, anaJohnson);
        }

        private Stream<Arguments> youthEntryNameSpecsScenario() {
            return Stream.of(
                Arguments.of(
                    "Testing youthNameSpecs with an Attendance, matching youthName and Surname",
                    attendanceRepository
                ),
                Arguments.of(
                    "Testing youthNameSpecs with a Strike, matching youthName and Surname",
                    strikeRepository
                ),
                Arguments.of(
                    "Testing youthNameSpecs with a ParticipationPoint, matching youthName and Surname",
                    participationPointRepository
                )
            );
        }

        @ParameterizedTest(name = "Scenario: {0}")
        @MethodSource("youthEntryNameSpecsNoMatchScenario")
        public <E extends YouthEntry> void shouldReturnEmptyList_whenSearchingForNonExistentYouth(
            String description,
            JpaSpecificationExecutor<E> repository
        ){
            createMeetingAndPersist();
            Specification<E> searchedSubstring = nameSpecs.nameOrSurnameLikeForYouthEntry("Alice");
            List<E> foundEntries = repository.findAll(searchedSubstring);
            List<Youth> entriesYouth = foundEntries.stream().map(E::getYouth).collect(Collectors.toList());

            assertThat(foundEntries).isEmpty();
        }

        private Stream<Arguments> youthEntryNameSpecsNoMatchScenario() {
            return Stream.of(
                Arguments.of(
                    "Testing youthNameSpecs with an Attendance. Should find no match.",
                    attendanceRepository
                ),
                Arguments.of(
                    "Testing youthNameSpecs with a Strike. Should find no match",
                    strikeRepository
                ),
                Arguments.of(
                    "Testing youthNameSpecs with a ParticipationPoint. Should find no match",
                    participationPointRepository
                )
            );
        }

        private Meeting createMeetingAndPersist(){
            Meeting meeting = aMeeting().withoutId().build();

            createYouthsAndPersist();

            var youths = List.of(johnDoe, janeDoe, bobSmith, anaJohnson);

            for (int i = 0; i < youths.size(); i++){
                var youth = youths.get(i);

                var attendance = anAttendance()
                    .withoutId()
                    .withYouth(youths.get(i))
                    .withStatus(AttendanceStatus.PRESENT)
                    .withAbsenceExcuse(null)
                    .build();

                meeting.addAttendances(attendance);

                var strike = aStrike()
                    .withoutId()
                    .active()
                    .withYouth(youths.get(i))
                    .withAmount(1)
                    .withReason("Some Bad Reason " + i)
                    .build();

                meeting.addStrikes(strike);

                var participationPoint = aParticipationPoint()
                    .withoutId()
                    .active()
                    .withYouth(youths.get(i))
                    .withAmount(1)
                    .withReason("Some Good Reason " + i)
                    .build();

                meeting.addParticipationPoints(participationPoint);
            }
            return meetingRepository.save(meeting);
        }
    }

    private void createYouthsAndPersist() {
        Set<String> uniqueCPFs = Stream.generate(CPFGenerator::generate)
            .distinct()
            .limit(4L)
            .collect(Collectors.toSet());

        var iterator = uniqueCPFs.iterator();

        johnDoe = aYouth()
                .withId(null)
                .withFirstName("John")
                .withSurname("Doe")
                .withCpf(iterator.next())
                .active()
                .build();

        janeDoe = aYouth()
                .withId(null)
                .withFirstName("Jane")
                .withSurname("Doe")
                .withCpf(iterator.next())
                .active()
                .build();

        bobSmith = aYouth()
                .withId(null)
                .withFirstName("Bob")
                .withSurname("Smith")
                .withCpf(iterator.next())
                .inactive()
                .build();

        anaJohnson = aYouth()
                .withId(null)
                .withFirstName("Ana")
                .withSurname("Johnson")
                .withCpf(iterator.next())
                .inactive()
                .build();

        youthRepository.saveAll(List.of(johnDoe, janeDoe, bobSmith, anaJohnson));
    }
}