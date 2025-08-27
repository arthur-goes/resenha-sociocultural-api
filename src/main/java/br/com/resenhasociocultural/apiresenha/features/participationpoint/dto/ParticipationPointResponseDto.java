package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;

public record ParticipationPointResponseDto(
    Long id,
    Long meetingId,
    YouthSimpleDto youth,
    int amount,
    String reason,
    boolean active
) {
}
