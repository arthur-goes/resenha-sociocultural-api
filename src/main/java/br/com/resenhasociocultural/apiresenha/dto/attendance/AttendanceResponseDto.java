package br.com.resenhasociocultural.apiresenha.dto.attendance;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;
import br.com.resenhasociocultural.apiresenha.enums.AttendanceStatus;

import java.time.LocalDate;

public record AttendanceResponseDto (
    Long id,
    Long meetingId,
    YouthSimpleDto youth,
    LocalDate date,
    AttendanceStatus attendanceStatus,
    String absenceExcuse
){};

