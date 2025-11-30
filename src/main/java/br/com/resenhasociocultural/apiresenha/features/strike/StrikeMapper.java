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
public interface StrikeMapper extends YouthEntryMapper<Strike, StrikeCreateDto> {
    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    StrikeResponseDto toResponseDto(Strike strike);

    Set<StrikeResponseDto> toResponseDtoList(Set<Strike> strikes);
    List<StrikeResponseDto> toResponseDtoList(List<Strike> strikes);

}
