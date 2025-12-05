package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AttendanceFilter(
    String youthNameSubstring,

    @PastOrPresent
    LocalDate date,

    @PastOrPresent
    LocalDate initialDate,

    @PastOrPresent
    LocalDate finalDate
) {
}
