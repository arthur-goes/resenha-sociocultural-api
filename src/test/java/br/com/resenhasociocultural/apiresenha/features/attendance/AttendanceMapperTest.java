package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceEntryBuilder.anAttendanceEntry;
import static br.com.resenhasociocultural.apiresenha.features.meeting.builder.MeetingBuilder.aMeeting;
import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;
import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthSimpleDtoBuilder.anYouthSimpleDto;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ExtendWith(MockitoExtension.class)
public class AttendanceMapperTest {
    @InjectMocks
    @Spy
    AttendanceMapper attendanceMapper = Mappers.getMapper(AttendanceMapper.class);

    @Mock
    YouthMapper youthMapper;

    private Meeting meeting;

    private AttendanceEntry attendance;

    private Youth youth;

    private YouthSimpleDto youthSimpleDto;

    private final Set<AttendanceEntry> attendances = new HashSet<>();

    @BeforeEach
    public void setUp(){
        youth = aYouth().build();

        attendance = anAttendanceEntry()
            .withYouth(youth)
            .build();

        meeting = aMeeting().build();

        meeting.addAttendanceEntries(attendance);

        youthSimpleDto = anYouthSimpleDto()
            .withId(youth.getId())
            .withFirstName(youth.getFirstName())
            .withSurname(youth.getSurname())
            .build();

        attendances.add(attendance);
    }

    @Test
    public void givenAttendanceEntryEntity_thenMapToAttendanceResponseDto(){
        when(youthMapper.toSimpleResponseDTO(any()))
            .thenReturn(youthSimpleDto);

        AttendanceResponseDto responseDto = attendanceMapper.toAttendanceResponse(attendance);

        assertThat(responseDto.id())
            .isEqualTo(attendance.getId());

        assertThat(responseDto.attendanceStatus())
            .isEqualTo(attendance.getAttendanceStatus());

        assertThat(responseDto.date())
            .isEqualTo(meeting.getDate());

        assertThat(responseDto.youth())
            .isEqualTo(youthSimpleDto);

        assertThat(responseDto.meetingId())
            .isEqualTo(meeting.getId());

        assertThat(responseDto.absenceExcuse())
            .isNull();
    }

    @Test
    public void givenAttendanceEntriesList_thenMapToAttendanceResponseDtoSet(){

        Youth youth2 = aYouth()
            .withId(2L)
            .withFirstName("Jane")
            .withSurname("Dane")
            .build();

        AttendanceEntry attendance2 = anAttendanceEntry()
            .withId(2L)
            .withYouth(youth2)
            .withStatus(AttendanceStatus.ABSENT)
            .withAbsenceExcuse("Some excuse")
            .build();

        YouthSimpleDto youthSimpleDto2 = anYouthSimpleDto()
            .withId(youth2.getId())
            .withFirstName(youth2.getFirstName())
            .withSurname(youth2.getSurname())
            .build();

        meeting.addAttendanceEntries(attendance2);
        attendances.add(attendance2);

        when(youthMapper.toSimpleResponseDTO(youth))
            .thenReturn(youthSimpleDto);
        when(youthMapper.toSimpleResponseDTO(youth2))
            .thenReturn(youthSimpleDto2);

        Set<AttendanceResponseDto> mappedAttendances = attendanceMapper.toResponseListDto(attendances);

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
}
