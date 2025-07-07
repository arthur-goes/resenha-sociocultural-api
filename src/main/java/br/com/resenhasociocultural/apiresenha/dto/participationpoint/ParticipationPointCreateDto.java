package br.com.resenhasociocultural.apiresenha.dto.participationpoint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParticipationPointCreateDto(
    Long meetingId,
    @NotNull
    Long youthId,
    @NotNull
    int amount,
    @NotBlank
    String reason,
    boolean active
) {
}
