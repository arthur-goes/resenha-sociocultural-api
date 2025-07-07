package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.participationpoint.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.participationpoint.ParticipationPointResponseDto;
import br.com.resenhasociocultural.apiresenha.model.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.service.YouthService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public abstract class ParticipationPointMapper {
    public abstract ParticipationPoint toEntity(ParticipationPointCreateDto dto);
    public abstract ParticipationPoint toResponseDto(ParticipationPointResponseDto dto);

    public Set<ParticipationPoint> toEntityWithYouth(Set<ParticipationPointCreateDto> dtos, @Context YouthService youthService){
        return dtos.stream().map((participationPointDto) -> {
            ParticipationPoint participationPoint = toEntity(participationPointDto);
            Youth youth = youthService.findById(participationPointDto.youthId());
            participationPoint.setYouth(youth);
            return participationPoint;
        }).collect(Collectors.toSet());
    }
}
