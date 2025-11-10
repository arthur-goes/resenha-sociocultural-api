package br.com.resenhasociocultural.apiresenha.features.youth.builder;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthResponseDto;

import java.time.LocalDate;

public final class YouthResponseDtoBuilder {
    private Long id = 1L;
    private String firstName = "John";
    private String surname = "Doe";
    private LocalDate birthDate = LocalDate.of(2005,5,5);
    private String cpf = "57469987029";
    private String motherName = "Default Mother";
    private String fatherName = "Default Father";
    private String emergencyContactName = "Default Emergency Contact";
    private String emergencyContactRelationship = "Default Emergency Contact Relationship";
    private String emergencyContactPhone = "0123456789";
    private LocalDate creationDate = LocalDate.now();

    private YouthResponseDtoBuilder() {
    }

    public static YouthResponseDtoBuilder aYouthResponseDto() {
        return new YouthResponseDtoBuilder();
    }

    public YouthResponseDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public YouthResponseDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public YouthResponseDtoBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public YouthResponseDtoBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public YouthResponseDtoBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public YouthResponseDtoBuilder withMotherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public YouthResponseDtoBuilder withFatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public YouthResponseDtoBuilder withEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
        return this;
    }

    public YouthResponseDtoBuilder withEmergencyContactRelationship(String emergencyContactRelationship) {
        this.emergencyContactRelationship = emergencyContactRelationship;
        return this;
    }

    public YouthResponseDtoBuilder withEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
        return this;
    }

    public YouthResponseDtoBuilder withCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public YouthResponseDto build() {
        return new YouthResponseDto(id, firstName, surname, birthDate, cpf, motherName, fatherName, emergencyContactName, emergencyContactRelationship, emergencyContactPhone, creationDate);
    }
}
