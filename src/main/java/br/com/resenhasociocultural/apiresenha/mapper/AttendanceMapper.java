package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.service.YouthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public abstract class AttendanceMapper {

    protected YouthService youthService;

    public AttendanceMapper(YouthService youthService) {
        this.youthService = youthService;
    }

    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    public abstract AttendanceResponseDto toAttendanceResponse(Attendance attendance);

    public abstract List<AttendanceResponseDto> toAttendanceResponseListDto(List<Attendance> attendance);
    public abstract List<AttendanceForMeetingResponseDto> toAttendanceForMeetingDto(List<Attendance> attendances);
    public abstract Attendance toEntity(AttendanceCreateDto dto);
}
