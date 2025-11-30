package br.com.resenhasociocultural.apiresenha.factories;

import br.com.resenhasociocultural.apiresenha.factories.dto.MeetingFactoryResult;
import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static br.com.resenhasociocultural.apiresenha.features.meeting.builder.MeetingBuilder.aMeeting;

public class MeetingFactory {

    public static MeetingFactoryResult generateCompleteMeeting(List<Youth> youths){
        var attendanceResult = AttendanceFactory.generateAttendances(youths);
        var strikeResult = StrikeFactory.generateStrikes(youths);
        var participationPointResult = ParticipationPointFactory.generateParticipationPoints(youths);

        var attendances = new HashSet<>(attendanceResult.generatedAttendances());
        var strikes = new HashSet<>(strikeResult.generatedStrikes());
        var participationPoints = new HashSet<>(participationPointResult.generatedParticipationPoints());

        Meeting meeting = aMeeting()
            .withoutId()
            .withTheme("Some theme")
            .withDate(LocalDate.now())
            .withAttendances(attendances)
            .withStrikes(strikes)
            .withParticipationPoints(participationPoints)
            .withMinutosDeSabedoriaLesson("Some Lesson")
            .build();

        return new MeetingFactoryResult(
            meeting,
            attendanceResult,
            strikeResult,
            participationPointResult
        );
    }
}
