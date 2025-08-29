package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import java.time.LocalDate;

public record AttendanceFilterDto(
    String youthName,
    LocalDate date,
    LocalDate initialDate,
    LocalDate finalDate
) {
}
