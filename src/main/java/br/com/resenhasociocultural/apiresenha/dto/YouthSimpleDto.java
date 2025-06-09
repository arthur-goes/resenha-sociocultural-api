package br.com.resenhasociocultural.apiresenha.dto;

public record YouthSimpleDto(
        Long id,
        String firstName,
        String Surname
) implements YouthView {}
