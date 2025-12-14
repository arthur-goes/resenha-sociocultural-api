package br.com.resenhasociocultural.apiresenha.features.strike.dto;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record StrikeCreate(
    YouthSummary youth,
    @NotBlank
    int amount,
    String reason,
    @NotNull
    boolean active
) implements YouthEntryDto {};
