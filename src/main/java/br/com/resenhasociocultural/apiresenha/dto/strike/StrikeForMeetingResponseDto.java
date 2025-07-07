package br.com.resenhasociocultural.apiresenha.dto.strike;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;

public record StrikeForMeetingResponseDto(
    Long id,
    YouthSimpleDto youth,
    int amount,
    String reason,
    boolean active
) {
}
