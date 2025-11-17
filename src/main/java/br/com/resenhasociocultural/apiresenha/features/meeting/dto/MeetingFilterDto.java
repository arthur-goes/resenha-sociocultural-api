package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record MeetingFilterDto(
    String theme,

    @PastOrPresent
    LocalDate initialDate,

    @PastOrPresent
    LocalDate finalDate,

    @PastOrPresent
    LocalDate date
) {
}
