package br.com.resenhasociocultural.apiresenha.factories.dto;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;

public record MeetingFactoryResult(
    Meeting meeting,
    AttendanceFactoryResult attendanceResult,
    StrikeFactoryResult strikeResult,
    ParticipationPointFactoryResult participationPointResult
) {
}
