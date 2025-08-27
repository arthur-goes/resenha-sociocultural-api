package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;

public record ParticipationPointForMeetingResponseDto(
    Long id,
    YouthSimpleDto youth,
    int amount,
    String reason,
    boolean active
) {
}
