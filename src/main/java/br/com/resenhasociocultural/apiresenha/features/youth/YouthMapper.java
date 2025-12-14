package br.com.resenhasociocultural.apiresenha.features.youth;

import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthCreate;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthResponse;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthUpdate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface YouthMapper {
    YouthResponse toResponseDto(Youth youth);
    List<YouthResponse> toResponseDtoList(List<Youth> youths);

    YouthSummary toSimpleResponseDto(Youth youth);
    List<YouthSummary> toSimpleResponseDtoList(List<Youth> youths);

    Youth toEntity(YouthCreate youthCreate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateYouthFromDto(YouthUpdate updateDto, @MappingTarget Youth youth);
}
