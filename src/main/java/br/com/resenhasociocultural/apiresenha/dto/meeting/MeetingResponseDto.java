package br.com.resenhasociocultural.apiresenha.dto.meeting;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.model.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.model.Strike;

import java.time.LocalDate;
import java.util.Set;

public record MeetingResponseDto(
    Long id,
    LocalDate date,
    String theme,
    String minutosDeSabedoriaLesson,
    Set<AttendanceForMeetingResponseDto> attendanceList,
    Set<ParticipationPoint> participationPoints,
    Set<Strike> strikes
) {

}
