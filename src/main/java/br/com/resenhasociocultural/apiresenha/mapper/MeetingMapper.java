package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingUpdateDto;
import br.com.resenhasociocultural.apiresenha.model.Meeting;
import br.com.resenhasociocultural.apiresenha.service.YouthService;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AttendanceMapper.class, StrikeMapper.class, ParticipationPointMapper.class})
public interface MeetingMapper {
    MeetingResponseDto toResponseDto(Meeting meeting);
    List<MeetingResponseDto> meetingListToResponseDot(List<Meeting> meetings);

    Meeting toEntity(MeetingCreateDto meetingCreateDto, @Context YouthService youthService);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void toUpdatedEntity(MeetingUpdateDto dto, @MappingTarget Meeting meeting);
}
