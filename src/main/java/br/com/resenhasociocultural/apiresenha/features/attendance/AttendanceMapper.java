package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreate;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceResponse;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthEntryMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = {YouthMapper.class})
public interface AttendanceMapper extends YouthEntryMapper<Attendance, AttendanceCreate> {

    List<AttendanceResponse> toResponseListDto(Set<Attendance> attendance);
    List<AttendanceResponse> toResponseListDto(List<Attendance> attendance);

    @Mapping(source = "meeting.id", target = "meetingId")
    @Mapping(source = "meeting.date", target = "date")
    AttendanceResponse toResponseDto(Attendance attendance);

    List<AttendanceCreate> toCreateDtoList(Set<Attendance> attendances);

}