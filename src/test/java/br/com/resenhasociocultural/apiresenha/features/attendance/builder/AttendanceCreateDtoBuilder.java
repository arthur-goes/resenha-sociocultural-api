package br.com.resenhasociocultural.apiresenha.features.attendance.builder;

import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceCreate;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSummary;

public class AttendanceCreateDtoBuilder {
    private Long meetingId = 1L;
    private YouthSummary youth;
    private AttendanceStatus status = AttendanceStatus.PRESENT;
    private String absenceExcuse = null;

    public static AttendanceCreateDtoBuilder anAttendanceCreateDto(){
        return new AttendanceCreateDtoBuilder();
    }

    public AttendanceCreateDtoBuilder withMeetingId(Long id){
        this.meetingId = id;
        return this;
    }

    public AttendanceCreateDtoBuilder withYouth(YouthSummary youth){
        this.youth = youth;
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

    public AttendanceCreate build(){
        return new AttendanceCreate(
            youth,
            status,
            absenceExcuse
        );
    }
}
