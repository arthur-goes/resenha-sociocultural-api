package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record MeetingFilterDto(
    @Pattern(
        regexp = "^[A-Za-z]([A-Za-z\\s]*[A-Za-z])$",
        message = "O tema pesquisado deve conter somente letras ou espaços, e sempre deve começar e terminar com uma letra"
    )
    String theme,

    @PastOrPresent
    LocalDate initialDate,

    @PastOrPresent
    LocalDate finalDate,

    @PastOrPresent
    LocalDate date
) {
}
