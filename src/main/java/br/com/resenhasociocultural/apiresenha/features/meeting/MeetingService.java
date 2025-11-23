package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import br.com.resenhasociocultural.apiresenha.features.participationpoint.ParticipationPoint;
import br.com.resenhasociocultural.apiresenha.features.strike.Strike;
import br.com.resenhasociocultural.apiresenha.features.youth.*;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Consumer;

@AllArgsConstructor
@Service
public class MeetingService {

    private MeetingRepository meetingRepository;
    private MeetingMapper meetingMapper;
    private YouthService youthService;
    private MeetingSpecs meetingSpecs;
    private MeetingValidator validator;

    public List<Meeting> findWithFilters(MeetingFilterDto filters){
        validator.validateFilters(filters);
        Specification<Meeting> specs = meetingSpecs.buildSpecificationsFromFilter(filters);
        return meetingRepository.findAll(specs);
    }

    @Transactional
    public Meeting create(MeetingCreateDto dto) {
        Meeting meeting = meetingMapper.toEntity(dto);

        validator.validateYouthEntries(meeting);
        addYouthProxies(meeting);

        return meetingRepository.save(meeting);
    }

    private void addYouthProxies(Meeting meeting){
        Set<Attendance> attendances = meeting.getAttendanceEntries();
        Set<Strike> strikes = meeting.getStrikeEntries();
        Set<ParticipationPoint> participationPoints = meeting.getParticipationPointEntries();

        Consumer<Set<? extends YouthEntry>> replaceYouthWithProxies = (youthEntries) -> {
            for (YouthEntry youthEntry: youthEntries) {
                Youth youthProxy = youthService.getYouthReference(youthEntry.getYouth().getId());
                youthEntry.setYouth(youthProxy);
            }
        };

        replaceYouthWithProxies.accept(attendances);
        replaceYouthWithProxies.accept(strikes);
        replaceYouthWithProxies.accept(participationPoints);
    }


    public Meeting findById(Long id) {
        return meetingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Could not find a meeting with id " + id));
    }

    @Transactional
    public Meeting update(Long id, MeetingUpdateDto dto){
        Meeting meeting = meetingMapper.toEntity(dto);
        meeting.setId(id);
        validator.validateYouthEntries(meeting);
        addYouthProxies(meeting);
        return meetingRepository.save(meeting);
    }

    public void delete(Long id){
        meetingRepository.delete(findById(id));
    }

}
