package br.com.resenhasociocultural.apiresenha.features.meeting.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceUpdate;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointUpdate;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.Set;


public record MeetingUpdate(
    Long id,

    @PastOrPresent(message = "A data do encontro não pode ser futura")
    @NotNull(message = "A data precisa ser preenchida")
    LocalDate date,

    @NotBlank(message = "O Tema precisa ser fornecido")
    String theme,

    String minutosDeSabedoriaLesson,

    Set<AttendanceUpdate> attendances,
    Set<StrikeUpdate> strikes,
    Set<ParticipationPointUpdate> participationPoints
){}
