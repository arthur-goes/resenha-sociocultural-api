package br.com.resenhasociocultural.apiresenha.features.role.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleCreate(
    @NotBlank
    String name
) {
}
