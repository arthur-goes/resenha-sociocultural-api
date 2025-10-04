package br.com.resenhasociocultural.apiresenha.features.userprofile.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileCreateDto(
    @NotBlank
    String username,

    @NotBlank
    String password,

    @NotBlank
    String firstName,

    @NotBlank
    String surname
) {
}
