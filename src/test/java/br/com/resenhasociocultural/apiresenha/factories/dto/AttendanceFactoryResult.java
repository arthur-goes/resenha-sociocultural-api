package br.com.resenhasociocultural.apiresenha.factories.dto;

import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;

import java.util.List;

public record AttendanceFactoryResult(
    List<Attendance> generatedAttendances,
    List<Attendance> statusPresentAttendances,
    List<Attendance> statusAbsentAttendances,
    List<Attendance> statusExcusedAbsenceAttendances
) {
}
