package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.strike.StrikeCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.strike.StrikeResponseDto;
import br.com.resenhasociocultural.apiresenha.model.Strike;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.service.YouthService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public abstract class StrikeMapper {
    public abstract Strike toEntity(StrikeCreateDto dto);
    public abstract Set<StrikeResponseDto> toResponseDto(Set<Strike> strikes);

    public Set<Strike> toEntityWithYouth(Set<StrikeCreateDto> dtos, @Context YouthService youthService){
        return dtos.stream().map((strikeDto) -> {
            Youth youth = youthService.findById(strikeDto.youthId());
            Strike strike = toEntity(strikeDto);
            strike.setYouth(youth);
            return strike;
        }).collect(Collectors.toSet());
    };
}
