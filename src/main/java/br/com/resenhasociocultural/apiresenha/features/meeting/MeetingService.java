package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.features.attendance.Attendance;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreate;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilter;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdate;
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

    public List<Meeting> findWithFilters(MeetingFilter filters){
        validator.validateFilters(filters);
        Specification<Meeting> specs = meetingSpecs.buildSpecificationsFromFilter(filters);
        return meetingRepository.findAll(specs);
    }

    @Transactional
    public Meeting create(MeetingCreate dto) {
        Meeting meeting = meetingMapper.toEntity(dto);

        validator.validateYouthEntries(meeting);
        addYouthProxies(meeting);

        return meetingRepository.save(meeting);
    }

    private void addYouthProxies(Meeting meeting){
        Set<Attendance> attendances = meeting.getAttendances();
        Set<Strike> strikes = meeting.getStrikes();
        Set<ParticipationPoint> participationPoints = meeting.getParticipationPoints();

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
    public Meeting update(Long id, MeetingUpdate dto){
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
