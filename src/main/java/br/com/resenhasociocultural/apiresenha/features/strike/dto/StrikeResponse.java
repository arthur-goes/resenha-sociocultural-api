package br.com.resenhasociocultural.apiresenha.features.strike.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;

import java.time.LocalDate;

public record StrikeResponse(
    Long id,
    LocalDate date,
    YouthSummary youth,
    int amount,
    String reason,
    Long meetingId,
    boolean active
) {
}
