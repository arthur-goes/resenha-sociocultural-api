package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;

import java.time.LocalDate;

public record ParticipationPointResponseDto(
    Long id,
    LocalDate date,
    Long meetingId,
    YouthSimpleDto youth,
    int amount,
    String reason,
    boolean active
) {
}
