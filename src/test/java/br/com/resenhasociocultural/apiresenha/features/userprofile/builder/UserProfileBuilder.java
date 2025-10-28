package br.com.resenhasociocultural.apiresenha.features.userprofile.builder;

import br.com.resenhasociocultural.apiresenha.features.role.Role;
import br.com.resenhasociocultural.apiresenha.features.userprofile.UserProfile;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public final class UserProfileBuilder {

    private Long id = 1L;
    private String username = "username@username.com";
    private String password = "password";
    private String firstName = "name";
    private String surname = "surname";
    private LocalDate creationDate = LocalDate.now();
    private Set<Role> roles = new HashSet<>();

    private UserProfileBuilder() {
    }

    public static UserProfileBuilder anUserProfile() {
        return new UserProfileBuilder();
    }

    public UserProfileBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserProfileBuilder withUsername(String username) {
        this.username = username;
        return this;
    }

    public UserProfileBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserProfileBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserProfileBuilder withSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public UserProfileBuilder withCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public UserProfileBuilder withRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }

    public UserProfile build() {
        return new UserProfile(
            id,
            username,
            password,
            firstName,
            surname,
            creationDate,
            roles
        );
    }
}