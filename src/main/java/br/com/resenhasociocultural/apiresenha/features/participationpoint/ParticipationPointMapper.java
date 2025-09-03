package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface ParticipationPointMapper extends YouthEntryMapper<ParticipationPoint, ParticipationPointCreateDto> {
    ParticipationPointResponseDto toResponseDto(ParticipationPoint entity);
}
