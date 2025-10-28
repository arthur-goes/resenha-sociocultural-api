package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.*;

import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface AttendanceMapper extends YouthEntryMapper<AttendanceEntry, AttendanceCreateDto> {

    Set<AttendanceResponseDto> toResponseListDto(Set<AttendanceEntry> attendance);
    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    AttendanceResponseDto toAttendanceResponse(AttendanceEntry attendance);

}