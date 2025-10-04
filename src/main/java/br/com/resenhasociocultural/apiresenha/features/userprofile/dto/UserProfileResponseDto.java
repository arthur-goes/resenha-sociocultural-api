package br.com.resenhasociocultural.apiresenha.features.userprofile.dto;

import br.com.resenhasociocultural.apiresenha.features.role.dto.RoleResponseDto;

import java.util.List;
import java.util.Set;

public record UserProfileResponseDto(
    Long id,
    String username,
    String password,
    String firstName,
    String surname,
    List<RoleResponseDto> roles
) {
}
