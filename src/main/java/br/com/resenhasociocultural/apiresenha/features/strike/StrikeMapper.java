package br.com.resenhasociocultural.apiresenha.features.strike;

import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeCreateDto;
import br.com.resenhasociocultural.apiresenha.features.strike.dto.StrikeResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
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
