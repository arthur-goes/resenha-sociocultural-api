package br.com.resenhasociocultural.apiresenha.dto;

import java.time.LocalDate;
import java.util.Set;

public record MeetingDto(
        Long id,
        LocalDate date,
        String theme,
        Set<MeetingAttendanceDto> presenceList
) {
}
