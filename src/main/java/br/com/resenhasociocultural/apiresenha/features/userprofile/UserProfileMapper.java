package br.com.resenhasociocultural.apiresenha.features.userprofile;

import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileCreate;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileResponse;
import br.com.resenhasociocultural.apiresenha.features.userprofile.dto.UserProfileUpdate;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    @Mapping(target = "password", ignore = true)
    UserProfile toEntity(UserProfileCreate dto);
    UserProfileResponse toDto(UserProfile userProfile);
    List<UserProfileResponse> toResponseDtoList(List<UserProfile> users);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "password", ignore = true)
    UserProfile toUpdatedEntity(UserProfileUpdate dto, @MappingTarget UserProfile userProfileToUpdate);
}
