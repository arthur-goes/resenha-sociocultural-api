package br.com.resenhasociocultural.apiresenha.builder;

import br.com.resenhasociocultural.apiresenha.model.Youth;

import java.time.LocalDate;
import java.util.ArrayList;

public class YouthBuilder {

    private Youth youth;

    YouthBuilder(){
        youth = new Youth(
            1L,
            "Ana", "Silva", LocalDate.of(1995, 5, 15), "11111111111",
            "Maria Silva", "João Silva", "Carlos Silva", "Tio", "11987654321",
            null,
            true
        );
    }

    public static YouthBuilder aYouth(){
        return new YouthBuilder();
    }

    public YouthBuilder withId(Long id){
        youth.setId(id);
        return this;
    }

    public YouthBuilder withName(String name){
        youth.setFirstName(name);
        return this;
    }

    public YouthBuilder inactive(){
        youth.setActive(false);
        return this;
    }

    public Youth build(){
        return this.youth;
    }
}