package br.com.resenhasociocultural.apiresenha.features.strike.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;

import java.time.LocalDate;

public record StrikeResponseDto(
    Long id,
    LocalDate date,
    YouthSimpleDto youth,
    int amount,
    String reason,
    Long meetingId,
    boolean active
) {
}
