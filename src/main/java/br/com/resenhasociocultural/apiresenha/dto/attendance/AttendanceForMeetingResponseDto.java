package br.com.resenhasociocultural.apiresenha.dto.attendance;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;
import br.com.resenhasociocultural.apiresenha.enums.AttendanceStatus;

public record AttendanceForMeetingResponseDto(
    Long id,
    YouthSimpleDto youth,
    AttendanceStatus attendanceStatus,
    String absenceExcuse
){};

