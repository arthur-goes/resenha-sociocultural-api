package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.YouthCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.YouthResponseDto;
import br.com.resenhasociocultural.apiresenha.dto.YouthSimpleDto;
import br.com.resenhasociocultural.apiresenha.dto.YouthUpdateAdminDto;
import br.com.resenhasociocultural.apiresenha.model.Youth;
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
