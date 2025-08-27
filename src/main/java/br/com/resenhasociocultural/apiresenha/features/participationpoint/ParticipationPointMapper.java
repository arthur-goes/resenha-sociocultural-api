package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
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
