package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthCreateDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface YouthMapper {
    YouthResponseDto toResponseDto(Youth youth);
    List<YouthResponseDto> toResponseDtoList(List<Youth> youths);

    YouthSimpleDto toSimpleResponseDto(Youth youth);
    List<YouthSimpleDto> toSimpleResponseDtoList(List<Youth> youths);

    Youth toEntity(YouthCreateDto youthCreateDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateYouthFromDto(YouthUpdateDto updateDto, @MappingTarget Youth youth);
}
