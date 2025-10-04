package br.com.resenhasociocultural.apiresenha.features.userprofile;

import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileCreateDto;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileResponseDto;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileUpdateDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "password", ignore = true)
    UserProfile toEntity(UserProfileCreateDto dto);
    UserProfileResponseDto toDto(UserProfile userProfile);
    List<UserProfileResponseDto> toResponseDtoList(List<UserProfile> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    UserProfile toUpdatedEntity(UserProfileUpdateDto dto, @MappingTarget UserProfile userProfileToUpdate);
}
