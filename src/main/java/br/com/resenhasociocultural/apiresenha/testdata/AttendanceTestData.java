package br.com.resenhasociocultural.apiresenha.testdata;

import br.com.resenhasociocultural.apiresenha.enums.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.model.Meeting;
import br.com.resenhasociocultural.apiresenha.repository.AttendanceRepository;
import br.com.resenhasociocultural.apiresenha.repository.MeetingRepository;
import br.com.resenhasociocultural.apiresenha.repository.YouthRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

@Component
public class AttendanceTestData {

    private AttendanceRepository attendanceRepository;
    private YouthRepository youthRepository;
    private MeetingRepository meetingRepository;

    public AttendanceTestData(AttendanceRepository attendanceRepository, YouthRepository youthRepository, MeetingRepository meetingRepository) {
        this.attendanceRepository = attendanceRepository;
        this.youthRepository = youthRepository;
        this.meetingRepository = meetingRepository;
    }

    public void createAttendanceTestData(){
        List<Attendance> attendances = new ArrayList<>();

        Meeting meeting1 = new Meeting(
            null,
            LocalDate.of(2025, 06, 11),
            "Tigrinho",
            "Lição 100",
            new HashSet<Attendance>(),
            null,
            null
        );

        meetingRepository.save(meeting1);

        attendances.add(
            new Attendance(
                null,
                meeting1,
                youthRepository.findById(1L).get(),
                AttendanceStatus.PRESENT,
                null
            )
        );

        attendanceRepository.saveAll(attendances);

    }
}
