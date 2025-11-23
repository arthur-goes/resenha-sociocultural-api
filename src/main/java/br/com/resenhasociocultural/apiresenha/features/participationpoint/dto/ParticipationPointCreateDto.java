package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParticipationPointCreateDto(
    Long id,
    Long meetingId,
    YouthSimpleDto youth,
    @NotNull
    int amount,
    @NotBlank
    String reason,
    boolean active
) implements YouthEntryDto {};
