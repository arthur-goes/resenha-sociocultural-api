package br.com.resenhasociocultural.apiresenha.dto.youth;

public record YouthSimpleDto(
        Long id,
        String firstName,
        String surname
) implements YouthView {}
