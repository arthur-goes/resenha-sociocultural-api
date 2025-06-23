package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.mapper.MeetingMapper;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.model.Meeting;
import br.com.resenhasociocultural.apiresenha.model.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.model.Strike;
import br.com.resenhasociocultural.apiresenha.repository.MeetingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class MeetingService {
    private MeetingRepository meetingRepository;
    private AttendanceService attendanceService;
    private ParticipationPointService participationPointService;
    private StrikeService strikeService;
    private YouthService youthService;

    private MeetingMapper meetingMapper;

    public MeetingService(
        MeetingRepository meetingRepository,
        AttendanceService attendanceService,
        ParticipationPointService participationPointService,
        StrikeService strikeService,
        YouthService youthService,
        MeetingMapper meetingMapper
    )
    {
        this.meetingRepository = meetingRepository;
        this.attendanceService = attendanceService;
        this.participationPointService = participationPointService;
        this.strikeService = strikeService;
        this.youthService = youthService;
        this.meetingMapper = meetingMapper;
    }

    public List<Meeting> findAll(){
        return meetingRepository.findAll();
    }

    public void create(MeetingCreateDto dto) {
        Meeting meeting = meetingMapper.toEntity(dto);
        Set<Attendance> attendances = meeting.getAttendanceList();
        attendances.stream().map((attendance) -> {
            attendance.
        })
        meetingRepository.save(meeting);
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar um encontro com id " + id));
    }
}
