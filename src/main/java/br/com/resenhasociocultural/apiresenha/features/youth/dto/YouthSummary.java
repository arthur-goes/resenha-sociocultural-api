package br.com.resenhasociocultural.apiresenha.features.youth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record YouthSummary(
    @NotNull
    Long id,

    @NotBlank
    String firstName,

    @NotBlank
    String surname
) implements YouthView {}
