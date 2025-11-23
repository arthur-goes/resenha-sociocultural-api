package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MeetingTest {

    private Meeting meeting;
    private Youth youth;

    @BeforeEach
    public void setUp(){
        Meeting meeting = new Meeting();
        meeting.setId(1L);
        meeting.setDate(LocalDate.now());
        meeting.setTheme("Test");
        meeting.setMinutosDeSabedoriaLesson("Test Lesson");
        this.meeting = meeting;

        Youth youth = new Youth();
        youth.setActive(true);
        youth.setId(1L);
        youth.setCpf("11111111111");
        youth.setFirstName("Youth");
        youth.setSurname("Test");

        this.youth = youth;
    }

    @Test
    @DisplayName("Should not allow multiple Attendances for same Youth, even if Attendance IDs are distinct")
    public void shouldNotAllowMultipleAttendancesForSameYouthInMeeting(){
        Attendance attendance1 = new Attendance();
        attendance1.setId(1L);
        attendance1.setAttendanceStatus(AttendanceStatus.PRESENT);
        attendance1.setYouth(this.youth);

        Attendance attendance2 = new Attendance();
        attendance2.setId(2L);
        attendance2.setAttendanceStatus(AttendanceStatus.ABSENT);
        attendance2.setYouth(this.youth);

        this.meeting.addAttendances(attendance1);
        this.meeting.addAttendances(attendance2);

        Set<Attendance> attendanceEntries = this.meeting.getAttendanceEntries();

        assertThat(attendanceEntries.size()).isEqualTo(1);

        attendanceEntries.stream().forEach((attendanceEntry) -> {
            assertThat(attendanceEntry.getId()).isEqualTo(1L);
        });
    }

    @Test
    public void shouldNotAllowDuplicatedStrikeInMeeting(){
        Strike strike = new Strike();
        strike.setId(1L);
        strike.setAmount(1);
        strike.setYouth(this.youth);
        strike.setReason("Any Reason");

        this.meeting.addStrikes(strike);
        this.meeting.addStrikes(strike);

        Set<Strike> strikeEntries = this.meeting.getStrikeEntries();

        assertThat(strikeEntries.size()).isEqualTo(1);
    }

    @Test
    public void shouldAllowTwoDifferentStrikesForSameYouth(){
        Strike strike1 = new Strike();
        strike1.setId(1L);
        strike1.setAmount(1);
        strike1.setYouth(this.youth);
        strike1.setReason("Any Reason 1");

        Strike strike2 = new Strike();
        strike2.setId(2L);
        strike2.setAmount(1);
        strike2.setYouth(this.youth);
        strike2.setReason("Any Reason 2");

        this.meeting.addStrikes(strike1);
        this.meeting.addStrikes(strike2);

        Set<Strike> strikeEntries = this.meeting.getStrikeEntries();

        assertThat(strikeEntries.size()).isEqualTo(2);
    }

    @Test
    public void shouldNotAllowDuplicatedParticipationPointInMeeting(){
        ParticipationPoint participation = new ParticipationPoint();
        participation.setId(1L);
        participation.setAmount(1);
        participation.setYouth(this.youth);
        participation.setReason("Any Reason");

        this.meeting.addParticipationPoints(participation);
        this.meeting.addParticipationPoints(participation);

        Set<ParticipationPoint> participationPointEntries = this.meeting.getParticipationPointEntries();

        assertThat(participationPointEntries.size()).isEqualTo(1);
    }

    @Test
    public void shouldAllowTwoDifferentParticipationPointForSameYouth(){
        ParticipationPoint participation1 = new ParticipationPoint();
        participation1.setId(1L);
        participation1.setAmount(1);
        participation1.setYouth(this.youth);
        participation1.setReason("Any Reason 1");

        ParticipationPoint participation2 = new ParticipationPoint();
        participation2.setId(2L);
        participation2.setAmount(1);
        participation2.setYouth(this.youth);
        participation2.setReason("Any Reason 2");

        this.meeting.addParticipationPoints(participation1);
        this.meeting.addParticipationPoints(participation2);
        Set<ParticipationPoint> participationPointEntries = this.meeting.getParticipationPointEntries();

        assertThat(participationPointEntries.size()).isEqualTo(2);
    }
}
