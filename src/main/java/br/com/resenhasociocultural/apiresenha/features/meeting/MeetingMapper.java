package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceMapper;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPointMapper;
import br.com.resenhasociocultural.apiresenha.features.strike.StrikeMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
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
