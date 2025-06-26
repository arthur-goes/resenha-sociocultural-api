package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.model.Meeting;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.service.YouthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public abstract class AttendanceMapper {

    @Autowired
    protected YouthService youthService;

    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    public abstract AttendanceResponseDto toAttendanceResponse(Attendance attendance);

    public abstract Set<AttendanceResponseDto> toAttendanceResponseListDto(Set<Attendance> attendance);
    public abstract Set<AttendanceForMeetingResponseDto> toAttendanceForMeetingDto(List<Attendance> attendances);
    public abstract Attendance toEntity(AttendanceCreateDto dto);
    //public abstract Set<Attendance> toEntitySet(Set<AttendanceCreateDto> dtos);

    public Set<Attendance> toEntityWithYouth(Set<AttendanceCreateDto> attendanceCreateDtos){
        Set<Attendance> attendances = attendanceCreateDtos.stream().map((attendanceDto) -> {
            Attendance attendance = this.toEntity(attendanceDto);
            Long youthId = attendanceDto.youthId();
            Youth youth = youthService.findById(youthId);
            attendance.setYouth(youth);
            return attendance;
       }).collect(Collectors.toSet());
        return attendances;
    }
}
