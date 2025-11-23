package br.com.resenhasociocultural.apiresenha.features.meeting.builder;

import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPointEntry;
import br.com.resenhasociocultural.apiresenha.features.strike.StrikeEntry;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class MeetingBuilder {
    private Long id = 1L;
    private LocalDate date = LocalDate.now();
    private String theme = "Standard Theme";
    private String minutosDeSabedoriaLesson = "Standar Lesson";
    private Set<Attendance> attendances = new HashSet<>();
    private Set<StrikeEntry> strikes = new HashSet<>();
    private Set<ParticipationPointEntry> participationPoints = new HashSet<>();

    private Set<Attendance> attendancesToAdd;
    private Set<StrikeEntry> strikesToAdd;
    private Set<ParticipationPointEntry> participationPointsToAdd;

    public static MeetingBuilder aMeeting(){
        return new MeetingBuilder();
    }

    public MeetingBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public MeetingBuilder withoutId(){
        this.id = null;
        return this;
    }

    public MeetingBuilder withDate(LocalDate date){
        this.date = date;
        return this;
    }

    public MeetingBuilder withTheme(String theme){
        this.theme = theme;
        return this;
    }

    public MeetingBuilder withMinutosDeSabedoriaLesson(String lesson){
        this.minutosDeSabedoriaLesson = lesson;
        return this;
    }

    public MeetingBuilder withAttendances(Set<Attendance> attendances){
        this.attendancesToAdd = attendances;
        return this;
    }

    public MeetingBuilder withStrikes(Set<StrikeEntry> strikes){
        this.strikesToAdd = strikes;
        return this;
    }

    public MeetingBuilder withParticipationPoints(Set<ParticipationPointEntry> participationPoints){
        this.participationPointsToAdd = participationPoints;
        return this;
    }

    public Meeting build(){
        var meeting = new Meeting(
            id,
            date,
            theme,
            minutosDeSabedoriaLesson,
            attendances,
            strikes,
            participationPoints
        );

        if (attendancesToAdd != null) attendancesToAdd.forEach(meeting::addAttendanceEntries);
        if (strikesToAdd != null) strikesToAdd.forEach(meeting::addStrikeEntries);
        if (participationPointsToAdd != null) participationPointsToAdd.forEach(meeting::addParticipationPointEntries);

        return meeting;
    }

}
