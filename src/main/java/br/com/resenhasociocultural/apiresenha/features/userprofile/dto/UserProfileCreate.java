package br.com.resenhasociocultural.apiresenha.features.userprofile.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileCreate(
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
