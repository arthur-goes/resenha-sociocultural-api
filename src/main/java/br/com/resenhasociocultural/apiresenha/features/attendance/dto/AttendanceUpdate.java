package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;
import jakarta.validation.constraints.NotNull;

public record AttendanceUpdate(
  @NotNull
  Long id,

  YouthSummary youth,

  @NotNull
  AttendanceStatus attendanceStatus,

  String absenceExcuse
) {
}
