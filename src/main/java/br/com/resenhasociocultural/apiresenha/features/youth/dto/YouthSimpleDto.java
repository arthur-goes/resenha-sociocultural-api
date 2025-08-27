package br.com.resenhasociocultural.apiresenha.features.youth.dto;

public record YouthSimpleDto(
        Long id,
        String firstName,
        String surname
) implements YouthView {}
