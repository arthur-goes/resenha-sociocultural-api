package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import jakarta.validation.constraints.NotNull;

public record AttendanceUpdateDto(
  @NotNull
  Long id,

  YouthSimpleDto youth,

  @NotNull
  AttendanceStatus attendanceStatus,

  String absenceExcuse
) {
}
