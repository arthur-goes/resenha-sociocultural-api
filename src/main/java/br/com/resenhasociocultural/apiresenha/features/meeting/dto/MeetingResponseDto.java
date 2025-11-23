package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointResponseDto;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeResponseDto;

import java.time.LocalDate;
import java.util.List;

public record MeetingResponseDto(
    Long id,
    LocalDate date,
    String theme,
    String minutosDeSabedoriaLesson,
    List<AttendanceResponseDto> attendanceEntries,
    List<StrikeResponseDto> strikeEntries,
    List<ParticipationPointResponseDto> participationPointEntries
) {

}
