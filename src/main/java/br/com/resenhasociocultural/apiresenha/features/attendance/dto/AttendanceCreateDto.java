package br.com.resenhasociocultural.apiresenha.features.attendance.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthEntryDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import jakarta.validation.constraints.NotNull;

public record AttendanceCreateDto(
    Long meetingId,

    @NotNull
    Long youthId,

    @NotNull
    String youthFirstName,

    @NotNull
    String youthSurname,

    @NotNull
    AttendanceStatus attendanceStatus,

    String absenceExcuse
)
implements YouthEntryDto {
};
