package br.com.resenhasociocultural.apiresenha.features.userprofile.dto;

import br.com.resenhasociocultural.apiresenha.features.role.dto.RoleResponse;

import java.util.List;

public record UserProfileResponse(
    Long id,
    String username,
    String password,
    String firstName,
    String surname,
    List<RoleResponse> roles
) {
}
