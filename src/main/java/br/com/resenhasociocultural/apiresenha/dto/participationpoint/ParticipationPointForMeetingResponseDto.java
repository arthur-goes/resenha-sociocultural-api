package br.com.resenhasociocultural.apiresenha.dto.participationpoint;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthSimpleDto;

public record ParticipationPointForMeetingResponseDto(
    Long id,
    YouthSimpleDto youth,
    int amount,
    String reason,
    boolean active
) {
}
