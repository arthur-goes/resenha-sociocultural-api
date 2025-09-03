package br.com.resenhasociocultural.apiresenha.features.meeting;

import br.com.resenhasociocultural.apiresenha.exception.DateConflictArgumentException;
import br.com.resenhasociocultural.apiresenha.exception.InconsistentDateIntervalArgumentException;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingCreateDto;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingFilterDto;
import br.com.resenhasociocultural.apiresenha.exception.ResourceNotFoundException;
import br.com.resenhasociocultural.apiresenha.features.meeting.dto.MeetingUpdateDto;
import br.com.resenhasociocultural.apiresenha.features.youth.YouthService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeetingService {

    private MeetingRepository meetingRepository;
    private MeetingMapper meetingMapper;
    private YouthService youthService;
    private MeetingSpecs meetingSpecs;

    public MeetingService(
        MeetingRepository meetingRepository,
        MeetingMapper meetingMapper,
        YouthService youthService,
        MeetingSpecs meetingSpecs
    )
    {
        this.meetingRepository = meetingRepository;
        this.meetingMapper = meetingMapper;
        this.youthService = youthService;
        this.meetingSpecs = meetingSpecs;
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
}
