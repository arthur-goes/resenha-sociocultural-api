package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.utils.CPFGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static br.com.resenhasociocultural.apiresenha.features.attendance.builder.AttendanceBuilder.anAttendance;
import static br.com.resenhasociocultural.apiresenha.features.meeting.builder.MeetingBuilder.aMeeting;
import static br.com.resenhasociocultural.apiresenha.features.participationpoint.builder.ParticipationPointBuilder.aParticipationPoint;
import static br.com.resenhasociocultural.apiresenha.features.strike.builder.StrikeBuilder.aStrike;
import static br.com.resenhasociocultural.apiresenha.features.youth.builder.YouthBuilder.aYouth;
import static org.assertj.core.api.Assertions.assertThat;

public class MeetingTest {

    private Meeting meeting;
    private Youth youth;

    @BeforeEach
    public void setUp(){
        meeting = aMeeting()
            .withId(1L)
            .withDate(LocalDate.now())
            .withTheme("Test Theme")
            .withMinutosDeSabedoriaLesson("Test Lesson")
            .build();

        youth = aYouth()
            .withId(1L)
            .withFirstName("Test fisrstName")
            .withSurname("Test surname")
            .withCpf(CPFGenerator.generate())
            .active()
            .build();

        this.youth = youth;
    }

    @Test
    public void shouldNotAllowMultipleAttendancesForSameYouthInMeeting(){

        Attendance attendance1 = anAttendance()
            .withoutId()
            .withStatus(AttendanceStatus.PRESENT)
            .withYouth(youth)
            .build();

        Attendance attendance2 = anAttendance()
            .withoutId()
            .withStatus(AttendanceStatus.ABSENT)
            .withYouth(youth)
            .build();

        this.meeting.addAttendances(attendance1);
        this.meeting.addAttendances(attendance2);

        Set<Attendance> attendances = this.meeting.getAttendanceEntries();

        assertThat(attendances.size()).isEqualTo(1);

        attendances.forEach((attendanceEntry) -> {
            assertThat(attendanceEntry.getAttendanceStatus()).isEqualTo(attendance1.getAttendanceStatus());
        });
    }

    @Test
    public void shouldNotAllowDuplicatedStrikeInMeeting(){
        Strike strike = aStrike()
            .withoutId()
            .withAmount(1)
            .withYouth(youth)
            .withReason("Test reason")
            .build();

        this.meeting.addStrikes(strike);
        this.meeting.addStrikes(strike);

        Set<Strike> strikeEntries = this.meeting.getStrikeEntries();

        assertThat(strikeEntries.size()).isEqualTo(1);
    }

    @Test
    public void shouldAllowTwoDifferentStrikesForSameYouth(){
        Strike strike1 = aStrike()
            .withoutId()
            .withYouth(youth)
            .withReason("Any Reason 1")
            .withAmount(1)
            .build();

        Strike strike2 = aStrike()
            .withoutId()
            .withYouth(youth)
            .withReason("Any Reason 2")
            .withAmount(1)
            .build();

        this.meeting.addStrikes(strike1);
        this.meeting.addStrikes(strike2);

        Set<Strike> strikeEntries = this.meeting.getStrikeEntries();

        assertThat(strikeEntries.size()).isEqualTo(2);
    }

    @Test
    public void shouldNotAllowDuplicatedParticipationPointInMeeting(){
        ParticipationPoint participation = aParticipationPoint()
            .withoutId()
            .withAmount(1)
            .withYouth(youth)
            .withReason("Test reason")
            .build();

        this.meeting.addParticipationPoints(participation);
        this.meeting.addParticipationPoints(participation);

        Set<ParticipationPoint> participationPointEntries = this.meeting.getParticipationPointEntries();

        assertThat(participationPointEntries.size()).isEqualTo(1);
    }

    @Test
    public void shouldAllowTwoDifferentParticipationPointForSameYouth(){
        ParticipationPoint participation1 = aParticipationPoint()
            .withoutId()
            .withYouth(youth)
            .withReason("Any Reason 1")
            .withAmount(1)
            .build();

        ParticipationPoint participation2 = aParticipationPoint()
            .withoutId()
            .withYouth(youth)
            .withReason("Any Reason 2")
            .withAmount(1)
            .build();

        this.meeting.addParticipationPoints(participation1);
        this.meeting.addParticipationPoints(participation2);
        Set<ParticipationPoint> participationPointEntries = this.meeting.getParticipationPointEntries();

        assertThat(participationPointEntries.size()).isEqualTo(2);
    }
}
