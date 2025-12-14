package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreate;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingResponse;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceMapper;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdate;
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
    MeetingCreate toCreateDto(Meeting meeting);
    MeetingUpdate toUpdateDto(Meeting meeting);

    MeetingResponse toResponseDto(Meeting meeting);
    List<MeetingResponse> toResponseDtoList(List<Meeting> meetings);

    Meeting toEntity(MeetingCreate dto);
    Meeting toEntity(MeetingUpdate dto);
    Meeting toEntity(MeetingResponse dto);
}
