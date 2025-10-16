package br.com.resenhasociocultural.apiresenha.features.youth;

import java.time.LocalDate;

public class YouthBuilder {

    private Long id = 1L;
    private String firstName = "Fulano";
    private String surname = "de Tal";
    private LocalDate birthDate = LocalDate.of(2005, 5, 10);
    private String cpf = "12345678901";
    private String motherName = "Maria de Tal";
    private String fatherName = "João de Tal";
    private String emergencyContactName = "Maria de Tal";
    private String emergencyContactRelationship = "Mãe";
    private String emergencyContactPhone = "11987654321";
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
