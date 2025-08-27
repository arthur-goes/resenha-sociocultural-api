package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
