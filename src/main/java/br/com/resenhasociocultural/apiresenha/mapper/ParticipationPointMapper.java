package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.participationpoint.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.model.ParticipationPoint;
import org.mapstruct.Mapper;

@Mapper
public interface ParticipationPointMapper {
    ParticipationPoint toEntity(ParticipationPointCreateDto dto);
}
