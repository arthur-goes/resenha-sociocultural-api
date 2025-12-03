package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointCreateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface ParticipationPointMapper extends YouthEntryMapper<ParticipationPoint, ParticipationPointCreateDto> {
    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    ParticipationPointResponseDto toResponseDto(ParticipationPoint entity);

    Set<ParticipationPointResponseDto> toResponseDtoList(Set<ParticipationPoint> participationPoints);
    List<ParticipationPointResponseDto> toResponseDtoList(List<ParticipationPoint> participationPoints);
}
