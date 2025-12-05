package br.com.resenhasociocultural.apiresenha.features.userprofile.dto;

public record UserProfileUpdate(
    Long id,
    String username,
    String password,
    String firstName,
    String surname
) {
    public UserProfileUpdate withId(Long id){
        return new UserProfileUpdate(id, this.username, this.password, this.firstName, this.surname);
    }
}
