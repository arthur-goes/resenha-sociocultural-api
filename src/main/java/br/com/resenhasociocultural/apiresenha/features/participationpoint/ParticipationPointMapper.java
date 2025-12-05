package br.com.resenhasociocultural.apiresenha.features.participationpoint;

import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointCreate;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.dto.ParticipationPointResponse;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface ParticipationPointMapper extends YouthEntryMapper<ParticipationPoint, ParticipationPointCreate> {
    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    ParticipationPointResponse toResponseDto(ParticipationPoint entity);

    Set<ParticipationPointResponse> toResponseDtoList(Set<ParticipationPoint> participationPoints);
    List<ParticipationPointResponse> toResponseDtoList(List<ParticipationPoint> participationPoints);
}
