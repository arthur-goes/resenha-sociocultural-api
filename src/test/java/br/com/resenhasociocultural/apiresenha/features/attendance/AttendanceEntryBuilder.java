package br.com.resenhasociocultural.apiresenha.features.attendance;

import br.com.resenhasociocultural.apiresenha.features.meeting.Meeting;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;

public class AttendanceEntryBuilder {
    private Long id = 1L;
    private Meeting meeting = null;
    private Youth youth = null;
    private AttendanceStatus attendanceStatus;
    private String absenceExcuse;

    public static AttendanceEntryBuilder anAttendanceEntry(){
        return new AttendanceEntryBuilder();
    }

    public AttendanceEntryBuilder withId(Long id){
        this.id = id;
        return this;
    }

    public AttendanceEntryBuilder withoutId(){
        this.id = null;
        return this;
    }

    public AttendanceEntryBuilder withStatus(AttendanceStatus status){
        this.attendanceStatus = status;
        return this;
    }

    public AttendanceEntryBuilder withAbsenceExcuse(String excuse){
        this.absenceExcuse = excuse;
        return this;
    }

    public AttendanceEntry build(){
        return new AttendanceEntry(
            id,
            meeting,
            youth,
            attendanceStatus,
            absenceExcuse
        );
    }
}
