package br.com.resenhasociocultural.apiresenha.features.youth.dto;

import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record YouthUpdate(
        String firstName,
        String surname,
        LocalDate birthDate,
        @CPF
        String cpf,
        String motherName,
        String fatherName,
        String emergencyContactName,
        String emergencyContactRelationship,
        String emergencyContactPhone
) {
}
