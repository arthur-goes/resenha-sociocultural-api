package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;

public record AttendanceForMeetingResponse(
    Long id,
    YouthSummary youth,
    AttendanceStatus attendanceStatus,
    String absenceExcuse
){};

