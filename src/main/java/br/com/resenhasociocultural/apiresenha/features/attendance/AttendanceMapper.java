package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.MeetingChildCollectionMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface AttendanceMapper extends MeetingChildCollectionMapper<Attendance, AttendanceCreateDto> {

    Set<AttendanceResponseDto> toResponseListDto(Set<Attendance> attendance);
    Set<AttendanceForMeetingResponseDto> toAttendanceForMeetingDto(List<Attendance> attendances);

    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    AttendanceResponseDto toAttendanceResponse(Attendance attendance);

}
