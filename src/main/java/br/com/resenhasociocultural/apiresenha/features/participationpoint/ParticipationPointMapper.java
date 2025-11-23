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
public interface ParticipationPointMapper extends YouthEntryMapper<ParticipationPointEntry, ParticipationPointCreateDto> {
    @Mapping(source = "meeting.id", target = "meetingId")
    ParticipationPointResponseDto toResponseDto(ParticipationPointEntry entity);

    Set<ParticipationPointResponseDto> toResponseDtoList(Set<ParticipationPointEntry> participationPoints);
    List<ParticipationPointResponseDto> toResponseDtoList(List<ParticipationPointEntry> participationPoints);
}
