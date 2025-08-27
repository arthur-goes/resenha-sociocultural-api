package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeCreateDto;
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

    Set<AttendanceCreateDto> attendanceList,
    Set<StrikeCreateDto> strikes,
    Set<ParticipationPointCreateDto> participationPoints
) {
}
