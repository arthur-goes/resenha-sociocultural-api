package br.com.resenhasociocultural.apiresenha.dto.meeting;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.participationpoint.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.strike.StrikeCreateDto;
import br.com.resenhasociocultural.apiresenha.model.Strike;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Set;

public record MeetingCreateDto(
    @PastOrPresent(message = "A data do encontro não pode ser futura")
    @NotNull(message = "A data precisa ser preenchida")
    LocalDate date,

    @NotBlank(message = "O Thema precisa ser fornecido")
    String theme,

    String minutosDeSabedoriaLesson,

    Set<AttendanceCreateDto> attendances
    //Set<StrikeCreateDto> strikes,
    //Set<ParticipationPointCreateDto> participationPoints
) {
}
