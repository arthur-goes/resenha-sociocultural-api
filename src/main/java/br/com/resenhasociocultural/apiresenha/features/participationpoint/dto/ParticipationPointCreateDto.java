package br.com.resenhasociocultural.apiresenha.features.participationpoint.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParticipationPointCreateDto(
    Long id,
    Long meetingId,
    @NotNull
    Long youthId,
    @NotBlank
    String youthFirstName,
    @NotBlank
    String youthSurname,
    @NotNull
    int amount,
    @NotBlank
    String reason,
    boolean active
)
implements YouthEntryDto {};
