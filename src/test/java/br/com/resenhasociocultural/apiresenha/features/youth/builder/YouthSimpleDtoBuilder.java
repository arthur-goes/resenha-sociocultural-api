package br.com.resenhasociocultural.apiresenha.features.youth.builder;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;

public final class YouthSimpleDtoBuilder {
    private Long id = 1L;
    private String firstName = "John";
    private String surname = "Doe";

    private YouthSimpleDtoBuilder() {
    }

    public static YouthSimpleDtoBuilder aYouthSimpleDto() {
        return new YouthSimpleDtoBuilder();
    }

    public YouthSimpleDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public YouthSimpleDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public YouthSimpleDtoBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public YouthSummary build() {
        return new YouthSummary(id, firstName, surname);
    }
}
