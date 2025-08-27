package br.com.resenhasociocultural.apiresenha.features.strike.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;

public record StrikeResponseDto(
    Long id,
    YouthSimpleDto youth,
    int amount,
    String reason,
    Long meetingId,
    boolean active
) {
}
