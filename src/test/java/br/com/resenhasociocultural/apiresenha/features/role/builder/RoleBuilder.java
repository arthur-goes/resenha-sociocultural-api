package br.com.resenhasociocultural.apiresenha.features.role.builder;

import br.com.resenhasociocultural.apiresenha.features.role.Role;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfile;

import java.util.HashSet;
import java.util.Set;

public class RoleBuilder {
    private Long id = 1L;
    private String name = "ADMIN";
    private Set<UserProfile> users = new HashSet<>();

    public RoleBuilder(){}

    public static RoleBuilder aRole(){
        return new RoleBuilder();
    }

    public RoleBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public RoleBuilder withoutId(){
        this.id = null;
        return this;
    }

    public RoleBuilder withName(String name){
        this.name = name;
        return this;
    }

    public Role asAdmin(){
        return new RoleBuilder()
            .withId(1L)
            .withName("ADMIN")
            .build();
    }

    public Role asManager(){
        return new RoleBuilder()
            .withId(2L)
            .withName("MANAGER")
            .build();
    }

    public Role asCoordinator(){
        return new RoleBuilder()
            .withId(3L)
            .withName("COORDINATOR")
            .build();
    }

    public Role asUser(){
        return new RoleBuilder()
            .withId(4L)
            .withName("USER")
            .build();
    }

    public Role build(){
        return new Role(
            id,
            name,
            users
        );
    }
}
