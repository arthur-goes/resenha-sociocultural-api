package br.com.resenhasociocultural.apiresenha.features.youth.builder;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthCreateDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public final class YouthCreateDtoBuilder {
    private @NotBlank String firstName = "John";
    private String surname = "Doe";
    private @Past LocalDate birthDate = LocalDate.of(2005,5,5);
    private @CPF String cpf = "57469987029";
    private String motherName = "Default Mother Name";
    private String fatherName = "Default Father Name";
    private String emergencyContactName = "Default Emergency Contact Name";
    private String emergencyContactRelationship = "Default Emergency Contact Relationship";
    private String emergencyContactPhone = "0123456789";

    private YouthCreateDtoBuilder() {
    }

    public static YouthCreateDtoBuilder aYouthCreateDto() {
        return new YouthCreateDtoBuilder();
    }

    public YouthCreateDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public YouthCreateDtoBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public YouthCreateDtoBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public YouthCreateDtoBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public YouthCreateDtoBuilder withMotherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public YouthCreateDtoBuilder withFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public YouthCreateDtoBuilder withEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
        return this;
    }

    public YouthCreateDtoBuilder withEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
        return this;
    }

    public YouthCreateDtoBuilder withEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
        return this;
    }

    public YouthCreateDto build() {
        return new YouthCreateDto(firstName, surname, birthDate, cpf, motherName, fatherName, emergencyContactName, emergencyContactRelationship, emergencyContactPhone);
    }
}
