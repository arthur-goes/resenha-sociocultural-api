package br.com.resenhasociocultural.apiresenha.dto.strike;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;

public record StrikeResponseDto(
    Long id,
    YouthSimpleDto youth,
    int amount,
    String reason,
    Long meetingId,
    boolean active
) {
}
