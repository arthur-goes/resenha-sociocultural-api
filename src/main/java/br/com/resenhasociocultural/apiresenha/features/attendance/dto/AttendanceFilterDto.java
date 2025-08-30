package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AttendanceFilterDto(
    String youthName,

    @PastOrPresent
    LocalDate date,

    @PastOrPresent
    LocalDate initialDate,

    @PastOrPresent
    LocalDate finalDate
) {
}
