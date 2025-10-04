package br.com.resenhasociocultural.apiresenha.features.userprofile.dto;

public record UserProfileUpdateDto(
    Long id,
    String username,
    String password,
    String firstName,
    String surname
) {
    public UserProfileUpdateDto withId(Long id){
        return new UserProfileUpdateDto(id, this.username, this.password, this.firstName, this.surname);
    }
}
