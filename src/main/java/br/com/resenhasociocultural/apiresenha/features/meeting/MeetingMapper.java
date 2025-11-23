package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceMapper;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPointMapper;
import br.com.resenhasociocultural.apiresenha.features.strike.StrikeMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(
    componentModel = "spring",
    uses = {AttendanceMapper.class, StrikeMapper.class, ParticipationPointMapper.class},
    collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MeetingMapper {
    MeetingCreateDto toCreateDto(Meeting meeting);

    MeetingResponseDto toResponseDto(Meeting meeting);
    List<MeetingResponseDto> toResponseDtoList(List<Meeting> meetings);

    Meeting toEntity(MeetingCreateDto dto);
    Meeting toEntity(MeetingUpdateDto dto);
    Meeting toEntity(MeetingResponseDto dto);
}
