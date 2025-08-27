package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeForMeetingResponseDto;

import java.time.LocalDate;
import java.util.Set;

public record MeetingResponseDto(
    Long id,
    LocalDate date,
    String theme,
    String minutosDeSabedoriaLesson,
    Set<AttendanceForMeetingResponseDto> attendanceList,
    Set<StrikeForMeetingResponseDto> strikes,
    Set<ParticipationPointForMeetingResponseDto> participationPoints
) {

}
