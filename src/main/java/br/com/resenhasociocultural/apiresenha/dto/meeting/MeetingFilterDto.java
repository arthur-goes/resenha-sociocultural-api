package br.com.resenhasociocultural.apiresenha.dto.meeting;


import java.time.LocalDate;

public record MeetingFilterDto(
    String theme,
    LocalDate initialDate,
    LocalDate finalDate,
    LocalDate date
) {
}
