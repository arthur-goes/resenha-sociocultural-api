package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;

import java.time.LocalDate;

public record AttendanceResponseDto (
    Long id,
    Long meetingId,
    YouthSimpleDto youth,
    LocalDate date,
    AttendanceStatus attendanceStatus,
    String absenceExcuse
){};

