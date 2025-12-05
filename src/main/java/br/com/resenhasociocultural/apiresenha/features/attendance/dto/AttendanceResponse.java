package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;

import java.time.LocalDate;

public record AttendanceResponse(
    Long id,
    Long meetingId,
    YouthSummary youth,
    LocalDate date,
    AttendanceStatus attendanceStatus,
    String absenceExcuse
){};

