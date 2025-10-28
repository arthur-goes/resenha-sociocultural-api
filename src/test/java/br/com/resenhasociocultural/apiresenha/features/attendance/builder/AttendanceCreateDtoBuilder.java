package br.com.resenhasociocultural.apiresenha.features.attendance.builder;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreateDto;

public class AttendanceCreateDtoBuilder {
    private Long meetingId = 1L;
    private Long youthId = 1L;
    private AttendanceStatus status = AttendanceStatus.PRESENT;
    private String absenceExcuse = null;

    public static AttendanceCreateDtoBuilder anAttendanceCreateDto(){
        return new AttendanceCreateDtoBuilder();
    }

    public AttendanceCreateDtoBuilder withMeetingId(Long id){
        this.meetingId = id;
        return this;
    }

    public AttendanceCreateDtoBuilder withYouthId(Long id){
        this.youthId = id;
        return this;
    }

    public AttendanceCreateDtoBuilder statusPresent(){
        this.status = AttendanceStatus.PRESENT;
        return this;
    }

    public AttendanceCreateDtoBuilder statusAbsent(){
        this.status = AttendanceStatus.ABSENT;
        return this;
    }

    public AttendanceCreateDtoBuilder statusExcusedAbsence(){
        this.status = AttendanceStatus.EXCUSED_ABSENCE;
        return this;
    }

    public AttendanceCreateDtoBuilder withAbsenceExcuse(){
        this.absenceExcuse = absenceExcuse;
        return this;
    }

    public AttendanceCreateDto build(){
        return new AttendanceCreateDto(
            meetingId,
            youthId,
            status,
            absenceExcuse
        );
    }
}
