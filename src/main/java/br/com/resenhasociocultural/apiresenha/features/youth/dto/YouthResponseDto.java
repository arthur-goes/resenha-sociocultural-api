package br.com.resenhasociocultural.apiresenha.features.youth.dto;

import java.time.LocalDate;

public record YouthResponseDto(
        Long id,
        String firstName,
        String surname,
        LocalDate birthDate,
        String cpf,
        String motherName,
        String fatherName,
        String emergencyContactName,
        String emergencyContactRelationship,
        String emergencyContactPhone,
        LocalDate creationDate
) implements YouthView {
}
