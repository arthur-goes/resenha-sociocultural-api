package br.com.resenhasociocultural.apiresenha.features.strike.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StrikeCreateDto(
    Long meetingId,
    @NotNull
    Long youthId,
    @NotBlank
    int amount,
    String reason,
    @NotNull
    boolean active
) {
}
