package br.com.resenhasociocultural.apiresenha.builder;

import br.com.resenhasociocultural.apiresenha.dto.youth.YouthUpdateAdminDto;

public class YouthUpdateDtoBuilder {
    private Long id;
    private String name;
    private String surname;

    private YouthUpdateDtoBuilder(){
        this.id = 1L;
        this.name = "Sample name";
        this.surname = "Sample surname";
    }

    public static YouthUpdateDtoBuilder aYouthUpdateDto(){
        return new YouthUpdateDtoBuilder();
    }

    public YouthUpdateDtoBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public YouthUpdateDtoBuilder withName(String name){
        this.name = name;
        return this;
    }

    public YouthUpdateDtoBuilder withSurname(String surname){
        this.surname = surname;
        return this;
    }

    public YouthUpdateAdminDto build(){
        return new YouthUpdateAdminDto(
            this.id,
            this.name,
            this.surname,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        );
    }
}
