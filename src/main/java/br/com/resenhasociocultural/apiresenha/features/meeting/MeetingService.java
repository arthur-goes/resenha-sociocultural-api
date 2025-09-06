package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.exception.DateConflictArgumentException;
import br.com.resenhasociocultural.apiresenha.exception.InconsistentDateIntervalArgumentException;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceEntry;
import br.com.resenhasociocultural.apiresenha.features.attendance.AttendanceStatus;
import br.com.resenhasociocultural.apiresenha.features.attendance.dto.AttendanceForMeetingResponseDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import br.com.resenhasociocultural.apiresenha.features.youth.Youth;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthMapper;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthResponseDto;
import br.com.resenhasociocultural.apiresenha.features.youth.dto.YouthSimpleDto;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;
    private MeetingMapper meetingMapper;
    private YouthService youthService;
    private MeetingSpecs meetingSpecs;
    private YouthMapper youthMapper;

    public MeetingService(
        MeetingRepository meetingRepository,
        MeetingMapper meetingMapper,
        YouthService youthService,
        MeetingSpecs meetingSpecs,
        YouthMapper youthMapper
    )
    {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.youthService = youthService;
        this.meetingSpecs = meetingSpecs;
        this.youthMapper = youthMapper;
    }

    public List<Meeting> findWithFilters(MeetingFilterDto filters){
        validateFilters(filters);
        Specification<Meeting> specs = buildSpecificationsFromFilter(filters);
        return meetingRepository.findAll(specs);
    }

    private void validateFilters(MeetingFilterDto filters){
        boolean isDateIntervalGiven = filters.initialDate() != null && filters.finalDate() != null;
        boolean isSingleDateGiven = filters.date() != null;

        boolean isDateIntervalInconsistent = (filters.initialDate() == null || filters.finalDate() == null) && filters.initialDate() != filters.finalDate();
        boolean areDateParamsConflicting = isSingleDateGiven && isDateIntervalGiven;

        if (isDateIntervalInconsistent){
            throw new InconsistentDateIntervalArgumentException();
        }

        if (areDateParamsConflicting){
            throw new DateConflictArgumentException();
        }
    }

    private Specification<Meeting> buildSpecificationsFromFilter(MeetingFilterDto filters){
        Specification<Meeting> specs = ((root, query, cb) -> cb.conjunction());

        boolean isDateBetweenFilterApplied = filters.initialDate() != null && filters.finalDate() != null;
        boolean isDateBetweenFilterInverted = isDateBetweenFilterApplied && (filters.initialDate().isAfter(filters.finalDate()));

        if (filters.date() != null){
            specs = specs.and(meetingSpecs.dateEqual(filters.date()));
        }

        if (filters.theme() != null){
            specs = specs.and(meetingSpecs.themeLike(filters.theme()));
        }

        if (!isDateBetweenFilterApplied){
            return specs;
        }

        if (isDateBetweenFilterInverted){
            return specs.and(meetingSpecs.dateBetween(filters.finalDate(), filters.initialDate()));
        }

        return specs.and(meetingSpecs.dateBetween(filters.initialDate(), filters.finalDate()));
    }

    @Transactional
    public void create(MeetingCreateDto dto) {
        Meeting meeting = meetingMapper.toEntity(dto, youthService);
        meetingRepository.save(meeting);
    }

    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Não foi possível localizar um encontro com id " + id));
    }

    public Meeting update(MeetingUpdateDto dto){
        System.out.println("Entrou no Service!");
        Meeting meeting = meetingMapper.toEntity(dto, youthService);
        return meetingRepository.save(meeting);
    }

    public void delete(Long id){
        meetingRepository.delete(findById(id));
    }

    public Meeting prepareNewMeetingData() {
        List<Youth> activeYouhts = youthService.findAllActiveYouths();

        Set<AttendanceEntry> attendanceEntries = activeYouhts.stream().map(youth -> {
            AttendanceEntry attendanceEntry = new AttendanceEntry();
            attendanceEntry.setYouth(youth);
            return attendanceEntry;
        }).collect(Collectors.toSet());

        Meeting meeting = new Meeting();
        meeting.setAttendanceEntries(attendanceEntries);

        return meeting;
    }
}
