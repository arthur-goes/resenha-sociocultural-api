package br.com.resenhasociocultural.apiresenha.service;

import br.com.resenhasociocultural.apiresenha.dto.attendance.AttendanceCreateDto;
import br.com.resenhasociocultural.apiresenha.dto.meeting.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.mapper.AttendanceMapper;
import br.com.resenhasociocultural.apiresenha.mapper.MeetingMapper;
import br.com.resenhasociocultural.apiresenha.model.Attendance;
import br.com.resenhasociocultural.apiresenha.model.Meeting;
import br.com.resenhasociocultural.apiresenha.model.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.model.Strike;
import br.com.resenhasociocultural.apiresenha.repository.MeetingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;
    private MeetingMapper meetingMapper;
    private AttendanceMapper attendanceMapper;

    public MeetingService(
        MeetingRepository meetingRepository,
        MeetingMapper meetingMapper,
        AttendanceMapper attendancemapper
    )
    {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.attendanceMapper = attendancemapper;
    }

    public List<Meeting> findAll(){
        return meetingRepository.findAll();
    }

    @Transactional
    public void create(MeetingCreateDto dto) {
        Meeting meeting = meetingMapper.toEntity(dto);
        meetingRepository.save(meeting);
        //Set<Attendance> attendances = attendanceMapper.toEntitySet(dto.attendanceList());
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar um encontro com id " + id));
    }
}
