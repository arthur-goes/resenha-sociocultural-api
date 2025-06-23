package br.com.resenhasociocultural.apiresenha.dto.youth;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record YouthUpdateAdminDto(
        @NotNull
        Long id,
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
