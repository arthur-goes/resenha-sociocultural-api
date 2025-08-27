package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;

public record AttendanceForMeetingResponseDto(
    Long id,
    YouthSimpleDto youth,
    AttendanceStatus attendanceStatus,
    String absenceExcuse
){};

