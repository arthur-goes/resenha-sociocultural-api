package br.com.resenhasociocultural.apiresenha.dto.participationpoint;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;

public record ParticipationPointResponseDto(
    Long id,
    Long meetingId,
    YouthSimpleDto youth,
    int amount,
    String reason,
    boolean active
) {
}
