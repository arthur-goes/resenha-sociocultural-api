package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceMapper;
import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static br.com.resenhasociocultural.apiresenha.features.meeting.MeetingSpecs.*;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;
    private MeetingMapper meetingMapper;
    private AttendanceMapper attendanceMapper;
    private YouthService youthService;

    public MeetingService(
        MeetingRepository meetingRepository,
        MeetingMapper meetingMapper,
        AttendanceMapper attendanceMapper,
        YouthService youthService
    )
    {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.attendanceMapper = attendanceMapper;
        this.youthService = youthService;
    }

    public List<Meeting> find(MeetingFilterDto filterDto){
        Specification<Meeting> specs = ((root, query, cb) -> cb.conjunction());

        LocalDate initialDate = filterDto.initialDate();
        LocalDate finalDate = filterDto.finalDate();
        LocalDate date = filterDto.date();
        String theme = filterDto.theme();

        if (initialDate != null){
            if (finalDate != null) {
                specs = specs.and(dateBetween(filterDto.initialDate(), filterDto.finalDate()));
            } else {
                specs = specs.and(dateFrom(filterDto.initialDate()));
            }
        }

        if (date != null){
            specs = specs.and(dateEqual(date));
        }

        if (theme != null){
            specs = specs.and(themeLike(theme));
        }

        return meetingRepository.findAll(specs);
    }

    @Transactional
    public void create(MeetingDto dto) {
        Meeting meeting = meetingMapper.toEntity(dto, youthService);
        meetingRepository.save(meeting);
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar um encontro com id " + id));
    }

    public Meeting update(MeetingDto dto){
        Meeting meeting = meetingMapper.toEntity(dto, youthService);
        return meetingRepository.save(meeting);
    }

    public void delete(Long id){
        meetingRepository.delete(findById(id));
    }
}
