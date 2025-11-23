package br.com.resenhasociocultural.apiresenha.features.strike;

import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeCreateDto;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface StrikeMapper extends YouthEntryMapper<StrikeEntry, StrikeCreateDto> {
    @Mapping(source = "meeting.id", target = "meetingId")
    StrikeResponseDto toResponseDto(StrikeEntry strike);

    Set<StrikeResponseDto> toResponseDtoList(Set<StrikeEntry> strikes);
    List<StrikeResponseDto> toResponseDtoList(List<StrikeEntry> strikes);

}
