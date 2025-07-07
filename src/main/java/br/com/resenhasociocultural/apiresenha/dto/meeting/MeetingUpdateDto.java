package br.com.resenhasociocultural.apiresenha.dto.meeting;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record MeetingUpdateDto(
    @NotNull
    Long id,
    String theme,
    @PastOrPresent
    LocalDate date,
    String minutosDeSabedoriaLesson
) {
}
