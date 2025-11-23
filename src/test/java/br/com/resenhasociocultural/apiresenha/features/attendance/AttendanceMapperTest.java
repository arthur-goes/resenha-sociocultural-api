package br.com.resenhasociocultural.apiresenha.features.attendance;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapperImpl;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;

import org.assertj.core.groups.Tuple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceCreateDtoBuilder.anAttendanceCreateDto;
import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceEntryBuilder.anAttendanceEntry;
import static br.com.resenhasociocultural.apiresenha.features.meeting.builder.MeetingBuilder.aMeeting;
import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;
import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthSimpleDtoBuilder.aYouthSimpleDto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import static org.mockito.Mockito.*;

@Import({AttendanceMapperImpl.class, YouthMapperImpl.class})
@ExtendWith({SpringExtension.class})
public class AttendanceMapperTest {

    @Autowired
    AttendanceMapper attendanceMapper;

    private Meeting meeting;

    private AttendanceEntry attendance;

    private Youth youth;

    private final Set<AttendanceEntry> attendances = new HashSet<>();

    @BeforeEach
    public void setUp(){
        youth = aYouth()
            .withId(1L)
            .withFirstName("John")
            .withSurname("Doe")
            .build();

        attendance = anAttendanceEntry()
            .withId(10L)
            .withYouth(youth)
            .withStatus(AttendanceStatus.PRESENT)
            .build();

        meeting = aMeeting()
            .withId(100L)
            .withDate(LocalDate.now())
            .build();

        meeting.addAttendanceEntries(attendance);

        attendances.add(attendance);
    }

    @Test
    public void givenAttendanceEntryEntity_thenMapToAttendanceResponseDto(){

        AttendanceResponseDto responseDto = attendanceMapper.toResponseDto(attendance);

        assertThat(responseDto.id())
            .isEqualTo(attendance.getId());

        assertThat(responseDto.attendanceStatus())
            .isEqualTo(attendance.getAttendanceStatus());

        assertThat(responseDto.date())
            .isEqualTo(meeting.getDate());

        assertThat(responseDto.youth().id())
            .isEqualTo(youth.getId());

        assertThat(responseDto.meetingId())
            .isEqualTo(meeting.getId());

    }

    @Test
    public void givenAttendanceEntriesList_thenMapToAttendanceResponseDtoSet(){
        Youth youth2 = aYouth()
            .withId(2L)
            .withFirstName("Jane")
            .withSurname("Dane")
            .build();

        AttendanceEntry attendance2 = anAttendanceEntry()
            .withId(11L)
            .withYouth(youth2)
            .withStatus(AttendanceStatus.ABSENT)
            .withAbsenceExcuse("Some excuse")
            .build();

        YouthSimpleDto youthSimpleDto2 = aYouthSimpleDto()
            .withId(youth2.getId())
            .withFirstName(youth2.getFirstName())
            .withSurname(youth2.getSurname())
            .build();

        meeting.addAttendanceEntries(attendance2);
        attendances.add(attendance2);

        List<AttendanceResponseDto> mappedAttendances = attendanceMapper.toResponseListDto(attendances);

        assertThat(mappedAttendances.size()).isEqualTo(2);

        List<Tuple> actualData = mappedAttendances.stream()
            .map(dto -> tuple(
                dto.id(),
                dto.attendanceStatus(),
                dto.absenceExcuse(),
                dto.youth().id(),
                dto.meetingId()
            ))
            .collect(Collectors.toList());

        assertThat(actualData).containsExactlyInAnyOrder(
            tuple(attendance.getId(), attendance.getAttendanceStatus(), attendance.getAbsenceExcuse(), youth.getId(), meeting.getId()),
            tuple(attendance2.getId(), attendance2.getAttendanceStatus(), attendance2.getAbsenceExcuse(), youth2.getId(), meeting.getId())
        );
    }

    @Test
    public void givenAttendanceCreateDto_thenMapToAttendanceEntry(){
        YouthSimpleDto youthDto = aYouthSimpleDto()
            .withId(youth.getId())
            .withFirstName(youth.getFirstName())
            .withSurname(youth.getSurname())
            .build();

        AttendanceCreateDto createDto = anAttendanceCreateDto()
            .withMeetingId(null)
            .withYouth(youthDto)
            .statusPresent()
            .build();

        AttendanceEntry createdAttendance = attendanceMapper.toEntity(createDto);

        assertThat(createdAttendance.getYouth().getId()).isEqualTo(youth.getId());
        assertThat(createdAttendance.getYouth().getFirstName()).isEqualTo(youth.getFirstName());
        assertThat(createdAttendance.getYouth().getSurname()).isEqualTo(youth.getSurname());

        assertThat(createdAttendance.getAttendanceStatus()).isEqualTo(createDto.attendanceStatus());
        assertThat(createdAttendance.getAbsenceExcuse()).isEqualTo(null);
    }
}
