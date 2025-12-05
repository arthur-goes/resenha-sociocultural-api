package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;
import jakarta.validation.constraints.NotNull;

public record AttendanceCreate(
    YouthSummary youth,

    @NotNull
    AttendanceStatus attendanceStatus,

    String absenceExcuse
) implements YouthEntryDto {};
