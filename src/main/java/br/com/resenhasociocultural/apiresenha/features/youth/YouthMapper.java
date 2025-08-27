package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthCreateDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthUpdateAdminDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface YouthMapper {
    YouthResponseDto youthToResponseDTO(Youth youth);

    YouthSimpleDto youthToSimpleResponseDTO(Youth youth);

    Youth youthCreateDtoToEntity(YouthCreateDto youthCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateYouthFromDto(YouthUpdateAdminDto youthUpdateAdminDto, @MappingTarget Youth youth);
}
