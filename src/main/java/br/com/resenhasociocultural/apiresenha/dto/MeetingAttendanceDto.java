package br.com.resenhasociocultural.apiresenha.dto;

public record MeetingAttendanceDto(
    Long attendanceId,
    Long meetingId,
    YouthSimpleDto youth,
    String status
) {
}
