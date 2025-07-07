package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.model.Youth;
import br.com.resenhasociocultural.apiresenha.service.YouthService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public abstract class AttendanceMapper {

    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    public abstract AttendanceResponseDto toAttendanceResponse(Attendance attendance);

    public abstract Set<AttendanceResponseDto> toResponseListDto(Set<Attendance> attendance);
    public abstract Set<AttendanceForMeetingResponseDto> toAttendanceForMeetingDto(List<Attendance> attendances);
    public abstract Attendance toEntity(AttendanceCreateDto dto);
    //public abstract Set<Attendance> toEntitySet(Set<AttendanceCreateDto> dtos);

    public Set<Attendance> toEntityWithYouth(Set<AttendanceCreateDto> attendanceCreateDtos, @Context YouthService youthService){
        return attendanceCreateDtos.stream().map((attendanceDto) -> {
            Attendance attendance = this.toEntity(attendanceDto);
            Youth youth = youthService.findById(attendanceDto.youthId());
            attendance.setYouth(youth);
            return attendance;
       }).collect(Collectors.toSet());
    }
}
