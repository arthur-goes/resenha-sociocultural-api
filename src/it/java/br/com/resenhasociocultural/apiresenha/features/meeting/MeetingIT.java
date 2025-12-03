package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.Application;
import br.com.resenhasociocultural.apiresenha.factories.*;
import br.com.resenhasociocultural.apiresenha.factories.dto.MeetingFactoryResult;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceRepository;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPointRepository;
import br.com.resenhasociocultural.apiresenha.features.strike.StrikeRepository;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static br.com.resenhasociocultural.apiresenha.features.meeting.builder.MeetingBuilder.aMeeting;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = "ADMIN")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MeetingIT {

  @Autowired
  MockMvcTester mvcTester;

  @Autowired
  ObjectMapper objectMapper;

  @Autowired
  YouthRepository youthRepository;

  @Autowired
  MeetingRepository meetingRepository;
  @Autowired
  AttendanceRepository attendanceRepository;
  @Autowired
  StrikeRepository strikeRepository;
  @Autowired
  ParticipationPointRepository participationPointRepository;

  @Autowired
  MeetingMapper meetingMapper;

  @Autowired
  EntityManager entityManager;

  @Test
  @Transactional
  public void shouldCreateNewMeetingAndReturnStatusCreated() throws Exception{
    int amountOfYouths = 3;
    List<Youth> persistedYouths = createYouthsAndPersist(amountOfYouths);

    MeetingFactoryResult meetingFactoryResult = MeetingFactory.generateCompleteMeeting(persistedYouths);
    MeetingCreateDto createDto = meetingMapper.toCreateDto(meetingFactoryResult.meeting());
    String requestBody = objectMapper.writeValueAsString(createDto);

    var result = mvcTester.post()
      .uri("/meetings")
      .contentType(MediaType.APPLICATION_JSON)
      .content(requestBody)
      .exchange();

    MeetingResponseDto responseMeeting = objectMapper.readValue(result.getResponse().getContentAsString(), MeetingResponseDto.class);

    result.assertThat().hasStatus(HttpStatus.CREATED);
    result.assertThat().bodyJson().isNotNull();

    assertThat(responseMeeting)
      .usingRecursiveComparison()
      .ignoringFields(
        "id",
        "attendances.id",
        "attendances.meetingId",
        "attendances.date",
        "strikes.id",
        "strikes.meetingId",
        "strikes.date",
        "participationPoints.id",
        "participationPoints.meetingId",
        "participationPoints.date"
      ).isEqualTo(createDto);

    assertThat(responseMeeting.id()).isNotNull();

    assertThat(responseMeeting.attendances())
      .allSatisfy(attendance -> {
        assertThat(attendance.id()).isNotNull();
        assertThat(attendance.meetingId()).isNotNull();
        assertThat(attendance.date()).isNotNull();
      });

    assertThat(responseMeeting.strikes())
      .allSatisfy(strike -> {
        assertThat(strike.id()).isNotNull();
        assertThat(strike.meetingId()).isNotNull();
        assertThat(strike.date()).isNotNull();
      });

    assertThat(responseMeeting.participationPoints())
      .allSatisfy(participationPoint -> {
        assertThat(participationPoint.id()).isNotNull();
        assertThat(participationPoint.meetingId()).isNotNull();
        assertThat(participationPoint.date()).isNotNull();
      });

    assertThat(meetingRepository.count()).isEqualTo(1);
    assertThat(attendanceRepository.count()).isEqualTo(amountOfYouths);
    assertThat(strikeRepository.count()).isEqualTo(amountOfYouths);
    assertThat(participationPointRepository.count()).isEqualTo(amountOfYouths);
  }

  @Test
  @Transactional
  public void shouldUpdateAttendance_whenUpdatingMeeting() throws Exception{
    int amountOfYouths = 3;
    List<Youth> persistedYouths = createYouthsAndPersist(amountOfYouths);

    var meeting = createMeetingAndPersist(persistedYouths);

    var updatedAttendance = meeting
      .getAttendances()
      .stream()
      .filter(attendance -> attendance.getAttendanceStatus() == AttendanceStatus.PRESENT)
      .toList()
      .get(0);

    Long updatedAttendanceId = updatedAttendance.getId();
    updatedAttendance.setAttendanceStatus(AttendanceStatus.ABSENT);
    MeetingUpdateDto updateDto = meetingMapper.toUpdateDto(meeting);
    String bodyJson = objectMapper.writeValueAsString(updateDto);

    var result = mvcTester
      .put()
      .uri("/meetings/{id}", meeting.getId())
      .contentType(MediaType.APPLICATION_JSON)
      .content(bodyJson)
      .exchange();

    entityManager.flush();
    entityManager.clear();

    result.assertThat().hasStatusOk();
    result.assertThat().bodyJson().isNotNull();

    var attendanceToVerify = attendanceRepository.findById(updatedAttendanceId);
    assertThat(attendanceToVerify).isPresent();
    assertThat(attendanceToVerify.get().getId()).isEqualTo(updatedAttendanceId);
    assertThat(attendanceToVerify.get().getAttendanceStatus()).isEqualTo(AttendanceStatus.ABSENT);
  }

  @Test
  @Transactional
  public void shouldUpdateStrike_whenUpdatingMeeting() throws Exception{
    int amountOfYouths = 3;
    List<Youth> persistedYouths = createYouthsAndPersist(amountOfYouths);

    var meeting = createMeetingAndPersist(persistedYouths);

    var updatedStrike = meeting
      .getStrikes()
      .iterator()
      .next();

    String originalReason = updatedStrike.getReason();
    String newReason = "Some new Reason";

    Long updatedStrikeId = updatedStrike.getId();
    updatedStrike.setReason(newReason);
    MeetingUpdateDto updateDto = meetingMapper.toUpdateDto(meeting);
    String bodyJson = objectMapper.writeValueAsString(updateDto);

    var result = mvcTester
      .put()
      .uri("/meetings/{id}", meeting.getId())
      .contentType(MediaType.APPLICATION_JSON)
      .content(bodyJson)
      .exchange();

    entityManager.flush();
    entityManager.clear();

    result.assertThat().hasStatusOk();
    result.assertThat().bodyJson().isNotNull();

    var strikeToVerify = strikeRepository.findById(updatedStrikeId);
    assertThat(strikeToVerify).isPresent();
    assertThat(strikeToVerify.get().getId()).isEqualTo(updatedStrikeId);
    assertThat(strikeToVerify.get().getReason()).isNotEqualTo(originalReason);
    assertThat(strikeToVerify.get().getReason()).isEqualTo(newReason);
  }

  @Test
  @Transactional
  public void shouldUpdateParticipationPoint_whenUpdatingMeeting() throws Exception{
    int amountOfYouths = 3;
    List<Youth> persistedYouths = createYouthsAndPersist(amountOfYouths);

    var meeting = createMeetingAndPersist(persistedYouths);

    var updatedParticipationPoint = meeting
      .getParticipationPoints()
      .iterator()
      .next();

    String originalReason = updatedParticipationPoint.getReason();
    String newReason = "Some new Reason";

    Long updatedParticipationPointId = updatedParticipationPoint.getId();
    updatedParticipationPoint.setReason(newReason);
    MeetingUpdateDto updateDto = meetingMapper.toUpdateDto(meeting);
    String bodyJson = objectMapper.writeValueAsString(updateDto);

    var result = mvcTester
      .put()
      .uri("/meetings/{id}", meeting.getId())
      .contentType(MediaType.APPLICATION_JSON)
      .content(bodyJson)
      .exchange();

    entityManager.flush();
    entityManager.clear();

    result.assertThat().hasStatusOk();
    result.assertThat().bodyJson().isNotNull();

    var participationPointToVerify = participationPointRepository.findById(updatedParticipationPointId);
    assertThat(participationPointToVerify).isPresent();
    assertThat(participationPointToVerify.get().getId()).isEqualTo(updatedParticipationPointId);
    assertThat(participationPointToVerify.get().getReason()).isNotEqualTo(originalReason);
    assertThat(participationPointToVerify.get().getReason()).isEqualTo(newReason);
  }

  @ParameterizedTest(name = "Scenario: {0}")
  @Transactional
  @MethodSource("nonExistentYouthIdInYouthEntryScenario")
  public void shouldNotCreateMeeting_whenYouthIdInYouthEntryIsNonExistent(String description, Meeting meeting) throws Exception{
    var createDto = meetingMapper.toCreateDto(meeting);
    var bodyJson = objectMapper.writeValueAsString(createDto);

    var result = mvcTester.post()
        .uri("/meetings")
        .contentType(MediaType.APPLICATION_JSON)
        .content(bodyJson)
        .exchange();

    result.assertThat().hasStatus(HttpStatus.BAD_REQUEST);
    result.assertThat().bodyJson().extractingPath("$.message").asString().startsWith("Não foi possível prosseguir com a solicitação.");
  }

  private Stream<Arguments> nonExistentYouthIdInYouthEntryScenario(){
    List<Youth> persistedYouths = createYouthsAndPersist(3);

    var attendances = AttendanceFactory.generateAttendances(persistedYouths).generatedAttendances();
    var strikes = StrikeFactory.generateStrikes(persistedYouths).generatedStrikes();
    var participationPoints = ParticipationPointFactory.generateParticipationPoints(persistedYouths).generatedParticipationPoints();

    attendances.iterator().next().getYouth().setId(100L);

    var meetingWithAttendance = aMeeting().withAttendances(new HashSet<>(attendances)).build();
    var meetingWithStrikes = aMeeting().withStrikes(new HashSet<>(strikes)).build();
    var meetingWithParticipationPoints = aMeeting().withParticipationPoints(new HashSet<>(participationPoints)).build();

    return Stream.of(
        Arguments.of("Attendance with invalid non existent youth id.", meetingWithAttendance),
        Arguments.of("Strike with invalid non existent youth id.", meetingWithStrikes),
        Arguments.of("ParticipationPoint with invalid non existent youth id.", meetingWithParticipationPoints)
    );
  }

  private Meeting createMeetingAndPersist(List<Youth> youths){
    MeetingFactoryResult meetingResult = MeetingFactory.generateCompleteMeeting(youths);
    var meeting = meetingRepository.save(meetingResult.meeting());

    entityManager.flush();
    entityManager.detach(meeting);

    return meeting;
  }

  private List<Youth> createYouthsAndPersist(int amountOfYouths){
    var youthFactoryResult = YouthFactory.generateYouthsWithoutIds(amountOfYouths);
    return youthRepository.saveAll(youthFactoryResult.generatedYouths());
  }

}