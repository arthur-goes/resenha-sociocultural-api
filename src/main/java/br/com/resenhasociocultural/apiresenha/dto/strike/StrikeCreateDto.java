package br.com.resenhasociocultural.apiresenha.dto.strike;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;

public record StrikeCreateDto(
    YouthSimpleDto youthSimpleDto,
    int amount,
    String reason,
    Long meetingId,
    boolean active
) {
}
