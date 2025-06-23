package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.model.Meeting;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AttendanceMapper.class})
public interface MeetingMapper {
    MeetingResponseDto meetingToResponseDto(Meeting meeting);
    List<MeetingResponseDto> meetingListToResponseDot(List<Meeting> meetings);
    Meeting toEntity(MeetingCreateDto meetingCreateDto);
}
