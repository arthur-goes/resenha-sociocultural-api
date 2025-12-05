package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponse;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointResponse;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeResponse;

import java.time.LocalDate;
import java.util.List;

public record MeetingResponse(
    Long id,
    LocalDate date,
    String theme,
    String minutosDeSabedoriaLesson,
    List<AttendanceResponse> attendances,
    List<StrikeResponse> strikes,
    List<ParticipationPointResponse> participationPoints
) {

}
