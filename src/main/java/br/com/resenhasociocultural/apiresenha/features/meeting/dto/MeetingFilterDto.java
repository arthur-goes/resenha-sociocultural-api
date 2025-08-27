package br.com.resenhasociocultural.apiresenha.features.meeting.dto;


import java.time.LocalDate;

public record MeetingFilterDto(
    String theme,
    LocalDate initialDate,
    LocalDate finalDate,
    LocalDate date
) {
}
