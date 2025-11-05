package br.com.resenhasociocultural.apiresenha.features.youth.builder;

import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

import java.time.LocalDate;

public class YouthBuilder {

    private Long id = 1L;
    private String firstName = "John";
    private String surname = "Doe";
    private LocalDate birthDate = LocalDate.of(2005, 5, 10);
    private String cpf = "12345678901";
    private String motherName = "Default Mother";
    private String fatherName = "Default Father";
    private String emergencyContactName = "Default Emergency Contact";
    private String emergencyContactRelationship = "Default Emergency Contact Relationship";
    private String emergencyContactPhone = "0123456789";
    private LocalDate creationDate = LocalDate.now();
    private boolean active = true;

    public static YouthBuilder aYouth() {
        return new YouthBuilder();
    }

    public YouthBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public YouthBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public YouthBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public YouthBuilder withBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public YouthBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public YouthBuilder withMotherName(String motherName) {
        this.motherName = motherName;
        return this;
    }

    public YouthBuilder withFatherName(String fatherName) {
        this.motherName = fatherName;
        return this;
    }

    public YouthBuilder withEmergencyContactPhone(String phone) {
        this.emergencyContactPhone = phone;
        return this;
    }

    public YouthBuilder inactive() {
        this.active = false;
        return this;
    }

    public YouthBuilder active() {
        this.active = true;
        return this;
    }

    public YouthBuilder withoutId() {
        this.id = null;
        return this;
    }

    public Youth build() {
        return new Youth(
            id,
            firstName,
            surname,
            birthDate,
            cpf,
            motherName,
            fatherName,
            emergencyContactName,
            emergencyContactRelationship,
            emergencyContactPhone,
            creationDate,
            active
        );
    }
}
