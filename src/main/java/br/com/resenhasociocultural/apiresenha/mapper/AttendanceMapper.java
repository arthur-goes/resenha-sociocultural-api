package br.com.resenhasociocultural.apiresenha.mapper;

import br.com.resenhasociocultural.apiresenha.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface AttendanceMapper {
    @Mapping(source = "meeting.id", target = "meetingId")
    AttendanceResponseDto toAttendanceResponse(Attendance attendance);

    List<AttendanceResponseDto> toAttendanceResponseList(List<Attendance> attendance);
}
