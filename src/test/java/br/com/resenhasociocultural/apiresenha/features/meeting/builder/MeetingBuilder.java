package br.com.resenhasociocultural.apiresenha.features.meeting.builder;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceEntry;
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
    private Set<AttendanceEntry> attendances = new HashSet<>();
    private Set<StrikeEntry> strikes = new HashSet<>();
    private Set<ParticipationPointEntry> participationPoints = new HashSet<>();

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

    public Meeting build(){
        return new Meeting(
            id,
            date,
            theme,
            minutosDeSabedoriaLesson,
            attendances,
            strikes,
            participationPoints
        );
    }

}
