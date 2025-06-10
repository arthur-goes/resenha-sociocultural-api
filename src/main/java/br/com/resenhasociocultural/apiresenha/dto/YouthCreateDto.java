package br.com.resenhasociocultural.apiresenha.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record YouthCreateDto(
        @NotBlank
        String firstName,
        String surname,
        @Past
        LocalDate birthDate,
        @CPF
        String cpf,
        String motherName,
        String fatherName,
        String emergencyContactName,
        String emergencyContactRelationship,
        String emergencyContactPhone
) implements YouthView {
}
