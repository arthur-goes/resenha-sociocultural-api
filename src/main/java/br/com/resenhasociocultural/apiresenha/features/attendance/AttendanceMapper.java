package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface AttendanceMapper extends YouthEntryMapper<Attendance, AttendanceCreateDto> {

    List<AttendanceResponseDto> toResponseListDto(Set<Attendance> attendance);
    List<AttendanceResponseDto> toResponseListDto(List<Attendance> attendance);

    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    AttendanceResponseDto toResponseDto(Attendance attendance);

    List<AttendanceCreateDto> toCreateDtoList(Set<Attendance> attendances);

}