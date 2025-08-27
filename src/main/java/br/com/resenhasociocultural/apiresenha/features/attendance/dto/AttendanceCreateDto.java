package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

public record AttendanceCreateDto(
    Long meetingId,
    @NotNull
    Long youthId,
    @NotNull
    AttendanceStatus attendanceStatus,
    String absenceExcuse
) {
}
