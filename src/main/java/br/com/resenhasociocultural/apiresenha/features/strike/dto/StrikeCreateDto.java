package br.com.resenhasociocultural.apiresenha.features.strike.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StrikeCreateDto(
    Long id,
    Long meetingId,
    YouthSimpleDto youth,
    @NotBlank
    int amount,
    String reason,
    @NotNull
    boolean active
) implements YouthEntryDto {};
