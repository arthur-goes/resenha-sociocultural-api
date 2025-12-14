package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;

import java.time.LocalDate;

public record ParticipationPointResponse(
    Long id,
    LocalDate date,
    Long meetingId,
    YouthSummary youth,
    int amount,
    String reason,
    boolean active
) {
}
